package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.*;
import org.usfirst.frc.team1635.util.XboxControllerButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
public class ElevatorSubsystem extends Subsystem {

	CANTalon elevatorActuator, elevatorRoller;
	DigitalInput limitSwitchTop, limitSwitchTwo;
	AnalogPotentiometer analogPot;

	public ElevatorSubsystem() {
		super();
		elevatorActuator = new CANTalon(RobotMap.elevatorMotorCANPort);
		elevatorRoller = new CANTalon(RobotMap.elevatorRollerMotorCANPort);
		limitSwitchTop = new DigitalInput(RobotMap.topLimitSwitchDioPort);
		limitSwitchTwo = new DigitalInput(RobotMap.bottomLimitSwitchDioPort);
		analogPot = new AnalogPotentiometer(RobotMap.potentiometerAnalogPort, 3600.0 / 5);

	}

	// Whatever command you set as default will run when the enable button is
	// pressed in Driver Station
	// ------------------------------------------------------------
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ControlElevator());

	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void controlElevator() {
		boolean leftBumper = Robot.oi.StartController().getBumper(Hand.kLeft);
		boolean rightBumper = Robot.oi.StartController().getBumper(Hand.kRight);

		if (leftBumper == true) {
			elevatorActuator.set(-0.4);
		} else if (rightBumper == true) {
			elevatorActuator.set(0.5);
		} else {
			elevatorStop();
		}
	}

	public void elevatorRollerControl() {
		boolean RT = Robot.oi.StartController().getXButton();
		if (RT == true) {
			elevatorRoller.set(1);
		} else {
			elevatorRoller.set(0);
		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public double getPotentiometerValue() {
		double potVal = analogPot.get();
		return potVal;
	}

	public void operateElevatorParams(double speed) {
		elevatorActuator.set(speed);

	}

	public void elevatorUpParams(double speedValue) {
		elevatorActuator.set(speedValue);
	}

	public void elevatorDownParams(double speedValue) {
		elevatorActuator.set(speedValue);
	}

	public void elevatorStop() {
		elevatorActuator.set(0);
	}

}
