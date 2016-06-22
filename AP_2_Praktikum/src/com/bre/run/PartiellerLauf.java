package com.bre.run;

import java.time.LocalDate;

public class PartiellerLauf extends Lauf {

	private static final int RUNDE = 100;

	private int anteilGelaufenProzent;

	public PartiellerLauf(int timeRunMin, LocalDate date, Laufstrecke track) {
		super(timeRunMin, date, track);
	}

	public boolean minimumErreicht(int min) {
		if (anteilGelaufenProzent >= min) {
			return true;
		} else {
			return false;
		}
	}

	public int anzahlGanzeRunden() {
		return anteilGelaufenProzent / RUNDE;
	}
}
