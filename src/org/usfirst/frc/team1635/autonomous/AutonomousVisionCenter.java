package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.DriveWithVision;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrectionSlow;
import org.usfirst.frc.team1635.robot.commands.WiggleForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousVisionCenter extends CommandGroup {

	public AutonomousVisionCenter() {

		addSequential(new DriveWithVision());
		// next method takes double executionTime, double speed
		addSequential(new TimeoutDriveWithCorrectionSlow(
				RobotMap.autoVisionStraightTime
				, RobotMap.autoVisionStraightSpeed));
		addSequential(new WiggleForward());
	}
}
