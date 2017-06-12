package project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class StappenMotorController implements Runnable {
		
	private GpioPinDigitalOutput selectedPin, selectedPinDir;
	
	private GpioController gpio;
	
	private boolean direction, turn, motorStand = false;
	
	
	public StappenMotorController(GpioController gpio, GpioPinDigitalOutput selectedPin,  GpioPinDigitalOutput selectedPinDir)
	{
		this.gpio = gpio;
		
		this.selectedPin = selectedPin;
		this.selectedPinDir = selectedPinDir;
		
		this.gpio.setShutdownOptions(true, PinState.LOW, this.selectedPin);
	}
	
	@Override
	public void run()
	{
		while(true) 
		{
			if(this.motorStand) 
			{
				this.draaiWiel();
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        this.motorStand = false;
			}
		}
	}
	
	public void setDir(boolean direction, boolean turn) 
	{
		this.direction = direction;
		this.turn = turn; 
		this.motorStand = true;
	}
	
	public boolean getmotorStand() { return this.motorStand; }
	
	public void draaiWiel() 
	{
        
		if(!this.direction) this.selectedPinDir.low();
		else this.selectedPinDir.high();
		
		if(this.turn){
	        for(int i = 0; i < 2500; i++)
	        {
	        	this.selectedPin.toggle();
	            try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
		}
		else
		{
			for(int i = 0; i < 2500; i++)
	        {
				try{ 
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
		}
		
        
	}
	
	public void berekenStappenWielen(int snelheidRobot, int richtingRobot)
	{
		// Lastig om nu te maken, robot zal eerst gemaakt moeten worden om te kunnen testen
		// Heeft tevens nog wat onderzoek nodig:
		//		Welke formules voor het aantal stappen (per wiel).
	}
	
	public void killMotoren() 
	{
		// Je kan deze functie apart testen door de wielen te laten stoppen.
	}
}
