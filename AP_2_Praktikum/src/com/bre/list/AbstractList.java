package com.bre.list;

import com.bre.run.Lauf;

public interface AbstractList {

	/**
	* Fügt den Lauf l am Anfang der Liste ein
	* @param l neuer Lauf für die Liste
	*/
	public void addFirst (Lauf l);
	/**
	* Fügt den Lauf am Ende der Liste ein
	* @param l neuer Lauf für die Liste
	*/
	public void addLast (Lauf l);
	/**
	* @return das erste Element der Liste
	*/
	public Lauf getFirst ();
	/**
	* @return das letzte Element der Liste
	*/
	public Lauf getLast ();
	/**
	* @param n legt den Index fest, z.B. Lauf an der Stelle 3
	* @return der Lauf an der Indexstelle n.
	*/
	public Lauf get (int n);
	/**
	* Diese Methode iteriert über alle Läufe und liefert den
	* Lauf mit der besten Geschwindigkeit zurück
	* @return Lauf mit der besten Geschwindigkeit
	*
	*/
	public Lauf getBestRun ();
	/**
	* @return true wenn die Liste leer ist
	*/
	public boolean isEmpty ();
	/**
	* @return Anzahl der Element in dieser Liste
	*/
	public int size();
	
	public void remove(int i); 
	
	public int indexOf(Lauf l);
	/**
	* Diese Methode soll eine for-each Schleife über alle Läufe
	* enthalten, um diese zu analysieren. Die konkrete Analyse ist über eine
	* Implementierung des Analyzer-Interfaces (siehe nächste Seite) festgelegt. Das
	* Analyzer-Interface stellt eine analyze (Lauf l) Methode Bereit. Diese Methode
	* soll für jeden Lauf aufgerufen werden, damit alle Läufe in die Analyse
	* einfließen.
	* @param a eine Implementierung des Analyzer
	*/
	public void analyzeRuns (AbstractAnalyzer a);
}
