package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.lejos.Coord;
import de.tuhh.diss.lejos.PlotbotControl;
import de.tuhh.diss.plotbot.shape.Plottable;

public class Ship implements Plottable{
	
	private Coord lowerLeftCorner;
	private int width;

	public Ship (Coord lowerLeftCorner, int width) {	
		
		this.lowerLeftCorner = lowerLeftCorner;
		this.width = width;
		this.plot(new PlotbotControl());
	}

	public void plot(PlotbotControl pc) {
		
       new Rectangle(new Coord(((1/3) * this.width)+this.lowerLeftCorner.getX(),245),
				     new Coord((((1/3) * this.width)+this.lowerLeftCorner.getX()+20), 255)).plot(pc); 
	  
       new Line(new Coord(((1/3) * this.width)+this.lowerLeftCorner.getX(),245),
			    new Coord(((1/3) * this.width)+this.lowerLeftCorner.getX(), this.lowerLeftCorner.getY()),true).plot(pc);
       
	   new Line(new Coord(this.lowerLeftCorner.getX(), this.lowerLeftCorner.getY()),
			    new Coord(this.lowerLeftCorner.getX() + this.width, this.lowerLeftCorner.getY()),true).plot(pc);

	   new Line(new Coord(this.lowerLeftCorner.getX()+ this.width,this.lowerLeftCorner.getY()),
			    new Coord(this.lowerLeftCorner.getX()+ this.width-1,this.lowerLeftCorner.getY()-1),false).plot(pc);
	   
	   new Line(new Coord(this.lowerLeftCorner.getX()+ this.width-1,this.lowerLeftCorner.getY()-1),
			    new Coord(this.lowerLeftCorner.getX()+1 , this.lowerLeftCorner.getY()-1),true).plot(pc);
	   
	   new Line(new Coord(this.lowerLeftCorner.getX()+1 , this.lowerLeftCorner.getY()-1),
			    new Coord(this.lowerLeftCorner.getX(), this.lowerLeftCorner.getY()),false).plot(pc);
	   
	   new Line(new Coord(this.lowerLeftCorner.getX()+1,this.lowerLeftCorner.getY()),
			   new Coord(((1/3) * this.width)+this.lowerLeftCorner.getX(),235),false).plot(pc);
	   
	   new Line( new Coord(((1/3) * this.width)+this.lowerLeftCorner.getX(),245),
			     new Coord(this.lowerLeftCorner.getX() + this.width-1, this.lowerLeftCorner.getY()),false).plot(pc);
	    
	}

	public Coord getLowerLeftCorner() {
		return this.lowerLeftCorner;
	}

	public void setLowerLeftCorner(Coord lowerLeftCorner) {
		this.lowerLeftCorner = lowerLeftCorner;
	}
	
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
