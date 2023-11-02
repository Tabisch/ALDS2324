import java.util.Scanner;

public class Aufgabe6 {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int limit = sc.nextInt();

        for (int i = 1 ; i <= limit ; i++) {
            if(paritialKorrekt(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean paritialKorrekt(int n) {

        while(n == 42) {}

        if(n % 2 == 0) {
            return false;
        }

        for (int i = 3 ; i < n ; i=i+2) {
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
