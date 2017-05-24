package project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class StappenMotorController implements Runnable {
		
	private GpioPinDigitalOutput selectedPin;
	private GpioController gpio;
	
	
	public StappenMotorController(GpioController gpio, Pin selectedPin)
	{
		this.gpio = gpio;
		
		this.selectedPin = this.gpio.provisionDigitalOutputPin(selectedPin);
		this.gpio.setShutdownOptions(true, PinState.LOW, this.selectedPin);
	}
	
	@Override
	public void run()
	{	
        for(int i = 0; i < 5000; i++)
        {
        	this.selectedPin.toggle();
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
	
	public void draaiWiel(/*int stappenWiel*/)
	{
		
	}
}
