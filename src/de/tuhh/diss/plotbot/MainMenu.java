package de.tuhh.diss.plotbot;

import de.tuhh.diss.plotbot.shape.Ship;

import de.tuhh.diss.lejos.Coord;
import de.tuhh.diss.lejos.PlotbotControl;
import de.tuhh.diss.plotbot.shape.Rectangle;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class MainMenu {

	public static final String[] ITEMS = {"Rectangle", "Ship", "Calibration"};	
	public static final String TITLE = "Choose Shape to draw:";
	private TextMenu menu;

	public MainMenu() {
		menu = new TextMenu(ITEMS, 1, TITLE);
	}
	public void start() {
		int selection = -1;
		do {
			selection = menu.select();
		}while(selection < 0);

		
		while(Button.ENTER.isDown()) {
		}
	
		if (selection == 0) {
			int width = dimensionInput("Enter the width of the rectangle");
			int height = dimensionInput("Enter the height of the rectangle");
			Coord lowerLeftCorner = new Coord(0, 230 + 25 - height);
			Coord upperRightCorner = new Coord(width, 230 + 25);	
			new Rectangle(lowerLeftCorner, upperRightCorner);
		
		} else if (selection == 1) {
			int width = dimensionInput("Enter the width of the ship");
			int height = dimensionInput("Enter the height of the ship");
			Coord lowLeft = new Coord(0, 230 + 25 - height);
			new Ship(lowLeft, width);
		
		} else if (selection == 2) {
			PlotbotControl pc = new PlotbotControl();
			pc.calibration();
		}
	}
  public int dimensionInput(String s) {
	    int dimension = 0;
	    LCD.clear();
		LCD.drawString(s, 0, 0);
		while (!Button.ENTER.isDown()) {
			LCD.drawInt(dimension, 0, 1);
			if (Button.RIGHT.isDown()) {
				while(!Button.RIGHT.isUp()) {
				}
				dimension ++;
			} else if (Button.LEFT.isDown()) {
				while (!Button.LEFT.isUp()){
				}
				dimension -- ;
			}
		}
		while (!Button.ENTER.isUp()) {
		}
		return dimension;
  }
}
