package org.usfirst.frc.team1635.util;

import org.usfirst.frc.team1635.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The second game pad controller mirrors all the commands on the first game
 * controller.
 *
 * @author Bogdan
 */
public class DualGameController {

	private XboxController masterController;
	private XboxController secondaryController;

	public DualGameController(int masterControllerPort, int secondaryControllerPort) {
		this.masterController = new XboxController(masterControllerPort);
		this.secondaryController = new XboxController(secondaryControllerPort);
	}

	public DualGameController() {
		this(RobotMap.driverControllerPort, RobotMap.driverControllerPort2);
	}

	public boolean getTrigger(Hand hand) {
		return masterController.getTrigger(hand);
	}
	
	public boolean getTop(Hand hand) {
		// TODO figure out which button is best mapped to Top
		return false;
	}

	public boolean	getAButton() {
		return masterController.getAButton() || secondaryController.getAButton();
	}
	
	public boolean	getBackButton() {
		return masterController.getBackButton() || secondaryController.getBackButton();
	}
	public boolean	getBButton() {
		return masterController.getBButton() || secondaryController.getBButton();
	}
	
	public boolean	getBumper(GenericHID.Hand hand){
		return masterController.getBumper(hand) || secondaryController.getBumper(hand);
	} 

	public boolean getBumper() {
		return getBumper() || this.secondaryController.getBumper();
	}
	
	//String	getName()
	public int getPOV(int pov) {
	  return masterController.getPOV(pov);
    }
	
	public int getPOVCount() {
		return masterController.getPOV();
	}
	
	public double getRawAxis(int axis) {
		return masterController.getRawAxis(axis);
	}
	
	public boolean getRawButton(int button) {
		return masterController.getRawButton(button) ||
				secondaryController.getRawButton(button);
	}
	
	public boolean getStartButton() {
		return masterController.getStartButton() || 
				secondaryController.getStartButton();
	}
	
	public boolean getStickButton(GenericHID.Hand hand) {
		return masterController.getStickButton(hand) ||
				secondaryController.getStickButton();
	}
	//boolean	getTop(GenericHID.Hand hand) This is not supported for the XboxController.
	//boolean	getTrigger(GenericHID.Hand hand) This is not supported for the XboxController.
	//double	getTriggerAxis(GenericHID.Hand hand) Get the trigger axis value of the controller.

	//GenericHID.HIDType	getType() Get the type of the HID.
	public double getX(GenericHID.Hand hand) {
		return masterController.getX(hand);
	}
	
	public boolean getXButton() {
		return masterController.getXButton() || secondaryController.getXButton();
	}
	
	public double getY(GenericHID.Hand hand) {
		return masterController.getY(hand);
	}
	public boolean getYButton() {
		return masterController.getYButton() || secondaryController.getYButton();
	}
	//void	setOutput(int outputNumber, boolean value) Set a single HID output value for the HID.
	//void	setOutputs(int value) Set all HID output values for the HID.
	//void	setRumble(GenericHID.RumbleType type, double value) Set the rumble output for the HID.
}
