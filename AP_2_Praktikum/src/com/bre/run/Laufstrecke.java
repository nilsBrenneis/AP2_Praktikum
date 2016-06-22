package com.bre.run;

public class Laufstrecke {

	private static final int HOUR = 60;

	private String trackName;
	private int lengthMtrs;
	private String start;
	private String end;
	private int timesRun;
	
	
	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public Laufstrecke(String trackName, int lengthMtrs, String start, String stop) {
		this.trackName = trackName;
		this.lengthMtrs = lengthMtrs;
		this.start = start;
		this.end = stop;
	}

	public void incrementTimesRun() {
		timesRun++;
	}

	public int getLength() {
		return lengthMtrs;
	}

	public int getTimesRun() {
		return timesRun;
	}

	public String getTrackName() {
		return trackName;
	}
	
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public boolean isCircuit() {
		if (start.equals(end)) {
			return true;
		} else {
			return false;
		}
	}

	public double getMetersPerHour(double timeRunMin) {
		double timeRunHrs = timeRunMin / HOUR;
		double mtrsPerHour = lengthMtrs / timeRunHrs;
		return mtrsPerHour;
	}

}
