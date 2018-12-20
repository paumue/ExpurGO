import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior
{ 
	protected final EV3LargeRegulatedMotor engineR; 
	protected final EV3LargeRegulatedMotor engineL;

	EmergencyStop(EV3LargeRegulatedMotor motorR, EV3LargeRegulatedMotor motorL)
	{ 
		engineR = motorR;
		engineL = motorL; 
	}
	public void action()
	{
		engineR.stop(true);
		engineL.stop(true);
		int buttonID;
		LCD.clear();
		LCD.drawString("Arbitration halted", 0, 0);
		LCD.drawString("Right to continue", 0, 1);
		LCD.drawString("Escape to exit", 0, 2);

		do
		{
			buttonID = Button.waitForAnyPress();
		}

		while (buttonID != Button.ID_RIGHT && buttonID != Button.ID_ESCAPE); if (buttonID == Button.ID_ESCAPE)
		{
			System.exit(1);
		} 
		else {
			LCD.clear();
			LCD.drawString("Arbitration", 0, 0); 
		}
	}

	public void suppress()
	{ 
	}
	public boolean takeControl()
	{ 
		return (Button.ENTER.isDown());
	} 
}