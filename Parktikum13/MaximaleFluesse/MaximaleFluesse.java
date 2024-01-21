/**
 * @author Fabian Schlaghecken
 * @version 1.0
 * @since 29.12.2023
 * Programm zur Ermittlung des Maximalen Flusses
 * Variante 1: Algorithmus nach Ford Fulkerson
 * Variante 2: Algorithmus nach Edmonds-Karp
 */

public class MaximaleFluesse
{
	 
	/**
	 * @param graph Ausgehende Adjazenzmatrix zur Darstellung des Graphen
	 */
    public static void main(String[] args)
    {
        int graph[][] = new int[][]
        {		  // 0 (Quelle),  1,  2,  3,  4,  5,  6,  7 (Senke)
                             {0, 21, 20,  0,  0,  0,  0,  0}, // 0 (Quelle)
                             {0,  0,  9,  0,  7,  0,  0,  0}, // 1
                             {0,  0,  0,  8,  0, 15,  0,  0}, // 2
                             {0,  0,  0,  0,  5,  0,  0,  0}, // 3
                             {0,  0,  0,  0,  0,  0, 11,  0}, // 4
                             {0,  0,  0,  0,  0,  0,  4, 10}, // 5
                             {0,  0,  0,  0,  0,  0,  0, 23}, // 6
                             {0,  0,  0,  0,  0,  0,  0,  0}, // 7 (Senke)
                             
                             // Knoten 0: Wasser
                             // Knoten 1: Rathaus
                             // Knoten 4: Kino
                             // Knoten 6: Schule
                             // Knoten 5: Hotel
                             // Knoten 7: Zentrum
        };
        
        // Weiterer Graph, zum Testen der Algorithmen
        /*
        int graph[][] = new int[][]
        { // 0,   1,   2,   3
         	{0, 100, 100,   0}, // 0
        	{0,   0,   1, 100}, // 1
        	{0,   0,   0, 100}, // 2
        	{0,   0,   0,   0}, // 3
        };
		*/

        // Ford Fulkerson Algorithmus
        long start_fordFulkerson = System.currentTimeMillis();
        System.out.println("##########################################################");
        System.out.println("#               Ford Fulkerson Algorithmus               #");
        System.out.println("##########################################################");
        
											// Adjazenzmatrix, Quelle, Senke)     
        int maxFluss = FordFulkerson.fordFulkersonMethode(graph, 0, 7) * 100;
        System.out.println("Der maximale Fluss beträgt: " + maxFluss + " l/min\n");
        
        System.out.println("Benötigte Zeit für den Ford Fulkerson Algorithmus: " + (System.currentTimeMillis()-start_fordFulkerson) + " Millisekunden\n");
        System.out.println("##########################################################################################\n\n\n");

        // Edmonds Karp Algorithmus
        long start_edmondsKarpMethode = System.currentTimeMillis();
        System.out.println("##########################################################");
        System.out.println("#                Edmond Karp Algorithmus                 #");
        System.out.println("##########################################################");
		
        								// Adjazenzmatrix, Quelle, Senke)       
        maxFluss = EdmondsKarp.edmondsKarpMethode(graph, 0, 7) * 100;
        System.out.println("Der maximale Fluss beträgt: " + maxFluss + " l/min\n");
        System.out.println("Benötigte Zeit für den Edmond Karp Algorithmus: " + (System.currentTimeMillis()-start_edmondsKarpMethode) + " Millisekunden");
        System.out.println("##########################################################################################\n\n");
        
        if (maxFluss < 1700)
        	System.out.println("Das Wasser reicht nicht aus!\nEs sind weniger als die benötigten 1700 l/min verfügbar!");
        else
        {
        	System.out.println("Es ist genügend Löschwasser verfügbar und");     
        	
        	if (maxFluss < 3000)
        	{
        		System.out.println("es kann ein weiteres Stockwerk angebaut werden!");
        	}
        	else
        	{
        		System.out.println("laut Löschwasserbedarfstabelle können beliebig viele Stockwerke angebaut werden!");
        	}
        }

    }
}
