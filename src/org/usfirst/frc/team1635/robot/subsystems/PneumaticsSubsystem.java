package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.Robot;
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.commands.ControlPneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PneumaticsSubsystem extends Subsystem {
	Compressor compressor;
	Solenoid gearSolenoid;
	Solenoid gearShifter;
	Solenoid flapsSolenoid;

	public PneumaticsSubsystem() {
		super();
		compressor = new Compressor(RobotMap.compressorPort);
		gearSolenoid = new Solenoid(RobotMap.gearSolenoidPort);
		gearShifter = new Solenoid(RobotMap.gearShifterPort);
		flapsSolenoid = new Solenoid(RobotMap.flapsPort);
	}

	// Whatever command you set as default will run when the enable button is
	// pressed in Driver Station
	// ------------------------------------------------------------
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ControlPneumatics());
	}

	// Functions Utilizing the Xbox Controller's Buttons or Axes
	// ------------------------------------------------------------
	public void shiftDriveGears() {
		if (Robot.oi.StartController().getStickButton(Hand.kLeft)) {
			if (gearShifter.get()) {
				gearShifter.set(false);
			} else if (gearShifter.get()) {
				gearShifter.set(true);
			}
		}
	}

	public void controlGearPiston() {
		if (Robot.oi.StartController().getBButton()) {
			gearSolenoid.set(true);
			Timer.delay(0.5);
			gearSolenoid.set(false);
		}
	}

	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void log(){ 
		SmartDashboard.putBoolean("FlapState", gearSolenoid.get());
	}
	
	public void extendPiston() {
		gearSolenoid.set(true);
	}

	public void retractPiston() {
		gearSolenoid.set(false);
	}

	public void moveFlapsForElevator() {
		if (Robot.elevatorSystem.isElevatorDown = false) {
			flapsSolenoid.set(true);
		}
		if (Robot.elevatorSystem.isElevatorDown = true) {
			flapsSolenoid.set(false);
		}
		
	}

	public void moveFlapsForGears(){ 
		
	}
	public void moveFlapsUp(){ 
		flapsSolenoid.set(false);
	}
	
	public void moveFlapsDown(){ 
		flapsSolenoid.set(true);
		
	}
}
