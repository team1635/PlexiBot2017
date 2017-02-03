package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

/**
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
    	setTimeout(6);
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
