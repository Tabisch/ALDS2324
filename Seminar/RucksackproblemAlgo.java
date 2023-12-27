package Seminar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
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

public class RucksackproblemAlgo {

    public static void main(String[] args) {

        List<CargoItem> items = new ArrayList<CargoItem>();

        items.add(new CargoItem(191, 201, "Objekt1"));
        items.add(new CargoItem(239, 141, "Objekt2"));
        items.add(new CargoItem(66, 50, "Objekt3"));
        items.add(new CargoItem(249, 38, "Objekt4"));
        items.add(new CargoItem(137, 79, "Objekt5"));
        items.add(new CargoItem(54, 73, "Objekt6"));
        items.add(new CargoItem(153, 232, "Objekt7"));
        items.add(new CargoItem(148, 48, "Objekt8"));

        int weightLimit = 645;

        CargoItem[] knapsack = knapsack(weightLimit, items.toArray(new CargoItem[items.size()]));

        int summeWeight = 0;
        int summeProfit = 0;

        for (int i = 0; i < knapsack.length; i++) {
            System.out.println(knapsack[i].toString());
            summeProfit += knapsack[i].getProfit();
            summeWeight += knapsack[i].getWeight();
        }

        System.out.println("summeWeight: " + summeWeight);
        System.out.println("summeProfit: " + summeProfit);
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
