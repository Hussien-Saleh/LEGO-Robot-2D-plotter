package de.tuhh.diss.lejos;


/**
 * This Class is used for control of the plotting robot. A great amount of time should spend for controlling the robot.
 * Add a suitable constructor and add further methods you need for driving the motors, evaluating the sensors etc.
 */
public class PlotbotControl {
	
	private SwivelArm swivelArm;
	private Pen pen;
	private Wheel wheel;
	
	private Coord penCoord;
	
	private int motorArmAngleMax;
	private int motorPenAngle;
	
	private static final int LIGHTSENSOR_THRESHOLD = 500;
	private static final int DISTANCE_ARM_PEN = 80;
	private static final int SPEED = 180;
	
	private int prevY; 
	private int prevX;
	private int prevAngle;
	
		public PlotbotControl() {
			this.wheel = new Wheel();
			this.swivelArm = new SwivelArm(0, false);
			this.pen = new Pen(0, false);
			this.penCoord = new Coord(0,0);
			this.setMotorArmAngleMax(0);
			this.setMotorPenAngle(0);
			this.prevX = 0;
			this.prevY = 0;
			this.prevAngle = 0;
	
			/*while(true) {
				this.getSwivelArm().getSwivelArmMotor().forward();
			}*/
			
		}
		
		public void calibration() {
			int motorAngleMax = 0;
			
			this.wheel.setWheelMotorSpeed(SPEED);
			this.swivelArm.setSwivelArmMotorSpeed(SPEED);
			this.pen.setPenMotorSpeed(SPEED);
			
			this.pen.getPenMotor().resetTachoCount();
			this.swivelArm.getSwivelArmMotor().resetTachoCount();
			this.wheel.getWheelMotor().resetTachoCount();
			
			while (!this.pen.getPenTouchSensor().isPressed()) {
				this.pen.getPenMotor().backward();
			}
			
			this.setMotorPenAngle(this.pen.getPenMotor().getTachoCount());
			this.pen.setMotorAngle(this.getMotorPenAngle());
			this.pen.penMotorStop();
			this.pen.getPenMotor().resetTachoCount();
			
			while(this.wheel.getWheelLightSensor().readNormalizedValue() != PlotbotControl.LIGHTSENSOR_THRESHOLD) {
				this.wheel.wheelMoveBackwards();
			}
			
			this.wheel.wheelMotorStop();
			this.wheel.getWheelMotor().resetTachoCount();
			
			
			while (!this.swivelArm.getSwivelArmTouchSensor().isPressed()) {
				this.swivelArm.getSwivelArmMotor().backward();
			}
			
			this.swivelArm.getSwivelArmMotor().stop();
			motorAngleMax = this.swivelArm.getSwivelArmMotor().getTachoCount();
			
			this.swivelArm.getSwivelArmMotor().rotate(-motorAngleMax);
			this.swivelArm.getSwivelArmMotor().stop();
			this.swivelArm.getSwivelArmMotor().resetTachoCount();
			this.setMotorArmAngleMax(motorAngleMax);
			this.swivelArm.setMotorAngleMax(this.motorArmAngleMax);
			
		}
		
		public void penToXy(int x, int y) {
			int period = 1;
			if (this.prevY != y) {
				this.wheel.wheelMove(y - this.prevY);
				this.prevY = y;
				
			}
			
			if (this.prevX != x) {
				int angle = this.penCoord.xyToAngle(x - prevX, PlotbotControl.DISTANCE_ARM_PEN);
				if (angle > 0) {
					int refrY = PlotbotControl.DISTANCE_ARM_PEN;
				for (int i = 1; i <= angle ; i += period) {
					this.swivelArm.swivelArmMove(period, this.prevAngle);
					this.prevAngle += period;
					if (this.penCoord.angleToY(i + period) != refrY) {
						this.wheel.wheelMove(refrY - this.penCoord.angleToY(i + period));
						prevY = prevY + (refrY - this.penCoord.angleToY(i + period));
						refrY = this.penCoord.angleToY(i + period);
					}
				}
			} else {
				for (int i = -1; i >= angle ; i -= period) {
					int refrY = PlotbotControl.DISTANCE_ARM_PEN;
					this.swivelArm.swivelArmMove(-period, this.prevAngle);
					this.prevAngle -= period;
					if (this.penCoord.angleToY(i - period) != refrY) {
						this.wheel.wheelMove(refrY - this.penCoord.angleToY(i - period));
						prevY = prevY + (refrY - this.penCoord.angleToY(i - period));
						refrY = this.penCoord.angleToY(i - period);
					}
				}
			}
				this.prevX = x;
		}
	}
		
		public void diagonal(int x, int y) {
			if (this.prevX != x) {
				int angle = this.penCoord.xyToAngle(x - prevX, PlotbotControl.DISTANCE_ARM_PEN);
				if (angle > 0) {
					this.swivelArm.swivelArmMove(angle, this.prevAngle, true);
					this.wheel.setWheelMotorSpeed(SPEED / 2);
					this.wheel.wheelMove(y - this.prevY);
					this.wheel.setWheelMotorSpeed(SPEED);
					this.prevAngle += angle;
				
			} else {
					this.swivelArm.swivelArmMove(angle, this.prevAngle, true);
					this.wheel.setWheelMotorSpeed(SPEED / 2);
					this.wheel.wheelMove(y - this.prevY);
					this.wheel.setWheelMotorSpeed(SPEED);
					this.prevAngle += angle;
				
			}
				this.prevX = x;
				this.prevY = y;
			}
		}
		
		public void penUp() {
			this.pen.penUp();
		}
		
		public void penDown(){
			this.pen.penDown();
		}
		
		public SwivelArm getSwivelArm() {
			return this.swivelArm;
		}

		public void setSwivelArm(SwivelArm swivelArm) {
			this.swivelArm = swivelArm;
		}

		public Pen getPen() {
			return this.pen;
		}

		public void setPen(Pen pen) {
			this.pen = pen;
		}

		public Wheel getWheel() {
			return this.wheel;
		}

		public void setWheel(Wheel wheel) {
			this.wheel = wheel;
		}

		public Coord getPenCoord() {
			return this.penCoord;
		}

		public void setPenCoord(Coord penCoord) {
			this.penCoord = penCoord;
		}
	
		public int getMotorArmAngleMax() {
			return this.motorArmAngleMax;
		}

		public void setMotorArmAngleMax(int motorArmAngleMax) {
			this.motorArmAngleMax = motorArmAngleMax;
		}

		public int getMotorPenAngle() {
			return this.motorPenAngle;
		}

		public void setMotorPenAngle(int motorPenAngle) {
			this.motorPenAngle = motorPenAngle;
		}
}
