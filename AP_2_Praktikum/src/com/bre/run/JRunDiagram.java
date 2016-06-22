package com.bre.run;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import com.bre.list.MyList;

public class JRunDiagram extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8791914453587868785L;
	
	private MyList runs = new MyList();

	private final int MAX_DIM_Y = 380;
	private final int MAX_DIM_X = 0;
	private final int LENGTH_CO_SYS = 400;
	private final int HEIGHT_CO_SYS = 300;
	private final int OFFSET = 70;
	private final int RADIUS_CIRCLE = 10;

	private int countDotsCoSys = 4;
	private int xMaxValue = 10000;
	private int yMaxValue = 300;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintCoordinateSystem(g);
		for (int i = 0; i < runs.size(); i++) {
			int time = runs.get(i).getLaufzeit();
			int length = runs.get(i).getTrack().getLength();

			int xCoordinate = length / (xMaxValue / LENGTH_CO_SYS);

			double yMaxValueDoub = yMaxValue;
			double heightCoSysDoub = HEIGHT_CO_SYS;
			double yCoordianteDoub = time / (yMaxValueDoub / heightCoSysDoub);
			int yCoordinate = (int) yCoordianteDoub;
			yCoordinate = HEIGHT_CO_SYS - yCoordinate + OFFSET;
			g.fillOval(xCoordinate, yCoordinate, RADIUS_CIRCLE, RADIUS_CIRCLE);
		}
		g.setColor(Color.BLACK);
	}

	public void paintOval(int x, int y) {
		Graphics g = getGraphics();
		g.fillOval(x, y, RADIUS_CIRCLE, RADIUS_CIRCLE);
	}

	private void paintCoordinateSystem(Graphics g) {
		int xStepsize = LENGTH_CO_SYS / countDotsCoSys;
		int xStepsizeText = xMaxValue / countDotsCoSys;
		for (int i = 1; i <= countDotsCoSys; i++) {
			g.drawString(xStepsizeText + "m", xStepsize, MAX_DIM_Y);
			xStepsizeText += xStepsizeText / i;
			xStepsize += xStepsize / i;
		}

		int yStepsize = HEIGHT_CO_SYS / countDotsCoSys;
		int yStepsizeText = yMaxValue / countDotsCoSys;
		int text = yStepsizeText * countDotsCoSys;
		for (int i = 1; i <= countDotsCoSys; i++) {
			g.drawString(text + "min", MAX_DIM_X, yStepsize);
			yStepsize += yStepsize / i;
			text -= yStepsizeText;
		}
	}

	public int getCountDotsCoSys() {
		return countDotsCoSys;
	}

	public void setCountDotsCoSys(int countDotsCoSys) {
		this.countDotsCoSys = countDotsCoSys;
	}

	public int getxMaxValue() {
		return xMaxValue;
	}

	public void setxMaxValue(int xMaxValue) {
		this.xMaxValue = xMaxValue;
	}

	public int getyMaxValue() {
		return yMaxValue;
	}

	public void setyMaxValue(int yMaxValue) {
		this.yMaxValue = yMaxValue;
	}

	public int getMAX_DIM_Y() {
		return MAX_DIM_Y;
	}

	public int getMAX_DIM_X() {
		return MAX_DIM_X;
	}

	public int getLENGTH_CO_SYS() {
		return LENGTH_CO_SYS;
	}

	public int getHEIGHT_CO_SYS() {
		return HEIGHT_CO_SYS;
	}

	public int getOFFSET() {
		return OFFSET;
	}

	public int getRADIUS_CIRCLE() {
		return RADIUS_CIRCLE;
	}
	
	public MyList getRuns() {
		return runs;
	}
}
