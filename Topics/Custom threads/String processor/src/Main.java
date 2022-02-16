import java.util.Locale;
import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        // implement this method
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if(input.toUpperCase().equals(input)) {
                System.out.println("FINISHED");
                break;
            } else {
                System.out.println(input.toUpperCase());
            }
        }

    }
}