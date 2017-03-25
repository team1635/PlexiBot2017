package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.TurnToSetPointLi;
import org.usfirst.frc.team1635.robot.commands.WiggleForward;
import org.usfirst.frc.team1635.robot.commands.DriveWithVision;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrection;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousVisionLeft extends CommandGroup {

	public AutonomousVisionLeft() {
		addSequential(new TimeoutDriveWithCorrection(RobotMap.autoLeftDriveToTurn));
		addSequential(new TurnToSetPointLi(RobotMap.autoLeftTurnRight, true));
		addSequential(new DriveWithVision());
		addSequential(new WiggleForward());
	}
}
