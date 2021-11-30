package de.tuhh.diss.lejos;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Pen {
	
	private NXTRegulatedMotor penMotor;
	private TouchSensor penTouchSensor;
	private int motorAngle;
	private boolean reverse;
	
	public Pen (int motorAngle, boolean reverse) {
		this.penMotor = Motor.B;
		this.penTouchSensor = new TouchSensor(SensorPort.S2);
		this.motorAngle = motorAngle;
		this.reverse = reverse;
	}
	
	public boolean penUp() {
		if (reverse == false) {
		while (!this.penTouchSensor.isPressed()) {
			this.penMotor.backward();
		}
		this.penMotor.stop();
		return true;
		} else {
			while (!this.penTouchSensor.isPressed()) {
				this.penMotor.forward();
			}
			this.penMotor.stop();
			return true;
		}
	}
	
	public boolean penDown() {
		if (reverse == false) {
		if (this.penTouchSensor.isPressed()) {
			this.penMotor.rotate(-this.motorAngle);
			this.penMotor.stop();
			return true;
		} else {
			this.getPenMotor().stop();
			return false;
		}
		} else {
			if (this.penTouchSensor.isPressed()) {
				this.penMotor.rotate(this.motorAngle);
				this.penMotor.stop();
				return true;
			} else {
				this.penMotor.stop();
				return false;
			}
		}
	}
	
	public void setPenMotorSpeed(int speed) {
		this.penMotor.setSpeed(speed);
	}
	
	public void penMotorStop() {
		this.penMotor.stop();
	}
	
	public NXTRegulatedMotor getPenMotor() {
		return this.penMotor;
	}

	public void setPenMotor(NXTRegulatedMotor penMotor) {
		this.penMotor = penMotor;
	}

	public TouchSensor getPenTouchSensor() {
		return this.penTouchSensor;
	}

	public void setPenTouchSensor(TouchSensor penTouchSensor) {
		this.penTouchSensor = penTouchSensor;
	}

	public int getMotorAngle() {
		return this.motorAngle;
	}

	public void setMotorAngle(int motorAngle) {
		this.motorAngle = motorAngle;
	}
}
