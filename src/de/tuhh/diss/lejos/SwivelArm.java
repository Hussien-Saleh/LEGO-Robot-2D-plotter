package de.tuhh.diss.lejos;

import lejos.nxt.Motor;


import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class SwivelArm {
	
	private NXTRegulatedMotor swivelArmMotor;
	private TouchSensor swivelArmTouchSensor;
	private int motorAngleMax;
	private static final int ARMMOTOR_GEAR_RATIO = 84;
    
	private boolean reverse;
	
	public SwivelArm(int motorAngleMax, boolean reverse) {
		this.swivelArmMotor = Motor.A;
		this.swivelArmTouchSensor = new TouchSensor(SensorPort.S1);
		this.motorAngleMax = motorAngleMax;
		this.reverse = reverse;
	}
/*
 *                                   motor                            arm
 * 84 arm motor gear ratio        84*revolutions (84*360)      1 revolutions(1*360)
 *                                   theta1                          theta2
 */

	public int armAngleToMotorAngle(int angle) {
		
		return (int) (angle * SwivelArm.ARMMOTOR_GEAR_RATIO);
	}
	
	public int motorAngleToArmAngle(int angle) {
		return (int) (angle / SwivelArm.ARMMOTOR_GEAR_RATIO );
	}
	
	public int swivelArmMove(int angle, int anglePrev) {
		if (reverse == false) {
		if (((this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) < this.motorAngleMax) &&
		    ((this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) > -this.motorAngleMax)) { 
		this.swivelArmMotor.rotate(-this.armAngleToMotorAngle(angle));
		this.swivelArmMotor.stop();
		return angle;
		} else {
			return 0;
		}
		} else {
			if (this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev) < -this.motorAngleMax &&
			   (this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) > this.motorAngleMax) { 
				this.swivelArmMotor.rotate(this.armAngleToMotorAngle(angle));
				this.swivelArmMotor.stop();
				return angle;
				} else {
					return 0;
				}
		}
	}
	
	public int swivelArmMove(int angle, int anglePrev , boolean status) {
		if (reverse == false) {
		if ((this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) < this.motorAngleMax && 
			(this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) > -this.motorAngleMax) { 
		this.swivelArmMotor.rotate(-this.armAngleToMotorAngle(angle), status);
		return angle;
		} else {
			return 0;
		}
		} else {
			if ((this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) < -this.motorAngleMax && 
				(this.armAngleToMotorAngle(angle) + this.armAngleToMotorAngle(anglePrev)) > this.motorAngleMax) { 
				this.swivelArmMotor.rotate(this.armAngleToMotorAngle(angle), status);
				return angle;
				} else {
					return 0;
				}
		}
	}
	
	public void setSwivelArmMotorSpeed(int speed) {
		this.swivelArmMotor.setSpeed(speed);
	}
	
	public void swivelArmStop() {
		this.swivelArmMotor.stop();
	}
	
	public NXTRegulatedMotor getSwivelArmMotor() {
		return this.swivelArmMotor;
	}

	public void setSwivelArmMotor(NXTRegulatedMotor nxtRegulatedMotor) {
		this.swivelArmMotor = nxtRegulatedMotor;
	}

	public TouchSensor getSwivelArmTouchSensor() {
		return this.swivelArmTouchSensor;
	}

	public void setSwivelArmTouchSensor(TouchSensor swivelArmTouchSensor) {
		this.swivelArmTouchSensor = swivelArmTouchSensor;
	}

	public int getMotorAngleMax() {
		return this.motorAngleMax;
	}

	public void setMotorAngleMax(int motorAngleMax) {
		this.motorAngleMax = motorAngleMax;
	}
	
	public boolean isReverse() {
		return this.reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
}
