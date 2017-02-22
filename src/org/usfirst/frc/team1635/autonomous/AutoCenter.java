package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.PopGear;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrectionSlow;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		
		addSequential(new TimeoutDriveWithCorrectionSlow(5.7, RobotMap.timeOutDriveCorrectionSlow));
		
		
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
