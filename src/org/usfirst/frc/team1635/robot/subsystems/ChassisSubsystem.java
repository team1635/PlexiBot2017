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
	//SerialPort serial_port;
	//AHRS imu; 
	//Port k

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
		//byte update_rate_hz = 50;
		//serial_port =  new SerialPort(57600, SerialPort.Port.kMXP);
		//imu =  new AHRS(
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

	public void driveWithParams(double left, double right) {
		drive.tankDrive(left, right);

	}

	public void stop() {
		drive.tankDrive(0.0, 0.0);
	}

	public void turn60Degs(){ 
		
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
