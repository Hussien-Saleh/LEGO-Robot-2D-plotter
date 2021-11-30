package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.lejos.Coord;
import de.tuhh.diss.lejos.PlotbotControl;

public class Line implements Plottable {
	
	private Coord start; 
	private Coord end;
	
	public Line(Coord start, Coord end, boolean diagonalFlag) {
		
		this.start=start;
		this.end=end;
		
		if(diagonalFlag==true){
		this.plot(new PlotbotControl());	
		}
		else{
		this.plotDiagonal(new PlotbotControl());
		}
	}

	public void plot(PlotbotControl pc) {
		pc.penToXy(this.start.getX(), this.start.getY());
		pc.penDown();
		pc.penToXy(this.end.getX(), this.end.getY());
		pc.penUp();
		
	}

	public void plotDiagonal(PlotbotControl pc) {
		pc.diagonal(this.start.getX(), this.start.getY());
		pc.penDown();
		pc.diagonal(this.end.getX(), this.end.getY());
		pc.penUp();
		
	}

	public Coord getStart() {
		return this.start;
	}

	public void setStart(Coord start) {
		this.start = start;
	}

	public Coord getEnd() {
		return this.end;
	}

	public void setEnd(Coord end) {
		this.end = end;
	}

}
