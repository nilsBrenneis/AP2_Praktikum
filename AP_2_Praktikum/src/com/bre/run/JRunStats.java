package com.bre.run;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JRunStats extends JPanel {

	private final static int DATE_LOCATION_OFFSET = 20;
	private final static int DISTANCE_BAR = 35;
	private final static int HEIGHT_BAR = 30;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1347050978484481745L;

	private Lauf fastest;
	private Lauf slowest;
	private ArrayList<Lauf> runs = new ArrayList<Lauf>();

	public JRunStats(ArrayList<Lauf> runs, Lauf fastest, Lauf slowest) {
		this.runs = runs;
		this.fastest = fastest;
		this.slowest = slowest;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new JLabel("Rundenzeit"));
		System.out.println("showStats");

		

		int yCoordinate = 0;
		for (Lauf run : runs) {
			int time = run.getLaufzeit();
			String date = run.getDate().toString();
			if (run.equals(fastest)) {
				g.setColor(Color.GREEN);
			}
			if (run.equals(slowest)) {
				g.setColor(Color.RED);
			}
			// Balkendiagramm
			g.drawRect(0, yCoordinate, time, HEIGHT_BAR);
			// Datum
			g.drawString(date, 0, yCoordinate + DATE_LOCATION_OFFSET);
			// Minutenangabe
			g.drawString(time + " Minuten", time + 100, yCoordinate + DATE_LOCATION_OFFSET);
			yCoordinate += DISTANCE_BAR;
			g.setColor(Color.BLACK);
		}
	}
}
