import java.util.Scanner;

public class Aufgabe9
{
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
		for(int i = 0 ; i < Auftragsliste.length ; i++) {
			if(Auftragsliste[i].getAuftragsnr() == Auftragsnr)
			{
				return i;
			}
		}

		return -1;
	}
}
