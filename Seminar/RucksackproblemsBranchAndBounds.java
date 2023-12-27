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
        return this.name + " - weight: " + this.weight + " - profit: " + this.profit;
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

public class RucksackproblemsBranchAndBounds {

    static int bestValue;
    static List<CargoItem> bestSelection;

    public static void main(String[] args) {
        
        bestSelection = new ArrayList<CargoItem>();
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

        int availableValue = 0;
        for (CargoItem item : items) {
            availableValue += item.getProfit();
        }

        Collections.sort(items);

        knapsack(new ArrayList<CargoItem>(), 0, items, availableValue, 0, weightLimit);

        Collections.sort(bestSelection);

        int summeWeight = 0;
        int summeProfit = 0;

        Iterator<CargoItem> iterator = bestSelection.iterator();

        while (iterator.hasNext()) {
            CargoItem temp = iterator.next();

            System.out.println(temp.toString());
            summeProfit += temp.getProfit();
            summeWeight += temp.getWeight();
        }

        System.out.println("summeWeight: " + summeWeight);
        System.out.println("summeProfit: " + summeProfit);
    }

    static void knapsack(List<CargoItem> selectedItems, int selectedValue, List<CargoItem> availableItems, int availableValue, int currentWeight, int weightLimit) {
        
        if (availableItems.isEmpty()) // Rekusionsanker
        {
            // Testen, ob neue beste Kombination
            if (selectedValue > bestValue) {
                bestValue = selectedValue;
                bestSelection.clear();
                bestSelection.addAll(selectedItems);
            }

        } else {
            // Entfernen, für nächsten Rekursionsschritt
            CargoItem item = availableItems.remove(0);

            int weight = item.getWeight();
            int value = item.getProfit();
            
            // Bound: keine beste Kombination mehr möglich
            if (selectedValue + availableValue > bestValue) {

                // Test Gegenstand noch einfügbar ?
                if (currentWeight + weight <= weightLimit) {

                    selectedItems.add(item);
                    knapsack(selectedItems, selectedValue + value, availableItems, availableValue - value, currentWeight + weight, weightLimit);
                    selectedItems.remove(item);

                }

                knapsack(selectedItems, selectedValue, availableItems, availableValue - value, currentWeight, weightLimit);

            }

            // Backtracking
            availableItems.add(item);
        }
    }
}
