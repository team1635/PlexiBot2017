package org.usfirst.frc.team1635.robot.subsystems;

//WPILIB imports
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;

//Local Package Imports
import org.usfirst.frc.team1635.robot.RobotMap;
import org.omg.CORBA.ExceptionList;
import org.omg.IOP.ExceptionDetailMessage;
import org.usfirst.frc.team1635.robot.OI;
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.commands.ClimberWithController;

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
public class RollerClimbSubsystem extends Subsystem {
	Talon rollerTalonSR;
	Talon rollerTalonSRx2;

	public RollerClimbSubsystem() {
		super();
		rollerTalonSR = new Talon(RobotMap.rollerClimbMotorPwmPort);
		rollerTalonSRx2 = new Talon(RobotMap.rollerClimbMotor2PwmPort);

	}

	// Whatever command you set as default will run when the enable button
	// is pressed in Driver Station
	// ------------------------------------------------------------
	public void initDefaultCommand() {

		setDefaultCommand(new ClimberWithController());

	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void rollerClimbWithController() {
		boolean isStartPressed = Robot.oi.StartController().getStartButton();

		if (isStartPressed == true) {
			rollerTalonSR.set(-1);
			rollerTalonSRx2.set(-1);
		} else {
			stopRoller();
		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void operateRollerParams(double finalInput) {
		System.out.println("RollerWithParamsActivated");
		rollerTalonSR.set(finalInput);
		// rollerTalonSRx2.set(finalInput);
	}

	public void stopRoller() {
		rollerTalonSR.set(0);
		rollerTalonSRx2.set(0);
	}

	public double obtainTalonSRLastValue() {
		double getTalonSRValue = rollerTalonSR.get();
		return getTalonSRValue;

	}

	public double obtainTalonSRx2LastValue() {
		double getTalonSRx2Value = rollerTalonSRx2.get();
		return getTalonSRx2Value;
	}

}
