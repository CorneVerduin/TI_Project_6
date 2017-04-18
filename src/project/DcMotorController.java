package project;

public class DcMotorController {
	private int switch;
	
	public void stopMotor() 
	{
		// Te testen door de dcmotor apart te laten stoppen.
	}
	
	public void startMotor() 
	{
		// Te testen door de dcmotor apart te laten starten.
	}
	
	public void wachtOpKnop(int switch)
	{
		// Kan niet echt apart, dan moeten de knoppen eerst worden aangesloten.
		boolean success = false;
		/*
		while(tijd) // wellicht enkele seconden.
		{
			if(switch == pressed)
			{
				this.stopMotor();
				success = true;
			}
		}
		if(success = false)
		{
			this.resetMotor();
			this.wachtOpKnop(switch)
		}
		*/
	}
	
	public void resetMotor() 
	{
		// Te testen door de dcmotor apart te resetten.
		// Dit houd in:
		// - naar beginstand draaien
		// - naar vorige stand
		// hangt af van waar hij mee bezig is.
	}
}
