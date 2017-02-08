package org.usfirst.frc.team1635.util;

//WPILIB imports
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
*/
public class XboxControllerButton extends JoystickButton {

	public enum Name {
		kA (1),
		kB (2),
		kX (3),
		kY (4);
		
		private final int number;
		
		Name(int number) {
			this.number = number;
		}
		
		int getNumber() {
			return number;
			
		}
	}
	
	public XboxControllerButton(GenericHID joystick, Name buttonName) {
		super(joystick, buttonName.getNumber());
	}

}
