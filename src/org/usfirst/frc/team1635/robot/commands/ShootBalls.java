package org.usfirst.frc.team1635.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1635.robot.Robot;
/**
 *
 */
public class ShootBalls extends Command {

	private int state = 1;
	// 1 = We are below the danger point
	// 2 = We are at the danger point (lower flaps)
	// 3 = We are between the danger point and the sweet spot
	// 4 = we are at the sweet spot.  stop this command and get out of here.
	
    public ShootBalls() {
    	requires(Robot.elevatorSystem);
    	requires(Robot.pneumaticsSystem );
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.elevatorSystem.getPotentiometerValue() > 520) {
    		state = 1;
    	} else {
    		state = 4;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.elevatorSystem.setRollerState(true);
    	switch (state) {
    	case 1:  
    		if (Robot.elevatorSystem.isElevatorAtDangerSpot()) {
    			Robot.elevatorSystem.stopElevator();
    			state = 2;
    		} else {
    			Robot.elevatorSystem.setElevatorSpeed(.5);
    		}
    		break;
    	case 2:
    		Robot.elevatorSystem.moveFlapsDown();
    		state = 3;
    		break;
    	case 3:
    		if (Robot.elevatorSystem.isElevatorAtSweetSpot()) {
    			Robot.elevatorSystem.stopElevator();
    			state = 4;
    		} else {
    			Robot.elevatorSystem.setElevatorSpeed(.5);
    		}
    		break;
    	default:
    		state = 4;
    	}
  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (state == 4);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSystem.stopElevator();
    	Robot.elevatorSystem.setFlapsDown(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
