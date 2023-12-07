public class Aufgabe21und22
{
	//Elemente in Liste einf�gen
	static void fillList(SimpleList<String>  list)
  	{
		//Zahlen von 0 bis 19 als Zeichenketten (Strings) einf�gen
    	for (int i = 0; i < 20; ++i) 
		{
      		list.add("" + i);
    	}
  	}

	//Liste vom Anfang bis zum Ende durchlaufen und Elemente ausgeben
  	static void printList(SimpleList list)
  	{
		SimpleIterator it = list.iterator();
		while (it.hasNext()) 
		{
      		System.out.print((String)it.next()+" ");
    	}
    	System.out.println("\n-");
  	}

  	public static void main(String[] args)
  	{
  	    System.out.println("Aufgabe 21");
  	    System.out.println("----------------------------------------");
  	    
  	    //Erzeugen der LinkedList f�r Aufgabe 21

		MyLinkedList<String> mll = new MyLinkedList<String>();
		mll.add("Dies");
		mll.add("ist");
		mll.add("eine");
		mll.add("schöne");
		mll.add("Aufgabe");
		mll.add("zum");
		mll.add("Warmwerden");

		SimpleIterator si = mll.iterator();

		for (int i = 0; i < mll.size; i++) {
			System.out.println(mll.get(i));
		}
        
        //Letztes Element entfernen
        
        //Erstes Element entfernen 
        
        //Die Liste wieder komplettieren

        //Elemente 1 - 5 entfernen
        
        //Element ausgeben
        
        System.out.println("Aufgabe 22");
        System.out.println("----------------------------------------");
        
        //Erzeugen der LinkedList f�r Aufgabe 22

		MyLinkedList<String> mll2 = new MyLinkedList<String>();

		fillList(mll2);
        
		String first = mll2.remove(0);
		String last = mll2.remove(mll2.size() - 1);

		mll2.add(first);
		mll2.add(last);

		mll2.remove(1);
		mll2.remove(2);
		mll2.remove(3);
		mll2.remove(4);
		mll2.remove(5);

        //Geeignete Testf�lle

		printList(mll2);

		System.out.println(mll2.indexOf(first));
		System.out.println(mll2.indexOf(""));
    }
}