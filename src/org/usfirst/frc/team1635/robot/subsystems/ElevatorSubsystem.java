package org.usfirst.frc.team1635.robot.subsystems;

import java.math.*;
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.*;
import org.usfirst.frc.team1635.util.XboxControllerButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	DigitalInput limitSwitchTop, limitSwitchBottom;
	AnalogPotentiometer analogPot;
	boolean isElevatorDown; // True is Down, False is Up

	public ElevatorSubsystem() {
		super();
		elevatorActuator = new CANTalon(RobotMap.elevatorMotorCANPort);
		elevatorRoller = new CANTalon(RobotMap.elevatorRollerMotorCANPort);
		limitSwitchTop = new DigitalInput(RobotMap.topLimitSwitchDioPort);
		limitSwitchBottom = new DigitalInput(RobotMap.bottomLimitSwitchDioPort);
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
		// Elevator Up = True
		// Elevator Down = False

		if ((Robot.oi.StartController().getBumper(Hand.kLeft))) {
			operateElevatorParams(-0.7);
			// if(this.getPotentiometerValue() == 242){ //TODO:Change number
			// when we get actual Bottom Pot Value
			// elevatorStop();
			// isElevatorDown = true;
			// }
			// if(!this.getBottomLimit()){
			// elevatorStop();
			// isElevatorDown = true;
			// }

			// && (this.getBottomLimit()))
		} else if (Robot.oi.StartController().getBumper(Hand.kRight)) {
			operateElevatorParams(0.7);

			isElevatorDown = false;

		}

		else {
			elevatorStop();
		}

		Robot.pneumaticsSystem.moveFlapsForElevator();
	}

	public void elevatorRollerControl() {
		if (Robot.oi.StartController().getXButton()) {
			elevatorRoller.set(1);

		} else {
			elevatorRoller.set(0);
		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void log() {
		SmartDashboard.putNumber("Potentiometer Value", Robot.elevatorSystem.getPotentiometerValue());
		SmartDashboard.putBoolean("Bottom Stop", this.getBottomLimit());
		SmartDashboard.putBoolean("Top Stop", this.getTopLimit());

	}

	public void turnRollerOn(boolean TrueOrFalse) {
		if (TrueOrFalse) {
			elevatorRoller.set(1);
		} else if (!TrueOrFalse) {
			elevatorRoller.set(0);
		}
	}

	public boolean isElevatorAtSweetSpot() {
		if (Math.abs(getPotentiometerValue() - 388.0) <= 5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElevatorAtDangerSpot() {
		if (Math.abs(getPotentiometerValue() - 520.0) <= 5) {
			return true;
		} else {
			return false;
		}

	}

	public double getPotentiometerValue() {
		double potVal = analogPot.get();
		return potVal;
	}

	public boolean getTopLimit() {
		return limitSwitchTop.get();
	}

	public boolean getBottomLimit() {
		System.out.println(limitSwitchBottom.get());
		return (!(limitSwitchBottom.get()));
		
	}

	public void operateElevatorParams(double speed) {
		elevatorActuator.set(speed);

	}

	// Miguel's method for auto
	public void lowerElevatorAutomatically() {
		operateElevatorParams(-0.7);
		if (this.getPotentiometerValue() == 242) { // TODO:Change number when we
													// get actual Bottom Pot
													// Value
			elevatorStop();
			isElevatorDown = true;
		}
		if (this.getBottomLimit()) {
			elevatorStop();
			isElevatorDown = true;
		}
	}

	public void elevatorStop() {
		elevatorActuator.set(0);
	}

}
