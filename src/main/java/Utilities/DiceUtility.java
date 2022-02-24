package Utilities;

import java.util.Random;

public class DiceUtility {
    public static int rollDice() {
        Random random = new Random();
        return random.nextInt(7 - 1) + 1;
    }
}
