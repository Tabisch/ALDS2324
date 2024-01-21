import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author Fabian Schlaghecken
 * @version 1.0
 * @since 23.12.2023
 * Berechnung des Maximalen Flusses in einem Graphen mit der Edmonds Karp-Methode.
 * Als Graph wird von der 'main()'-Methode eine Adjazenzmatrix übergeben.
 */
class EdmondsKarp
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

        public Kante(int vonKnoten, int nachKnoten, int kapazitaet)
        {
            this.vonKnoten = vonKnoten;
            this.nachKnoten = nachKnoten;
            this.kapazitaet = kapazitaet;
        }
        
		@Override
		public String toString()
		{
			return "Kante [vonKnoten=" + vonKnoten + ", nachKnoten=" + nachKnoten + ", kapazitaet=" + kapazitaet
					+ " l/min]";
		}
    }

	/**
     * Berechnung des Maximalen Flusses für den übergebenen Ausgangsgraphen
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
     * @param vorwaertskanten Array mit den Restkapazitäten auf den Vorärtskanten im Residualgraph
     * @param rueckwaertskanten Array mit dem Durchfluss auf den Rückwärtskanten im Residualgraph              
     * @return Gibt den berechneten, Maximalen Fluss des Graphen zurück
     */
    static int edmondsKarpMethode(int ausgangsgraph[][], int quelle, int senke)
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
     * In der Methode 'naechsterPfad()' wird der in der Methode 'breitensuche()'
     * gefundene Pfad ausgewertet, indem die maximal mögliche Kapazität ermittelt wird, sowie
     * die Vorwärtskanten und Rückwärtskanten im Residualgraph angepasst werden
     * @param gefundenerPfad Das Ergebnis der 'breitensuche()' wird als Array von Kanten abgebildet,
     *                       wobei der Index für den jeweiligen 'nachKnoten' der dort hinterlegten Kante steht
     * @param vorwaertskanten Array mit den Restkapazitäten auf den Vorärtskanten im Residualgraph
     * @param rueckwaertskanten Array mit dem Durchfluss auf den Rückwärtskanten im Residualgraph
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
     * @param maxPfadKapazitaet Maximaler Durchfluss für den aktuellen Pfad
     * @return Summe der jeweils durch die Rekursion ermittelten 'maxPfadKapazitaet' der einzelnen Pfade
     */
	static int naechsterPfad(int[][] vorwaertskanten, int[][] rueckwaertskanten, int quelle, int senke)
	{
		Kante[] gefundenerPfad = breitensuche(vorwaertskanten, rueckwaertskanten, quelle, senke);
		
		// Rekursionsanker:
		// Wenn keine Kante mit der Senke als 'nachKnoten' gefunden wurde, gibt es keinen vollständigen Pfad mehr
		if (gefundenerPfad[senke] == null)
			return 0;
		else
		{
			int maxPfadKapazitaet = maxKapazitaetErmitteln(vorwaertskanten, rueckwaertskanten, quelle, senke, gefundenerPfad);
			
			graphenAktualisieren(vorwaertskanten, rueckwaertskanten, quelle, senke, gefundenerPfad, maxPfadKapazitaet);
			
			// Rekursionsschritt
			return maxPfadKapazitaet + naechsterPfad(vorwaertskanten, rueckwaertskanten, quelle, senke);
		}
	}
	
	
	/**
	 * In dieser Methode wird der kürzeste Pfad von der Quelle zur Senke mit dem Breitensuchverfahren gebildet.
	 * Kürzeste Pfad bedeutet, dass von der 'quelle' zur 'senke' so wenig Knoten wie möglich besucht werden sollen
	 * @param quelle Index der Quelle im Residualgraph
	 * @param senke Index der Senke im Residualgraph
	 * @param gefundenerPfad Das Ergebnis der 'breitensuche()' wird als Array von Kanten abgebildet,
     *                       wobei der Index für den jeweiligen 'nachKnoten' der dort hinterlegten Kante steht
     * @param q Queue mit den "vonKnoten", die Ebene für Ebene von links nach rechts im Graph
     *          hinsichtlich möglicher 'nachKnoten' geprüft werden
     * @param besucht boolean-Array um Knoten zu markieren, die bereits besucht wurden
	 * @return gefundenerPfad Das Array mit den gefundenen Kanten wird zurückgegeben
	 */
	static Kante[] breitensuche(int[][] vorwaertskanten, int[][] rueckwaertskanten, int quelle, int senke)
	{
		Kante[] gefundenerPfad = new Kante[vorwaertskanten.length];
		
		Queue<Integer> q = new ArrayDeque<>(vorwaertskanten.length);
		
		boolean[] besucht = new boolean[vorwaertskanten.length];
		Arrays.fill(besucht, false);
		
		// Die Quelle als besucht markieren und der Queue als erste Station hinzufügen
		besucht[0]=true;
		q.offer(0);
		
		// Da die Queue nach und nach mit neuen, möglichen "nachKnoten" gefüllt wird,
		// ist die Schleife fertig, sobald keine Knoten mehr angesteuert werden können.
		while (!q.isEmpty())
		{
			int vonKnoten = q.poll();
			
			for (int nachKnoten=0; nachKnoten < vorwaertskanten.length; nachKnoten++)
			{
				if ((vorwaertskanten[vonKnoten][nachKnoten] > 0 || rueckwaertskanten[vonKnoten][nachKnoten] > 0)
						&& !besucht[nachKnoten])
				{
					gefundenerPfad[nachKnoten] = new Kante(vonKnoten, nachKnoten, vorwaertskanten[vonKnoten][nachKnoten]);
					
					// Wenn eine Kante zur Senke gefunden wurde,
					// ist der Pfad vollständig und kann zurückgegeben werden
					if (nachKnoten == senke)
						return gefundenerPfad;
					
					q.offer(nachKnoten);				
					besucht[nachKnoten] = true;
				}
			}
		}
		
		return gefundenerPfad;
	}
	

	/**
	 * Es wird die maximal mögliche Flusskapazität des Pfades ermittelt
	 * @param maxPfadKapazitaet Maximal mögliche Flusskapazität des Pfades
	 * @return die maximal mögliche Flusskapazität des Pfades
	 */
	private static int maxKapazitaetErmitteln(int[][] vorwaertskanten, int[][] rueckwaertskanten, int quelle, int senke, Kante[] gefundenePfade)
	{
		int maxPfadKapazitaet = Integer.MAX_VALUE;
		
		// Gefundenen Pfad ausgehend von der Senke zurückbilden
		// Dabei für die jeweiligen Kanten die maximal mögliche Kapazität ermitteln
		for (Kante k = gefundenePfade[senke]; k != null; k = gefundenePfade[k.vonKnoten])
		{
			// Wenn die Kapazität > 0 ist, wurde die Vorwärtskante der beiden Knoten verwendet
			if (k.kapazitaet > 0)
			{
				System.out.println(k.toString());
				
				if (k.kapazitaet < maxPfadKapazitaet)
					maxPfadKapazitaet=k.kapazitaet;
			}
			
			// Es wurde die Rückwärtskante der beiden Knoten verwendet
			else
			{
				System.out.println(k.toString()  + " => Residualkapazität verwendet: "+ rueckwaertskanten[k.vonKnoten][k.nachKnoten] + " l/min");
				
				if (rueckwaertskanten[k.vonKnoten][k.nachKnoten] < maxPfadKapazitaet)
					maxPfadKapazitaet=rueckwaertskanten[k.vonKnoten][k.nachKnoten];
			}
		}
		
		System.out.println("Maximaler Fluss des Pfades: " + maxPfadKapazitaet + " l/min");
		System.out.println("==========================================================\n");
		
		return maxPfadKapazitaet;
	}
	
	/**
	 * Nach einem gefundenen Pfad von der Quelle zur Senke, müssen die Kapazitäten auf den Vorwärtskanten
	 * und der Durchfluss auf den Rückwärtskanten aktualisiert werden
	 * @param maxPfadKapazitaet Zuvor ermittelte, maximal verfügbare Kapazität auf dem gefundenen Pfad
	 */
	private static void graphenAktualisieren(int[][] vorwaertskanten, int[][] rueckwaertskanten, int quelle, int senke, Kante[] gefundenePfade, int maxPfadKapazitaet)
	{		
		for (Kante k = gefundenePfade[senke]; k != null; k = gefundenePfade[k.vonKnoten])
		{
			// Wenn die Kapazität > 0 ist, wurde die Vorwärtskante der beiden Knoten verwendet
			if (k.kapazitaet > 0)
			{
				// Die Kapazität auf der Vorwärtskante verringert sich
				vorwaertskanten[k.vonKnoten][k.nachKnoten] -= maxPfadKapazitaet;
				
				// Der Durchfluss auf der Rückwärtskante erhöht sich
				rueckwaertskanten[k.nachKnoten][k.vonKnoten] += maxPfadKapazitaet;
			}
			
			// Es wurde die Rückwärtskante der beiden Knoten verwendet
			else
			{
				// Der Fluss wurde umgeleitet, wodurch wieder Kapazität
				// entsprechend der maxPfadKapazitaet auf der Vorwärtskante verfügbar wird
				vorwaertskanten[k.nachKnoten][k.vonKnoten] += maxPfadKapazitaet;
				
				// Durch die Umleitung des Flusses, wurde der Durchfluss auf der Rückwärtskante verringert
				rueckwaertskanten[k.vonKnoten][k.nachKnoten] -= maxPfadKapazitaet;				
			}
		}
	}
}