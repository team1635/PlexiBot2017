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
public class ChassisSubsystem extends PIDSubsystem {
	private CANTalon frontLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon backLeftMotor;
	private CANTalon backRightMotor;
	private RobotDrive drive;
	AHRS navX;

	double turnSpeed = .45;
	private static final double kP = 0;
	private static final double kI = 0;
	private static final double kD = 0;
	double kToleranceDegrees = 2.0f;
	
	public ChassisSubsystem() {
		super(kP, kI, kD);
		setInputRange(-180.0f, 180.0f);
		setOutputRange(-1.0, 1.0);
		setAbsoluteTolerance(kToleranceDegrees);
		PIDController myController = this.getPIDController();
		myController.setContinuous(true);
		LiveWindow.addActuator("ChassisSubsystem", "turnControllerPID", myController);

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
	}

	public boolean isDoneTurning(){ 
		return true; //turnControllerPID.onTarget(); 
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

	public void resetYaw() {
		navX.reset();
	}

	public double convertNavXtoInches() {
		double inches = navX.getDisplacementX() * 1.116;
		return inches;
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

	public void driveStraight(double speed) {
		double speedCorrection = .01 * getYawValue();
		drive.tankDrive(speed - speedCorrection, speed + speedCorrection);
	}

	@Override
	protected double returnPIDInput() {
		return navX.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		drive.arcadeDrive(0.0, turnSpeed * output);		
	}

}
