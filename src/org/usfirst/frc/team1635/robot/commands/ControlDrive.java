package org.usfirst.frc.team1635.robot.commands;

//Local Package Imports
import org.usfirst.frc.team1635.robot.Robot;
//------------------------------------------------------------
// WPILIB Imports 
import edu.wpi.first.wpilibj.command.Command;
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
public class ControlDrive extends Command {

    public ControlDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassisSystem);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSystem.drive();
    	Robot.oi.masterToSecondary(Robot.oi.StartController(), Robot.oi.StartController2());
    	Robot.chassisSystem.log();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassisSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
