package com.bre.list;

import com.bre.run.Lauf;
import com.bre.run.Laufstrecke;

public class StreckenCounter implements AbstractAnalyzer {

	private int cnt;
	private Laufstrecke track;
	
	public StreckenCounter(Laufstrecke track){
		this.track = track;
		cnt = 0;
	}

	public int getTrackCounter(){
		return cnt;
	}
	
	@Override
	public void analyze(Lauf l) {
		if (l.getTrack() == track){
			cnt++;
		}
	}

}
