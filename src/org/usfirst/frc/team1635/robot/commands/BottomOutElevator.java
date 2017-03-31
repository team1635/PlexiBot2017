package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BottomOutElevator extends Command {
	boolean isBottomedOut;

	public BottomOutElevator() {
		requires(Robot.elevatorSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		isBottomedOut = Robot.elevatorSystem.IsElevatorBottomedOut();
		Robot.elevatorSystem.setElevatorSpeed(-0.5);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isBottomedOut;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevatorSystem.stopElevator();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
