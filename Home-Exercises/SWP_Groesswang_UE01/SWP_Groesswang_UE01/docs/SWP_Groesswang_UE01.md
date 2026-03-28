# SWP Groesswang UE01
Stundenaufwand: 

## Aufgabe 1
### Lösungsidee

Hallo wiedereinmal zu einer wunderschönen Programmierübung. Endlich diesmal mit einer angenehmeren Soprache als Java.
In dieser Aufgabe sollen wir die Übung 10 aus dem 2. Semester in Java umsetzten. Zumindest die Programmierteile.

Da der gesamte Code schon in C++ existiert, gibt es einen guten Ansatz für die Algorithmen.

In der Angabe wird ebenfalls der `DataCollector`erwähnt. Im Gegensatz zu der anderen Übung
wird dieser auch erweitert. 

Neben der schon exisiterenden `#actionsOnNumber` Methode gibt es noch eine Methode `#countedEqual`. 
Welche für die einzelnen Zeichenvergleiche verwendet werden. 

Neben dieser bestehenden Logik sollen ebenfalls noch folgende Operationen implementiert werden:
* Anzahl der durchgeführten additiven Operationen,
* Anzahl der durchgeführten multiplikativen Operationen,
* Anzahl der durchgeführten Indizierungen,
* Anzahl der durchgeführten Vergleiche,
* Anzahl der durchgeführten Zuweisungen.

Es gibt verschiedene Ansätze diese Funktionen zu implementieren. Hier wurde darauf entschieden, die Funktionen im ``DataCollector`` implementieren.
Der Vorgang ist hierbei den Rechenprozess als Funktion darzustellen und neben der eigentlichen Funktion wird mitgezählt.

Zum Beispiel wird bei ``#add(int a,int b)`` einfach die Zahlen zusammengezählt und retourniert. Gleichzeitig wird die Anzahl der additiven Operationen um 1 erhöht.
Gleiches bei ``#multiply(int a,int b)`` und ``#<E> assignment(E a)``. Assignment ist hierbei generisch Implementiert, um es für alle Typen zu erlauben. 

Für die Operationen ``+= ; ++`` wird hierbei ersetzt mit einen Aufruf von ``#assignment(add(a,1))`` als Beispiel für `a++`; 

*FunFact*: ``#multiyply(int a,int b)`` wurde nie verwendet, da in den Algorithmen keine Multiplikationen vorkommen.

Spannender wird es bei den Index-Operationen, da diese schwerer mit dieser Idee zum implementierten sind.
Hier wurde entschieden, die Lese und Schreib Operationen getrennt zu implementieren. Es gibt also eine Funktion ``#getIndex(E[] arr, int index)`` und eine Funktion ``#setIndex(E[] arr, int index, int value)``.
Neben einem Array gibt es noch andere Typen, wo eine Indexierung möglich ist. Für diese Typen gibt eine passende Überladung der Funktionen.

Um diese abstrakter zu implementieren, gibt es eine Überladung für List<E> und String. Es wurde auf List entscheiden, da die übergeordnete Klasse ``Collection``
keine Indexierung erhält. 

Für die Vergleiche gibt es die Methode ``#compareTerm(booelan a)`` wo man einfach nur den eigentlichen Vergleich als Parameter übergibt.

Angepasstes Verhalten der Searchmethoden:
-> Bei einer negativen Startposition wird die Suchmethode abgebrochen und -1 returniert. Da hier ein Verwendungsfehler vorliegt.
-> Wenn das Pattern länger als das String ist, wird die Suchmethode abgebrochen und -1 returniert.

Da nun alle FUnktionen implementiert sind, war der nächste Schritt die Algorithmen auf Java zu übersetzen und jegliche Operation durch die Funktionen des DataCollectors zu ersetzen.
Nach diesem Schritt war es nur noch nötig die Statistiken zu ermitteln und diese auf eine CSV auszugeben.

Danach war es Zeit, sich dem Testen zu widmen.

### Testfälle
Da die Algorithmen schon in C++ existieren, gab es schon eine gute Testsuite, die die Algorithmen testet.
Daher wurde hier nur kleine Unittests implementiert, und alle möglichen Operationen wurden getestet.

Die Unit-Tests sind in zwei Bereiche aufgeteilt: Suchalgorithmen und `DataCollector`.

**1) Tests der Suchalgorithmen (`BruteSearch`, `KnuthMorrisPrattSearch`, `BoyerMooreSearch`)**

- **Trefferfälle:** Es wird geprüft, ob bekannte Patterns an den erwarteten Positionen gefunden werden (`0`, `2`, `10`, `17`).
- **Treffer mit Startposition:** Mit einer Startposition (`5`) werden spätere Treffer korrekt gefunden (`10`, `17`).
- **Pattern nicht gefunden:** Rückgabe ist `-1`.
- **Leerer Text:** Rückgabe ist `-1`.
- **Pattern länger als Text:** Rückgabe ist `-1`.
- **Negative Startposition:**
  - `KnuthMorrisPrattSearch` liefert `-1`.
  - `BruteSearch` und `BoyerMooreSearch` werfen aktuell eine `StringIndexOutOfBoundsException`.

**2) Tests des `DataCollector`**

- **Zeichenvergleiche (`countedEqual`)**
  - Korrekte Anzahl der Vergleiche wird gezählt.
  - Korrekte/inkorrekte Vergleiche pro Zeichen werden richtig aggregiert.
- **Arithmetik und Zuweisung**
  - `add`, `multiply` und `assignment` liefern das erwartete Ergebnis.
  - Die jeweiligen Counter werden korrekt inkrementiert.
- **Vergleichsoperationen (`compareTerm`)**
  - Rückgabewert entspricht dem übergebenen Term.
  - Vergleichszähler werden korrekt erhöht.
- **Indexoperationen (`getIndex` fuer `List`, Array, `String`)**
  - Rückgabewerte sind korrekt.
  - Index-Counter wird korrekt inkrementiert.
- **Schreibzugriffe (`setIndexOfCollection`)**
  - Der Zielwert wird korrekt gesetzt.
  - Index- und Assignment-Counter werden korrekt inkrementiert.
- **Unveränderlichkeit der Statistikliste**
  - Die ueber `getCharComparison()` gelieferte Liste ist schreibgeschützt.

Damit decken die Tests sowohl normale Suchfälle als auch wichtige Negativ- und Randfälle ab und
prüfen zusätzlich die korrekte Statistik-Erfassung durch den `DataCollector`.

Da der Inhalt der Statistik überprüft wurde musste jetzt nur noch die CSV-Ausgabe überprüft ob diese Exact ausgeschrieben wurde. Dies wurde mit einer Konsolenausgabe überprüft.
Hier wurde ersichtlich das die Konsolen Ausgabe gleich der CSV-Ausgabe ist.

## Aufgabe 2
### Lösungsidee

### Testfälle


