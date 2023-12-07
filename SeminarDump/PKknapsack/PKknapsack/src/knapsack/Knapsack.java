package knapsack;
import java.util.ArrayList;
import java.util.List;

class Item {
    
	private String object;  //object -> Objektname für Umzug
	private int value;	   //value -> Profit in 1000€
    private double size;	//size -> Volumen in m
    

    //Konstruktor mit Methoden um einzelne Items aufzurufen
    public Item(String object, double size, int value) {
    	
    	this.object = object;
    	this.size = size;
    	this.value = value;
    	
    }
   
	int getValue() {
		return value;
	}
	
	double getSize() {
		return size;
	}
	
	public String toString() {
    	return this.object + " " + this.value + " " + this.size;
    }
  }
public class Knapsack {
  /*
   * statische Attribute, sollen bis dato beste Zwischenlösung speichern
   * bestValue
   * bestSelection
   */
  static int bestValue;
  static List<Item> bestSelection;
  
  
  /*
   * Operation knapsack, zum Herausfinden des bestmöglichen Values unter maximalem Volumen
   * selectedValue ausgewählter Profit
   * availableValue verfügbarer Profit
   * currentSpace momentan erreichtes Volumen
   * SpaceLimit Volumenlimit von Container 
   */
  public static void knapsack(List<Item> selectedItems,		
		  					  int selectedValue,			
		  					  List<Item> availableItems,	
		  					  int availableValue,			
		  					  double currentSpace,			
		  					  double spaceLimit)				
  	{	
	  if (availableItems.isEmpty())			//alle Items sind verarbeitet, Ergebnis wird als Bound/Messlatte gemerkt
	  {
		  if (selectedValue > bestValue)
		  {
			  bestValue = selectedValue;
			  bestSelection.clear();
			  bestSelection.addAll(selectedItems);
		  }
	  } 
	  else
		  {
			  Item item = availableItems.remove(0);
			  double size = item.getSize();
			  int value = item.getValue(); 
			  if (selectedValue + availableValue > bestValue)
				  {
				  //neues Optimum nicht möglich, Item wird genommen und weiter nach verfügbaren Items geguckt
				  if (currentSpace + size <= spaceLimit) 
					  {
					  selectedItems.add(item); 
					  knapsack(selectedItems, selectedValue+value,
							   availableItems, availableValue-value, 
							   currentSpace+size, spaceLimit); 
					  selectedItems.remove(item);  //Entfernen des Items
					  } 
				  knapsack(selectedItems, selectedValue,
						   availableItems, availableValue, 
						   currentSpace, spaceLimit); 
				  } 
			  availableItems.add(item);  //verfügbare Items zu Liste hinzufügen
		  	}
		 }

