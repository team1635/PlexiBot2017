package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.commands.RotateToSetPoint;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrection;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLift extends CommandGroup {

	public AutonomousLift() {

		requires(Robot.chassisSystem);

		addSequential(new TimeoutDriveWithCorrection(2));
		addSequential((new RotateToSetPoint(-62)));
		addSequential(new TimeoutDriveWithCorrection(1));
		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
