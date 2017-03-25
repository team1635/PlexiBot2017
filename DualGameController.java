package org.usfirst.frc.team1635.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The second game pad controller mirrors all the commands on the first game
 * controller.
 *
 * @author Bogdan
 */
public class DualGameController extends GenericHID implements GameController {

	private GameController masterController;
	private GameController secondaryController;

	public DualGameController(GameController masterController, GameController secondaryController) {
		this.masterController = masterController;
		this.secondaryController = secondaryController;
	}

	public DualGameController(int masterControllerPort, int secondaryControllerPort) {
		this.masterController = new SingleGameController(masterControllerPort);
		this.secondaryController = new SingleGameController(secondaryControllerPort);
	}

	public DualGameController() {
		this(1, 2);
	}

	public double getLeftJoystickUpDown() {
		return getRawAxis(2);
	}

	public double getX(Hand hand) {
		return getLeftJoystickUpDown();
	}

	public double getRightJoystickUpDown() {
		return getRawAxis(5);
	}

	public double getY(Hand hand) {
		// TODO: This is probably better mapped to the second axis of the left
		// joystick.
		return getRightJoystickUpDown();
	}

	public double getFrontButtonAxis() {
		return getRawAxis(3);
	}

	public double getZ(Hand hand) {
		return getFrontButtonAxis();
	}

	public double getTwist() {
		// TODO figure out which axis is best mapped here.
		return 0.0;
	}

	public double getThrottle() {
		// TODO figure out which axis is best mapped here.
		return 0.0;
	}

	public double getRawAxis(int i) {
		double masterControllerValue = masterController.getRawAxis(i);
		double returnValue;

		if (masterControllerValue != 0.0) {
			returnValue = masterControllerValue;
		} else {
			returnValue = secondaryController.getRawAxis(i);
		}

		return returnValue;
	}

	public boolean getTrigger(Hand hand) {
		// TODO figure out which button is best mapped to trigger
		return false;
	}

	public boolean getTop(Hand hand) {
		// TODO figure out which button is best mapped to Top
		return false;
	}

	public boolean getBumper(Hand hand) {
		// TODO figure out which button is best mapped to Bumper
		return false;
	}

	public boolean getRawButton(int i) {
		return masterController.getRawButton(i) || secondaryController.getRawButton(i);
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

	public boolean getFrontRightButton() {
		return getRawButton(6);
	}

	public boolean getFrontLeftButton() {
		return getRawButton(5);
	}

	@Override
	public int getPOV(int pov) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPOVCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HIDType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutput(int outputNumber, boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOutputs(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRumble(RumbleType type, double value) {
		// TODO Auto-generated method stub

	}
}
