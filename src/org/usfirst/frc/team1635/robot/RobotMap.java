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
	public static int elevatorMotorCANPort =3;  
	public static int elevatorRollerMotorCANPort =2;
				//Drive 
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
	public static int potentiometerAnalogPort = 0;

	// ------------------------------------------------------------
	// Digital Input/Output Ports ( DIO)
	public static int topLimitSwitchDioPort = 0;
	public static int bottomLimitSwitchDioPort = 0;

	// ------------------------------------------------------------
	// Pnumatics Control Module Ports (PCM)
	public static int compressorPort = 0;
	public static int gearSolenoidPort = 5; 
	public static int gearShifterPort = 4; 
	// ------------------------------------------------------------
	// DriverStation Ports(DSP)
	public static int driverControllerPort = 0;
	// ------------------------------------------------------------
	
}
