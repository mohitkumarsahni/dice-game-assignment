import Models.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public enum RankTable {
    RANK_TABLE;

    private long winningScore;
    private final Map<UUID, Player> rankTable = new LinkedHashMap<>();

    public long getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(long winningScore) {
        this.winningScore = winningScore;
    }

    public Map<UUID, Player> getRankTable() {
        return rankTable;
    }

    public void printRanks() {
        long i = 0;
        for (UUID key : rankTable.keySet()) {
            System.out.println(i+1 + " "+rankTable.get(key).getName());
            i = i + 1;
        }
    }
}
