package project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Centraal {

	private GpioController gpio = GpioFactory.getInstance();
	
	private final boolean WIEL_VOORUIT = false;
	private final boolean WIEL_ACHTERUIT = true;
	
	private final int RICHTING_VOORUIT = 0;
	private final int RICHTING_ACHTERUIT = 1;
	private final int RICHTING_RECHTS = 2;
	private final int RICHTING_LINKS = 3;
	private final int RICHTING_DRAAI = 4;
	
	private final int RICHTING_SCHUIN_RV = 5;
	private final int RICHTING_SCHUIN_RA = 6;
	private final int RICHTING_SCHUIN_LA = 7;
	private final int RICHTING_SCHUIN_LV = 8;
	

	private final Boolean DRAAINIET = null;
	
	
	//stepper pins
	private final GpioPinDigitalOutput PinLa = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00); 
	private final GpioPinDigitalOutput PinLv = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02); 
	private final GpioPinDigitalOutput PinRv = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03); 
	private final GpioPinDigitalOutput PinRa = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04); 
	
	//directional pins
	private final GpioPinDigitalOutput PinLaDir = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12);
	private final GpioPinDigitalOutput PinLvDir = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
	private final GpioPinDigitalOutput PinRvDir = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14);
	private final GpioPinDigitalOutput PinRaDir = this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
	
	private Thread MotorLa;
	private Thread MotorLv;
	private Thread MotorRv;
	private Thread MotorRa;
	
	private StappenMotorController MotorLaController;
	private StappenMotorController MotorLvController;
	private StappenMotorController MotorRvController;
	private StappenMotorController MotorRaController;
	
	public static void main(String[] args)  
	{
		Centraal centraal = new Centraal();
		
		Camera cam = new Camera();
		Thread camThread = new Thread(cam);
		camThread.start();
		
		centraal.InitMotors();
		centraal.initThreads();
		
		//rijen instellen in buffer
		centraal.rijRichting(centraal.RICHTING_LINKS);
		centraal.rijRichting(centraal.RICHTING_RECHTS);
		centraal.rijRichting(centraal.RICHTING_VOORUIT);
		centraal.rijRichting(centraal.RICHTING_ACHTERUIT);
		centraal.rijRichting(centraal.RICHTING_DRAAI);
		
		
		// start motoren
		centraal.MotorLa.start();
		centraal.MotorLv.start();
		centraal.MotorRv.start();
		centraal.MotorRa.start();
	
		// nieuwe richtingen:
	}
	
	public void initThreads()
	{
		this.MotorLa = new Thread(this.MotorLaController);
		this.MotorLv = new Thread(this.MotorLvController);
		this.MotorRv = new Thread(this.MotorRvController);
		this.MotorRa = new Thread(this.MotorRaController);
	}
	
	//
	public void InitMotors()
	{
		this.MotorLvController = new StappenMotorController(this.gpio, this.PinLa, this.PinLvDir);
		this.MotorRvController = new StappenMotorController(this.gpio, this.PinLv, this.PinRvDir);
		this.MotorLaController = new StappenMotorController(this.gpio, this.PinRv, this.PinLaDir);
		this.MotorRaController = new StappenMotorController(this.gpio, this.PinRa, this.PinRaDir);
	}
	
	public void setDirections(Boolean wielLvDir, Boolean wielLaDir, Boolean wielRvDir, Boolean wielRaDir) 
	{
			this.MotorLvController.setDirection(wielLvDir);
			this.MotorLaController.setDirection(wielLaDir);
			this.MotorRvController.setDirection(wielRvDir);
			this.MotorRaController.setDirection(wielRaDir);

	}
	
	public void rijRichting(int richting) 
	{
		if(richting == this.RICHTING_VOORUIT) this.setDirections(this.WIEL_VOORUIT, this.WIEL_VOORUIT,this.WIEL_VOORUIT,this.WIEL_VOORUIT);
		if(richting == this.RICHTING_ACHTERUIT) this.setDirections(this.WIEL_ACHTERUIT, this.WIEL_ACHTERUIT, this.WIEL_ACHTERUIT, this.WIEL_ACHTERUIT);
		if(richting == this.RICHTING_RECHTS) this.setDirections(this.WIEL_ACHTERUIT, this.WIEL_VOORUIT, this.WIEL_VOORUIT, this.WIEL_ACHTERUIT);
		if(richting == this.RICHTING_LINKS) this.setDirections(this.WIEL_ACHTERUIT, this.WIEL_VOORUIT, this.WIEL_VOORUIT, this.WIEL_ACHTERUIT);
		if(richting == this.RICHTING_SCHUIN_RV) this.setDirections(this.WIEL_VOORUIT, this.DRAAINIET,  this.DRAAINIET, this.WIEL_VOORUIT);
		if(richting == this.RICHTING_SCHUIN_RA) this.setDirections(this.DRAAINIET, this.WIEL_ACHTERUIT, this.WIEL_ACHTERUIT, this.DRAAINIET);
		if(richting == this.RICHTING_SCHUIN_LA) this.setDirections(this.WIEL_ACHTERUIT, this.DRAAINIET,  this.DRAAINIET, this.WIEL_ACHTERUIT);
		if(richting == this.RICHTING_SCHUIN_LV) this.setDirections(this.DRAAINIET, this.WIEL_VOORUIT, this.WIEL_VOORUIT, this.DRAAINIET);
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