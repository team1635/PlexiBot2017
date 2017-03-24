package org.usfirst.frc.team1635.robot.commands;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeoutDriveWithCorrectionSlow extends Command { 
	private double executionTime; 
	private double speed; 
    public TimeoutDriveWithCorrectionSlow(double executionTime, double speed) {
        this.executionTime = executionTime;
        this.speed = speed; 
        setTimeout(executionTime);
        requires(Robot.chassisSystem);
    }


    protected void initialize() {
    	Robot.chassisSystem.resetDisplacement();
    	Robot.chassisSystem.resetYaw();
    	//Timer.delay(0.1);
    	//Robot.pneumaticsSystem.setLowGear();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSystem.log();
    	Robot.chassisSystem.driveStraightBob(speed);
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
        
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
