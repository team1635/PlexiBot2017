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

	public boolean getRawButton(int i) {
		return getRawButton(i) || secondaryController.getRawButton(i);
	}

	public boolean getButtonA() {
		return getRawButton(1);
	}

	public boolean getButtonB() {
		return getRawButton(2);
	}

	public boolean getButtonX() {
		return getRawButton(3);
	}

	public boolean getButtonY() {
		return getRawButton(4);
	}

	public boolean getBumper(Hand hand) {
		return getBumper(hand) || this.secondaryController.getBumper(hand);
	}

	public boolean getBumper() {
		return getBumper() || this.secondaryController.getBumper();
	}
	
	public boolean getStartButton() {
		return this.masterController.getStartButton() || 
				this.secondaryController.getStartButton();
	}

}
