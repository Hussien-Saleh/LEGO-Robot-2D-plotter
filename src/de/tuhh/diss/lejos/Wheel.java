package de.tuhh.diss.lejos;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Wheel {
	
	private NXTRegulatedMotor wheelMotor;
    private LightSensor wheelLightSensor;
    
	private static final int WHEELMOTOR_GEAR_RATIO = 5;
	private static final int WHEEL_DIAMETER = 56;
	private static final double PI = 3.14;
	
	public Wheel() {
		this.wheelMotor = Motor.C;
		this.wheelLightSensor = new LightSensor(SensorPort.S3);	
	}
	

	/*                             MotorGear                 Wheel             Distance
	 *wheel gear ratio=5 means   5 Revolutions (5*360)      1 Revolutions      PI. Diameter
	 *                            theta motor angle                            distance
	 */
	
	public int distanceToMotorAngle(int distance) {
		return (int) ((distance * Wheel.WHEELMOTOR_GEAR_RATIO * 360)/(Wheel.PI * Wheel.WHEEL_DIAMETER));
	}
	
	public int MotorAngleToDistance(int angle) {
		return (int) ((angle* Wheel.WHEEL_DIAMETER * Wheel.PI)/ (Wheel.WHEELMOTOR_GEAR_RATIO * 360));
	}
	
	public int wheelMove(int distance) {
		this.wheelMotor.rotate(this.distanceToMotorAngle(distance));
		this.wheelMotor.stop();
		this.wheelMotor.resetTachoCount();
		return distance;
	}
	public int wheelMove(int distance, boolean status) {
		this.wheelMotor.rotate(this.distanceToMotorAngle(distance), status);
		this.wheelMotor.resetTachoCount();
		return distance;
	}
	
	public void setWheelMotorSpeed(int speed) {
		this.wheelMotor.setSpeed(speed);
	}
	
	public void wheelMotorStop() {
		this.wheelMotor.stop();
	}
	
	public void wheelMoveBackwards() {
		this.wheelMotor.backward();
	}
	
	public NXTRegulatedMotor getWheelMotor() {
		return this.wheelMotor;
	}

	public void setWheelMotor(NXTRegulatedMotor wheelMotor) {
		this.wheelMotor = wheelMotor;
	}

	public LightSensor getWheelLightSensor() {
		return this.wheelLightSensor;
	}

	public void setWheelLightSensor(LightSensor wheelLightSensor) {
		this.wheelLightSensor = wheelLightSensor;
	}

}
