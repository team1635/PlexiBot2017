// Local Package Imports
package org.usfirst.frc.team1635.robot;
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
public class RobotMap {
	// ------------------------------------------------------------
	// CAN IDs/Ports
	public static int elevatorMotorCANPort = 3;
	public static int elevatorRollerMotorCANPort = 2;
	// Drive
	public static int frontLeftMotorCANPort = 4;
	public static int frontRightMotorCANPort = 6;
	public static int backLeftMotorCANPort = 1;
	public static int backRightMotorCANPort = 5;

	// ------------------------------------------------------------
	// Pulse Width Modulation Ports (PWM)
	public static int rollerClimbMotorPwmPort = 1;
	public static int rollerClimbMotor2PwmPort = 2;
	// ------------------------------------------------------------
	// Analog Ports (Analog)
	public static int sonarPort = 0;
	public static int potentiometerAnalogPort = 2;

	// ------------------------------------------------------------
	// Digital Input/Output Ports ( DIO)
	public static int topLimitSwitchDioPort = 0;
	public static int bottomLimitSwitchDioPort = 1;

	// ------------------------------------------------------------
	// Pnumatics Control Module Ports (PCM)
	public static int compressorPort = 0;
	public static int gearSolenoidPort = 6;
	public static int gearShifterPort = 4;
	public static int flapsPort = 7;

	// ------------------------------------------------------------
	// DriverStation Ports(DSP)
	public static int driverControllerPort = 0;
	// ------------------------------------------------------------
	// Command Vars
	public static double timeOutDriveWithCorrectionSpd = 0.7;
	public static double timeOutDriveCorrectionSlow = .52; 
	
	//2.8 went long
	//2.0 was a little short, just a little
	//2.15 was a little short
	//2.2 was short
	public static double autoCenterDriveTime = 2.3; //Seconds 
	
//	public static double autoLeftDriveToTurn = 2.35; //Seconds 
	public static double autoLeftDriveToTurn = autoCenterDriveTime;
	public static double autoLeftTurnRight = 56;//Degs 
	//2.41 was too long
	public static double autoLeftDriveToGearHolder = 2.3; // Seconds 
	
    // 2.8 is what we used in the lab
	public static double autoRightDriveToTurn = autoCenterDriveTime; //Seconds	
	public static double autoRightTurnLeft = 55; //Degs  
	public static double autoRightDriveToGearHolder = 2.5; //Seconds
		
	public static double autoWiggleBackSpeed = -0.45;
	public static double autoWiggleForwardSpeed = 0.6;
	public static double autoWiggleMoveTime = 0.13;
	public static double autoWiggleStopTime = 0.2;
	
	public static double autoErrorCorrection =  20.0;
	public static double autoErrorTolerance = 7.0;
	public static double autoRotateSpeed = 0.35;
	public static double autoRotateSpeedLi = 0.4;
	public static double autoSpeed = 0.45;  

	public static double inTargetDistance = 47;
}
