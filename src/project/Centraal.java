package project;

public class Centraal {
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
	
	
}
