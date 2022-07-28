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
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i] = 1;
                } else {
                    battlefield[x + i][y] = 1;
                }
            }
            deck--;
        }
    }


    private static void drawField(int[][] battlefield) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < battlefield[1].length; j++) {
                if (battlefield[j][i] == 0) {
                    System.out.print("- ");
                }else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
