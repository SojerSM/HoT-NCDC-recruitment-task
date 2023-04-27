import java.util.Scanner;

public class Bricks {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

        scanner.close();
    }
}
