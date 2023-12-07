

/**
 * Implementierung der Schnittstelle SimpleList<E> mit Hilfe
 * eines Feldes, das bei Bedarf vergr��ert wird.
 *
 * Die gespeicherten Objektreferenzen d�rfen im Gegensatz zur 
 * Klasse java.util.ArrayList<E> in der Java-Klassenbibliothek 
 * nicht gleich null sein.
 */
/**
 * @author Manfred Meyer
 *
 * @param <E>
 */
public class MyArrayList<E>
implements SimpleList<E>
{
	/**
	 * Feld zur Speicherung der Nutzdaten (Elemente)
	 */
	private E[] data;  	 
	
	/**
	 * aktuelle Anzahl der Elemente in der Liste
	 */
	private int size;	
	
	/**
	 * Erzeugung einer leeren Liste
	 */
	public MyArrayList()			  
	{
		this(10);
	}

	/**
	 * Erzeugung einer leeren Liste
	 */
	@SuppressWarnings("unchecked")    
	public MyArrayList(int initialCapacity)			  
	{
		this.data = (E[]) new Object[initialCapacity];	
		this.size = 0;
	}
	
	/**
	 * Sicherstellen, dass das Feld gross genug ist, um mind.
	 * minCapacity Objekte speichern zu k�nnen.
	 * Ist das nicht der Fall, wird ein gr��eres Feld erzeugt,
	 * und die Elemente werden in das neue Feld umkopiert.
	 * 
	 * @param minCapacity sicherzustellende Mindestkapazit�t
	 */
	@SuppressWarnings("unchecked")
	protected void ensureCapacity(int minCapacity)
	{
		int oldCapacity = this.data.length;
	
		if (minCapacity>oldCapacity) 
		{
	    	E[] oldData = this.data;
	    	
	    	// Hier erfolgt die Erzeugung des neuen Feldes.
	    	// F�r die Gr��e des neuen Feldes sind verschiedene
	    	// Strategien denkbar.	
	    	int newCapacity = (2 * oldCapacity <= oldCapacity + 500)
	    	        ? (2 * oldCapacity) : (oldCapacity + 500);
    	    if (newCapacity < minCapacity) newCapacity = minCapacity;
	    	this.data = (E[]) new Object[newCapacity];
	    
	    	// Kopieren der Daten aus dem alten in das neue Feld
	    	for (int i=0; i<oldCapacity; i++)
	    		this.data[i] = oldData[i];
		}
    }

	/**
	 * Sicherstellen, dass das Feld verkleinert wird, wenn sehr
	 * viele Elemente wieder entfernt wurden.
	 */
	@SuppressWarnings("unchecked")
	protected void shrinkCapacity()
	{
		int oldCapacity = this.data.length;
		int newCapacity = oldCapacity;
		
		// Hier erfolgt die Berechnung der neuen Feldgr��e. 
		// Daf�r sind verschiedene Strategien denkbar.
		if (oldCapacity - this.size > 500) newCapacity = oldCapacity - 500;
		if (this.size < oldCapacity/2) newCapacity = oldCapacity / 2;
		
		if (newCapacity < oldCapacity) 
		{
	    	E[] oldData = this.data;
	    	// Erzeugen des neuen Feldes
	    	this.data = (E[]) new Object[newCapacity];
	    
	    	// Kopieren der Daten aus dem alten in das neue Feld
	    	for (int i=0; i<newCapacity; i++)
	    		this.data[i] = oldData[i];
		}
    }
	
	/**
	 * �berpr�fung, ob ein Index im g�ltigen Bereich liegt
	 * 
	 * @param minIndex die Untergrenze f�r den Index	
	 * @param index der zu �berpr�fende Index
	 * @param maxIndex die Obergrenze f�r den Index
	 */
	private static void rangeCheck(int minIndex, int index, int maxIndex)
	{
		if (index<minIndex || index>maxIndex)
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Elemente ab der Position index+1 um 1 nach links schieben
	 * Das Element an der Position index wird �berschrieben.
	 * 	
	 * @param index die Position, ab der verschoben werden soll
	 */
	private void shiftLeft(int index)
	{
		for (int i=index; i<this.size-1; i++)
			this.data[i] = this.data[i+1];
		this.data[this.size-1] = null;  // Referenz entfernen
	}	
	 
	/**
	 * Elemente ab der Position index um 1 nach rechts schieben.
	 * Das Element an der Position index wird �berschrieben.
	 * 	
	 * @param index die Position, ab der verschoben werden soll
	 */
	private void shiftRight(int index)
	{
		for (int i=this.size; i>index; i--)
			this.data[i] = this.data[i-1];
	}	
	
	/**
	 * �berpr�fung, ob die Liste leer ist
	 * 
	 * 
	 */
	public boolean isEmpty()
	{
		return this.size==0;
	}
	
	/**
	 * Bestimmung der Anzahl der Elemente in der Liste
	 * 
	 * 
	 */
	public int size()
	{
		return this.size;
	}
	
	/**
	 * Einf�gen eines Elements am Ende der Liste 
	 * 
	 * 
	 */
	public boolean add(E o)
	{
		if (o!=null)
		{
			// Ausreichende Gr��e des Feldes sicherstellen
			ensureCapacity(this.size+1);
			// Objekt speichern
			this.data[this.size] = o;
			// Anzahl Elemente erh�hen
			this.size++;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Entfernen des ersten Elements der Liste, das zu element 
	 * inhaltsgleich ist 
	 *
	 * 
	 */
	public boolean remove(E element)
	{
		// Bestimmung des Elements mit dem kleinsten Index, das
		// zu o inhaltsgleich ist
		int index = this.indexOf(element);
		
		if (index>=0)
		{
			// Elemente ab der Position index+1 um 1 nach links schieben
			shiftLeft(index);
			// Anzahl der Elemente reduzieren
			this.size--;
			// P�rfen, ob Feld verkleinert werden kann
			shrinkCapacity();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * �berpr�fung, ob ein zu o inhaltsleiches Objekt in der
	 * Liste enthalten ist
	 *
	 * 
	 */
	public boolean contains(E o)
	{
		return (this.indexOf(o) >= 0);
	}
	
	/**
	 * Erzeugung eines Iterators zum sequentiellen Durchlauf durch
	 * die Liste
	 * 
	 * 
	 */
	public SimpleIterator<E> iterator()
	{
		return new SimpleArrayListIterator();
	}

	/**
	 * Erzeugung eines List-Iterators zum sequentiellen Durchlauf 
	 * durch die Liste
	 * 
	 * 
	 *
	 * @return ein neuer Listen-Iterator beginnend am Anfang der 
	 * Liste
	 */
	public SimpleListIterator<E> listIterator()
	{
		return new ExtendedSimpleArrayListIterator();
	}
	
	/**
	 * Erzeugung eines List-Iterators zum sequentiellen Durchlauf 
	 * durch die Liste
	 * 
	 *
	 *
	 * @return ein neuer Listen-Iterator beginnend an der Position 
	 * index
	 */
	public SimpleListIterator<E> listIterator(int index)
	{
		return new ExtendedSimpleArrayListIterator(index);
	}
	
    /**
     * Einf�gen eines Elements an der Position index
     *
	 * 
	 */
	public void add(int index, E element)
	{
		if (element!=null)
		{
			rangeCheck(0, index, this.size);

			// Ausreichende Gr��e des Feldes sicherstellen		
			ensureCapacity(this.size+1);
		
			// Elemente ab der Position index um 1 nach rechts schieben
			shiftRight(index);
						
			// Objekt an der Position index speichern
			this.data[index] = element;
			this.size++;
		}
	}
	
	/**
	 * Entfernen des Elements an der Position index und R�ckgabe 
	 * des entfernten Elements
	 *
	 * 
	 */
	public E remove(int index)
	{
		E temp = data[index];

		shiftLeft(index);

		return temp;

	}
		
	/**
	 * R�ckgabe des an der Position index gespeicherten Elements
	 *
	 * 
	 */
	public E get(int index)
	{
		rangeCheck(0, index, this.size-1);
		return this.data[index];
	}

	/**
	 * Bestimmung des Index des ersten Elements der Liste, das zu element 
	 * inhaltsgleich ist
	 *
	 * 
	 */
	public int indexOf(E element)
	{
		if (element!=null) 
		{
			int i = 0;
			while (i<this.size)
			{
				if (element.equals(this.data[i]))
					return i;
				i++;
			}
		}
		return -1;
	}	
	
	/**
	 * Innere Klasse f�r einen Iterator zum sequentiellen Durchlauf 
	 * durch die Liste
	 * 
	 * @author Manfred Meyer
	 *
	 */
	class SimpleArrayListIterator
	implements SimpleIterator<E>
	{
		/**
		 * aktuelle Position des Iterators
		 */
		int pos;	
		
		/**
		 * Konstruktor mit Anfangsposition vor dem 1. Element 
		 */
		SimpleArrayListIterator()
		{
			pos = -1;
		}
		
		/** 
		 * �berpr�fung, ob der Iterator noch ein weiteres Element
		 * besuchen kann
		 * 
		 * 
		 */
		public boolean hasNext()
		{
			return pos<size-1;
		}
		
		/**
		 * Schritt zum n�chsten Element und R�ckgabe des aktuellen
		 * Elements als Ergebnis 
		 * 
		 * 
		 */
		public E next()
		{
			pos++;
			if (pos<size)
				return data[pos];
			else	
			    return null;
		}
		
		/**
		 * Das zuletzt mit next() gelieferte Element wird gel�scht
		 * 
		 * 
		 */
		public void remove()
		{
			shiftLeft(pos);
			// Anzahl der Elemente reduzieren
			size--;	
			// Pr�fen, ob Feld verkleinert werden kann
			shrinkCapacity();
		}
	}
	
	class ExtendedSimpleArrayListIterator
	implements SimpleListIterator<E>
	{
		/**
		 * aktuelle Position des Iterators
		 */
		int pos;	
		
		/**
		 * Konstruktor mit Anfangsposition vor dem 1. Element 
		 */
		ExtendedSimpleArrayListIterator()
		{
			this.pos = -1;
		}

		/**
		 * Konstruktor mit beliebiger Anfangsposition 
		 */
		ExtendedSimpleArrayListIterator(int pos)
		{
			this.pos = pos;
		}
		
		/** 
		 * �berpr�fung, ob der Iterator noch ein weiteres Element
		 * r�ckw�rts besuchen kann
		 * 
		 * 
		 */
		public boolean hasPrevious()
		{
			return pos>0;
		}
		
		/**
		 * Schritt zum letzten Element und R�ckgabe des aktuellen
		 * Elements als Ergebnis 
		 * 
		 * 
		 */
		public E previous()
		{
			pos--;
			if (pos>=0)
				return data[pos];
			else	
			    return null;
		}
		
		/** 
		 * �berpr�fung, ob der Iterator noch ein weiteres Element
		 * besuchen kann
		 * 
		 * 
		 */
		public boolean hasNext()
		{
			return pos<size-1;
		}
		
		/**
		 * Schritt zum n�chsten Element und R�ckgabe des aktuellen
		 * Elements als Ergebnis 
		 * 
		 *
		 */
		public E next()
		{
			pos++;
			if (pos<size)
				return data[pos];
			else	
			    return null;
		}
		
		/**
		 * Das zuletzt mit next() gelieferte Element wird gel�scht
		 * 
		 *
		 */
		public void remove()
		{
			shiftLeft(pos);
			// Anzahl der Elemente reduzieren
			size--;	
			// P�rfen, ob Feld verkleinert werden kann
			shrinkCapacity();
		}
		
		/**
		 * Das zuletzt mit next() gelieferte Element wird ersetzt
		 * 
		 * 
		 */
		public void set(E o)
		{
			data[pos] = o;
		}
		
		/**
		 * Nach dem zuletzt mit next() gelieferten Element wird ein
		 * neues Element eingef�gt
		 * 
		 * 
		 */
		public void add(E o)
		{
			if (o!=null)
			{
				// Ausreichende Gr��e des Feldes sicherstellen		
				ensureCapacity(size+1);
				// Elemente ab der Position pos um 1 nach rechts schieben
				shiftRight(pos);		
				// Neues Element an der Position pos speichern
				data[pos] = o;
				size++;
			}
		}
	}
}
