package com.bre.run;
import java.time.LocalDate;

public class Lauf {
	private int timeRunMin;
	private LocalDate date;
	private Laufstrecke track;
	
	public Lauf(int timeRunMin, LocalDate date, Laufstrecke track) {
		this.timeRunMin = timeRunMin;
		this.date = date;
		this.track = track;
		track.incrementTimesRun();
	}
	
	public Lauf(int timeRunMin, int year, int month, int day, Laufstrecke track) {
		this.timeRunMin = timeRunMin;
		this.date = LocalDate.of(year, month, day);
		this.track = track;
		track.incrementTimesRun();
	}
	
	public int getLaufzeit() {
		return timeRunMin;
	}

	public LocalDate getDate() {
		return date;
	}

	public Laufstrecke getTrack() {
		return track;
	}
}
