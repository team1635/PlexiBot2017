package org.usfirst.frc.team1635.robot.subsystems;

// Local Package Imports
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.ControlDrive;
//------------------------------------------------------------

// CTRE Imports
import com.ctre.CANTalon;
//------------------------------------------------------------
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
//------------------------------------------------------------
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//.---.  ,--.    .-----. .------.  
///_   | /  .'   /  -.   \|   ___|  
//|   |.  / -.  '-' _'  ||  '--.   
//|   || .-.  '    |_  < `---.  '. 
//|   |' \  |  |.-.  |  |.-   |  | 
//|   |\  `'  / \ `-'   /| `-'   / 
//`---' `----'   `----''  `----''  

/**
 * 
 * @author Bogdan Bradu & Miguel Cruz ( @Acelogic_)
 *
 */
public class ChassisSubsystem extends Subsystem {
	private CANTalon frontLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon backLeftMotor;
	private CANTalon backRightMotor;
	private RobotDrive drive;
	AHRS navX;

	public ChassisSubsystem() {

		frontLeftMotor = new CANTalon(RobotMap.frontLeftMotorCANPort);
		frontRightMotor = new CANTalon(RobotMap.frontRightMotorCANPort);
		backLeftMotor = new CANTalon(RobotMap.backLeftMotorCANPort);
		backRightMotor = new CANTalon(RobotMap.backRightMotorCANPort);

		frontLeftMotor.enableBrakeMode(true);
		frontRightMotor.enableBrakeMode(true);
		backLeftMotor.enableBrakeMode(true);
		backRightMotor.enableBrakeMode(true);

		drive = new RobotDrive(backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor);

		try {
			byte update_rate_hz = 50;
			// navX = new IMU(serial_port,update_rate_hz);
			// navX = new IMUAdvanced(serial_port,update_rate_hz);
			navX = new AHRS(Port.kMXP, update_rate_hz);
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		if (navX != null) {
			LiveWindow.addSensor("IMU", "Gyro", navX);
		}

		firstIteration = true;
		boolean is_calibrating = navX.isCalibrating();
		if (firstIteration && !is_calibrating) {
			Timer.delay(0.3);
			navX.zeroYaw();
			firstIteration = false;
		}
	}

	// Whatever command you set as default will run when the enable button is
	// pressed in Driver Station
	// ------------------------------------------------------------
	protected void initDefaultCommand() {
		setDefaultCommand(new ControlDrive());
	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void drive() {
		drive.tankDrive(getLeftSpeed(), getRightSpeed());

	}

	public double getLeftSpeed() {
		return Robot.oi.StartController().getY(GenericHID.Hand.kLeft);
	}

	public double getRightSpeed() {
		return Robot.oi.StartController().getY(GenericHID.Hand.kRight);
	}

	// Functions Dedicated for Autonomous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void log() {
		SmartDashboard.putNumber("NavXPitch", getPitchValue());
		SmartDashboard.putNumber("NavXyaw", getYawValue());
		SmartDashboard.putNumber("NavXRoll", getRollValue());
		//SmartDashboard.putNumber("NavXDisplacement X", getXDisplacementValue());
		//SmartDashboard.putNumber("NavXDisplacement Y", getYDisplacementValue());
		//SmartDashboard.putNumber("NavXDisplacement X No Inches", getXDisplacementNoInches());
		//SmartDashboard.putNumber("NavxDisplacement Y No Inches", getYDisplacementValueNoInches());
	}

	public void driveWithParams(double left, double right) {
		drive.tankDrive(left, right);

	}

	public void stop() {
		drive.tankDrive(0.0, 0.0);
	}

	// Functions For Nav X
	// ------------------------------------------------------------
	boolean firstIteration, direction, isGoalReached;
	double degrees, DistanceToStop;

	public float getPitchValue() {
		return navX.getPitch();
	}

	public float getYawValue() {
		return navX.getYaw();
	}

	public float getRollValue() {
		return navX.getRoll();
	}

	public double getXDisplacementValue() {
		double inches = navX.getDisplacementX() * 1.116;
		return inches;
	}

	public float getXDisplacementNoInches() {
		float displacement = navX.getDisplacementX();
		return displacement;
	}

	public float getYDisplacementValueNoInches() {
		float displacement = navX.getDisplacementY();
		return displacement;
	}

	public double getYDisplacementValue() {
		double inches = navX.getDisplacementY() * 1.116;
		return inches;
	}

	public void resetYaw() {
		navX.reset();
	}

	public void resetDisplacement() {
		navX.resetDisplacement();
	}
	
	public double getVisionDistance() {
		double distance = SmartDashboard.getNumber("VisionDistance", 0.0); //mispelled
		return distance;
	}
	
	public double getVisionError() {
		double error = SmartDashboard.getNumber("VisionError", 0.0);
		return error;
	}

	public void setRotation(double deg, boolean dir) {
		resetYaw();
		this.degrees = deg;
		this.direction = dir;
	}

	public void AngularRotation() {
		isGoalReached = false;
		if (direction) {// turn to the right
			if (getYawValue() < degrees + 1.0 && getYawValue() > degrees - 1.0) {
				drive.tankDrive(0, 0);
				isGoalReached = true;
			} else {
				drive.tankDrive(0.4, -0.4);
			}
		} else if (!direction) {// turn to the left
			double inverted = -degrees;
			if (getYawValue() < inverted + 1.0 && getYawValue() > inverted - 1.0) {
				drive.tankDrive(0, 0);
				isGoalReached = true;
			} else {
				drive.tankDrive(-0.4, 0.4);
			}
		}
	}

	public void setDistToStop(double dist_) {
		this.DistanceToStop = dist_;
	}

	public void resetIsGoalReachFlag() {
		isGoalReached = false;
	}

	public boolean getGoalFlag() {
		return isGoalReached;
	}

	public void driveStraightBob(double speed) {

		double speedCorrection = .01 * getYawValue();
		drive.tankDrive(speed - speedCorrection, speed + speedCorrection);
	}
	
	public void wiggleForward() {
		
		drive.tankDrive(RobotMap.autoWiggleBackSpeed, RobotMap.autoWiggleForwardSpeed); //5 was slow
		Timer.delay(RobotMap.autoWiggleMoveTime); //1 was too little //2 was way much
		stop();
		Timer.delay(RobotMap.autoWiggleStopTime);
		drive.tankDrive(RobotMap.autoWiggleForwardSpeed, RobotMap.autoWiggleBackSpeed);
		Timer.delay(RobotMap.autoWiggleMoveTime);
		stop();
	}
	
	public void driveWithVision() {
		//When perfectly in target the bottom left corner of the left strip
		//is about -50 (i.e. to the right) of the center, so we are adjusting
		//to make sure we are aiming for 0;
		double error = getVisionError();
		System.out.println("Error we received from the pi = " + error);
		error = error + RobotMap.autoErrorCorrection;
		System.out.println("Error after we corrected it = " + error);
		double direction = 0;
		
		//We don't want to compensate direction for very small errors.
		if (Math.abs(error) > RobotMap.autoErrorTolerance) {
			//direction = error * -1.0 / Math.abs(error); //kept going the wrong way
			direction = error / Math.abs(error);
		} else {
			direction = 0; //keep going straight
		}
		
		System.out.println("after correction and tolerance filter error = " + error);
		System.out.println("direction = " + direction);
		double rotateSpeed = RobotMap.autoRotateSpeed * direction;
		System.out.println("robot spining at rotateSpeed = " + rotateSpeed );
		drive.arcadeDrive(RobotMap.autoSpeed, rotateSpeed);
	}
	
	public boolean inTarget() {
		System.out.println("VisionDistance = " + getVisionDistance());
		return (getVisionDistance() > RobotMap.inTargetDistance);
	}



}
