package Seminar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class CargoItem implements Comparable<CargoItem> {

    int weight, profit;
    String name;

    public CargoItem(int weight, int profit, String name) {
        this.weight = weight;
        this.profit = profit;
        this.name = name;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getProfit() {
        return this.profit;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + " - weight: " + this.weight + " - profit: " + this.profit + " - density: " + (this.profit * 1.0 /this.weight);
    }

    @Override
    public int compareTo(CargoItem o) {
        if (o.weight == this.weight) {
            return 0;
        }

        if (o.weight < this.weight) {
            return 1;
        }

        return -1;
    }
}

public class RucksackproblemAlgoTiming {

    public static void main(String[] args) {

        int weightLimit = 64500;
        List<CargoItem> items = new ArrayList<CargoItem>();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("AlgoOutput250000.txt", true));
            
			List<String> allLines = Files.readAllLines(Paths.get("input250000.csv"));

			for (String line : allLines) {
				String[] parts = line.split(",");

                items.add(new CargoItem(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),parts[2]));

                long start = System.currentTimeMillis();

                knapsack(weightLimit, items.toArray(new CargoItem[items.size()]));

                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;

                System.out.println(parts[2] + ": " + timeElapsed);
                writer.write(parts[2] + ": " + timeElapsed);
                writer.newLine();
			}

            writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static CargoItem[] knapsack(int capacity, CargoItem[] items) {

        // Nach Gewicht sortieren
        Arrays.sort(items);

        // Gewicht/Wert Tabelle erstellen
        int[][] DP = new int[items.length + 1][capacity + 1];

        // Gegenstände iterieren
        for (int i = 1; i <= items.length; i++) {
            // Gewicht iterieren
            for (int weightCounter = 1; weightCounter <= capacity; weightCounter++) {

                // Vorherigen Wert übernehmen
                DP[i][weightCounter] = DP[i - 1][weightCounter];

                    // Testen Gewicht unter Limit
                if (weightCounter >= items[i - 1].getWeight() &&
                    // Testen, ob Ergebnis besser ohne verherigen gegenstand
                    DP[i - 1][weightCounter - items[i - 1].getWeight()] + items[i - 1].getProfit() > DP[i][weightCounter]) 
                {
                    DP[i][weightCounter] = DP[i - 1][weightCounter - items[i - 1].getWeight()] + items[i - 1].getProfit();
                }
            }
        }

        int weightCountDown = capacity;
        List<CargoItem> itemsSelected = new ArrayList<CargoItem>();

        for (int i = items.length; i > 0; i--) {
            if (DP[i][weightCountDown] != DP[i - 1][weightCountDown]) {
                CargoItem itemIndex = items[i - 1];
                itemsSelected.add(itemIndex);
                weightCountDown -= items[i - 1].getWeight();
            }
        }

        return itemsSelected.toArray(new CargoItem[itemsSelected.size()]);
    }
}
