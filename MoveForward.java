import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.robotics.subsumption.Behavior;

public class MoveForward implements Behavior
{ 
	private final EV3LargeRegulatedMotor engineR; 
	private final EV3LargeRegulatedMotor engineL; 
	private boolean isActive = false;

	MoveForward(EV3LargeRegulatedMotor motorR, EV3LargeRegulatedMotor motorL)
	{ 
		engineR = motorR;
		engineL = motorL;
	}

	public void action()
	{ 
		isActive = true;
		engineL.setSpeed(ExpurGO.SPEED); 
		engineR.setSpeed(ExpurGO.SPEED); 
		engineR.forward(); 
		engineL.forward();

		while (isActive)
		{ } 
		engineR.stop(true); 
		engineL.stop(true);
	}

	public void suppress()
	{ 
		isActive = false;
	}

	public boolean takeControl()
	{
		return true; 
	}
}