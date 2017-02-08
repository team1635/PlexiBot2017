package org.usfirst.frc.team1635.robot.subsystems;


//WPILIB imports
import edu.wpi.first.wpilibj.command.Subsystem;

//Local Package Imports
import org.usfirst.frc.team1635.robot.RobotMap;
import org.usfirst.frc.team1635.robot.OI;
import org.usfirst.frc.team1635.robot.Robot;

//------------------------------------------------------------
//CTRE Imports
import com.ctre.CANTalon;
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
public class RollerClimbSubsystem extends Subsystem {
	CANTalon rollerTalon; 
	
   public RollerClimbSubsystem(){ 
	   super(); 
	   rollerTalon = new CANTalon(RobotMap.rollerClimbMotorCANPort); 
	   
	   
	   
   }
   
   public void rollerWithController(){ 
	 boolean isStartPressed = Robot.oi.StartController().getStartButton();
	 boolean isBackPressed = Robot.oi.StartController().getBackButton(); 
	 
	 while(isStartPressed ==true){ 
		 // todo: Moving Forward while start is pressed
	 }
	 while(isBackPressed ==true){ 
		 //todo : Moving Backwards while back is pressed
	 }
	 
   }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

