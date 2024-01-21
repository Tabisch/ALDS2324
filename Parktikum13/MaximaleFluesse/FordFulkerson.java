import java.util.Arrays;
import java.util.Stack;

/**
 * @autor Fabian Schlaghecken
 * @version 1.0
 * @since 29.12.2023
 * Berechnung des Maximalen Flusses in einem Graphen mit der Ford Fulkerson-Methode,
 * bei Anwendung der Tiefensuche (depth-first search).
 * Als Graph wird von der 'main()'-Methode eine Adjazenzmatrix übergeben.
 */
class FordFulkerson
{
	
	/**
	 * Aus den Objekten der Klasse 'Kante' werden die
	 * augmentierenden Pfade im Graphen gebildet
	 * @param vonKnoten Quellknoten der Kante
	 * @param nachKnoten Zielknoten der Kante
	 * @param kapazitaet Maximal mögliche Kapazität auf der Vorwärtskante im Residualgraph
	 */
	static public class Kante
	{
		int vonKnoten;
		int nachKnoten;
		int kapazitaet;
				
		public Kante (int vonKnoten, int nachKnoten, int kapazitaet)
		{
			this.vonKnoten=vonKnoten;
			this.nachKnoten=nachKnoten;
			this.kapazitaet=kapazitaet;
		}

		@Override
		public String toString()
		{
			return "Kante [vonKnoten=" + vonKnoten + ", nachKnoten=" + nachKnoten + ", kapazitaet=" + kapazitaet
					+ " l/min]";
		}
	}
	
	/**
     * Berechnung des maximalen Flusses für den übergebenen Ausgangsgraphen
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
     * @param vorwaertskanten Array mit den Restkapazitäten auf den Vorärtskanten im Residualgraph
     * @param rueckwaertskanten Array mit dem Durchfluss auf den Rückwärtskanten im Residualgraph
     * @return Gibt den berechneten, Maximalen Fluss des Graphen zurück
     */
    static int fordFulkersonMethode(int ausgangsgraph[][], int quelle, int senke)
    {        
    	
    	// Übertragung der Kapazitäten der Vorwärtskanten aus der übergebenen Adjazenzmatrix
    	int[][] vorwaertskanten = new int[ausgangsgraph.length][ausgangsgraph[0].length];
    	for (int i = 0; i < ausgangsgraph.length; i++)
    	{
    		for (int j = 0; j < ausgangsgraph[i].length; j++)
            {
    			vorwaertskanten[i][j] = ausgangsgraph[i][j];
            }
        }
    	 
    	// Initialisierung der Rückwärtskanten des Residualgraphen mit 0 für jede Kante
    	int[][] rueckwaertskanten = new int[ausgangsgraph.length][ausgangsgraph[0].length];
		for (int i = 0; i < ausgangsgraph.length; i++)
		{
			for (int j = 0; j < ausgangsgraph[i].length; j++)
			{
				rueckwaertskanten[i][j] = 0;
			}
		}
    	        
        return naechsterPfad(vorwaertskanten, rueckwaertskanten, quelle, senke);
    }

    /**
     * In der Methode 'naechsterPfad()' wird der in der Methode 'tiefensuche()'
     * gefundene Pfad ausgewertet, indem die maximal mögliche Kapazität ermittelt wird, sowie
     * die Vorwärtskanten und Rückwärtskanten im Residualgraph angepasst werden
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
	 * @param besucht Boolean Array zur Markierung der besuchten Knoten
     * @param neuerPfad sammelt die möglichen Kanten in einem Stack<Kante>
     * @param maxPfadKapazitaet maximaler Fluss im aktuellen Pfad
     * @return Summe der jeweils durch die Rekursion ermittelten 'maxPfadKapazitaet' der einzelnen Pfade
     */
    private static int naechsterPfad(int[][] vorwaertskanten, int[][] rueckwaertskanten, int quelle, int senke)
    {
        boolean[] besucht = new boolean[vorwaertskanten.length];
		Arrays.fill(besucht, false);
        
    	Stack<Kante> neuerPfad = tiefensuche(vorwaertskanten, rueckwaertskanten, new Stack<Kante>(), besucht, quelle, senke, 0, 1);
    	
    	// Rekursionsanker
    	// Es ist kein weiterer Pfad mehr vorhanden
    	if(neuerPfad == null)
    		return 0;
    	else
    	{
    		int maxPfadKapazitaet = maxPfadKapazitaetErmittelnUndGraphenAktualisieren(vorwaertskanten, rueckwaertskanten, neuerPfad);
    		
    		// Rekursionsschritt
    		return maxPfadKapazitaet+naechsterPfad(vorwaertskanten, rueckwaertskanten, quelle, senke);
    	}
	}
	
