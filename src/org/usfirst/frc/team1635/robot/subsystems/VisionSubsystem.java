package org.usfirst.frc.team1635.robot.subsystems;

import java.util.ArrayList;

//import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.*;
//import org.opencv.core.Core.*;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
//import org.usfirst.frc.team1635.vision.VisionPipelineExample;
import org.usfirst.frc.team1635.vision.BobPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

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
public class VisionSubsystem extends Subsystem {
	public final Object visionLock = new Object();
	private int centerX;

	public VisionSubsystem() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);

		CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);
		CvSource secondStream = CameraServer.getInstance().putVideo("FirstStage", 320, 240);
		// CvSink cameraStream = CameraServer.getInstance().getVideo();
		// Mat oneFrame = new Mat();
		// cameraStream.grabFrame(oneFrame);
		// Imgcodecs.imwrite("/tmp/picture0", oneFrame);

		// VisionThread visionThread = new VisionThread(camera, new
		// VisionPipelineExample(), pipeline -> {
		VisionThread visionThread = new VisionThread(camera, new BobPipeline(), pipeline -> {
			secondStream.putFrame(pipeline.hsvThresholdOutput());
			if (!pipeline.filterContoursOutput().isEmpty()) {
				ArrayList<MatOfPoint> myPolys = pipeline.filterContoursOutput();
				int polyCnt = myPolys.size();
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (visionLock) {
					centerX = r.x + (r.width / 2);
				}
				// outputStream.putFrame(pipeline.hslThresholdOutput());
				outputStream.putFrame(pipeline.hsvThresholdOutput());
				System.out.println("Vision: CenterX = " + centerX);
				SmartDashboard.putNumber("polyCnt", polyCnt);
			} else {
				// System.out.println("Vision: No target in sight");
			}
		});

		visionThread.start();

	}
	
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new DualCameras());
		// setDefaultCommand(new VisionProcessing());
	}
}