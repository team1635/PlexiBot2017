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
	Talon  rollerTalonSR;
	Talon rollerTalonSRx2; 

	public RollerClimbSubsystem() {
		super();
		rollerTalonSR = new Talon (RobotMap.rollerClimbMotorPwmPort);
		rollerTalonSRx2 = new Talon(RobotMap.rollerClimbMotor2PwmPort); 

	}

	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ClimberWithController());
	}
	
	public void rollerWithController() {
		boolean isStartPressed = Robot.oi.StartController().getStartButton();
		boolean isBackPressed = Robot.oi.StartController().getBackButton();
		double output = 0.0;

		if (isStartPressed && isBackPressed == true) {
			output = 0.0;
			operateRollerParams(output);
			// Climb should not activate when the Startbutton
			// And backButtons are pressed
		}

		if (isBackPressed == true) {
			output = -1; 
			operateRollerParams(output);
		// Retracts the climber
		}
		else{ 
			output = 0.0; 
			operateRollerParams(output);
		}
		if (isStartPressed == true){ 
			output = 1; 
			operateRollerParams(output);
		// Activates the climber	
		}

	}

	public void operateRollerParams(double finalInput) {
		rollerTalonSR.set(finalInput);
		rollerTalonSRx2.set(finalInput);
	}
	public void stopRoller() {
		rollerTalonSR.set(0);
		rollerTalonSRx2.set(0);
	}

	public double obtainTalonSRLastValue() { 
		double getTalonSRValue = rollerTalonSR.get();
		return getTalonSRValue;
		
	}
	
	public double obtainTalonSRx2LastValue(){ 
		double getTalonSRx2Value = rollerTalonSRx2.get(); 
		return getTalonSRx2Value; 
	}

	
	
}
