/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1635.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Ripped off from the wpilib JoystickButton;
 * 
 * @author Bogdan
 */
public class GameControllerButton extends Button {

	GameController m_controller;
	int m_buttonNumber;

	/**
	 * Create a GameController button for triggering commands
	 * 
	 * @param controller
	 *            The GenericHID object that has the button (e.g. Joystick,
	 *            KinectStick, etc)
	 * @param buttonNumber
	 *            The button number (see {@link GenericHID#getRawButton(int) }
	 */

	public GameControllerButton(GameController controller, int buttonNumber) {
		m_controller = controller;
		m_buttonNumber = buttonNumber;
	}

	/**
	 * Gets the value of the controller button
	 * 
	 * @return The value of the controller button
	 */
	public boolean get() {
		return m_controller.getRawButton(m_buttonNumber);
	}
}
