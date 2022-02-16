import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();
        String[] stringNewspaper = firstLine.split(" ");
        String[] stringNote = secondLine.split(" ");

        Map<String, Integer> map = new HashMap<>();
        for( String str : stringNewspaper) {
            if(map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        boolean success = true;
        for(String note: stringNote) {
            if(map.containsKey(note)) {
                if(map.get(note) > 0) {
                    map.put(note, map.get(note) -1);
                } else {
                    map.remove(note);
                    success = false;
                    break;
                }
            } else {
                success = false;
                break;
            }
        }

        System.out.println(success ? "You get money" : "You are busted");

    }
}
