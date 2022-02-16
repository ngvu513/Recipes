import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);

        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.print(checkAnagrams(str1, str2));
    }
    public static  String checkAnagrams (String str1, String str2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        map1 = getMapChar(str1.toLowerCase());
        map2 = getMapChar(str2.toLowerCase());
        if(map1.equals(map2))
            return "yes";
        return "no";
    }

    public static HashMap<Character, Integer> getMapChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for(char ch : chars) {
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }
}
