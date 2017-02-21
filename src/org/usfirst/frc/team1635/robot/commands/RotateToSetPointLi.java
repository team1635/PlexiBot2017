package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *true- clockwise, false - counterclockwise
 */
public class RotateToSetPointLi extends Command {
	private double rotation;
	boolean dirrection;
	
	/**
	 *true- clockwise, false - counterclockwise
	 */
    public RotateToSetPointLi(double rot, boolean direction) {
    	this.rotation=rot;
    	this.dirrection = direction;
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassisSystem);
    	requires(Robot.elevatorSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassisSystem.resetYaw();
    	Robot.pneumaticsSystem.setLowGear();
    	Robot.chassisSystem.setRotation(rotation, dirrection);  	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSystem.AngularRotation();
    	Robot.chassisSystem.log();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassisSystem.getGoalFlag();
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
