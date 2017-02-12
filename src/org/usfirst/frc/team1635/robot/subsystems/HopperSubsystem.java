package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.*;
import org.usfirst.frc.team1635.util.XboxControllerButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

//------------------------------------------------------------

//.---.  ,--.    .-----. .------.  
///_   | /  .'   /  -.   \|   ___|  
//|   |.  / -.  '-' _'  ||  '--.   
//|   || .-.  '    |_  < `---.  '. 
//|   |' \  |  |.-.  |  |.-   |  | 
//|   |\  `'  / \ `-'   /| `-'   / 
//`---' `----'   `----''  `----''  

/**
 * 
 * @author Bogdan Bradu & Miguel Cruz ( @Acelogic_)
 *
 */
public class HopperSubsystem extends Subsystem {

	CANTalon hopperActuator;
	DigitalInput limitSwitchOne, limitSwitchTwo;

	public HopperSubsystem() {
		super();
		hopperActuator = new CANTalon(RobotMap.actuatorMotorCANPort);
		limitSwitchOne = new DigitalInput(RobotMap.topLimitSwitchDioPort);
		limitSwitchTwo = new DigitalInput(RobotMap.bottomLimitSwitchDioPort);
	}

	public void controllerExtendHopper(XboxController FirstButton, XboxController SecondButton) {
	boolean xbutton = FirstButton.getXButton();
	boolean bButton = SecondButton.getBButton();

		if (xbutton == true) {
			hopperActuator.set(-0.4);
		} else if (bButton == true) {
			hopperActuator.set(0.5);
		} else {
			hopperStop();
		}
	}

	public void operateHopperParams(double speed) {
		hopperActuator.set(speed);

	}
	
	public void hopperUpParams(double speedValue){ 
		hopperActuator.set(speedValue);
	}
	
	public void hopperDownParams(double speedValue){ 
		hopperActuator.set(speedValue);
	}

	public void hopperStop() {
		hopperActuator.set(0);
	}
	

	public boolean isSwitchClosed(DigitalInput limitSwitch) {
		return !(limitSwitch.get());
	}
	
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new HopperWithController());

	}
}
