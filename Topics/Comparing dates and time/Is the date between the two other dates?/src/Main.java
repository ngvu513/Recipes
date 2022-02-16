import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate1 = LocalDate.parse(scanner.next());
        LocalDate localDate2 = LocalDate.parse(scanner.next());
        LocalDate localDate3 = LocalDate.parse(scanner.next());

        if((localDate1.isAfter(localDate2) && localDate1.isBefore(localDate3))
                ||(localDate1.isBefore(localDate2) && localDate1.isAfter(localDate3)))
        {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}