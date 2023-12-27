package Seminar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.SysexMessage;

// Algorithmus von Nemhauser und Ullmann“

class CargoItem {
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
}

public class RucksackproblemsBruteForce {

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

        knapsack(new ArrayList<CargoItem>(), 0, items, availableValue, 0, weightLimit);

        int summeWeight = 0;
        int summeProfit = 0;

        Iterator<CargoItem> iterator = bestSelection.iterator();

        while (iterator.hasNext()) {
            CargoItem temp = iterator.next();

            System.out.println(temp.getName());
            summeProfit += temp.getProfit();
            summeWeight += temp.getWeight();
        }

        System.out.println("summeWeight: " + summeWeight);
        System.out.println("summeProfit: " + summeProfit);
    }

    static void knapsack(List<CargoItem> selectedItems, int selectedValue, List<CargoItem> availableItems,
            int availableValue, int currentWeight, int weightLimit) {

        if (availableItems.isEmpty()) // Rekusionsanker
        {
            System.out.println(currentWeight + "," + selectedValue);
            if (currentWeight <= weightLimit && selectedValue > bestValue) {
                bestValue = selectedValue;
                bestSelection.clear();
                bestSelection.addAll(selectedItems);
            }

        } else {

            CargoItem item = availableItems.remove(0);

            int weight = item.getWeight();
            int value = item.getProfit();

            knapsack(selectedItems, selectedValue, availableItems, availableValue, currentWeight, weightLimit);

            selectedItems.add(item);
            knapsack(selectedItems, selectedValue + value, availableItems, availableValue - value, currentWeight + weight, weightLimit);
            selectedItems.remove(item);

            availableItems.add(item);
        }
    }
}
