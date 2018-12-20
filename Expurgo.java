import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator; 
import lejos.robotics.subsumption.Behavior;

public class ExpurGO
{
	public final static int SPEED = 180;
	EV3LargeRegulatedMotor engineR = new EV3LargeRegulatedMotor(MotorPort.A); EV3LargeRegulatedMotor engineL = new EV3LargeRegulatedMotor(MotorPort.C); EV3ColorSensor colorSensorR = new EV3ColorSensor(SensorPort.S1); EV3ColorSensor colorSensorL = new EV3ColorSensor(SensorPort.S3); Arbitrator arby;
	Behavior[] behaviorList = new Behavior[3];

	ExpurGo()
	{
		behaviorList[0] = new MoveForward(engineR, engineL);
		behaviorList[1] = new PhotosensorCollision(colorSensorR, colorSensorL,
		engineR, engineL); behaviorList[2] = new EmergencyStop(engineR, engineL);
		arby = new Arbitrator(behaviorList);
	}

	public static void main(String[] args)
	{ 
		ExpurGO autonomousExpurGO = new ExpurGO(); LCD.drawString("Arbitrating", 0, 0);
		autonomousExpurGO.arby.start();
	} 	
}