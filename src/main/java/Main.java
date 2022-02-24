import Models.Player;
import Utilities.DiceUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static RankTable rankTable = RankTable.RANK_TABLE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> playerList = new ArrayList<>();

        System.out.println("Welcome to DICE GAME!");
        System.out.print("Enter a winning score: ");
        long winningScore = sc.nextLong();
        rankTable.setWinningScore(winningScore);

        System.out.print("Enter number of players:");
        long numberOfPlayers = sc.nextLong();
        sc.nextLine();

        for(long i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter name for player "+(i+1)+":");
            String playerName = sc.nextLine();
            Player player = new Player(playerName);
            playerList.add(player);
        }

        Collections.shuffle(playerList);

        for(int i = 0; i < numberOfPlayers; i++) {
            Player player = playerList.get(i);
            if (player.getCurrentScore() >= winningScore) {
                if ((i+1) == numberOfPlayers && rankTable.getRankTable().size() != numberOfPlayers) {
                    i = 0;
                }
                continue;
            };
            int rolledNumber = 0;
            do {
                if (checkForPenalty(player)) break;
                if (rolledNumber == 6) {
                    System.out.print("Hello "+player.getName()+", You have rolled 6. Hence given 1 more chance. Please hit enter to roll the dice:");
                    sc.nextLine();
                } else {
                    System.out.print("Hello "+player.getName()+", Please hit enter to roll the dice:");
                    sc.nextLine();
                }
                rolledNumber = DiceUtility.rollDice();
                System.out.println("Rolled number is: "+rolledNumber);
                setPenalty(rolledNumber, player);
                updatePlayerScore(rolledNumber, player);
            } while(rolledNumber == 6);
            rolledNumber = 0;
            if ((i+1) == numberOfPlayers && rankTable.getRankTable().size() != numberOfPlayers) {
                i = -1;
            }
        }

        System.out.println("Thank you for playing Dice Game! Ranks are as follow:");
        rankTable.printRanks();
    }

    private static void updatePlayerScore(int rolledNumber, Player player) {
        player.setCurrentScore(player.getCurrentScore() + rolledNumber);
        if (player.getCurrentScore() >= rankTable.getWinningScore()) {
            rankTable.getRankTable().put(player.getUuid(), player);
            System.out.println("\t\t\t\tScore of "+player.getName()+" is "+player.getCurrentScore()+".");
        }
    }

    private static boolean checkForPenalty(Player player) {
        if (player.isPenalty()) {
            System.out.println("Hello "+player.getName()+", You have rolled 1 twice. Hence you have got a penalty and will be skipped for one roll.");
            player.setPenalty(false);
            player.setRolled1(false);
            return true;
        }
        return false;
    }

    private static void setPenalty(int rolledNumber, Player player) {
        if (player.isRolled1() && rolledNumber == 1) {
            player.setPenalty(true);
            player.setRolled1(false);
            return;
        } else if (rolledNumber == 1) {
            player.setRolled1(true);
            return;
        }
        player.setPenalty(false);
        player.setRolled1(false);
    }
}
