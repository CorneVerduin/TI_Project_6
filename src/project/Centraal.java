package project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Centraal {


	private GpioController gpio = GpioFactory.getInstance();	
	
	private final Pin PinLv = RaspiPin.GPIO_00;
	private final Pin PinRv = RaspiPin.GPIO_02;
	private final Pin PinLa = RaspiPin.GPIO_03;
	private final Pin PinRa = RaspiPin.GPIO_04;
	
	public static void main(String[] args) 
	{
		Centraal centraal = new Centraal();
		
		Thread MotorLv = new Thread(new StappenMotorController(centraal.gpio, centraal.PinLv));
		Thread MotorRv = new Thread(new StappenMotorController(centraal.gpio, centraal.PinRv));
		Thread MotorLa = new Thread(new StappenMotorController(centraal.gpio, centraal.PinLa));
		Thread MotorRa = new Thread(new StappenMotorController(centraal.gpio, centraal.PinRa));
		
		MotorLv.start();
		MotorRv.start();
		MotorLa.start();
		MotorRa.start();
	}	
	
	/*
	private int snelheidRobot;
	private int richtingRobot;
	
	private DcMotorController dcController;
	private StappenMotorController stappenMotorController;
	
	// je kan het best gewoon beginnen met het aansturen van de motoren.
	// en eventueel daarna alvast beginnen met het herkennen van de borden.
	
	// daarna kunnen we pas verder kijken naar de wat meer gecompliceerde functies.
	// tevens moet hiervoor de robot voor inelkaar zijn gezet xD
	
	public Centraal() 
	{
		this.dcController = new DcMotorController();
		this.stappenMotorController = new StappenMotorController();
	}
	
	public static void main(String args[])
	{
		StappenMotorController smp = new StappenMotorController();
		smp.draaiWiel();
	}
	
	public void noodStop() 
	{
		// simpelweg stoppen van stappen motoren
		// zie DcMotorController.stopMotor()
	}
	
	
	// https://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
	public int bepaalRichting() 
	{
		return richtingRobot;
		//this.richtingRobot = return waarde. (gebeurt niet in deze functie)
	}
	
	public int bepaalSnelheid() 
	{
		return snelheidRobot;
		//this.snelheidRobot = return waarde. (gebeurt niet in deze functie)
	}
	
	public boolean zoekBordInVrachtwagen() 
	{
		return true;
		// nog niet echt apart te implementeren.
	}
	
	public boolean zoekBordInMagazijn() 
	{
		return true;
		// nog niet echt apart te implementeren.
	}
	
	public void bepaalRoute()
	{
		// nog niet echt apart te implementeren.
	}
	
	public void plaatsKist()
	{
		// nog niet echt apart te implementeren.
	}
	
	public void pakKist() 
	{
		// nog niet echt apart te implementeren.
	}
	
*/	
}