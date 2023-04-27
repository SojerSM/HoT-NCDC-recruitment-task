import generator.Generator;

import java.util.Scanner;

/**
 * NCDC House of Talents recruitment task.
 * If doesn't compile, try to restart your computer. That's weird, works on my PC though...
 *
 * @author Sebastian Mazur
 * @version 0.0.2
 * @since 2023-04-27
 */
public class Bricks {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Generator generator = new Generator();
        generator.populateList(10000000);

        generator.writeToTheFile();
    }
}