	/**
	 * In dieser Methode wird mit dem Tiefensuchverfahren rekursiv die nächste Kante für den aktuellen Pfad ermittelt.
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
     * @param vorwaertskanten Array mit den Restkapazitäten auf den Vorärtskanten im Residualgraph
     * @param rueckwaertskanten Array mit dem Durchfluss auf den Rückwärtskanten im Residualgraph
	 * @param neuerPfad Stack<Kante> mit den aktuellen Kanten des augmentierenden Pfades
	 * @param besucht Boolean Array zur Markierung der besuchten Knoten
	 * @param vonKnoten Array-Position im Residualgraph, an welcher sich der Pfad aktuell befindet
	 * @param nachKnoten Array-Position im Residualgraph, welche angesteuert werden kann, um eine weitere Kante zu bilden
	 *                   (Bei Wert '-1' wurde kein gültiger zielknoten gefunden)
	 * @return Stack<Kante> mit einem vollständigen Pfad, oder null wenn kein Pfad mehr vorhanden ist
	 */
	private static Stack<Kante> tiefensuche(int[][] vorwaertskanten,int[][] rueckwaertskanten, Stack<Kante> neuerPfad, boolean[] besucht, int quelle, int senke, int vonKnoten, int nachKnoten)
	{
		// 1. Rekursionsanker
		// Der augmentierende Pfad ist vollständig,
		// da er an der Senke angekommen ist
		if (vonKnoten == senke)
		  return neuerPfad;
				
		besucht[vonKnoten]=true;
		
		nachKnoten = nachKnotenErmitteln(vorwaertskanten, rueckwaertskanten, vonKnoten, besucht);
		
		// Es ist kein 'nachKnoten' mehr vom 'vonKnoten' aus erreichbar
		if (nachKnoten == -1)
		{			
			// 2. Rekursionsanker
			// Wenn es sich bei dem aktuellen 'vonKnoten' um die 'quelle' handelt,
			// ist die Berechnung des maximalen Flusses abgeschlossen.
			// Es gibt keine möglichen Pfade mehr.
			if (vonKnoten == quelle)
				return null;
			
			// Rekursionsschritt
			// Das Programm befindet sich hier in einer Sackgasse
			// und muss an den zuvor besuchten 'vonKnoten' zurückspringen.
			// Daher, letzte Kante vom Stack 'neuerPfad' entfernen,
			// und an den 'vonKnoten' dieser Kante zurückspringen
			return tiefensuche(vorwaertskanten, rueckwaertskanten, neuerPfad, besucht, quelle, senke, neuerPfad.pop().vonKnoten, 0);
		}
		
		neuerPfad.add(new Kante (vonKnoten, nachKnoten, vorwaertskanten[vonKnoten][nachKnoten]));
		
		// Rekursionsschritt
		// Nächste Kante für den aktuellen Pfad suchen
		// Dafür wird als nächster 'vonKnoten' der 'nachKnoten' der aktuell ermittelten Kante verwendet
		return tiefensuche(vorwaertskanten, rueckwaertskanten, neuerPfad, besucht, quelle, senke, nachKnoten, 0);
		
	}
		
