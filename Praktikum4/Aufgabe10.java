import java.util.Arrays;
import java.util.Scanner;

public class Aufgabe10
{
	//a) Wie wirkt sich dieser Umstand auf die Terminierung des Verfahrens aus?
	//		Keine Auswirkung
	//b) Wie wirkt sich dieser Umstand auf die Korrektheit des Verfahrens aus?
	//		Korrektheit ist nicht mehr gegeben
	//c) Wie wirkt sich dieser Umstand auf die Komplexität des Verfahrens aus?
	//		Keine Auswirkung 


	static Auftrag[] Auftragsliste;
	public static void main (String[] args)
	{
		Auftragsliste = new Auftrag[5];
		// Lineare Suche - Firma Schnell und Sicher

		Scanner sc = new Scanner(System.in);

		for(int i = 0 ; i < Auftragsliste.length ; i++) {
			addAuftrag(new Auftrag(sc.nextInt()));
		}

		System.out.println("Suche");

		System.out.println(Arrays.toString(Auftragsliste));
		Arrays.sort(Auftragsliste);
		System.out.println(Arrays.toString(Auftragsliste));

		System.out.println(getIndex(sc.nextInt()));
	}

	public static void addAuftrag(Auftrag auftrag) {
		for(int i = 0 ; i < Auftragsliste.length ; i++) {
			if (Auftragsliste[i] == null) {
				Auftragsliste[i] = auftrag;
				return;
			}
		}
	}

	public static int getIndex(int Auftragsnr) {
		int left = 0;
		int right = Auftragsliste.length -1;
		int middle;

		do {
			middle = ( left + right ) / 2;
			System.out.println(Auftragsliste[middle].getAuftragsnr());
			if(Auftragsliste[middle].getAuftragsnr() == Auftragsnr) {
				return middle;
			}

			if(Auftragsliste[middle].getAuftragsnr() > Auftragsnr) {
				right = middle - 1;
			}

			if(Auftragsliste[middle].getAuftragsnr() < Auftragsnr) {
				left = middle + 1;
			}
			
		} while(left != middle && right != middle);

		return -1;
	}
}
