package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.TurnToSetPointLi;
import org.usfirst.frc.team1635.robot.commands.WiggleForwardWithButton;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrection;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {

	public AutonomousLeft() {
		addSequential(new TimeoutDriveWithCorrection(RobotMap.autoLeftDriveToTurn));
		addSequential(new TurnToSetPointLi(RobotMap.autoLeftTurnRight, true));
		addSequential(new TimeoutDriveWithCorrection(RobotMap.autoLeftDriveToGearHolder));
		addSequential(new WiggleForwardWithButton());
	}
}
