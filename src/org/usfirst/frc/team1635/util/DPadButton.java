package org.usfirst.frc.team1635.util;

import org.usfirst.frc.team1635.*;
import org.usfirst.frc.team1635.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.JoystickBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DPadButton extends Button {

	private XboxController joystick;
	private Direction direction;

	public DPadButton(XboxController gameController, Direction direction) {
		this.joystick = gameController;
		this.direction = direction;
	}

	@Override
	public boolean get() {
		int degree = joystick.getPOV(0);

		return degree == direction.degree;
	}

	public enum Direction {
		Up(0), Down(180), Left(270), Right(90);

		int degree;

		Direction(int degree) {
			this.degree = degree;
		}
	}
}
