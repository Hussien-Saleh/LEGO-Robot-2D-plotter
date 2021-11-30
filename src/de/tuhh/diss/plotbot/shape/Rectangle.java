package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.lejos.Coord;
import de.tuhh.diss.lejos.PlotbotControl;

public class Rectangle implements Plottable {
	
	private Coord lowerLeftCorner;
	private Coord upperRightCorner;
	
	public Rectangle (Coord lowerLeftCorner, Coord upperRightCorner) {
		
		this.lowerLeftCorner = lowerLeftCorner;
		this.upperRightCorner = upperRightCorner;
		this.plot(new PlotbotControl());
	}

	public void plot(PlotbotControl pc) {
		pc.penToXy(this.lowerLeftCorner.getX(), this.lowerLeftCorner.getY());
		pc.penDown();
		pc.penToXy(this.upperRightCorner.getX(), this.lowerLeftCorner.getY());
		pc.penToXy(this.upperRightCorner.getX(), this.upperRightCorner.getY());
		pc.penToXy(this.lowerLeftCorner.getX(), this.upperRightCorner.getY());
		pc.penToXy(this.lowerLeftCorner.getX(), this.lowerLeftCorner.getY());
		pc.penUp();
		
	}

	public Coord getLowerLeftCorner() {
		return lowerLeftCorner;
	}

	public void setLowerLeftCorner(Coord lowerLeftCorner) {
		this.lowerLeftCorner = lowerLeftCorner;
	}

	public Coord getUpperRightCorner() {
		return upperRightCorner;
	}

	public void setUpperRightCorner(Coord upperRightCorner) {
		this.upperRightCorner = upperRightCorner;
	}
}
