package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.commands.DriveWithVision;
import org.usfirst.frc.team1635.robot.commands.WiggleForwardWithButton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousVisionCenter extends CommandGroup {

	public AutonomousVisionCenter() {

		addSequential(new DriveWithVision());
		addSequential(new WiggleForwardWithButton());
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