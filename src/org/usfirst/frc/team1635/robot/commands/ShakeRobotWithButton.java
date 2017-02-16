package org.usfirst.frc.team1635.robot.commands;
//Local Package Imports
import org.usfirst.frc.team1635.robot.Robot;
//------------------------------------------------------------
//WPILIB Imports
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
public class ShakeRobotWithButton extends Command {

    public ShakeRobotWithButton() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassisSystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Shake Command Starting");
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSystem.shakeRobot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
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
