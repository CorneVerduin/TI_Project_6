package project;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Camera {
	private Mat frame = new Mat(), imgSvh = new Mat(), red = new Mat();	
	private VideoCapture cam;
	
	public Camera() 
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.cam = new VideoCapture(0);
	}
	

	public void openCam()
	{
		if(!cam.isOpened()){
		    System.out.println("Error");
		}
		else 
		{
			cam.read(frame);		
		}
	}

	public void findRed()
	{
		Imgproc.cvtColor(frame, imgSvh, Imgproc.COLOR_BGR2HSV);

		Core.inRange(imgSvh, new Scalar(109, 100, 100), new Scalar(189, 255, 255), red); 

		int count = Core.countNonZero(red);
		
		if(count > 3000) System.out.println("ROODODODODODODOD");
		else System.out.println("Geen rood :(");
	}
}