package com.bre.run;

import java.time.LocalDate;
import java.util.ArrayList;
import com.bre.list.MyList;

public class Runner {

	private static ArrayList<Laufstrecke> allTracks;
	private static MyList runs;
//	private static Scanner scanner;

	public static ArrayList<Laufstrecke> getAllTracks() {
		return allTracks;
	}
	
	public static String[] getTrackNameArray() {
		String[] tracks = new String[allTracks.size()];
		for (int i = 0; i < allTracks.size(); i++) {
			tracks[i] = allTracks.get(i).getTrackName();
		}
		return tracks;
	}

	private static void printConsoleOutput(Runner runner) {
		Lauf fastest = runner.getFastestRun();
		Laufstrecke mostUsedTrack = runner.getMostUsedTrack();
		int metersRun = runner.getMetersRun();

		System.out.println("Schnellster Lauf: " + fastest.getTrack().getTrackName() + "\tDatum: " + fastest.getDate());
		System.out.println("Meistgenutze Laufstrecke: " + mostUsedTrack.getTrackName());
		System.out.println("Insgesamt gelaufene Meter: " + metersRun);
	}

	public static void interpretConsoleInput(String input) {
		if (input.indexOf("Add") == 0) {
			int indexTrackName = 1;
			int indexTimeRun = 2;
			String[] splitInput = input.split(" ");

			String trackName = splitInput[indexTrackName];
			String timeRunStr = splitInput[indexTimeRun];

			addRun(trackName, timeRunStr);
		} else if (input.indexOf("Get Min") == 0) {
			printShortestTrack();
		} else if (input.indexOf("Get Max") == 0) {
			printLongestTrack();
		} else if (input.contains("Rename")) {
			int indexOldName = 1;
			int indexNewName = 2;
			String[] splitInput = input.split(" ");

			String oldName = splitInput[indexOldName];
			String newName = splitInput[indexNewName];
			renameTrack(oldName, newName);
		}
	}

	public static Laufstrecke getTrackByName(String name) {
		for (int i = 0; i < allTracks.size(); i++) {
			if (allTracks.get(i).getTrackName().equals(name)) {
				return allTracks.get(i);
			}
		}
		System.out.println("Laufstrecke nicht gefunden.");
		return null;
	}

	public static void addRun(String trackName, String timeRunStr) {
		int timeRun = Integer.parseInt(timeRunStr);
		Laufstrecke track = getTrackByName(trackName);
		if (track != null) {
			runs.addLast(new Lauf(timeRun, LocalDate.now(), track));
			int indexLastAddedRun = runs.size() - 1;
			String name = runs.get(indexLastAddedRun).getTrack().getTrackName();
			String date = runs.get(indexLastAddedRun).getDate().toString();
			int time = runs.get(indexLastAddedRun).getLaufzeit();
			System.out.println("Neuen " + name + " hinzugefügt. Datum: " + date + " Laufzeit: " + time);
		}
	}

	public Lauf getFastestRun() {
		Lauf fastest = runs.get(0);
		int laufzeit = fastest.getLaufzeit();
		double metersPerHour = fastest.getTrack().getMetersPerHour(laufzeit);

		for (int i = 1; i < runs.size(); i++) {
			int laufzeitToCompare = runs.get(i).getLaufzeit();
			double metersPerHourToCompare = runs.get(i).getTrack().getMetersPerHour(laufzeitToCompare);

			if (metersPerHourToCompare > metersPerHour) {
				fastest = runs.get(i);
			}
		}
		return fastest;
	}

	public static void printShortestTrack() {
		Laufstrecke shortestTrack = allTracks.get(0);
		for (int i = 1; i < allTracks.size(); i++) {
			int trackToCompare = allTracks.get(i).getLength();
			int track = shortestTrack.getLength();
			if (track > trackToCompare) {
				shortestTrack = allTracks.get(i);
			}
		}
		System.out.println("Kürzeste Strecke: " + shortestTrack.getTrackName());
	}

	public static void printLongestTrack() {
		Laufstrecke longestTrack = allTracks.get(0);
		for (int i = 1; i < allTracks.size(); i++) {
			int trackToCompare = allTracks.get(i).getLength();
			int track = longestTrack.getLength();
			if (track < trackToCompare) {
				longestTrack = allTracks.get(i);
			}
		}
		System.out.println("Längste Strecke: " + longestTrack.getTrackName());
	}

	public static void renameTrack(String oldName, String newName) {

		for (Laufstrecke track : allTracks) {
			if (track.getTrackName().indexOf(oldName) == 0) {
				track.setTrackName(newName);
				System.out.println("Strecke von: \"" + oldName + "\" in: \"" + newName + "\" umbenannt");
				break;
			}
		}
	}

	public int getMetersRun() {
		int metersRun = 0;
		for (int i = 0; i < runs.size(); i++) {
			int trackLength = runs.get(i).getTrack().getLength();
			metersRun += trackLength;
		}
		return metersRun;
	}

	public Laufstrecke getMostUsedTrack() {
		Laufstrecke mostUsed = runs.get(0).getTrack();
		for (int i = 1; i < runs.size(); i++) {
			Laufstrecke trackToCompare = runs.get(i).getTrack();
			if (mostUsed.getTimesRun() < trackToCompare.getTimesRun()) {
				mostUsed = trackToCompare;
			}
		}
		return mostUsed;
	}
	
	private static void initTracks() {
		allTracks.add(new Laufstrecke("Feldlauf", 6000, "Wohnung", "Rheinauhafen"));
		allTracks.add(new Laufstrecke("Waldlauf", 5000, "Arbeit", "Supermarkt"));
		allTracks.add(new Laufstrecke("Wiesenlauf", 10000, "Tor", "Tor"));
	}

	public static void main(String[] args) {
//		scanner = new Scanner(System.in);
		allTracks = new ArrayList<Laufstrecke>();
		Runner runner = new Runner();
//		String consoleInput;

		initTracks();
		JRunningAppFrame raf = new JRunningAppFrame();
		runs = raf.getDiagram().getRuns();

//		do {
//			consoleInput = scanner.nextLine();
//			interpretConsoleInput(consoleInput);
//		} while (!consoleInput.equals("Exit"));
//
//		for (Lauf ar : allRuns) {
//			System.out.println(ar.getDate() + " ein " + ar.getTrack().getTrackName());
//		}
		printConsoleOutput(runner);
	}
}
