package org.usfirst.frc.team1635.util;

/**
 * Interface to represent the functionality of an xBox360 controller.
 * 
 * @author Bogdan
 */
public interface GameController {

	boolean getButtonA();

	boolean getButtonB();

	boolean getButtonX();

	boolean getButtonY();

	double getFrontButtonAxis();

	boolean getFrontLeftButton();

	boolean getFrontRightButton();

	double getLeftJoystickUpDown();

	double getRawAxis(int i);

	boolean getRawButton(int i);

	double getRightJoystickUpDown();

}
