package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeoutDriveWithCorrection extends Command { 
	private double executionTime; 

    public TimeoutDriveWithCorrection(double executionTime) {
        this.executionTime = executionTime;
        setTimeout(executionTime);
        requires(Robot.chassisSystem);
    }


    protected void initialize() {
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    Robot.chassisSystem.correctWhileDrivingWOPitch(0.5);
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("is timed out" + this.timeSinceInitialized());
    	return isTimedOut();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    Robot.chassisSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    end();
    }
}
