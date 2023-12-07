import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2.12.2022
 * Das ist Code zur Demonstration des Rucksackproblems
 * @author simon 
 * @version 1.0
 * 
 * 
 */

class Item {
    
	private int objectNr;  //@param objectNr -> Objektnummer für Weltraummaterial
	private int value;	   //@param value -> Profit in 1000€
    private int weight;	   //@param weight -> Gewicht in Kg

    //Konstruktor mit Methoden um einzelne Items aufzurufen
    public Item(int objectNr, int weight, int value) {
    	
    	this.objectNr = objectNr;
    	this.weight = weight;
    	this.value = value;
    	
    }
   
	int getValue() {
		return value;
	}
	
	int getWeight() {
		return weight;
	}
	//@return wirft Itemdetails zurück
	public String toString() {
    	return this.objectNr + " " + this.value + " " + this.weight;
    }
  }
public class Knapsack {
  /*
   * statische Attribute, sollen bis dato beste Zwischenlösung speichern
   * @param bestValue
   * @param bestSelection
   */
  static int bestValue;
  static List<Item> bestSelection;
  
  
  /*
   * Operation knapsack, zum Herausfinden des bestmöglichen Values unter maximalem Gewicht
   * @param selectedValue ausgewählter Profit
   * @param availableValue verfügbarer Profit
   * @param currentWeight momentan erreichtes Gewicht
   * @param weightLimit max Gewicht von 645Kg
   */
  public static void knapsack(List<Item> selectedItems,		
		  					  int selectedValue,			
		  					  List<Item> availableItems,	
		  					  int availableValue,			
		  					  int currentWeight,			
		  					  int weightLimit)				
  	{	
	  if (availableItems.isEmpty())			//alle Items sind verarbeitet, Ergebnis wird als Bound/Messlatte gemerkt
	  {
		  if (selectedValue > bestValue)
		  {
			  bestValue = selectedValue;
			  bestSelection.clear();
			  bestSelection.addAll(selectedItems);
		  }
	  } // Verschiebung des Else-Parts auf äußeres IF, added by me
	  else
		  {
			  Item item = availableItems.remove(0);
			  int weight = item.getWeight();
			  int value = item.getValue(); 
			  if (selectedValue + availableValue > bestValue)
				  {
				  //neues Optimum nicht möglich, Item wird genommen und weiter nach verfügbaren Items geguckt
				  if (currentWeight + weight <= weightLimit) 
					  {
					  selectedItems.add(item); 
					  knapsack(selectedItems, selectedValue+value,
							   availableItems, availableValue-value, 
							   currentWeight+weight, weightLimit); 
					  selectedItems.remove(item);  //Entfernen des Items
					  } 
				  knapsack(selectedItems, selectedValue,
						   availableItems, availableValue, 
						   currentWeight, weightLimit); 
				  } 
			  availableItems.add(item);  //verfügbare Items zu Liste hinzufügen
		  	}
		 }
  

  public static void main(String[] args) {
	 
	  List<Item> items = new ArrayList<Item>();
	  bestSelection = new ArrayList<Item>();
	  
	  //@param items Liste mit Wert aus Aufgabenstellung befüllen
	  items.add(new Item(1,201,191));
	  items.add(new Item(2,141,239));
	  items.add(new Item(3,50,66));
	  items.add(new Item(4,38,249));
	  items.add(new Item(5,79,137));
	  items.add(new Item(6,73,54));
	  items.add(new Item(7,232,153));
	  items.add(new Item(8,48,148));
	  
	  
	  bestValue = 0; 
	  int availableValue = 0;
		for(Item item : items)
			availableValue += item.getValue();
	
	  knapsack(new ArrayList<Item>(),0,items,availableValue,0,645);
	  System.out.println("Die optimale Packliste:");
	  int value = 0;
	  int weight = 0;
	  //optimale Packliste bestimmen
	  for (Item item : bestSelection)
	  {
		  System.out.println(item);
		  value += item.getValue();
		  weight += item.getWeight();
	  }
	  System.out.println("Wert: "+value+" Gewicht: "+weight);
  }
}
