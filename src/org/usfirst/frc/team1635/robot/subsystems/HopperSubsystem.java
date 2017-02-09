package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

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
public class HopperSubsystem extends Subsystem {

   CANTalon hopperActuator; 
   DigitalInput limitSwitchOne , limitSwitchTwo;
  
   
	
	
	public HopperSubsystem(){ 
		super(); 
		hopperActuator = new CANTalon(RobotMap.actuatorMotorCANPort);
		limitSwitchOne = new DigitalInput(0);
		limitSwitchTwo = new DigitalInput(0); 
	}

	public void operateHopperParams(double speed) {		
		 		hopperActuator.set(speed);		
		
	}
	
	public void hopperStop(){ 
		hopperActuator.set(0);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }
}

