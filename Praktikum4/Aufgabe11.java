import java.util.Scanner;

public class Aufgabe11
{
	static Auftrag[] Auftragsliste;
	public static void main (String[] args)
	{
		Auftragsliste = new Auftrag[100];
		// Lineare Suche - Firma Schnell und Sicher

		Scanner sc = new Scanner(System.in);

		for(int i = 0 ; i < 2 ; i++) {
			addAuftrag(new Auftrag(sc.nextInt()));
		}

		System.out.println("Suche");

		System.out.println(getIndex(sc.nextInt()));
	}

	public static boolean addAuftrag(Auftrag auftrag) {

		int zielAddresse = auftrag.getAuftragsnr()%Auftragsliste.length;

		if (Auftragsliste[zielAddresse] == null) {
			Auftragsliste[zielAddresse] = auftrag;
			return true;
		}
		else
		{
			for (int i = 1 ; i < Auftragsliste.length ; i++)
			{
				if (Auftragsliste[zielAddresse + i] == null) {
					Auftragsliste[zielAddresse + i] = auftrag;
					return true;
				}
			}
		}

		return false;
	}

	public static int getIndex(int Auftragsnr) {
		int zielAddresse = Auftragsnr%Auftragsliste.length;

		if (Auftragsliste[zielAddresse].getAuftragsnr() == Auftragsnr) {
			return zielAddresse;
		}
		else
		{
			for (int i = 1 ; i < Auftragsliste.length ; i++)
			{
				if (Auftragsliste[zielAddresse + i].getAuftragsnr() == Auftragsnr) {
					return zielAddresse + i;
				}
			}
		}

		return -1;
	}

	public Auftrag getAuftragsnr(int Auftragsnr) {
		int addresse = getIndex(Auftragsnr);

		if(addresse != -1) {
			return Auftragsliste[addresse];
		}
		else {
			return null;
		}
	}
}
