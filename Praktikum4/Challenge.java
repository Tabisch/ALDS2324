
public class Challenge
{
	public static void main (String[] args)
	{
		// Vorbereitung: Fuellen und Test einer LinProbHashTable<String>
		// ---------------------------------------------------------------
		
		LinProbHashTable<String> lpht1=new LinProbHashTable<String>(10);
		
		ElementType<String> e1=new ElementType<String>("Heute");
		ElementType<String> e2=new ElementType<String>("ist");
		ElementType<String> e3=new ElementType<String>("ein");
		ElementType<String> e4=new ElementType<String>("schoener");
		ElementType<String> e5=new ElementType<String>("Tag");
		ElementType<String> e6=new ElementType<String>("fuer");
		ElementType<String> e7=new ElementType<String>("eine");
		ElementType<String> e8=new ElementType<String>("kleine");
		ElementType<String> e9=new ElementType<String>("Uebung");
		
		lpht1.add(e1);
		lpht1.add(e2);
		lpht1.add(e3);
		lpht1.add(e4);
		lpht1.add(e5);
		lpht1.add(e6);
		lpht1.add(e7);
		lpht1.add(e8);
		lpht1.add(e9);
		
		System.out.println("\nDirekte Ausgabe der Hash-Tabelle");
		for (int i=0; i<lpht1.ht.length; i++)
		{
			System.out.print(lpht1.ht[i]+" ");
		}
		System.out.println();
	}
}
