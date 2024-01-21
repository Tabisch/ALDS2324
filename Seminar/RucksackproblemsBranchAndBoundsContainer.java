package Seminar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class CargoItem implements Comparable<CargoItem> {

    int weight, profit;
    String name;

    public CargoItem(double weight, int profit, String name) {
        this.weight = (int)(weight * 1000);
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

public class RucksackproblemsBranchAndBoundsContainer {

    static int bestValue;
    static List<CargoItem> bestSelection;

    public static void main(String[] args) {
        
        bestSelection = new ArrayList<CargoItem>();
        List<CargoItem> items = new ArrayList<CargoItem>();

        items.add(new CargoItem(1.6, 668, "2-Sitzer Sofa"));
        items.add(new CargoItem(0.312, 186, "Beistelltisch"));
        items.add(new CargoItem(5.635, 770, "Buffetschrank"));
        items.add(new CargoItem(1.728, 702, "Ecksofa"));
        items.add(new CargoItem(0.125, 106, "Stereoanlage"));
        items.add(new CargoItem(0.154, 711, "Fernseher"));
        items.add(new CargoItem(0.728, 637, "Lowboard"));
        items.add(new CargoItem(11.52, 510, "Flügel"));
        items.add(new CargoItem(0.27, 646, "Gemälde"));
        items.add(new CargoItem(1.848, 968, "Bücherregal"));
        items.add(new CargoItem(1.5, 396, "Ohrensessel"));
        items.add(new CargoItem(1.625, 688, "Sideboard"));
        items.add(new CargoItem(0.32, 827, "Standuhr"));
        items.add(new CargoItem(0.162, 475, "Stehlampe"));
        items.add(new CargoItem(0.4, 411, "Teppich"));
        items.add(new CargoItem(3.468, 605, "Vitrine"));
        items.add(new CargoItem(3.85, 742, "Wohnwand"));
        items.add(new CargoItem(0.756, 617, "Wohnzimmertisch"));
        items.add(new CargoItem(0.08, 870, "Abfalleimer"));
        items.add(new CargoItem(0.288, 605, "Backofen"));
        items.add(new CargoItem(0.12, 679, "Barhocker"));
        items.add(new CargoItem(0.024, 484, "Bestecksatz"));
        items.add(new CargoItem(0.665, 686, "Eckbank"));
        items.add(new CargoItem(0.128, 807, "Geschirr"));
        items.add(new CargoItem(0.288, 852, "Geschirrspülmaschine"));
        items.add(new CargoItem(0.84, 832, "Esstisch"));
        items.add(new CargoItem(0.25, 409, "Hocker"));
        items.add(new CargoItem(0.192, 660, "Hängeschrank"));
        items.add(new CargoItem(0.15, 238, "Mikrowelle"));
        items.add(new CargoItem(0.99, 286, "Sideboard"));
        items.add(new CargoItem(2, 633, "Tiefkühltruhe"));
        items.add(new CargoItem(0.136, 549, "Bügelbrett"));
        items.add(new CargoItem(1.452, 222, "Kommode"));
        items.add(new CargoItem(0.42, 732, "Staubsauger"));
        items.add(new CargoItem(0.288, 695, "Trockner"));
        items.add(new CargoItem(0.288, 387, "Waschmaschine"));
        items.add(new CargoItem(0.168, 933, "Wäscheständer"));
        items.add(new CargoItem(4.004, 224, "Kleiderschrank"));
        items.add(new CargoItem(2.205, 562, "Boxspringbett"));
        items.add(new CargoItem(0.324, 504, "Spiegel"));
        items.add(new CargoItem(0.748, 183, "Kommode"));
        items.add(new CargoItem(0.08, 542, "Nachttisch"));
        items.add(new CargoItem(2.16, 569, "Sitzsack"));
        items.add(new CargoItem(0.828, 332, "Aktenschrank"));
        items.add(new CargoItem(0.105, 406, "Computer"));
        items.add(new CargoItem(0.1, 357, "Drucker"));
        items.add(new CargoItem(0.896, 780, "Schreibtisch"));
        items.add(new CargoItem(1.9635, 732, "Schrank"));
        items.add(new CargoItem(0.96, 783, "Sekretär"));
        items.add(new CargoItem(0.035, 907, "Besen"));
        items.add(new CargoItem(0.21, 664, "Biergarnitur"));
        items.add(new CargoItem(0.036, 163, "Eimer"));
        items.add(new CargoItem(0.76, 529, "Fahrrad"));
        items.add(new CargoItem(0.945, 191, "Gartentisch"));
        items.add(new CargoItem(0.648, 738, "Grill"));
        items.add(new CargoItem(0.175, 212, "Leiter"));
        items.add(new CargoItem(1.26, 957, "Motorrad"));
        items.add(new CargoItem(0.2, 222, "Mülltonne"));
        items.add(new CargoItem(0.294, 779, "Rasenmäher"));
        items.add(new CargoItem(0.36, 145, "Schubkarre"));
        items.add(new CargoItem(0.399, 394, "Sonnenliege"));
        items.add(new CargoItem(1.755, 918, "Tischtennisplatte"));
        items.add(new CargoItem(0.045, 931, "Werkzeugkasten"));
        items.add(new CargoItem(0.288, 764, "Aquarium"));
        items.add(new CargoItem(0.07425, 858, "Gitarre"));
        items.add(new CargoItem(0.208, 302, "Golfset"));
        items.add(new CargoItem(0.06, 413, "Nähmaschine"));
        items.add(new CargoItem(0.192, 388, "Planze"));
        items.add(new CargoItem(0.42, 923, "Schuhschrank"));

        int weightLimit = (int)((2.438 * 2.591 * 6.058) * 1000);

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
