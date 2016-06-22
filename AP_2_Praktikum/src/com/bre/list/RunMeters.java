package com.bre.list;

import com.bre.run.Lauf;

public class RunMeters implements AbstractAnalyzer {

	private double sum;

	public RunMeters() {
		sum = 0;
	}

	public double getRunMeters() {
		return sum;
	}

	@Override
	public void analyze(Lauf l) {
		sum += l.getTrack().getLength();
	}

}
