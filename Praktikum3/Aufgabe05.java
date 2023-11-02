import java.util.LinkedList;

public class Aufgabe05 
{

    // Terminierung: Ja
    // Laufzeit: O(n^2)
    // Speicher: O(1)

   public static void main(String[] args) 
   {
       VollkommendeZahlen(1000);

   }
   
   public static void VollkommendeZahlen(int n) {

    LinkedList<Integer> zahlen = new LinkedList<Integer>();

    for(int i = 1; i <=n ; i++ )
    {
        int summe = 0;

        for(int q = 1; q <= i/2; q++ )
        {
            if(i % q == 0)
            {
                summe = summe+q;
            }
        }

        if(summe == i) {
            zahlen.add(i);
        }

    }

    System.out.println(zahlen.toString());
   }
}
