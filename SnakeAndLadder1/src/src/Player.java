package src;

import java.util.Random;

public class Player {
    public String name;
    public int tilePlace;

    public Player(String name) {
        this.name = name;
        tilePlace = 0;
    }

    public int getTilePlace() {
        return tilePlace;
    }

    public int diceRoll()
    {
        Random r = new Random();
        return r.nextInt(1,7);
    }
}
