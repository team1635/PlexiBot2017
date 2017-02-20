package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * true- clockwise, false - counterclockwise
 */
public class RotateToSetPoint extends Command {
	private double rotation;
	boolean direction;

	/**
	 * true- clockwise, false - counterclockwise
	 */
	public RotateToSetPoint(double rot, boolean direction) {
		this.rotation = rot;
		this.direction = direction;
		requires(Robot.chassisSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassisSystem.setRotation(rotation, direction);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.chassisSystem.turn();
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
