package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveServoActuatorOut extends Command {

    public MoveServoActuatorOut() {
        requires(Robot.servoActuatorSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.servoActuatorSystem.moveToPosition(0.95);
    	Robot.servoActuatorSystem.moveToPosition(0.6);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
