package helpers;

import java.util.Random;

public enum Characters {
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    I('I'),
    J('J'),
    K('K'),
    L('L'),
    M('M'),
    N('N'),
    O('O');

    private static final Random random = new Random();
    private final char character;

    Characters(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }

    public static String generateCode() {
        Characters[] characters = values();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int value = random.nextInt(characters.length);

            code.append(characters[value]);
        }

        return code.toString();
    }
}
