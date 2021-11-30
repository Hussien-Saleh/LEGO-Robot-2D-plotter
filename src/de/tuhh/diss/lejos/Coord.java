package de.tuhh.diss.lejos;

public class Coord {
	
	private int x;
	private int y;
	private int angle;
	private static final int DISTANCE_ARM_PEN = 80;
	public Coord (int x, int y) {
        this.x = x;
        this.y = y;
	}
	
	public int angleToY (int angleInDegrees) {
		int y = (int) (DISTANCE_ARM_PEN * Math.cos(Math.toRadians(angleInDegrees)));
		return y;
	}
	
	public int angleToX (int angleInDegrees) {
		int x = (int) (DISTANCE_ARM_PEN * Math.sin(Math.toRadians(angleInDegrees)));
		return x;
	}
	
	public int xyToAngle (int x, int y) {
		int angle = (int) Math.toDegrees(Math.atan2(x, y));
		return angle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void addX(int x) {
		this.x += x;
	}
	
	public void subtractX(int x) {
		this.x -= x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void addY(int y) {
		this.y += y;
	}
	
	public void subtractY(int y) {
		this.y -= y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public void addAngle(int angle) {
		this.angle += angle;
	}


}
