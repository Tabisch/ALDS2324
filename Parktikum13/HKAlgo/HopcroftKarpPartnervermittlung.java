package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HopcroftKarpPartnervermittlung {
    // Konstanten für die Repräsentation von 'kein Knoten' und 'unendliche Distanz'
    private static final int NIL = 0;
    private static final int INF = Integer.MAX_VALUE;

    // Arrays für die Paarungen und Distanzen, sowie die Anzahl der Männer (m) und Frauen (n)
    private int[] pairU, pairV, dist;
    private int m, n;

    // Adjazenzliste zur Repräsentation des bipartiten Graphen
    private LinkedList<Integer>[] adj;

    // Konstruktor initialisiert die Datenstrukturen
    public HopcroftKarpPartnervermittlung(int m, int n) {
        this.m = m;
        this.n = n;
        pairU = new int[m + 1]; // Paarungen für Männer
        pairV = new int[n + 1]; // Paarungen für Frauen
        dist = new int[m + 1]; // Distanzen im Graphen
        adj = new LinkedList[m + 1]; // Adjazenzlisten für jeden Mann
        for (int i = 0; i <= m; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Fügt eine Kante zwischen einem Mann (u) und einer Frau (v) hinzu
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    // Breadth-First Search (BFS) zur Bestimmung der Distanzen im Graphen
    private boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 1; u <= m; u++) {
            if (pairU[u] == NIL) {
                dist[u] = 0;
                queue.add(u);
            } else {
                dist[u] = INF;
            }
        }
        dist[NIL] = INF;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (dist[u] < dist[NIL]) {
                for (int v : adj[u]) {
                    if (dist[pairV[v]] == INF) {
                        dist[pairV[v]] = dist[u] + 1;
                        queue.add(pairV[v]);
                    }
                }
            }
        }
        return dist[NIL] != INF;
    }

    // Depth-First Search (DFS) zur Suche nach augmentierenden Pfaden
    private boolean dfs(int u) {
        if (u != NIL) {
            for (int v : adj[u]) {
                if (dist[pairV[v]] == dist[u] + 1 && dfs(pairV[v])) {
                    pairV[v] = u;
                    pairU[u] = v;
                    return true;
                }
            }
            dist[u] = INF;
            return false;
        }
        return true;
    }

    // Hauptfunktion des Algorithmus zur Bestimmung des maximalen Matchings
    public int hopcroftKarp() {
        Arrays.fill(pairU, NIL);
        Arrays.fill(pairV, NIL);
        int matching = 0;

        // Solange BFS neue Ebenen findet, suche nach augmentierenden Pfaden mit DFS
        while (bfs()) {
            for (int u = 1; u <= m; u++) {
                if (pairU[u] == NIL && dfs(u)) {
                    matching++;
                }
            }
        }
        return matching;
    }

    // Hauptmethode zum Testen des Algorithmus
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Abfrage der Anzahl von Männern und Frauen
        System.out.print("Anzahl der Männer: ");
        int m = scanner.nextInt();
        System.out.print("Anzahl der Frauen: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Entfernt den verbleibenden Zeilenumbruch im Eingabestrom

        // Namen der Männer und Frauen generieren
        String[] menNames = new String[m];
        String[] womenNames = new String[n];
        for (int i = 0; i < m; i++) {
            menNames[i] = "Mann_" + (char) ('A' + i);
        }
        for (int i = 0; i < n; i++) {
            womenNames[i] = "Frau_" + (char) ('A' + i);
        }

        HopcroftKarpPartnervermittlung hk = new HopcroftKarpPartnervermittlung(m, n);

        // Abfrage der Sympathie-Werte und Hinzufügen von Kanten
        System.out.println("Geben Sie 'y' ein, wenn ein Paar zusammenpassen soll:");
        for (int u = 1; u <= m; u++) {
            for (int v = 1; v <= n; v++) {
                System.out.print("Passen " + menNames[u - 1] + " und " + womenNames[v - 1] + " zusammen? (y/n): ");
                String value = scanner.nextLine();
                if (value.equals("y")) {
                    hk.addEdge(u, v);
                }
            }
        }

        // Berechnung des maximalen Matchings und Ausgabe der Ergebnisse
        int matching = hk.hopcroftKarp();
        System.out.println("Maximales Matching: " + matching);
        for (int u = 1; u <= m; u++) {
            if (hk.pairU[u] != NIL) {
                System.out.println(menNames[u - 1] + " ist gepaart mit " + womenNames[hk.pairU[u] - 1]);
            }
        }
        scanner.close();
    }
}
