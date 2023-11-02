/*
 *  Algorithmen und Datenstrukturen - WS 2023/2024 - Westfälische Hochschule, Campus Bocholt
 *  ----------------------------------------------------------------------------------------
 *  
 *  Vorgabe für das Praktikum 01 am 12.10.2023
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PrimzahlenObergrenze
{

	public static void main (String[] args)
	{	
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Bis zur welcher Obergrenze sollen Primzahlen berechnet werden? ");
		long obergrenze=scanner.nextLong();

		//System.out.println("Das Suchen der Primzahlen bis zur Zahl "+obergrenze+" ist beendet, es gibt " + primzahlenTotalNaiv(obergrenze).size() +" Primzahlen!");

		System.out.println("Das Suchen der Primzahlen bis zur Zahl "+obergrenze+" ist beendet, es gibt " + primzahlenTotalNaiv_optimiert(obergrenze).size() +" Primzahlen!");

		System.out.println("Das Suchen der Primzahlen bis zur Zahl "+obergrenze+" ist beendet, es gibt " + sieb(obergrenze).size() +" Primzahlen!");


		System.out
		    .println("===============================================================================");		
	}
	
	//  Ausgangsbasis: sehr naive Implementierung primzahlenTotalNaiv

	//testet alle graden Zahlen (modulo 2 reicht aus)
	//nur ungrade zahlen testen (teiler = 3 und teiler + 2 rechnen)
	//quadrat ist das höchste (nur bis wurzel testen)
	// break wenn nicht primzahl
	
	private static List<Long> primzahlenTotalNaiv (long obergrenze)
	{
		System.out.println("primzahlenTotalNaiv");
		long start = System.currentTimeMillis();

		List<Long> primzahlen=new ArrayList<Long>();
		boolean istPrimzahl;
		long zuPruefen=2;
		
		while (zuPruefen<obergrenze)
		{
			istPrimzahl=true;
			
			for (int teiler=2; teiler<zuPruefen; teiler++)
			{
				if (zuPruefen%teiler==0)
				{
					istPrimzahl=false;
				}
			}
			
			if (istPrimzahl)
			{
				primzahlen.add(zuPruefen);
			}
			
			zuPruefen++;
		}

		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("Gefunden in: " + timeElapsed + " millis");

		return primzahlen;
	}	

	private static List<Long> primzahlenTotalNaiv_optimiert (long obergrenze)
	{
		System.out.println("primzahlenTotalNaiv_optimiert");
		long start = System.currentTimeMillis();

		List<Long> primzahlen=new ArrayList<Long>();
		boolean istPrimzahl;
		long zuPruefen=2;

		primzahlen.add(zuPruefen);
		
		while (zuPruefen<obergrenze)
		{
			istPrimzahl=true;
			
			if(zuPruefen%2 == 0) {
				istPrimzahl = false;
			}
			else
			{
				for (int teiler=3; teiler<(int)Math.sqrt(zuPruefen)+1; teiler = teiler+2)
				{
					if (zuPruefen%teiler==0)
					{
						istPrimzahl=false;
						break;
					}
				}
			}
			
			if (istPrimzahl)
			{
				primzahlen.add(zuPruefen);
			}
			
			zuPruefen++;
		}

		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("Gefunden in: " + timeElapsed + " millis");

		return primzahlen;
	}	

	private static List<Long> sieb (long obergrenze)
	{
		System.out.println("sieb");
		long start = System.currentTimeMillis();

		List<Long> zahlen=new LinkedList<Long>();
		List<Long> primzahlen=new LinkedList<Long>();

		for(long i = 2; i < obergrenze; i++) {
			zahlen.add(i);
		}

		while(zahlen.size() != 0) {
			long prim = zahlen.get(0);
			zahlen.remove(0);
			primzahlen.add(prim);

			if(prim > Math.sqrt(obergrenze))
			{
				primzahlen.addAll(zahlen);
				break;
			}

			Iterator<Long> i = zahlen.iterator();

			while(i.hasNext()) {
				if(i.next() % prim == 0) {
					i.remove();
				}
			}
		}

		
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("Gefunden in: " + timeElapsed + " millis");

		return primzahlen;
	}	
}
