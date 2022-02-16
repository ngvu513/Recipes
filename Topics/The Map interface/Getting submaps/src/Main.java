import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        int size = scanner.nextInt();
        int count = 0;
        Map<Integer, String> map = new HashMap<>();
        while (scanner.hasNext() && count < size) {
            int key = scanner.nextInt();
            String val = scanner.next();
            if(key >= from && key <= to) {
                map.put(key,val);
            }
            count++;
        }
        map.forEach((k,v) -> {System.out.println(k + " " + v);});

    }
}
