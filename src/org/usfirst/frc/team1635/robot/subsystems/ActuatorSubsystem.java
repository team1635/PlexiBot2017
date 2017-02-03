package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.MoveActuatorWithGamepad;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActuatorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon actuatorMotor;
	private DigitalInput topLimitSwitch;

	public ActuatorSubsystem() {
		super();
		actuatorMotor = new CANTalon(RobotMap.actuatorMotorCANPort);
		topLimitSwitch = new DigitalInput(RobotMap.topLimitSwitchDioPort);
	}
	
	public void actuate(double speed) {
		actuatorMotor.set(speed);
	}
	
	public void stop() {
		actuatorMotor.set(0);
	}
	
    public void initDefaultCommand() {
        //setDefaultCommand(new MoveActuatorWithGamepad());
    }
    
    public boolean getTopSwitch() {
    	return topLimitSwitch.get();
    }
}