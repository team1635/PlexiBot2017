package org.usfirst.frc.team1635.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static int actuatorMotorCANPort=1;
	//public static int actuatorMotorCANPort=2;
	//public static int actuatorMotorCANPort=3;
	//public static int actuatorMotorCANPort=4;
	//public static int actuatorMotorCANPort=5;
	//public static int actuatorMotorCANPort=6;
	
	public static int forwardLeftMotorCANPort = 5;
	public static int forwardRightMotorCANPort = 2; 
	public static int backLeftMotorCANPort = 6;
	public static int backRightMotorCANPort = 3;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static int driverControllerPort = 0;
	
	public static int sonarPort = 0;
	
	public static int topLimitSwitchDioPort = 0;
	
	public static int servoActuatorPwmPort = 0;
}
