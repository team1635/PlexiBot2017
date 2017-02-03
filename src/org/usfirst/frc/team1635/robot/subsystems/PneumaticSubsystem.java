package org.usfirst.frc.team1635.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticSubsystem extends Subsystem {
    
//	DoubleSolenoid dblSolenoid = new DoubleSolenoid(0,1);
	DoubleSolenoid dblSolenoid;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void extendPiston () {
    	dblSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPiston() {
    	dblSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}