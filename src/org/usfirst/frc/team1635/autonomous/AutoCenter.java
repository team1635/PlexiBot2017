package org.usfirst.frc.team1635.autonomous;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.PopGear;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrectionSlow;
import org.usfirst.frc.team1635.robot.commands.WiggleForward;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		//had 5.7 too faR
		//4.0 TOO FAR
		addSequential(new TimeoutDriveWithCorrectionSlow(RobotMap.autoCenterDriveTime, RobotMap.timeOutDriveCorrectionSlow));
		addSequential(new WiggleForward());
	}
}