	/**
	 * Ermittelt den 'nachKnoten' für den aktuellen 'vonKnoten'
     * @param vorwaertskanten Array mit den Restkapazitäten auf den Vorärtskanten im Residualgraph
     * @param rueckwaertskanten Array mit dem Durchfluss auf den Rückwärtskanten im Residualgraph
	 * @param vonKnoten Array-Position im Residualgraph, an welcher sich der Pfad aktuell befindet
	 * @param besucht Boolean Array zur Markierung der besuchten Knoten
	 * @return den 'nachKnoten', um die nächste Kante zu bilden, oder -1 wenn kein geeigneter 'nachKnoten' gefunden wurde
	 */
	private static int nachKnotenErmitteln(int[][] vorwaertskanten, int[][] rueckwaertskanten, int vonKnoten, boolean[] besucht)
	{
		for (int nachKnoten=0; nachKnoten<vorwaertskanten.length; nachKnoten++)
		{
			if ((vorwaertskanten[vonKnoten][nachKnoten] > 0 || rueckwaertskanten[vonKnoten][nachKnoten] > 0)
					&& !besucht[nachKnoten])
				return nachKnoten;
		}
		
		return -1;
	}

	/**
	 * Diese Methode ermittelt den maximalen Durchfluss im aktuellen Pfad.
	 * Zudem werden die Kapazitäten im Array 'vorwaertskanten' und Flüsse
	 * im Array 'rueckwaertskanten' für die Kanten des Pfades aktualisiert.
	 * @param maxPfadKapazitaet Maximal mögliche Flusskapazität des Pfades
	 * @param neuerPfad Stack<Kante> des aktuellen Pfades
	 * @param tempPfad Kopie von 'neuerPfad', mit diesem Stack werden die Kanten aktualisiert
	 * @return maxPfadKapazität
	 */
	private static int maxPfadKapazitaetErmittelnUndGraphenAktualisieren(int[][] vorwaertskanten, int[][]rueckwaertskanten, Stack<Kante> neuerPfad)
	{
		int maxPfadKapazitaet = Integer.MAX_VALUE;
		
		Stack<Kante> tempPfad = new Stack<Kante>();
		
		while(!neuerPfad.empty())
		{

			Kante temp = neuerPfad.pop();
			
			tempPfad.add(temp);	
			
			// Wenn die Kapazität > 0 ist, wurde die Vorwärtskante der beiden Knoten verwendet
			if (temp.kapazitaet > 0)
			{
				System.out.println(temp.toString());
				
				if (temp.kapazitaet < maxPfadKapazitaet)
					maxPfadKapazitaet=temp.kapazitaet;
			}
			
			// Es wurde die Rückwärtskante der beiden Knoten verwendet
			else
			{
				System.out.println(temp.toString()  + " => Residualkapazität verwendet: "+ rueckwaertskanten[temp.vonKnoten][temp.nachKnoten] + " l/min");
				
				if (rueckwaertskanten[temp.vonKnoten][temp.nachKnoten] < maxPfadKapazitaet)
					maxPfadKapazitaet=rueckwaertskanten[temp.vonKnoten][temp.nachKnoten];
			}
		}
		
		// Ermittelte 'maxPfadKapazitaet' für die einzelnen Kanten aktualisieren
		for (Kante kante : tempPfad)
		{
			
			if (kante.kapazitaet > 0)
			{
				// Die Kapazität auf der Vorwärtskante verringert sich
				vorwaertskanten[kante.vonKnoten][kante.nachKnoten] -= maxPfadKapazitaet;
				
				// Der Durchfluss auf der Rückwärtskante erhöht sich
				rueckwaertskanten[kante.nachKnoten][kante.vonKnoten] += maxPfadKapazitaet;
			}
			
			// Es wurde die Rückwärtskante der beiden Knoten verwendet
			else
			{
				// Der Fluss wurde umgeleitet, wodurch wieder Kapazität
				// entsprechend der maxPfadKapazitaet auf der Vorwärtskante verfügbar wird
				vorwaertskanten[kante.nachKnoten][kante.vonKnoten] += maxPfadKapazitaet;
				
				// Durch die Umleitung des Flusses, wurde der Durchfluss auf der Rückwärtskante verringert
				rueckwaertskanten[kante.vonKnoten][kante.nachKnoten] -= maxPfadKapazitaet;	
			}			
		}
		
		System.out.println("Maximaler Fluss des Pfades: " + maxPfadKapazitaet + " l/min");
		System.out.println("==========================================================\n");
		
		return maxPfadKapazitaet;
	}
}
    
