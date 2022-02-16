import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String character = scanner.next();
        char[] chars = input.toCharArray();
        List<String> characterList = new ArrayList<>();
        for(char ch: chars) {
            characterList.add(String.valueOf(ch));
        }
        System.out.println(Collections.frequency(characterList, character));
    }
}