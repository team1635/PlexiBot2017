package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithVision extends Command {

    public DriveWithVision() {
    	requires(Robot.chassisSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSystem.driveWithVision();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassisSystem.inTarget();
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
