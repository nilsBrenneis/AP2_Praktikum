package com.bre.list;

import com.bre.run.Lauf;

public interface AbstractAnalyzer {
	/**
	 * Dieses Interface stellt eine analyze(...) Methode bereit, mit der eine
	 * beliebige Operation für alle Läufe ausgeführt wird. Implementierungen
	 * können z.B. zählen, wie oft eine bestimmte Strecke gelaufen wurde oder
	 * wie viele Meter insgesamt gelaufen wurden.
	 * 
	 * @param l
	 *            dieser Lauf soll in die Analyse
	 *
	 *            miteinbezogen werden. Was auch immer analyze(...) herausfinden
	 *
	 *            soll, dieser Lauf l soll mit in die Daten einfließen.
	 */
	public void analyze(Lauf l);
}
