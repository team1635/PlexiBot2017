package org.usfirst.frc.team1635.robot;

import org.usfirst.frc.team1635.robot.commands.GusGearIntake;
import org.usfirst.frc.team1635.robot.commands.PopGearWithFlapsDown;
import org.usfirst.frc.team1635.robot.commands.ShootBalls;
import org.usfirst.frc.team1635.robot.commands.WiggleForward;
//Local Package Imports
import org.usfirst.frc.team1635.util.XboxControllerButton;
import org.usfirst.frc.team1635.util.DPadButton;
//------------------------------------------------------------
import org.usfirst.frc.team1635.util.DPadButton.Direction;

// WPILIB Imports
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
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
 *         This class is the glue that binds the controls on the physical
 *         operator interface to the commands and command groups that allow
 *         control of the robot.
 *
 */

public class OI {
	public XboxController gameController = new XboxController(RobotMap.driverControllerPort);
	Joystick gameControllerJoystickClass = new Joystick(0);
	Button aButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kA);
	Button bButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kB);
	Button yButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kY);
	Button xButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kX);
	DPadButton dPadUp = new DPadButton(gameController, DPadButton.Direction.Up);
	DPadButton dPadDown = new DPadButton(gameController, DPadButton.Direction.Down);
	DPadButton dPadLeft = new DPadButton(gameController, DPadButton.Direction.Left);
	DPadButton dPadRight = new DPadButton(gameController, DPadButton.Direction.Right);

	public OI() {
    	aButton.whenPressed(new WiggleForward());
    	//Don't use B - we switch camera with it.

		dPadUp.whenPressed(new ShootBalls());
		dPadDown.whenPressed(new GusGearIntake());
		dPadLeft.whenPressed(new PopGearWithFlapsDown());
	}

	// OI Functions ( These are passed through different methods as parameters
	// sometimes)

	// ------------------------------------------------------------
	public XboxController StartController() {
		return gameController;
	}

	public Joystick StartJoystick() {
		return gameControllerJoystickClass;
	}

	public double getRightTriggerValue() {
		return gameControllerJoystickClass.getRawAxis(3);
	}
}
