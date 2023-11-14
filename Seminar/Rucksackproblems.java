package Seminar;

import java.util.Arrays;

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

public class Rucksackproblems {

    public static void main(String[] args) {
        CargoItem[] items = new CargoItem[] { 
            new CargoItem( 191, 201, "Objekt1"),
            new CargoItem( 239, 141, "Objekt2"),
            new CargoItem( 66, 50, "Objekt3"),
            new CargoItem( 249, 38, "Objekt4"),
            new CargoItem( 137, 79, "Objekt5"),
            new CargoItem( 54, 73, "Objekt6"),
            new CargoItem( 153, 232, "Objekt7"),
            new CargoItem( 148, 48, "Objekt8")
        };

        int weightLimit = 645;
        System.out.println(knapSack(weightLimit, items, 0));

        int summeWeight = 0;
        int summeProfit = 0;

        /* for (int i = 0; i < selected.length; i++) {
            if(selected[i]) {
                System.out.println(items[i].toString());
                summeWeight += items[i].getWeight();
                summeProfit += items[i].getProfit();
            }
        } */

        System.out.println("summeWeight: " + summeWeight);
        System.out.println("summeProfit: " + summeProfit);
    }
  
    static int knapSack(int weightLimit, CargoItem items[], int listPosition) 
    {
        // Rek Anker
        if (weightLimit == 0) {
            // System.out.println("WeightLimit reached");
            return 0;
        }

        if (listPosition == items.length) {
            //System.out.println("Reached end of list.");
            return 0;
        }

        System.out.println("Checking Item " + items[listPosition].getName());
        System.out.println("Remaining weight " + weightLimit);

        // wenn der Gegenstand schwerer ist als erlaubt, dann überspringe
        if (items[listPosition].getWeight() > weightLimit) {
            System.out.println("weightLimit: Item " + items[listPosition].getName() + " is to heavy.");
            System.out.println("weightLimit: Leaving Item " + items[listPosition].getName());
            return knapSack(weightLimit, items, listPosition + 1);
        }

        int includeItem = items[listPosition].getProfit() +  knapSack(weightLimit - items[listPosition].getWeight(), items, listPosition + 1);
        int skipItem = knapSack(weightLimit, items, listPosition + 1 );

        // wenn der Profit größer ist mit dem Gegenstand, dann schließe ihn mit ein, ansonsten überspringen
        if (includeItem > skipItem) {
            System.out.println("profit: Including Item " + items[listPosition].getName());
            return includeItem;
        }
        else {
            System.out.println("profit: Leaving Item " + items[listPosition].getName());
            return skipItem;
        }
    }
}