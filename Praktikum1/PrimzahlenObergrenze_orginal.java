/*
 *  Algorithmen und Datenstrukturen - WS 2023/2024 - Westfälische Hochschule, Campus Bocholt
 *  ----------------------------------------------------------------------------------------
 *  
 *  Vorgabe für das Praktikum 01 am 12.10.2023
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimzahlenObergrenze_orginal
{

	public static void main (String[] args)
	{
		
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Bis zur welcher Obergrenze sollen Primzahlen berechnet werden? ");
		long obergrenze=scanner.nextLong();
		
		System.out.println("\nPrimzahlen, total naives Verfahren:");
		System.out.println("Das Suchen der Primzahlen bis zur Zahl "+obergrenze+" ist beendet, es gibt "
		    +primzahlenTotalNaiv(obergrenze).size()+" Primzahlen!");
		System.out
		    .println("===============================================================================");		
	}
	
	//  Ausgangsbasis: sehr naive Implementierung primzahlenTotalNaiv
	
	private static List<Long> primzahlenTotalNaiv (long obergrenze)
	{
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
		return primzahlen;
	}	
}
