import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        // implement this method
        int num = -1;
        while(scanner.hasNext() && num != 0) {
            num = scanner.nextInt();
            if (num % 2 == 0 && num != 0) {
                System.out.println(num);
            }
        }
    }
}