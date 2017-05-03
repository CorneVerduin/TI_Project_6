package project;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;


public class StappenMotorController {
	
	private int stappenWiel;
	
	// http://pi4j.com/usage.html
	GpioController gpio;
	
	//Constante variabelen voor de output pins:
	private final GpioPinDigitalOutput stepPin;
	
	public StappenMotorController()
	{
		// GPIO controller initaliseren.
		this.gpio = GpioFactory.getInstance();
		
		// Pin instellen voor output.
		this.stepPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22);
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
		// Je kan deze functie apart testen door de wielen te laten draaien.
		this.stepPin.setState(PinState.HIGH);
		/*for(int i = 0; i < stappenWiel; i++)
		{
			stepPin.pulse(1000);
		}*/
	}
}
