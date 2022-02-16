import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for(int i = 0; i < count; i++) {
            String storeName = scanner.next();
            String hour = scanner.next();
            LocalTime time = LocalTime.parse(hour);
            if (time.isAfter(LocalTime.of(20,00))) {
                System.out.println(storeName);
            }
        }
    }
}