package de.profmeyer.java.sorting;

public class Sortierverfahren
{
	 public static void swap(Comparable[] data, int i, int j)
	 {
	    Comparable temp = data[i];
	    data[i] = data[j];
	    data[j] = temp;
	 }
	 
	 public static void selectionSort(Comparable[] data)
	 {
	    for(int i=0; i<data.length; i++)
	    {
	       int min=i;  // aktuelles Minimum
	       for(int j=i+1; j<data.length; j++)
	          if (data[j].compareTo(data[min]) < 0)
	             min=j;  // neues Minimum gefunden
	       if (min != i)
	 	    swap(data,min,i); 
	    }
	 }

	 public static void insertionSort(Comparable[] data)
	 {
	    for (int i=1; i < data.length; i++)
	    {
	       // Entnehmen des einzufügenden Elements
	       Comparable temp = data[i];
	       // Bestimmen der Position k, hinter der eingefügt wird
	       int k = i-1;
	       while ((k >= 0) && (temp.compareTo(data[k]) < 0))
	       {
	    	   data[k+1] = data[k];
	    	   k--;
	       }
	       // Einfügen des neuen Elements an hinter Position k
	       data[k+1] = temp;
	    }
	 }
	 
	 //	BinaryInsertionSort 
	 // -------------------
	 
	 public static void binaryInsertionSort(Comparable[] data)
	 {
	    for (int i=1; i < data.length; i++)
	    {
	       // Bestimmen der Einfügeposition k im Bereich 0..i-1
	       int k = 0, l = i-1;
	       while (k <= l)
	       {
	    	   int m = (k + l)/2;
	    	   if (data[i].compareTo(data[m]) < 0)
	    		   l = m - 1;
	    	   else
	    		   k = m + 1;
	       }			
	       // Entnehmen des einzufügenden Elements
	       Comparable temp = data[i];
	       // Verschieben der Elemente zwischen k und i
	       for (int j = i; j > k; j--)
	          data[j] = data[j-1];
	       // Einfügen des neuen Elements an Position k
	       data[k] = temp;
	    }
	 }
	 
	 // BubbleSort
	 // ----------
	
	 public static void bubbleSort(Comparable[] data)
	 {
		 for(int i=0; i<data.length; i++)
			 for(int j=0; j<data.length-i-1; j++)
				 if (data[j].compareTo(data[j+1]) > 0)
				 {
					 Comparable temp= data[j];
					 data[j] = data[j+1];
					 data[j+1] = temp;
				 }
	 }
	
	 // Verbesserter BubbleSort
	 // -----------------------
	
	 public static void bubbleSort2(Comparable[] data)
 	 {
		 boolean getauscht;
		 int durchgang = 1;
		 do
		 {
			 getauscht=false;
			 for (int i=0; i<data.length-durchgang; i++)	
			 {
				 if (data[i].compareTo(data[i+1]) > 0)
				 {
					 Comparable temp=data[i];
					 data[i]=data[i+1];
					 data[i+1]=temp;
					 getauscht=true;
				 }	
			 } 
			 durchgang++;
		 } 
		 while (getauscht==true && durchgang<data.length);				
 	 }

	 // ShakerSort
	 // ----------
	
	 public static void shakerSort(Comparable[] data)
	 {
		 boolean vertauscht;
		 int durchgang = 1;
		 do 
		 {
			 vertauscht = false;
			
			 // Durchgang von rechts	
			 for( int i= durchgang-1; i<data.length-durchgang; i++)
			 {
				 if (data[i].compareTo(data[i+1]) > 0)
				 {
					 Comparable temp=data[i];
					 data[i]=data[i+1];
					 data[i+1]=temp;	
					 vertauscht=true;	
				 }		
			 }
		
			 // Durchgang von links
			 if (vertauscht)
			 {
				 vertauscht = false;
				 for (int i=data.length-durchgang-2; i>=durchgang-1; i--)
				 {
					 if (data[i].compareTo(data[i+1]) > 0)
					 {
						 Comparable temp=data[i];
						 data[i]=data[i+1];
						 data[i+1]=temp;	
						 vertauscht=true;	
					 }		
				 }
			 }
			 durchgang++;
		 }
		 while (vertauscht && (durchgang<=data.length/2));
	 }
	 
	 // QuickSort
	 // ---------
	
	 public static void quickSort(Comparable[] data)
	 {
		 quickSort(data,0,data.length-1);
	 }
	 
	 public static void quickSort(Comparable[] data, int von, int bis)
	 {
		 if (von < bis)
		 {
			 int pivotIndex = partition(data,von,bis);
			 quickSort(data, von, pivotIndex - 1);
			 quickSort(data, pivotIndex + 1, bis);			 
		 }
	 }
	 
	 public static int partition(Comparable[] data, int von, int bis)
	 {
		 Comparable pivot = data[bis];
		 int links = von;
		 int rechts = bis;
		 do
		 {
			 while ((rechts > von) && (data[rechts].compareTo(pivot) >= 0))
				 rechts--;
			 while (data[links].compareTo(pivot) < 0)
				 links++;

			 if (links < rechts)
				 swap(data,links,rechts);
		 }
		 while (links < rechts);
		 swap(data,bis,links);
		 return links;
	 }
	 
	 // ShellSort  (Original nach Shell: n/2, n/4, n/8, ..., 1)
	 // ---------
	
	 public static void shellSort1(Comparable[] data)
	 {
		 int h = data.length / 2; 
		 while (h > 0)
		 {
			 for (int i = h - 1; i < data.length; i++)
			 {
				 Comparable x = data[i];
				 int j = i;
				 while ((j >= h) && (data[j - h].compareTo(x) > 0))
				 {
					 data[j] = data[j - h];
					 j = j - h;
				 }
				 data[j] = x;
			 }
			 h = h / 2;
		 }
	 }

	 // HeapSort
	 // --------
	 
	 public static void heapSort(Comparable[] data)
	 {
		 int n = data.length;
		 //	Phase 1: wandle data[0 .. n-1] in Heap-Struktur um
		 for (int k = n/2; k > 0; k--)
			 downheap(data, k-1, n);
		 //	Phase 2: entnehme Elemente aus der Heap-Struktur
		 do
		 {
			 //	 tausche data[0] mit data[n-1]
			 Comparable x = data[0];
			 data[0] = data[n-1];
			 data[n-1] = x;
			 //	 lasse data[0] in die Heap-Struktur einsickern
			 n--;
			 downheap(data, 0, n);
		 } while (n > 1);
	 }
	
	 static void downheap(Comparable[] data, int k, int right)
	 { 
		 //versickere data[k] in den Heap data[k+1..rechts]
		 Comparable x = data[k];
		 while (k+1 <= right/2) // data[k+1] hat linken Sohn
		 {
			 int j = 2 * k + 1;
			 if ((j+1 < right) && (data[j].compareTo(data[j+1]) < 0))
				 j++;
			 if (x.compareTo(data[j]) >= 0)
				 break; // Halte an, Heap-Bedingung erfüllt!
			 else
			 {
				 data[k] = data[j];
				 k = j;
			 }
		 }
		 data[k] = x;
	 }
}
