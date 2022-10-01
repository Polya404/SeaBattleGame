import java.util.Random;
import java.util.Scanner;

public class Service {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[][] battlefield1 = new int[10][10];
    private static int[][] battlefield2 = new int[10][10];
    private static int[][] monitor1 = new int[10][10];
    private static int[][] monitor2 = new int[10][10];
    private static int count1 = 0;
    private static int count2 = 0;

    /**
     * this method performs a move and checks if the player hit the ship;
     * if there is a hit, then the move continues, if there is a miss, it goes to another player
     *
     * @param name        player name
     * @param monitor     player monitor
     * @param battlefield player field
     */
    private static void makeTurn(String name, int[][] monitor, int[][] battlefield) {
        while (true) {
            System.out.println(name + ", please, make your turn");
            hitTest(monitor);
            System.out.println("Please enter OX coordinate: ");
            int x = scanner.nextInt();
            System.out.println("Please enter OY coordinate: ");
            int y = scanner.nextInt();

            if (battlefield[x][y] == 1) {
                System.out.println("Hit! Make your turn again");
                monitor[x][y] = 2;
            } else if (monitor[x][y] == 1 && !(count1 < 10 | count2 < 10)) {
                System.out.println("You've been here before");
                makeTurn(name, monitor, battlefield);
            } else {
                System.out.println("Miss! Your opponents turn");
                monitor[x][y] = 1;
                break;
            }
            LocationShip.clearConsole();
        }
    }

    /**
     * this is an overloaded makeTurn method for playing with the computer,
     * the computer's turn is implemented using random values for the OX and OY coordinates
     *
     * @param monitor     computer monitor
     * @param battlefield computer field
     */
    private static void makeTurn(int[][] monitor, int[][] battlefield) {
        Random random = new Random();
        while (true) {
            hitTest(monitor);
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (battlefield[x][y] == 1) {
                monitor[x][y] = 2;
            } else if (monitor[x][y] == 1 && !(count2 < 10 | count1 < 10)) {
                makeTurn(monitor, battlefield);
            } else {
                System.out.println("Computer miss! Your turn");
                monitor[x][y] = 1;
                break;
            }
            LocationShip.clearConsole();
        }
    }

    /**
     * this method checks whether there was a hit, if it hit,
     * then a cell "X" will appear on the field, if a miss, then "."
     *
     * @param monitor player monitor
     */
    private static void hitTest(int[][] monitor) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < monitor.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < monitor[1].length; j++) {
                if (monitor[j][i] == 0) {
                    System.out.print("- ");
                } else if (monitor[j][i] == 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    /**
     * this method implements a game for two players,
     * where each player takes turns and after the move it
     * is checked whether the player has won or lost
     *
     * @param player1 player 1
     * @param player2 player 2
     */
    public static void game(Player player1, Player player2) {
        battlefield1 = new int[10][10];
        battlefield2 = new int[10][10];
        monitor1 = new int[10][10];
        monitor2 = new int[10][10];
        LocationShip.placeShips(player1.getName(), battlefield1);
        LocationShip.placeShips(player2.getName(), battlefield2);
        while (count1 < 10 && count2 < 10) {

            if (isWinCondition(player1, player2)) {
                break;
            } else {
                makeTurn(player1.getName(), monitor1, battlefield2);
            }

            if (isWinCondition(player1, player2)) {
                break;
            } else {
                makeTurn(player2.getName(), monitor2, battlefield1);
            }
        }

    }

    /**
     * this method implements a game for one player in which he
     * takes turns with the computer, after the move the check is
     * won by the player or the computer
     *
     * @param player player
     * @param comp   computer
     */
    public static void gameWithComputer(Player player, Player comp) {
        battlefield1 = new int[10][10];
        battlefield2 = new int[10][10];
        monitor1 = new int[10][10];
        monitor2 = new int[10][10];
        LocationShip.placeShips(player.getName(), battlefield1);
        LocationShip.placeShips(battlefield2);
        while (count1 < 10 && count2 < 10) {
            if (isWinCondition(player, comp)) {
                break;
            } else {
                makeTurn(player.getName(), monitor1, battlefield2);
            }

            if (isWinCondition(player, comp)) {
                break;
            } else {
                makeTurn(monitor2, battlefield1);
            }
        }
    }

    /**
     * this method keeps score and gives one point for each deck
     * knocked down and when the number of points is equal to the
     * number of decks then the player is considered the winner
     *
     * @param player1 player 1
     * @param player2 player 2
     * @return boolean
     */
    private static boolean isWinCondition(Player player1, Player player2) {
        count1 = 0;
        for (int[] ints : monitor1) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    count1++;
                }
            }
        }
        count2 = 0;
        for (int[] ints : monitor2) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    count2++;
                }
            }
        }
        return winnerCheck(player1, player2, count1, count2);
    }

    /**
     * this method checks if the number of points is equal to the number of decks
     *
     * @param player1 player 1
     * @param player2 player 2
     * @param count1  first player's score
     * @param count2  second player's score
     * @return boolean
     */
    private static boolean winnerCheck(Player player1, Player player2, int count1, int count2) {
        if (count1 >= 10) {
            System.out.println(player1.getName() + " WIN");
            int i1 = player1.getWin();
            player1.setWin(i1 + 1);
            int i2 = player2.getLoss();
            player2.setLoss(i2 + 1);
            return true;
        }
        if (count2 >= 10) {
            System.out.println(player2.getName() + " WIN");
            int i2 = player2.getWin();
            player2.setWin(i2 + 1);
            int i1 = player1.getLoss();
            player1.setLoss(i1 + 1);
            return true;
        }
        return false;
    }

    /**
     * this method checks by coordinates whether the ships are located correctly and whether they go beyond the field
     *
     * @param x           OX coordinate
     * @param y           OY coordinate
     * @param deck        number of ship decks
     * @param rotation    horizontal or vertical
     * @param battlefield field
     * @return boolean
     */
    protected static boolean isAvailable(int x, int y, int deck, int rotation, int[][] battlefield) {
        // out of bound check
        if (rotation == 1) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (rotation == 2) {
            if (x + deck > battlefield[0].length) {
                return false;
            }
        }

        //neighbours check
        while (deck != 0) {
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation == 1) {
                    yi = i;
                } else {
                    xi = i;
                }
                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0) {
                    if (battlefield[x + 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0) {
                    if (battlefield[x - 1 + xi][y + yi] != 0) {
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0) {
                    if (battlefield[x + xi][y + 1 + yi] != 0) {
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0) {
                    if (battlefield[x + xi][y - 1 + yi] != 0) {
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }

    /**
     * This method asks the player if he wants to play again
     *
     * @return boolean
     */
    public static boolean playAgain() {
        System.out.println("Do you want play again ? ... y/n");
        String userInput = scanner.next();
        userInput = userInput.toLowerCase();
        return userInput.equals("y");
    }
}
