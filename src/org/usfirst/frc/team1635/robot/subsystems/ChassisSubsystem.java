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
import com.kauailabs.navx.frc.AHRS.SerialDataType;

// WPILIB Imports 
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
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
	// SerialPort serial_port;
	AHRS navX;

	public ChassisSubsystem() {
		super();
		frontLeftMotor = new CANTalon(RobotMap.frontLeftMotorCANPort);
		frontRightMotor = new CANTalon(RobotMap.frontRightMotorCANPort);
		backLeftMotor = new CANTalon(RobotMap.backLeftMotorCANPort);
		backRightMotor = new CANTalon(RobotMap.backRightMotorCANPort);

		frontLeftMotor.enableBrakeMode(true);
		frontRightMotor.enableBrakeMode(true);
		backLeftMotor.enableBrakeMode(true);
		backRightMotor.enableBrakeMode(true);

		drive = new RobotDrive(backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor);
		drive.setSafetyEnabled(false); // TODO: Figure why we need this

		try {
			SerialPort serial_port = new SerialPort(57600, SerialPort.Port.kMXP);

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

	public void driveWithParams(double left, double right) {
		drive.tankDrive(left, right);

	}

	public void stop() {
		drive.tankDrive(0.0, 0.0);
	}

	// Functions For Nav X
	// ------------------------------------------------------------
	boolean firstIteration, direction, onTarget;
	double degrees, DistanceToStop;

	public void setRotation(double deg, boolean dir) {
		navX.zeroYaw();
		this.degrees = deg;
		this.direction = dir;
	}

	public float getPitchValue() {
		return navX.getPitch();
	}

	public float getYawValue() {
		return navX.getYaw();
	}

	public float getRollValue() {
		return navX.getRoll();
	}
	
	public void reset() {
		navX.zeroYaw();
	}

	public void turn() {
		onTarget = false;
		if (direction) {// turn to the right
			if (getYawValue() < degrees + 1.0 && getYawValue() > degrees - 1.0) {
				drive.tankDrive(0, 0);
				onTarget = true;
			} else {
				drive.tankDrive(0.45, -0.45);
			}
		} else if (!direction) {// turn to the left
			double inverted = -degrees;
			if (getYawValue() < inverted + 1.0 && getYawValue() > inverted - 1.0) {
				drive.tankDrive(0, 0);
				onTarget = true;
			} else {
				drive.tankDrive(-0.45, 0.45);
			}
		}
	}

	public void correctWhileDriving() {
		log();

		if (getPitchValue() < 4 && getPitchValue() > -4) {
			if (getYawValue() > 0) {
				if (getYawValue() < 1.5 && getYawValue() > 0) {
					drive.tankDrive(0.85, 0.85);
				} else if (getYawValue() > 1.5 && getYawValue() < 4) {
					drive.tankDrive(-0.35, 0.35);
				} else if (getYawValue() > 4) {
					drive.tankDrive(-0.45, 0.45);
				}
			} else if (getYawValue() < 0) {
				if (getYawValue() > -1.5 && getYawValue() < 0) {
					drive.tankDrive(0.85, 0.85);
				} else if (getYawValue() < -1.5 && getYawValue() > -4) {
					drive.tankDrive(0.35, -0.35);
				} else if (getYawValue() < -4) {
					drive.tankDrive(0.45, -0.45);
				}
			}
		} else {
			drive.tankDrive(0.75, 0.75);
		}
	}
	
	public void correctWhileDrivingWOPitch(double driveSpeed) {
		log();

		if (getYawValue() > 0) {
			if (getYawValue() < 1.5 && getYawValue() > 0) {
				drive.tankDrive(driveSpeed, driveSpeed);
			} else if (getYawValue() > 1.5 && getYawValue() < 4) {
				drive.tankDrive(-0.35, 0.35);
			} else if (getYawValue() > 4) {
				drive.tankDrive(-0.45, 0.45);
			}
		} else if (getYawValue() < 0) {
			if (getYawValue() > -1.5 && getYawValue() < 0) {
				drive.tankDrive(driveSpeed, driveSpeed);
			} else if (getYawValue() < -1.5 && getYawValue() > -4) {
				drive.tankDrive(0.35, -0.35);
			} else if (getYawValue() < -4) {
				drive.tankDrive(0.45, -0.45);
			}
		}

	}
	// Functions used to manage commands
	// ------------------------------------------------------------
	public void resetOnTarget() {
		onTarget = false;
	}

	public boolean isOnTarget() {
		return onTarget;
	}

}
