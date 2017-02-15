package org.usfirst.frc.team1635.robot.subsystems;

import org.usfirst.frc.team1635.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSubsystem extends Subsystem {
 Compressor compressor; 
 DoubleSolenoid dbSolenoid; 
 public PneumaticsSubsystem(){ 
	  super();
	  compressor = new Compressor(RobotMap.compressorPort); 
	  dbSolenoid = new DoubleSolenoid(1, 0); 
	
  }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void extendPiston () {
    	dbSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPiston() {
    	dbSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
}


