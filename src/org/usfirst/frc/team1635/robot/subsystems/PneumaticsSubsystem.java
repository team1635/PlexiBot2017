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

/**
 *
 */
public class PneumaticsSubsystem extends Subsystem {
	Compressor compressor;
	Solenoid gearSolenoid;
	Solenoid gearShifter;

	public PneumaticsSubsystem() {
		super();
		compressor = new Compressor(RobotMap.compressorPort);
		gearSolenoid = new Solenoid(RobotMap.gearSolenoidPort);
		gearShifter = new Solenoid(RobotMap.gearShifterPort);

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
			gearShifter.set(false);
		} else if (Robot.oi.StartController().getStickButton(Hand.kRight)) {
			gearShifter.set(true);
		}
	}

	public void controlGearPiston(){ 
		if(Robot.oi.StartController().getBButton()){
			gearSolenoid.set(true);
			Timer.delay(0.5);
			gearSolenoid.set(false);
		}
	}
	// Functions Dedicated for Automous Mode or General Purpose Commands
	// ------------------------------------------------------------
	public void extendPiston() {
		gearSolenoid.set(true);
	}

	public void retractPiston() {
		gearSolenoid.set(false);
	}

}
