import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        long count = map1.entrySet().stream()
                .filter(entry -> map2.containsKey(entry.getKey())
                        && map2.get(entry.getKey()).equals(entry.getValue()))
                .count();
        System.out.println(count);
    }
}