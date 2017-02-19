package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeadToUs extends Command {

    public DeadToUs() {
        requires(Robot.elevatorSystem);
        requires(Robot.pneumaticsSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.pneumaticsSystem.moveFlapsUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSystem.operateElevatorParams(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevatorSystem.getTopLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSystem.elevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
