package project;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class StappenMotorController implements Runnable {
		
	private GpioPinDigitalOutput selectedPin, selectedPinDir;
	
	private GpioController gpio;
	
	// buffer voor komende richtingen.
	List<Boolean> directions = new ArrayList<Boolean>();
	
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
		int directionCount = directions.size();
		int i;
		
		// loop door buffer en draai wiel
		for(i = 0; i < directionCount; i++)
		{
			this.draaiWiel(directions.get(i));
		}
	}
	
	public void setDirection(Boolean direction) 
	{ 
		this.directions.add(direction);
	}
	
	public void draaiWiel(Boolean direction) 
	{
		if(direction != null)
		{
			if(!direction) this.selectedPinDir.low();
			else this.selectedPinDir.high();
		}
		
        for(int i = 0; i < 2500; i++)
        {
        	if(direction != null) this.selectedPin.toggle();
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
