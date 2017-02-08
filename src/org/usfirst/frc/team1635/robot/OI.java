package org.usfirst.frc.team1635.robot;

//Local Package Imports
import org.usfirst.frc.team1635.robot.commands.ShakeRobotWithButton;
import org.usfirst.frc.team1635.util.XboxControllerButton;
//------------------------------------------------------------

// WPILIB Imports
import edu.wpi.first.wpilibj.GenericHID;
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
	XboxController gameController = new XboxController(RobotMap.driverControllerPort);

	Button aButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kA);
	Button bButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kB);
	Button yButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kY);
	Button xButton = new XboxControllerButton(gameController, XboxControllerButton.Name.kX);

	public OI() {
		// aButton.whenPressed(new PushPistonOut());
		// bButton.whenPressed(new PullPistonIn());
		
		yButton.whenPressed(new ShakeRobotWithButton());
		// xButton.whenPressed(new ActuateWithParameters());
	}

	
	// OI Functions ( These are passed through different methods as parameters
	// sometimes)

	// ------------------------------------------------------------
	public XboxController StartController() {
		return gameController;
	}

	public double getActuatorSpeed() {
		return gameController.getX(GenericHID.Hand.kLeft);
	}

	public double getLeftSpeed() {
		return gameController.getY(GenericHID.Hand.kLeft);
	}

	public double getRightSpeed() {
		return gameController.getY(GenericHID.Hand.kRight);
	}

	public double getServoActuatorSpeed() {
		return gameController.getTriggerAxis(GenericHID.Hand.kRight);
	}
}
