package Seminar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        try {
			List<String> allLines = Files.readAllLines(Paths.get("input.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("BranchAndBoundsOutput.txt", true));

			for (String line : allLines) {
				String[] parts = line.split(",");

                bestSelection = new ArrayList<CargoItem>();
                bestValue = 0;

                int weightLimit = 6450;

                int availableValue = 0;
                for (CargoItem item : items) {
                    availableValue += item.getProfit();
                }

                items.add(new CargoItem(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),parts[2]));

                long start = System.currentTimeMillis();

                knapsack(new ArrayList<CargoItem>(), 0, items, availableValue, 0, weightLimit);

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

    static void knapsack(List<CargoItem> selectedItems, int selectedValue, List<CargoItem> availableItems, int availableValue, int currentWeight, int weightLimit) {

        if (availableItems.isEmpty()) // Rekusionsanker
        {
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
