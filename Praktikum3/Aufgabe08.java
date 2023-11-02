/*
 *   Lehrveranstaltung Algorithmen und Datenstrukturen (ALDS)
 *   --------------------------------------------------------
 *   
 *   (c) Prof. Dr. Manfred Meyer, Westfälische Hochschule, Campus Bocholt
 *   
 *   Vorgabe zum Praktikum 3 
 *   
 *   Aufgabe 8
 *   
 */

public class Aufgabe08 
{

	public static void main(String[] args) 
	{
		int zahl = 100;

		long start = System.currentTimeMillis();

		System.out.println(f(zahl));

		long stop = System.currentTimeMillis();

		System.out.println(stop-start + "ms");

	}
	
	// Algorithmus zu Aufgabe 8
	// ------------------------
	//
	// Messungen und Vermutung zur Laufzeitkomplexität O(2^n)
	//
	// Sie brauchen diese Funktion nicht wirklich zu verstehen... kommt sp�ter!
	
	static int f(int n)
	{
		if (n < 2)
			return 1;
		else
			return f(n-1) + f(n-2);
	}
	
}
