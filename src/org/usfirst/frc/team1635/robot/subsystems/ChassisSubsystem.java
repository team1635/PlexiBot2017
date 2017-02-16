package org.usfirst.frc.team1635.robot.subsystems;

// Local Package Imports
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.DriveRobotWithSpeedInput;
//------------------------------------------------------------

// CTRE Imports
import com.ctre.CANTalon;
//------------------------------------------------------------

// WPILIB Imports 
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
//------------------------------------------------------------

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

	boolean onTarget;

	public ChassisSubsystem() {
		super();
		frontLeftMotor = new CANTalon(RobotMap.frontLeftMotorCANPort);
		frontRightMotor = new CANTalon(RobotMap.frontRightMotorCANPort);
		backLeftMotor = new CANTalon(RobotMap.backLeftMotorCANPort);
		backRightMotor = new CANTalon(RobotMap.backRightMotorCANPort);

		frontLeftMotor.enableBrakeMode(false);
		frontRightMotor.enableBrakeMode(false);
		backLeftMotor.enableBrakeMode(false);
		backRightMotor.enableBrakeMode(false);

		// drive = new RobotDrive(frontLeftMotor, backLeftMotor,
		// frontRightMotor, backRightMotor);
		drive = new RobotDrive(backLeftMotor, frontLeftMotor, backRightMotor, frontRightMotor);
		drive.setSafetyEnabled(false); // TODO: Figure why we need this

	}

	// Whatever command you set as default will run when the enable button is
	// pressed in Driver Station
	// ------------------------------------------------------------
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveRobotWithSpeedInput());
	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void drive() {
		drive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightSpeed());

	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------

	public void driveWithParams(double left, double right) {
		drive.tankDrive(left, right);

	}

	public void shakeRobotForwardParams(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void shakeRobotBackwardsParams(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void stop() {
		drive.tankDrive(0.0, 0.0);
	}

	public void shakeRobot() {
		shakeRobotForwardParams(0.5, 0.5);
		Timer.delay(0.1);
		stop();
		Timer.delay(0.2);
		shakeRobotBackwardsParams(-0.5, -0.5);
		Timer.delay(0.1);
		stop();
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
