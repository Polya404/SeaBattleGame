import java.util.Scanner;

public class LocationShip {
    private static Scanner scanner = new Scanner(System.in);
    private static int[][] battlefield1 = new int[10][10];
    private static int[][] battlefield2 = new int[10][10];
    private static int x;
    private static int y;
    private static int direction;

    public static void placeShips(int[][] battlefield) {
        int deck = 4;
        while (deck >= 1) {
            question(deck);
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

    private static void question(int i ){
        System.out.println("Please place your " + i + "-deck ships on the battlefield");
        System.out.println();

        drawField(battlefield1);

        System.out.println("Please enter OX coordinate: ");
        x = scanner.nextInt();
        System.out.println("Please enter OY coordinate: ");
        y = scanner.nextInt();
        System.out.println("Choose direction: ");
        System.out.println("1. Vertical.");
        System.out.println("2. Horizontal.");
        direction = scanner.nextInt();
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
