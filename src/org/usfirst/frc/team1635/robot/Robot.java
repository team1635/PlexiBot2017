
package org.usfirst.frc.team1635.robot;

// WPILIB Imports
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//------------------------------------------------------------
// Local Package Imports
import org.usfirst.frc.team1635.robot.subsystems.ChassisSubsystem;
import org.usfirst.frc.team1635.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1635.robot.subsystems.PneumaticsSubsystem;
//------------------------------------------------------------
import org.usfirst.frc.team1635.robot.subsystems.WinchClimbSubsystem;



// .---.  ,--.    .-----. .------.  
// /_   | /  .'   /  -.   \|   ___|  
// |   |.  / -.  '-' _'  ||  '--.   
// |   || .-.  '    |_  < `---.  '. 
// |   |' \  |  |.-.  |  |.-   |  | 
// |   |\  `'  / \ `-'   /| `-'   / 
// `---' `----'   `----''  `----''  

/**
 * 
 * @author Bogdan Bradu & Miguel Cruz ( @Acelogic_)
 *
 */
public class Robot extends IterativeRobot {
	// ~ Subsystem instantiation ~ (Basically creating an object from the Subsystem
	// .class files ) 
	public static final ChassisSubsystem chassisSystem = new ChassisSubsystem();
	public static final PneumaticsSubsystem pneumaticsSystem = new PneumaticsSubsystem(); 
	public static  WinchClimbSubsystem winchSystem = new WinchClimbSubsystem();
	public static  ElevatorSubsystem elevatorSystem = new ElevatorSubsystem(); 
	public static OI oi;
	Command autonomousCommand;
	

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		
	
		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand()
		SmartDashboard.putData(chassisSystem);
		SmartDashboard.putData(elevatorSystem);
		SmartDashboard.putData(pneumaticsSystem);
		SmartDashboard.putData(winchSystem);
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putNumber("Potentiometer Value", Robot.elevatorSystem.getPotentiometerValue());
	 
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		

	
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
