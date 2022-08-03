import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LocationShip {
    private static Scanner scanner = new Scanner(System.in);
    private static int direction;

    public static void placeShips(String name, int[][] battlefield) {
        int deck = 4;
        while (deck >= 1) {
            System.out.println(name + " please place your " + deck + "-deck ships on the battlefield");
            System.out.println();

            drawField(battlefield);

            System.out.println("Please enter OX coordinate: ");
            int x = scanner.nextInt();
            System.out.println("Please enter OY coordinate: ");
            int y = scanner.nextInt();
            System.out.println("Choose direction: ");
            System.out.println("1. Vertical.");
            System.out.println("2. Horizontal.");
            direction = scanner.nextInt();
            if (!Service.isAvailable(x, y, deck, direction, battlefield)) {
                System.out.println("Wrong coordinates");
                continue;
            }
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i] = 1;
                } else {
                    battlefield[x + i][y] = 1;
                }
            }
            deck--;
            clearConsole();
        }
    }

    public static void placeShips(int[][] battlefield) {
        Random random = new Random();
        int deck = 4;
        while (deck >= 1) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            direction = random.nextInt(2)+1;
            if (!Service.isAvailable(x, y, deck, direction, battlefield)) {
                continue;
            }
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i] = 1;
                } else {
                    battlefield[x + i][y] = 1;
                }
            }
            deck--;
            clearConsole();
        }
    }

    protected static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void drawField(int[][] battlefield) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < battlefield[1].length; j++) {
                if (battlefield[j][i] == 0) {
                    System.out.print("- ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
