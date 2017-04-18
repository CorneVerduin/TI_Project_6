package project;

public class Centraal {
	private int snelheidRobot;
	private int richtingRobot;
	
	private DcMotorController dcController;
	private StappenMotorController StappenMotorController;
	
	// je kan het best gewoon beginnen met het aansturen van de motoren.
	// en eventueel daarna alvast beginnen met het herkennen van de borden.
	
	// daarna kunnen we pas verder kijken naar de wat meer gecompliceerde functies.
	
	public Centraal() 
	{
		this.dcController = new DcMotorController();
		this.StappenMotorController = new StappenMotorController();
	}
	
	public void noodStop() 
	{
		// simpelweg stoppen van stappen motoren
		// zie DcMotorController.stopMotor()
	}
	
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
		// nog niet echt apart te implementeren.
	}
	
	public boolean zoekBordInMagazijn() 
	{
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
	
	
}
