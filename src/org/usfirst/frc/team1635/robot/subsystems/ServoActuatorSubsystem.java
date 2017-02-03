package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.MoveServoActuatorIn;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ServoActuatorSubsystem extends Subsystem {

	private Servo actuatorServo;
	private double target;
	
	public ServoActuatorSubsystem() {
		super();
		actuatorServo = new Servo(RobotMap.servoActuatorPwmPort);
	}
	
    public void moveToPosition(double target){ 
    	this.target = target;
    	actuatorServo.set(target);
    }

    public void initDefaultCommand() {
    	//TODO: Figure out why this crashes the robot
        //setDefaultCommand(new MoveServoActuatorIn());
    }
    
    public boolean isOnTarget() {
    	return (Math.abs(actuatorServo.get() - target) < .01);
    }
}

