
package org.usfirst.frc.team1635.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
// WPILIB Imports
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.awt.dnd.Autoscroll;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrection;
import org.usfirst.frc.team1635.robot.commands.TimeoutTankDriveParams;
import org.usfirst.frc.team1635.robot.commands.ZeroOutNavX;
import org.usfirst.frc.team1635.autonomous.AutonomousLeft;
import org.usfirst.frc.team1635.autonomous.AutonomousRight;
import org.usfirst.frc.team1635.robot.commands.PopGear;
import org.usfirst.frc.team1635.robot.commands.PopGearWithFlapsDown;
import org.usfirst.frc.team1635.robot.commands.TurnToSetPointLi;
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
	// ~ Subsystem instantiation ~ (Basically creating an object from the
	// Subsystem
	// .class files )
	public static final ChassisSubsystem chassisSystem = new ChassisSubsystem();
	public static final PneumaticsSubsystem pneumaticsSystem = new PneumaticsSubsystem();
	public static WinchClimbSubsystem winchSystem = new WinchClimbSubsystem();
	public static ElevatorSubsystem elevatorSystem = new ElevatorSubsystem();
	public static OI oi;
	Command autonomousCommand , autoLeft,  autoRight;
	SendableChooser chooser; 
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();

		SmartDashboard.putData(chassisSystem);
		SmartDashboard.putData(elevatorSystem);
		SmartDashboard.putData(pneumaticsSystem);
		SmartDashboard.putData(winchSystem);
		SmartDashboard.putData(Scheduler.getInstance());
		
		SmartDashboard.putData("Right: Drive To Turn", new TimeoutDriveWithCorrection(2.8)); //  2.8 perfect for driving straight
		SmartDashboard.putData("Right: Turn Left", new TurnToSetPointLi(55, false)); //True ClockWise, False Counter ClockWise 
		SmartDashboard.putData("Right: Drive To Gear Holder ", new TimeoutDriveWithCorrection(2.5));
		
		SmartDashboard.putData("Center : Drive To Gear Holder", new TimeoutDriveWithCorrection(2.6)); 
		
		SmartDashboard.putData("Left : Drive To Turn", new TimeoutDriveWithCorrection(2.43));
		SmartDashboard.putData("Left: Turn Right", new TurnToSetPointLi(56, true));
		SmartDashboard.putData("Left: Drive to Gear Holder", new TimeoutTankDriveParams(2.41)); 
		
		SmartDashboard.putData("Pop Gear With Flaps Down" , new PopGearWithFlapsDown());
		SmartDashboard.putData("Zero Out Nav X", new ZeroOutNavX());
		
		SmartDashboard.putData("Autonomous Left", new AutonomousLeft());
		SmartDashboard.putData("Autnomous Right", new AutonomousRight());
		
		chooser = new SendableChooser();
		chooser.addDefault("AutonomousLeft", autoLeft);
		chooser.addObject("AutonomousRight" , autoRight);
		
		SmartDashboard.putData("Auto mode", chooser);
		
		autoLeft = new AutonomousLeft(); 
		autoRight =  new AutonomousRight(); 
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
