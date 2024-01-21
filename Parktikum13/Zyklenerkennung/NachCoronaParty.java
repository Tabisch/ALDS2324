
public class NachCoronaParty {
	public static void main(String args[]) {
		
        Graph g = new Graph(11);

        // 0 Erdarbeiten
        // 1 Bodenplatte gießen
        // 2 Rohbau erstellen
        // 3 Dachstuhl errichten und Dach eindecken
        // 4 Fußbodenheizung legen
        // 5 Estrich legen
        // 6 Fenster und elektrische Rollläden einbauen
        // 7 Sanitärarbeiten ausführen
        // 8 Elektroarbeiten ausführen
        // 9 Malerarbeiten durchführen
        //10 Möblieren

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(4, 5);
        g.addEdge(8, 4);
        g.addEdge(5, 8);
        g.addEdge(8, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 9);
        g.addEdge(9, 10);

        g.DFS(0);
    }
}
