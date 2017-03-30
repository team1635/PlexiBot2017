
package org.usfirst.frc.team1635.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
// WPILIB Imports
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
import org.usfirst.frc.team1635.robot.commands.TimeoutDriveWithCorrectionSlow;
import org.usfirst.frc.team1635.robot.commands.TimeoutTankDriveParams;
import org.usfirst.frc.team1635.robot.commands.ZeroOutNavX;
import org.usfirst.frc.team1635.autonomous.AutoCenter;
import org.usfirst.frc.team1635.autonomous.AutonomousLeft;
import org.usfirst.frc.team1635.autonomous.AutonomousRight;
import org.usfirst.frc.team1635.autonomous.AutonomousVisionCenter;
import org.usfirst.frc.team1635.autonomous.AutonomousVisionLeft;
import org.usfirst.frc.team1635.autonomous.AutonomousVisionRight;
import org.usfirst.frc.team1635.robot.commands.DriveWithVision;
import org.usfirst.frc.team1635.robot.commands.TurnToSetPointLi;
import org.usfirst.frc.team1635.robot.commands.WiggleForward;
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
	Command autonomousCommand, autoLeft, autoRight, autoCenter, autoVisionCenter, autonomousVisionLeft,
			autonomousVisionRight;
	SendableChooser chooser;

	boolean forwardCameraOn = true;
	boolean processImage = true;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();

		forwardCameraOn = true;
		processImage = false;

		// SmartDashboard.putData(chassisSystem);
		// SmartDashboard.putData(elevatorSystem);
		// SmartDashboard.putData(pneumaticsSystem);
		// SmartDashboard.putData(winchSystem);
		// SmartDashboard.putData(Scheduler.getInstance());
		//
		// SmartDashboard.putData("Right: Drive To Turn", new
		// TimeoutDriveWithCorrection(2.8)); // 2.8 perfect for driving straight
		// SmartDashboard.putData("Right: Turn Left", new TurnToSetPointLi(55,
		// false)); //True ClockWise, False Counter ClockWise
		// SmartDashboard.putData("Right: Drive To Gear Holder ", new
		// TimeoutDriveWithCorrection(2.5));
		//
		// SmartDashboard.putData("Center : Drive To Gear Holder", new
		// TimeoutDriveWithCorrectionSlow(5.7,RobotMap.timeOutDriveCorrectionSlow
		// ));
		// SmartDashboard.putData("AutoCenter", new AutoCenter());
		//
		// SmartDashboard.putData("Left : Drive To Turn", new
		// TimeoutDriveWithCorrection(2.43));
		// SmartDashboard.putData("Left: Turn Right", new TurnToSetPointLi(56,
		// true));
		// SmartDashboard.putData("Left: Drive to Gear Holder", new
		// TimeoutTankDriveParams(2.41));
		//
		// SmartDashboard.putData("Pop Gear With Flaps Down" , new
		// PopGearWithFlapsDown());
		// SmartDashboard.putData("Zero Out Nav X", new ZeroOutNavX());
		//
		// SmartDashboard.putData("Autonomous Left", new AutonomousLeft());
		// SmartDashboard.putData("Autnomous Right", new AutonomousRight());
		
		SmartDashboard.putData("DriveWithVision", new DriveWithVision());
		SmartDashboard.putData("DriveWithConnSlow", new TimeoutDriveWithCorrectionSlow(RobotMap.autoVisionStraightSpeed, RobotMap.timeOutDriveCorrectionSlow));
		SmartDashboard.putData("WiggleForward", new WiggleForward());

		autoLeft = new AutonomousLeft();
		autoRight = new AutonomousRight();
		autoCenter = new AutoCenter();
		autoVisionCenter = new AutonomousVisionCenter();
		autonomousVisionLeft = new AutonomousVisionLeft();
		autonomousVisionRight = new AutonomousVisionRight();

		chooser = new SendableChooser();
		chooser.addDefault("AutonomousLeft", autoLeft);
		chooser.addObject("AutonomousRight", autoRight);
		chooser.addObject("Autonomous Center", autoCenter);
		chooser.addObject("AutoVision Left", autonomousVisionLeft);
		chooser.addObject("AutoVision Center", autoVisionCenter);
		chooser.addObject("AutoVision Right", autonomousVisionRight);

		SmartDashboard.putData("Autonomous Mode", chooser);

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	public void autonomousInit() {

		processImage = true;
		SmartDashboard.putBoolean("processImage", processImage);

		forwardCameraOn = true;
		SmartDashboard.putBoolean("forwardCameraOn", forwardCameraOn);

		autonomousCommand = (Command) chooser.getSelected();
		autonomousCommand.start();
		// schedule the autonomous command (example)

		// if (autonomousCommand != null)
		// autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		processImage = false;
		forwardCameraOn = true;
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
		if (Robot.oi.globalB) {
			Timer.delay(0.2);
			forwardCameraOn = !(forwardCameraOn);
		}
	 	Robot.oi.masterToSecondary(Robot.oi.StartController(), Robot.oi.StartController2());
		SmartDashboard.putBoolean("forwardCameraOn", forwardCameraOn);
		// if (Robot.oi.gameController.getXButton()) {
		// Timer.delay(0.2);
		// processImage = !(processImage);
		// }
		// SmartDashboard.putBoolean("processImage", processImage);

		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
