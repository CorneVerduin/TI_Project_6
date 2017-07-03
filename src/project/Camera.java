package project;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Camera implements Runnable
{
	private Mat frame = new Mat(), imgSvh = new Mat(), red = new Mat();	
	private VideoCapture cam;
	private boolean camFound = false;
	
	public Camera() 
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.cam = new VideoCapture(0);
	}
	
	@Override
	public void run()
	{
		this.openCam();
		while(true) 
		{
			if(this.camFound)
			{
				this.cam.read(this.frame);	
				this.findRed(); 	
			}
		}
	}

	public void openCam()
	{
		if(!this.cam.isOpened()){
		    System.out.println("Error");
		}
		else 
		{
			this.camFound = true;
		}
	}

	public void findRed()
	{
		Imgproc.cvtColor(this.frame, this.imgSvh, Imgproc.COLOR_BGR2HSV);

		Core.inRange(this.imgSvh, new Scalar(109, 100, 100), new Scalar(189, 255, 255), this.red); 

		int count = Core.countNonZero(this.red);
		
		Centraal centraal = new Centraal();
		
		if(count > 2000) 
		{
			// doe dansje aan het eind
			
			centraal.rijRichting(4);
		}
	}
}