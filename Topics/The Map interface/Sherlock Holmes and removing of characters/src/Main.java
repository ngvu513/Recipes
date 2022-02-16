import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next().toLowerCase();
        String str2 = scanner.next().toLowerCase();
        char[] chars2 = str2.toCharArray();
        HashMap<Character, Integer> hashMap1 = new HashMap<>();
        HashMap<Character, Integer> hashMap2 = new HashMap<>();

        hashMap1 = addMap(str1);
        hashMap2 = addMap(str2);
        int count = 0;
        for(var val: hashMap1.entrySet()) {
            if(hashMap2.containsKey(val.getKey())) {
                count += Math.abs(hashMap2.get(val.getKey())
                        - val.getValue());
                hashMap2.remove(val.getKey());
            } else {
                count += val.getValue();
            }
        }
        for( var val: hashMap2.entrySet()) {
            count += val.getValue();
        }
        System.out.println(count);
    }

    public static HashMap<Character, Integer> addMap(String str) {
        char[] chars1 = str.toCharArray();

        HashMap<Character, Integer> hashMap1 = new HashMap<>();

        for( char ch1: chars1) {
            if(hashMap1.containsKey(ch1)) {
                hashMap1.put(ch1, hashMap1.get(ch1) + 1);
            } else {
                hashMap1.put(ch1, 1);
            }
        }

        return hashMap1;
    }
}