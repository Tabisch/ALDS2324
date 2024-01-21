import java.util.*;

/**
 * @author Chantal Ewig
 * @version 1.0
 * @since 02.01.2024
 * Programm zur Ermittlung des k�rzesten Weges zwischen zwei Orten
 * Dijkstra Algorithmus
 */

public class DijkstraAlgorithmus {
	
	/**
	 * @param args Objekterstellung des vorgegebenen Graphen, der Knoten und der Nachbarknoten
	 */

	// Hauptmethode zur Demonstration des Dijkstra-Algorithmus
	public static void main(String[] args) {
		
		// Erstellen des Graphen und der Knoten
// Erstellen des Graphen und der Knoten
Node node0 = new Node("Seattle");
Node node1 = new Node("San Francisco");
Node node2 = new Node("Salt Lake City");
Node node3 = new Node("Minneapolis");
Node node4 = new Node("Omaha");
Node node5 = new Node("Los Angeles");
Node node6 = new Node("Phoenix");
Node node7 = new Node("El Paso");
Node node8 = new Node("Denver");
Node node9 = new Node("Kansas City");
Node node10 = new Node("Dallas");
Node node11 = new Node("Houston");
Node node12 = new Node("Memphis");
Node node13 = new Node("St.Louis");
Node node14 = new Node("Chicago");
Node node15 = new Node("New Orleans");
Node node16 = new Node("Indianapolis");
Node node17 = new Node("Louisville");
Node node18 = new Node("Birmingham");
Node node19 = new Node("Cleveland");
Node node20 = new Node("Buffalo");
Node node21 = new Node("Detroit");
Node node22 = new Node("Boston");
Node node23 = new Node("Miami");
Node node24 = new Node("New York");
Node node25 = new Node("Philadelphia");
Node node26 = new Node("Pittsburg");
Node node27 = new Node("Washington");

Map<Node, Map<Node, Integer>> graph = new HashMap<>();
// Hinzufügen der Kanten und Gewichte zum Graphen
graph.put(node0, Map.of(node1, 817, node2, 883, node3, 1641, node4, 1705));
graph.put(node1, Map.of(node0, 817, node5, 403, node2, 754));
graph.put(node2, Map.of(node0, 883, node8, 512, node1, 754, node5, 728, node6,651));
graph.put(node3, Map.of(node4, 373, node14, 411, node13, 559, node0, 1641));
graph.put(node4, Map.of(node8, 559, node9, 204, node3, 373, node14, 493));
graph.put(node5, Map.of(node1, 403, node2, 728, node6, 398));
graph.put(node6, Map.of(node5, 398, node2, 651, node7, 402, node8, 836));
graph.put(node7, Map.of(node6, 402, node8, 652, node10, 625, node11, 242));
graph.put(node8, Map.of(node2, 512, node7, 652, node4, 559, node9, 616, node10, 801, node6, 836));
graph.put(node9, Map.of(node8, 616, node4, 204, node10, 508, node12, 454, node13, 255));
graph.put(node10, Map.of(node11, 242, node7, 625, node12, 470, node9, 508));
graph.put(node11, Map.of(node15, 365, node10, 242, node7, 756));
graph.put(node12, Map.of(node13, 295, node9, 454, node10, 470, node15, 401, node18, 249, node17, 396));
graph.put(node13, Map.of(node3, 559, node9, 255, node12, 295, node14, 293, node16, 239, node17, 264));
graph.put(node14, Map.of(node3, 411, node21, 279, node19, 344, node13, 293, node16, 189, node4, 493));
graph.put(node15, Map.of(node11, 365, node18, 347, node23, 892, node12, 401));
graph.put(node16, Map.of(node14, 189, node13, 239, node17, 114, node19, 318, node21, 249));
graph.put(node18, Map.of(node12, 249, node15, 347, node23, 777, node17, 378, node27, 751));
graph.put(node17, Map.of(node16, 114, node13, 264, node12, 396, node18, 378, node26, 416, node27, 601));
graph.put(node21, Map.of(node14, 279, node19, 175, node20, 252, node16, 290));
graph.put(node19, Map.of(node21, 175, node14, 344, node26, 131, node16, 318, node20, 192));
graph.put(node20, Map.of(node19, 192, node26, 219, node24, 436, node22, 457, node21, 252, node27, 386, node25, 383));
graph.put(node22, Map.of(node24, 213, node20, 457));
graph.put(node23, Map.of(node27, 1101, node15, 892, node18, 777));
graph.put(node24, Map.of(node25, 93, node22, 213, node20, 436));
graph.put(node25, Map.of(node27, 140, node24, 93, node26, 304, node20, 383));
graph.put(node26, Map.of(node19, 131, node16, 360, node20, 219, node25, 304, node27, 241, node17, 416));
graph.put(node27, Map.of(node20, 386, node26, 241, node25, 140, node23, 1101, node17, 601, node18, 751));

		// Start- und Endknoten setzen
		Node startNode = node27;
		Node endNode = node0;

		int shortestDistance = dijkstra(graph, startNode, endNode);

		// Ausgabe 
		if (shortestDistance != -1) {
			System.out.println("Kürzeste Distanz von " + startNode.getName() + " zu " + endNode.getName() + ": "
					+ shortestDistance);
		} else {
			System.out.println("Es existiert kein Pfad von " + startNode.getName() + " zu " + endNode.getName());
		}
	}
	
	/**
	 * Berechnung des K�rzesten Weges vom Startknoten zum Zielknoten
	 * @param graph Mapliste des Graphen
	 * @param start Startknoten
	 * @param end Zielknoten
	 * @return Integer mit den Wert des k�rzesten Weges
	 */
	
	public static int dijkstra(Map<Node, Map<Node, Integer>> graph, Node start, Node end) {

		// Priorit�tswarteschlange f�r Auswahl des Knotens mit der geringsten Distanz
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getDistance()));
		start.setDistance(0);
		priorityQueue.add(start);

		// Haupt-Schleife 
		while (!priorityQueue.isEmpty()) {
			Node current = priorityQueue.poll();

			// Wenn der Endknoten erreicht wurde, breche den Algorithmus ab
			if (current.equals(end)) {
				return end.getDistance();
			}

			// Iteration �ber alle Nachbarn des aktuellen Knotens
			for (Map.Entry<Node, Integer> neighborEntry : graph.get(current).entrySet()) {
				Node neighbor = neighborEntry.getKey();
				int newDistance = current.getDistance() + neighborEntry.getValue();

				// Aktualisierung der Distanz, wenn k�rzerer Weg 
				if (newDistance < neighbor.getDistance()) {
					priorityQueue.remove(neighbor);
					neighbor.setDistance(newDistance);
					priorityQueue.add(neighbor);
				}
			}
		}

		// R�ckgabe von -1, wenn Endknoten nicht erreicht wurde
		return -1;
	}
}

// Ein Knoten im Graphen
class Node {
	/**
	 * Aus den Objekten Node werden die ben�tigten Knoten erstellt
	 * @param name Knotenbezeichnung
	 * @param distance Entfernung zum Startknoten
	 */
  private final String name;
  private int distance = Integer.MAX_VALUE; // Distanz des Knotens vom Startknoten

  public Node(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }
}