  public static void main(String[] args) {
	 
	  List<Item> items = new ArrayList<Item>();
	  bestSelection = new ArrayList<Item>();

	  //items Liste mit Wert aus Aufgabenstellung befüllen
	  items.add(new Item("2-Sitzer Sofa", 1.6, 6687));
	  items.add(new Item("Beistelltisch",	0.312,	186));
	  items.add(new Item("Buffetschrank",	5.635,	770));
	  items.add(new Item("Ecksofa",	1.728,	702));
	  items.add(new Item("Stereoanlage",	0.125,	106));
	  items.add(new Item("Fernseher",	0.154,	711));
	  items.add(new Item("Lowboard",	0.728,	637));
	  items.add(new Item("Flügel",	11.52,	510));
	  items.add(new Item("Gemälde",	0.27,	646));
	  items.add(new Item("Bücherregal",	1.848,	968));
	  items.add(new Item("Ohrensessel",	1.5,	396));
	  items.add(new Item("Sideboard",	1.625,	688));
	  items.add(new Item("Standuhr",	0.32,	827));
	  items.add(new Item("Stehlampe",	0.162,	475));
	  items.add(new Item("Teppich",	0.4,	411));
	  items.add(new Item("Vitrine",	3.468,	605));
	  items.add(new Item("Wohnwand",	3.85,	742));
	  items.add(new Item("Wohnzimmertisch",	0.756,	617));
	  items.add(new Item("Abfalleimer",	0.08,	870));
	  items.add(new Item("Backofen",	0.288,	605));
	  items.add(new Item("Barhocker",	0.12,	679));
	  items.add(new Item("Bestecksatz",	0.024,	484));
	  items.add(new Item("Eckbank",	0.665,	686));
	  items.add(new Item("Geschirr",	0.128,	807));
	  items.add(new Item("Geschirrspülmaschine",	0.288,	852));
	  items.add(new Item("Esstisch",	0.84,	832));
	  items.add(new Item("Hocker",	0.25,	409));
	  items.add(new Item("Hängeschrank",	0.192,	660));
	  items.add(new Item("Mikrowelle",	0.15,	238));
	  items.add(new Item("Sideboard",	0.99,	286));
	  items.add(new Item("Tiefkühltruhe",	2,	633));
	  items.add(new Item("Bügelbrett",	0.136,	549));
	  items.add(new Item("Kommode",	1.452,	222));
	  items.add(new Item("Staubsauger",	0.42,	732));
	  items.add(new Item("Trockner",	0.288,	695));
	  items.add(new Item("Waschmaschine",	0.288,	387));
	  items.add(new Item("Wäscheständer",	0.168,	933));
	  items.add(new Item("Kleiderschrank",	4.004,	224));
	  items.add(new Item("Boxspringbett",	2.205,	562));
	  items.add(new Item("Spiegel",	0.324,	504));
	  items.add(new Item("Kommode",	0.748,	183));
	  items.add(new Item("Nachttisch",	0.08, 542));
	  items.add(new Item("Sitzsack",	2.16,	569));
	  items.add(new Item("Aktenschrank",	0.828,	332));
	  items.add(new Item("Computer",	0.105,	406));
	  items.add(new Item("Drucker", 0.1,	357));
	  items.add(new Item("Schreibtisch",	0.896,	780));
	  items.add(new Item("Schrank",	1.9635,	732));
	  items.add(new Item("Sekretär",	0.96,	783));
	  items.add(new Item("Besen",	0.035,	907));
	  items.add(new Item("Biergarnitur",	0.21,	664));
	  items.add(new Item("Eimer",	0.036,	163));
	  items.add(new Item("Fahrrad",	0.76,	529));
	  items.add(new Item("Gartentisch",	0.945,	191));
	  items.add(new Item("Grill",	0.648,	738));
	  items.add(new Item("Leiter",	0.175,	212));
	  items.add(new Item("Motorrad",	1.26,	957));
	  items.add(new Item("Mülltonne",	0.2,	222));
	  items.add(new Item("Rasenmäher",	0.294,	779));
	  items.add(new Item("Schubkarre",	0.36, 145));
	  items.add(new Item("Sonnenliege",	0.399, 394));
	  items.add(new Item("Tischtennisplatte", 1.755, 918));
	  items.add(new Item("Werkzeugkasten",	0.045, 931));
	  items.add(new Item("Aquarium",	0.288, 764));
	  items.add(new Item("Gitarre",	0.07425, 858));
	  items.add(new Item("Golfset",	0.208, 302));
	  items.add(new Item("Nähmaschine",	0.06, 413));
	  items.add(new Item("Planze",	0.192, 388));
	  items.add(new Item("Schuhschrank",	0.42, 923));
	  
	  bestValue = 0; 
	  int availableValue = 0;
		for(Item item : items)
			availableValue += item.getValue();
	
	  knapsack(new ArrayList<Item>(),0,items,availableValue,0,38.267);
	  System.out.println("Die optimale Packliste:");
	  int value = 0;
	  int size = 0;
	  //optimale Packliste bestimmen
	  for (Item item : bestSelection)
	  {
		  System.out.println(item);
		  value += item.getValue();
		  size += item.getSize();
	  }
	  System.out.println("Wert: "+value+" Volumen: "+size);
  }
}
