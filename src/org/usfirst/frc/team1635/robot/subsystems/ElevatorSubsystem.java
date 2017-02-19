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
	DigitalInput limitSwitchTop, limitSwitchBottom, limitswitchDEBUG2, limitSwitchDEBUG3;
	AnalogPotentiometer analogPot;
	private boolean flapsDown;

	public ElevatorSubsystem() {
		super();
		elevatorActuator = new CANTalon(RobotMap.elevatorMotorCANPort);
		elevatorRoller = new CANTalon(RobotMap.elevatorRollerMotorCANPort);
		limitSwitchTop = new DigitalInput(RobotMap.topLimitSwitchDioPort);
		limitSwitchBottom = new DigitalInput(RobotMap.bottomLimitSwitchDioPort);
		limitswitchDEBUG2 = new DigitalInput(RobotMap.limitSwitchDEBUGPort2);
		limitSwitchDEBUG3 = new DigitalInput(RobotMap.limitSwitchDEBUGPort3);
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

		if ((Robot.oi.StartController().getBumper(Hand.kLeft))) {
			operateElevatorParams(-0.7);

		} else if (Robot.oi.StartController().getBumper(Hand.kRight)) {
			operateElevatorParams(0.7);

		}

		else {
			elevatorStop();
		}
		if (flapsDown) {
			Robot.pneumaticsSystem.moveFlapsDown();

		} else {
			Robot.pneumaticsSystem.moveFlapsUp();
		}
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
		SmartDashboard.putBoolean("DEBUG LIMIT SWITCH PORT 2", limitswitchDEBUG2.get());
		SmartDashboard.putBoolean("DEBUG LIMIT SWITCH PORT 3", limitSwitchDEBUG3.get());

	}

	public void turnRollerOn(boolean TrueOrFalse) {
		if (TrueOrFalse) {
			elevatorRoller.set(1);
			System.out.println("Debug. Roller Should be on");
		} else
			elevatorRoller.set(0);
		System.out.println("Debug. Roller Should Be off");
	}

	public void setFlapsDown(boolean flapsDown){ 
		this.flapsDown = flapsDown; 
	}
	public boolean isElevatorAtSweetSpot() {
		if (Math.abs(getPotentiometerValue() - 384.0) <= 5) { //388 is a bit high, 380 is too low,
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

	public void elevatorStop() {
		elevatorActuator.set(0);
	}

}
