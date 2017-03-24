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
	//2.3 in was short in practice match 3
	//2.4 spring ended up on the right side of the slot from the right auto. increasing
	public static double autoCenterDriveTime = 2.5; //Seconds
	
	public static double autoLeftDriveToTurn = autoCenterDriveTime;
	public static double autoLeftTurnRight = 55;//Degs 
	//2.41 was too long
	public static double autoLeftDriveToGearHolder = 2.3; // Seconds 
	
    // 2.8 is what we used in the lab
	public static double autoRightDriveToTurn = autoCenterDriveTime; //Seconds	
	public static double autoRightTurnLeft = autoLeftTurnRight; //Degs  
	public static double autoRightDriveToGearHolder = 2.5; //Seconds
	
	//1.0 didn't advance enough
	//1.5 sometimes stays hooked
	public static double wiggleTime = 2.0;
	//-.45 too slow on carpet .55 sloe .65 slow
	//-.8 is not advancing enough
	public static double autoWiggleBackSpeed = -0.65;
	//.6 too slow on carpet .7 slow .8 slow
	//.9 didn't advance enough
	public static double autoWiggleForwardSpeed = 0.98;
	//.13 too short
	//.2 t00 short
	//.3 t00 sh0rt
	//.2 doesn't advance enough
	public static double autoWiggleMoveTime = 0.25;
	
	public static double autoWiggleStopTime = 0.2;
	
	public static double autoErrorCorrection =  20.0;
	public static double autoErrorTolerance = 7.0;
	public static double autoRotateSpeed = 0.35;
	//.4 was slow
	public static double autoRotateSpeedLi = 0.6;
	public static double autoSpeed = 0.45;  

	public static double inTargetDistance = 47;
}
