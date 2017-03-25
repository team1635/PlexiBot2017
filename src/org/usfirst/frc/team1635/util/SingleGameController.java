package org.usfirst.frc.team1635.util;

//import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team1635.util.Joystick;
/**
 * The second game pad controller mirrors all the commands on the first game
 * controller.
 * 
 * A cleaner implementation would probably inherit from GenericHID, but this
 * should work
 *
 * @author Bogdan
 */
public class SingleGameController extends Joystick implements GameController {

	public SingleGameController(int ControllerPort) {
		super(ControllerPort);
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
}
