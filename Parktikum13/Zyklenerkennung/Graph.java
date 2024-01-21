import java.util.*;

/**
 * Graph Klasse fuer das Untersuchen einen Gerichteten Graphen nach einem Zykel
 * @author andreasklischies
 *
 */

	class Graph {
		
	    private int V; // Anzahl der Knoten (Vertices)
	    private LinkedList<Integer> adjList[];

	    Graph(int v) {
	        V = v;
	        adjList = new LinkedList[v];
	        for (int i = 0; i < v; ++i)
	            adjList[i] = new LinkedList();
	    }

	    void addEdge(int v, int w) {
	        adjList[v].add(w);
	    }
	    
	   void DFS(int v) {
	        // Initialisierung eines boolean-Array, um den Besuchsstatus der Knoten zu verfolgen
	        boolean visited[] = new boolean[V];
	        
	        // Erstellung einer Liste, um den Pfad waehrend der Tiefensuche zu verfolgen
	        List<Integer> path = new ArrayList<>();
	        
	        // Startet die Tiefensuche vom gegebenen Startknoten aus
	        boolean hasCycle = DFSUtil(v, visited, path);

	        // Ueberpruefet, ob ein Zyklus gefunden wurde
	        if (!hasCycle) {
	            System.out.println("Kein Zyklus gefunden.");
	        }
	    }

	    private boolean DFSUtil(int v, boolean visited[], List<Integer> path) {
	        visited[v] = true;
	        path.add(v);

	        Iterator<Integer> i = adjList[v].listIterator();
	        while (i.hasNext()) {
	            int n = i.next();

	            if (path.contains(n)) {
	                // Zyklus gefunden, gebe den Pfad aus und beende die Rekursion
	                System.out.print("Zykel gefunden: ");
	                printCycle(path, n);
	                return true;
	            }

	            if (!visited[n]) {
	                if (DFSUtil(n, visited, path)) {
	                    // Zyklus gefunden, beende die Rekursion
	                    return true;
	                }
	            }
	        }
	        // Entferne den aktuellen Knoten aus dem Pfad
	        path.remove(path.size() - 1);

	        // Kein Zyklus gefunden
	        return false;
	    }
	    // Funktion zur Ausgabe der Knoten bis zum Zykel
	    private void printCycle(List<Integer> path, int end) {
	        int start = path.indexOf(end);
	        for (int i = start; i < path.size(); i++) {
	            System.out.print(path.get(i) + " ");
	        }
	        System.out.println();
	    }
}