package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoadBalls extends Command {

    public LoadBalls() {
        requires(Robot.elevatorSystem);
        requires(Robot.pneumaticsSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumaticsSystem.moveFlapsDown();
    	Timer.delay(.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.elevatorSystem.getBottomLimit()) {
    		Robot.elevatorSystem.elevatorStop();
    	} else {
    	    Robot.elevatorSystem.operateElevatorParams(-0.1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean hitBottom = Robot.elevatorSystem.getBottomLimit();
    	System.out.println("Debug: Checking for Bottom");
    	if (hitBottom) {
    		System.out.println("Debug:Hit Bottom");
    	}
        return hitBottom;
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