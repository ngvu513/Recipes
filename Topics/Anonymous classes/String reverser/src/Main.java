import java.util.Scanner;
import java.util.function.Function;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();


        StringReverser reverser = new StringReverser() {
            public String reverse(String str) {
                StringBuilder stringBuilder = new StringBuilder(str);
                Function<String, Integer> converter = Integer::parseInt;

                return stringBuilder.reverse().toString();

            }
        };
        /* create an instance of an anonymous class here,
                                     do not forget ; on the end */

        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);
    }

}