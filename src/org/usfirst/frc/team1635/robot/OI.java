package org.usfirst.frc.team1635.robot;

import org.usfirst.frc.team1635.robot.commands.GusGearIntake;

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
	public XboxController gameController2 = new XboxController(RobotMap.driverControllerPort2);

	Button aButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kA);
	Button bButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kB);
	Button yButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kY);
	Button xButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kX);

	Button aButton2 = new XboxControllerButton(gameController2, XboxControllerButton.Name.kA);
	Button bButton2 = new XboxControllerButton(gameController2, XboxControllerButton.Name.kB);
	Button yButton2 = new XboxControllerButton(gameController2, XboxControllerButton.Name.kY);
	Button xButton2 = new XboxControllerButton(gameController2, XboxControllerButton.Name.kX);

	DPadButton dPadUp = new DPadButton(gameController, DPadButton.Direction.Up);
	DPadButton dPadDown = new DPadButton(gameController, DPadButton.Direction.Down);
	DPadButton dPadLeft = new DPadButton(gameController, DPadButton.Direction.Left);
	DPadButton dPadRight = new DPadButton(gameController, DPadButton.Direction.Right);

	DPadButton dPadUp2 = new DPadButton(gameController2, DPadButton.Direction.Up);
	DPadButton dPadDown2 = new DPadButton(gameController2, DPadButton.Direction.Down);
	DPadButton dPadLeft2 = new DPadButton(gameController2, DPadButton.Direction.Left);
	DPadButton dPadRight2 = new DPadButton(gameController2, DPadButton.Direction.Right);
	// -----------------------------------------------------------------------------------
	// For DualControllers
	public boolean globalA;
	public boolean globalB;
	public boolean globalX;
	public boolean globalY;
	public boolean globalLeftBumper;
	public boolean globalRightBumper;
	public boolean globalBackButton;
	public boolean globalStartButton;

	public void masterToSecondary(XboxController master, XboxController secondary) {
		this.globalA = master.getAButton() || secondary.getAButton();
		this.globalB = master.getBButton() || secondary.getBButton();
		this.globalY = master.getYButton() || secondary.getYButton();
		this.globalX = master.getXButton() || secondary.getXButton();
		this.globalLeftBumper = master.getBumper(Hand.kLeft) || secondary.getBumper(Hand.kLeft);
		this.globalRightBumper = master.getBumper(Hand.kRight) || secondary.getBumper(Hand.kRight);
		this.globalBackButton = master.getBackButton() || secondary.getBackButton();
		this.globalStartButton = master.getStartButton() || secondary.getStartButton();

	}
	
//---------------------------------------------------------------------------------------	

	public OI() {
		aButton.whenPressed(new WiggleForward());
		aButton2.whenPressed(new WiggleForward());
		// Don't use B - we switch camera with it.

		dPadUp.whenPressed(new ShootBalls());
		dPadUp2.whenPressed(new ShootBalls());

		dPadDown.whenPressed(new GusGearIntake());
		dPadDown2.whenPressed(new GusGearIntake());
		// dPadLeft.whenPressed(new PopGearWithFlapsDown());
	}

	// ------------------------------------------------------------
	public XboxController StartController() {
		return gameController;

	}

	public XboxController StartController2() {
		return gameController2;
	}

}
