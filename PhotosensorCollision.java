import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Behavior;

public class PhotosensorCollision implements Behavior
{ 
	protected final EV3LargeRegulatedMotor engineR; 
	protected final EV3LargeRegulatedMotor engineL; 
	private final EV3ColorSensor colorSensorR;
	private final EV3ColorSensor colorSensorL; 
	private final int Limitangle = 180; 
	private float[] RcolorSample;
	private float[] LcolorSample;
	private boolean bColorSensorR = false;
	private boolean bColorSensorL = false;
	private boolean isActive = false;
	private void reset;

	PhotosensorCollision(EV3ColorSensor sensorR, EV3ColorSensor sensorL, EV3LargeRegulatedMotor motorR,
	EV3LargeRegulatedMotor motorL)
	{
		engineR = motorR;
		engineL = motorL;
		colorSensorR = sensorR;
		colorSensorL = sensorL; 
		colorSensorR.setCurrentMode("Red"); 
		colorSensorL.setCurrentMode("Red");
		RcolorSample = new float[colorSensorR.sampleSize()]; 
		LcolorSample = new float[colorSensorL.sampleSize()];
	}

	public void resetBoolean()
	{ 
		bColorSensorR = false; 
		bColorSensorL = false;
	}

	public void action()
	{ 
		isActive = true;
		if (bColorSensorR)
		{
			double rand = Math.random();
			if (rand < 0.6)
			{
			engineR.setSpeed(ExpurGO.SPEED * 0.5F);
			} 
		else if (rand > 0.6)
		{
			engineL.setSpeed(ExpurGO.SPEED * 0.5F);
		} 
		else if (0.8 < rand)
		{
			engineR.setSpeed(ExpurGO.SPEED * 0.25F);
		} 
		else if (bColorSensorL) 
		{
			double rand = Math.random(); if (rand < 0.6) {
			engineL.setSpeed(ExpurGO.SPEED * 0.5F);
		}
		else if (rand > 0.6)
		{
			engineR.setSpeed(ExpurGO.SPEED * 0.5F);
		} 
		else if (rand > 0.8)
		{
			engineL.setSpeed(ExpurGO.SPEED * 0.25F); }
		}

		engineR.resetTachoCount();
		engineL.resetTachoCount();
		engineR.backward();
		engineL.backward();

		while (isActive && ((engineR.getTachoCount() > -Limitangle) ||(engineL.getTachoCount() > -Limitangle)))
		{ }

		engineL.stop(true);
		engineR.stop(true)

	}

	public void suppress()
	{ 
		isActive = false;
	}

	public boolean takeControl() 
	{
		resetBoolean();
		colorSensorR.fetchSample(RcolorSample, 0); 
		colorSensorL.fetchSample(LcolorSample, 0); 
		bColorSensorR = (RcolorSample[0] < 0.05); 
		bColorSensorL = (LcolorSample[0] < 0.05); 
		return (bColorSensorR || bColorSensorL);
	}
}


