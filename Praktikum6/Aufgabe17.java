import java.util.Arrays;

public class Aufgabe17 {
    public static void main(String[] args) {
        int[] zahlen = new int[] {41 , 25, 56 ,456 ,686786,23234,5675};

        selectionSort_rekursiv(zahlen);

        System.out.println(Arrays.toString(zahlen));
    }

    static void selectionSort_rekursiv(int[] zahlen) {
        selectionSort_rekursiv(zahlen, 0);
    }

    static void selectionSort_rekursiv(int[] zahlen, int currentPosition) {
        if(zahlen.length == currentPosition) {
            return;
        } 

        int lowestPosition = currentPosition;

        for (int i = currentPosition; i < zahlen.length; i++) {
            if(zahlen[lowestPosition] > zahlen[currentPosition]) {
                lowestPosition = currentPosition;
            }
        }

        int temp = zahlen[lowestPosition];

        for (int i = lowestPosition; i > currentPosition; i--) {
            zahlen[lowestPosition] = zahlen[lowestPosition-1]; 
        }

        zahlen[currentPosition] = temp;

        selectionSort_rekursiv(zahlen, currentPosition + 1);
    }
}
