package org.usfirst.frc.team1635.robot.subsystems;

//WPILIB imports
import edu.wpi.first.wpilibj.command.Subsystem;


//Local Package Imports
import org.usfirst.frc.team1635.robot.RobotMap;
import org.omg.CORBA.ExceptionList;
import org.omg.IOP.ExceptionDetailMessage;
import org.usfirst.frc.team1635.robot.OI;
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.commands.ClimberWithController;

//------------------------------------------------------------
//CTRE Imports
import com.ctre.CANTalon;
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
public class RollerClimbSubsystem extends Subsystem {
	CANTalon rollerTalon;

	public RollerClimbSubsystem() {
		super();
		rollerTalon = new CANTalon(RobotMap.rollerClimbMotorCANPort);

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
		rollerTalon.set(finalInput);
	}
	public void stopRoller() {
		rollerTalon.set(0);
	}

	public double ObtainTalonLastSetValue() { 
		double getTalonValue = rollerTalon.get();
		return getTalonValue;
	}

	
	
}
