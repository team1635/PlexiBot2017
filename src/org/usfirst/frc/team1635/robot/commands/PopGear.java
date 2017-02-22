package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.subsystems.PneumaticsSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PopGear extends Command {


	public PopGear() {
	
		
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.pneumaticsSystem);
		;
	}

	// Called just before this Command runs the first time
	
	protected void initialize() {
		Robot.pneumaticsSystem.popGear();

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
		end();
	}
}
