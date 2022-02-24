package Models;

import java.util.UUID;

public class Player {
    private final UUID uuid;
    private final String name;
    private long currentScore;
    private boolean rolled1;
    private boolean isPenalty;

    public Player(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.currentScore = 0;
        this.rolled1 = false;
        this.isPenalty = false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(long currentScore) {
        this.currentScore = currentScore;
    }

    public boolean isRolled1() {
        return rolled1;
    }

    public void setRolled1(boolean rolled1) {
        this.rolled1 = rolled1;
    }

    public boolean isPenalty() {
        return isPenalty;
    }

    public void setPenalty(boolean penalty) {
        isPenalty = penalty;
    }
}
