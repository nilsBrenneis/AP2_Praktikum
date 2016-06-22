package com.bre.list;

import java.util.NoSuchElementException;

import com.bre.run.Lauf;

public class MyList implements AbstractList {

	private Node first = null;

	@Override
	public void addFirst(Lauf l) {
		first = new Node(l, first);
	}

	@Override
	public void addLast(Lauf l) {
		if (isEmpty()) {
			addFirst(l);
		} else {
			Node runPointer = first;
			while (runPointer.next != null) {
				runPointer = runPointer.next;
			}
			runPointer.next = new Node(l, null);
		}
	}

	@Override
	public Lauf getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.run;
	}

	@Override
	public Lauf getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node runPointer = first;
		while (runPointer.next != null) {
			runPointer = runPointer.next;
		}
		return runPointer.run;
	}

	@Override
	public Lauf get(int i) {
		if (i >= 0 && i < size()) {
			Node runPointer = first;
			for (int j = 0; j < i; j++) {
				runPointer = runPointer.next;
			}
			return runPointer.run;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Lauf getBestRun() {
		if (!isEmpty()) {
			Node runPointer = first;
			Lauf fastest = runPointer.run;
			int runtime = fastest.getLaufzeit();
			double metersPerHour = fastest.getTrack().getMetersPerHour(runtime);

			while (runPointer.next != null) {
				int laufzeitToCompare = runPointer.run.getLaufzeit();
				double metersPerHourToCompare = runPointer.run.getTrack().getMetersPerHour(laufzeitToCompare);

				if (metersPerHourToCompare > metersPerHour) {
					fastest = runPointer.run;
				}
				runPointer = runPointer.next;
			}
			return fastest;
		}
		throw new NullPointerException();
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (!isEmpty()) {
			Node runPointer = first;
			size++;
			while (runPointer.next != null) {
				runPointer = runPointer.next;
				size++;
			}
		}
		return size;
	}

	private class Node {

		private Lauf run;
		private Node next;

		public Node(Lauf run, Node next) {
			this.run = run;
			this.next = next;
		}
	}

	@Override
	public void analyzeRuns(AbstractAnalyzer a) {
		// TODO Klausur!
		for (int i = 0; i < size(); i++) {
			a.analyze(this.get(i));
		}

	}

	@Override
	public void remove(int i) {
		Node node = first;
		Node n = null;
		if (i > size() || i < 0 || first.run == null) {
			throw new NoSuchElementException();
		} else if (i == 0) {
			first = first.next;
		} else {
			for (int j = 0; j < i - 1; j++) {
				node = node.next;
			}
			node.next = node.next.next;
		}
	}
	

	@Override
	public int indexOf(Lauf l) {
		Node node = first;
		int i = 0;

		while (node.run != null) {
			if (node.run == l) {
				return i;
			} else {
				node = node.next;
				i++;
			}
		}
		throw new NoSuchElementException();
	}

	public boolean contains(String startPunkt) {
		Node runPointer = first;
		boolean found = false;

		while (runPointer.next != null) {
			if (runPointer.run.getTrack().getStart().equals(startPunkt)) {
				found = true;
				continue;
			}
		}
		return found;
	}
}
