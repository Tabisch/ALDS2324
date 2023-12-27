import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Aufgabe30 {

    public static void main(String[] args) {
        String two = "TWO";
        String four = "FOUR";

        int counter = 0;

        HashMap<Character,Integer> mapping = new HashMap<Character,Integer>();

        mapping.put('T',0);
        mapping.put('W',0);
        mapping.put('O',0);
        mapping.put('F',0);
        mapping.put('U',0);
        mapping.put('R',0);

        while (true) {
            counter = updateMapping(mapping, counter++);
            System.out.println(counter);
        }
    }

    static int textToNumber(String text, Map<Character,Integer> mapping) {

        int temp = 0;

        for (int i = 0; i < text.length(); i++) {
            temp = temp * 10 + mapping.get(text.charAt(i));
        }

        return temp;
    }

    static int updateMapping(Map<Character,Integer> mapping, int counter) {

        int temp = counter;

        temp++;

        HashSet<Character> set = new HashSet<Character>();

        while (true) {

            set.clear();

            for (Character character : String.valueOf(temp).toCharArray()) {
                set.add(character);

                if (set.contains('0')) {
                    set.clear();
                }
            }

            if (set.size() == mapping.size()) {
                break;
            }

            temp++;
        }

        return temp;
    }

    public void setMapping(Map<Character,Integer> mapping, int textToNumber) {

        char[] keys = mapping.keySet().toArray();

        for (int i = 0; i < mapping.size(); i++) {
            mapping.entrySet()
        }
    }
}