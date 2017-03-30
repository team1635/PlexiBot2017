package org.usfirst.frc.team1635.robot.subsystems;

//WPILIB imports
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Talon;

//Local Package Imports
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.control.ControlWinch;
import org.omg.CORBA.ExceptionList;
import org.omg.IOP.ExceptionDetailMessage;
import org.usfirst.frc.team1635.robot.OI;
import org.usfirst.frc.team1635.robot.Robot;

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
public class WinchClimbSubsystem extends Subsystem {
	Talon rollerTalonSR;
	Talon rollerTalonSRx2;

	public WinchClimbSubsystem() {
		super();
		rollerTalonSR = new Talon(RobotMap.rollerClimbMotorPwmPort);
		rollerTalonSRx2 = new Talon(RobotMap.rollerClimbMotor2PwmPort);

	}

	// Whatever command you set as default will run when the enable button
	// is pressed in Driver Station
	// ------------------------------------------------------------
	public void initDefaultCommand() {

		setDefaultCommand(new ControlWinch());

	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void operateWinch() {

		if (Robot.oi.globalBackButton) {
			rollerTalonSR.set(-1);
			rollerTalonSRx2.set(-1);
		} else if (!Robot.oi.globalBackButton) {
			stopWinch();
		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// -----------------------------------------------------------

	public void stopWinch() {
		rollerTalonSR.set(0);
		rollerTalonSRx2.set(0);
	}

}
