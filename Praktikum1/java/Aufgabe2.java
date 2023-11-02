import java.util.Scanner;
import java.util.Scanner;

public class Aufgabe2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Zahl1? ");
        long zahl1 = scanner.nextLong();

        System.out.println("Zahl2? ");
        long zahl2 = scanner.nextLong();

        long start = System.currentTimeMillis();
        System.out.println(ggtNaiv(zahl1, zahl2));
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;

		System.out.println("Gefunden in: " + timeElapsed + " millis");

        start = System.currentTimeMillis();
        System.out.println(ggt(zahl1, zahl2));
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;

		System.out.println("Gefunden in: " + timeElapsed + " millis");
    }

    private static long ggtNaiv(long zahl1, long zahl2) {
        System.out.println("ggtNaiv");
        long min = Math.min(zahl1, zahl2);
        for (long i = min; i >= 2; i--) {
            if (zahl1 % i == 0 && zahl2 % i == 0) {
                return i;
            }
        }
        return 1;
    }

    private static long ggt(long zahl1, long zahl2) {
        System.out.println("ggt");
        while (zahl2 != 0) {
            if (zahl1 > zahl2) {
                zahl1 = zahl1 - zahl2;
            } else {
                zahl2 = zahl2 - zahl1;
            }
        }
        return zahl1;
    }
}
