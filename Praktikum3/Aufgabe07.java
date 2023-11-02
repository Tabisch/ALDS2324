/*
 *   Lehrveranstaltung Algorithmen und Datenstrukturen (ALDS)
 *   --------------------------------------------------------
 *   
 *   (c) Prof. Dr. Manfred Meyer, Westfälische Hochschule, Campus Bocholt
 *   
 *   Vorgabe zum Praktikum 3 
 *   
 *   Aufgabe 7
 *   
 */

public class Aufgabe07 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
	
	// Algorithmus f1 zu Aufgabe 7 a)
	// ------------------------------
	//
	// - Terminierung: Ja
	// - Korrektheit: keine Spezifikation
	// - Laufzeitkomplexität: O(n)
	// - Speicherkomplexität: O(n)
	
	static void f1(int n)
	{
		for (int i=0; i<n; i++)
		{
			int[] a=new int[n];
			while (i<n)
			{
				a[i]=i;
				System.out.println(i++);
			}
		}
	}
	
	// Algorithmus f2 zu Aufgabe 7 b)
	// ------------------------------
	//
	// - Terminierung: Ja
	// - Korrektheit: keine Spezifikation
	// - Laufzeitkomplexität: O(n^2)
	// - Speicherkomplexität: O(n)

	static void f2(int n)
	{
		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				int[] a = new int[n];
				a[j] = j;
				System.out.println(j);
			}
		}
	} 

	// Algorithmus f3 zu Aufgabe 7 c)
	// ------------------------------
	//
	// - Terminierung: Ja
	// - Korrektheit: keine Spezifikation
	// - Laufzeitkomplexität: O(n)
	// - Speicherkomplexität: O(1)

	static void f3(int n) 
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < n) 
		{
			while (j < n)
			{
				while (k < n) 
				{
					System.out.println(k++);
				}
				j++;
			}
			i++;
		}
	}

	// Algorithmus f4 zu Aufgabe 7 d)
	// ------------------------------
	//
	// - Terminierung: Nein
	// - Korrektheit: Keine Spezifikation
	// - Laufzeitkomplexität:
	// - Speicherkomplexität: O(1)
	
	static void f4(int n) 
	{
		int i = 0;
		int j = 0;
		while (i < n) 
		{
			System.out.println(i++);
			i = i * j;
		}
	} 

	// Algorithmus f5 zu Aufgabe 7 e)
	// ------------------------------
	//
	// - Terminierung: Ja
	// - Korrektheit: keine Spezifikation
	// - Laufzeitkomplexität: O(n^2)
	// - Speicherkomplexität: O(n^2)
	
	static void f5(int n)
	{
		int i = 1;
		int j = 1;
		int k = 1;
		while (i < n) 
		{
			while (j < n) 
			{
				int[] a = new int[i * j * k];
				k = 0;
				while (k < n) {
					System.out.println(k++);
					a[i - 1] = j;
				}
				j++;
			}
			i++;
		}
	} 


}
