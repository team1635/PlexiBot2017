
package org.usfirst.frc.team1635.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1635.robot.subsystems.ChassisSubsystem;
import org.usfirst.frc.team1635.robot.subsystems.PneumaticSubsystem;
import org.usfirst.frc.team1635.robot.subsystems.ServoActuatorSubsystem;
import org.usfirst.frc.team1635.robot.subsystems.VisionSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ChassisSubsystem chassisSystem = new ChassisSubsystem();
	public static final ServoActuatorSubsystem servoActuatorSystem = new ServoActuatorSubsystem(); 
    public static final PneumaticSubsystem airSystem  = new PneumaticSubsystem();
    //public static ActuatorSubsystem actuator;

	public static OI oi;
	public static VisionSubsystem visionSystem;
    Command autonomousCommand;
    //SendableChooser smartDashboardCmdExe; 

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		visionSystem = new VisionSubsystem();
		//smartDashboardCmdExe = new SendableChooser();
		// instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
		//smartDashboardCmdExe.addObject("ShakeRobot", new ShakeRobotWithButton());
		//SmartDashboard.putData(actuator);
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(chassisSystem);
		SmartDashboard.putData(servoActuatorSystem);
		SmartDashboard.putNumber("hue_min", 0.0);
		SmartDashboard.putNumber("hue_max", 178.0);
		SmartDashboard.putNumber("sat_min", 0.0);
		SmartDashboard.putNumber("sat_max", 42.0);
		SmartDashboard.putNumber("val_min", 211.0);
		SmartDashboard.putNumber("val_max", 255.0);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		 
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
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
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
		SmartDashboard.putNumber("Voltage",chassisSystem.getVoltage());
		SmartDashboard.putNumber("Averge Voltage",chassisSystem.getAverageVoltage());
        SmartDashboard.putNumber("Distance",chassisSystem.getDistance());
		SmartDashboard.putNumber("Averge Distance",chassisSystem.getAverageDistance());
        SmartDashboard.putNumber("Distance Value",chassisSystem.getDistance());
		SmartDashboard.putNumber("Averge Distance Value",chassisSystem.getAverageDistance());
		
		//SmartDashboard.putBoolean("Top Switch", actuator.getTopSwitch());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
