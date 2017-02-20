package org.usfirst.frc.team1635.robot.subsystems;

import java.math.*;
import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.*;
import org.usfirst.frc.team1635.util.XboxControllerButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
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
	Solenoid flapsSolenoid;
	private boolean flapsDown;

	public ElevatorSubsystem() {
		super();
		elevatorActuator = new CANTalon(RobotMap.elevatorMotorCANPort);
		elevatorRoller = new CANTalon(RobotMap.elevatorRollerMotorCANPort);
		limitSwitchTop = new DigitalInput(RobotMap.topLimitSwitchDioPort);
		limitSwitchBottom = new DigitalInput(RobotMap.bottomLimitSwitchDioPort);
		analogPot = new AnalogPotentiometer(RobotMap.potentiometerAnalogPort, 3600.0 / 5);
		flapsSolenoid = new Solenoid(RobotMap.flapsPort);

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

		if ((Robot.oi.StartController().getTriggerAxis(Hand.kLeft) > .3)) {
			setElevatorSpeed(-0.7);
			

		} else if (Robot.oi.StartController().getTriggerAxis(Hand.kRight) > .3) {
			setElevatorSpeed(0.7);
			if(isElevatorAtSweetSpot()){ 
				stopElevator();
				Timer.delay(0.3);
				System.out.println("Debug. SweetSpot Triggered");
			}
			if(isElevatorAtDangerSpot() && !getFlapState()){ 
				stopElevator();
				setFlapsDown(true);
				Timer.delay(0.3);
				System.out.println("Debug. DangerZone Triggered");
			}
			

		}
		
		else {
			stopElevator();
		}

		if (flapsDown) {
			moveFlapsDown();

		} else {
			moveFlapsUp();
		}
	}

	public void elevatorRollerControl() {
		if (Robot.oi.StartController().getXButton()) {
			elevatorRoller.set(1);

		} else {
			elevatorRoller.set(0);
		}
	}

	public void controlFlaps() {
		if (Robot.oi.StartController().getStartButton()) {
			moveFlapsDown();
			Timer.delay(0.1);

		} else {
			moveFlapsUp();

		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void log() {
		SmartDashboard.putNumber("Potentiometer Value", Robot.elevatorSystem.getPotentiometerValue());
		SmartDashboard.putBoolean("Bottom Stop", this.getBottomLimit());
		SmartDashboard.putBoolean("Top Stop", this.getTopLimit());

	}

	public void setRollerState(boolean status) {
		if (status) {
			elevatorRoller.set(1);
			System.out.println("Debug. Roller Should be on");
		} else
			elevatorRoller.set(0);
		System.out.println("Debug. Roller Should Be off");
	}

	public void setFlapsDown(boolean flapsDown) {
		this.flapsDown = flapsDown;
	}

	public void moveFlapsForGears() {

	}

	public void moveFlapsUp() {
		flapsSolenoid.set(false);
	}

	public void moveFlapsDown() {
		flapsSolenoid.set(true);

	}

	public boolean getFlapState(){ 
		return flapsDown;
	}
	public boolean isElevatorAtSweetSpot() {
		if (Math.abs(getPotentiometerValue() - 384.0) <= 5) { // 388 is a bit
																// high, 380 is
																// too low,
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

		return (!(limitSwitchBottom.get()));

	}

	public void setElevatorSpeed(double speed) {
		elevatorActuator.set(speed);

	}

	public void stopElevator() {
		elevatorActuator.set(0);
	}

}
