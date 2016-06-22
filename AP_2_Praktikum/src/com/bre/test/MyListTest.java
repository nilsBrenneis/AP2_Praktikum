package com.bre.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.bre.list.DoubleMyList;
import com.bre.list.MyList;
import com.bre.run.Lauf;
import com.bre.run.Laufstrecke;

public class MyListTest {

	@Test
	public void isEmpty_Test() {
		MyList runs = new MyList();

		assertTrue(runs.isEmpty());

		runs.addLast(new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel")));
		assertFalse(runs.isEmpty());
	}

	@Test
	public void addFirst_getFirst_Test() {
		MyList runs = new MyList();

		try {
			runs.getFirst();
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		Lauf lOne = new Lauf(40, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel"));
		runs.addFirst(lOne);
		assertTrue(lOne == runs.getFirst());

		Lauf lTwo = new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei"));
		runs.addFirst(lTwo);
		assertTrue(lTwo.equals(runs.getFirst()));
	}

	@Test
	public void indexOf_Test() {
		MyList runs = new MyList();

		runs.addLast(new Lauf(45, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel")));

		runs.addLast(new Lauf(66, LocalDate.of(2016, 3, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel")));

		Lauf l = new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei"));
		runs.addLast(l);

		int j = runs.indexOf(l);
		assertTrue(j == 2);
	}

	@Test
	public void addLast_getLast_Test() {
		DoubleMyList runs = new DoubleMyList();

		try {
			runs.getLast();
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		Lauf lOne = new Lauf(55, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel"));
		runs.addLast(lOne);
		assertTrue(lOne.equals(runs.getLast()));

		Lauf lTwo = new Lauf(161, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei"));
		runs.addLast(lTwo);
		assertTrue(lTwo.equals(runs.getLast()));
	}

	@Test
	public void getSize_Test() {
		DoubleMyList runs = new DoubleMyList();

		assertTrue(0 == runs.size());

		runs.addFirst(new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel")));
		assertTrue(1 == runs.size());
		runs.addFirst(new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei")));
		assertTrue(2 == runs.size());

		runs.addLast(new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufDrei", 6000, "TestortStartDrei", "TestortZielDrei")));
		assertTrue(3 == runs.size());
	}

	@Test
	public void getLauf_Test() {
		MyList runs = new MyList();

		try {
			runs.get(-1);
			fail();
		} catch (NoSuchElementException e) {
			//TODO evtl. illargexc
			assertTrue(true);
		}

		try {
			runs.get(0);
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		try {
			runs.get(10);
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}

		Lauf lOne = new Lauf(30, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel"));
		runs.addLast(lOne);
		Lauf lTwo = new Lauf(161, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei"));
		runs.addLast(lTwo);

		Lauf lThree = new Lauf(60, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufDrei", 6000, "TestortStartDrei", "TestortZielDrei"));
		runs.addLast(lThree);

		assertTrue(lOne == runs.get(0));
		assertTrue(lTwo == runs.get(1));
		assertTrue(lThree == runs.get(2));
	}

	@Test
	public void getBestRun_Test() {
		MyList runs = new MyList();

		try {
			runs.getBestRun();
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}

		Lauf l = new Lauf(30, LocalDate.of(2016, 2, 22),
				new Laufstrecke("Testlauf", 5000, "TestortStart", "TestortZiel"));
		runs.addLast(l);
		runs.addLast(new Lauf(50, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufZwei", 3000, "TestortStartZwei", "TestortZielZwei")));
		runs.addLast(new Lauf(160, LocalDate.of(2016, 2, 22),
				new Laufstrecke("TestlaufDrei", 6000, "TestortStartDrei", "TestortZielDrei")));

		assertTrue(l == runs.getBestRun());
	}
}