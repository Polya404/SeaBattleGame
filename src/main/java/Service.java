import java.util.Scanner;

public class Service {
    private static Scanner scanner = new Scanner(System.in);
    private static int[][] battlefield1 = new int[10][10];
    private static int[][] battlefield2 = new int[10][10];
    private static int[][] monitor1 = new int[10][10];
    private static int[][] monitor2 = new int[10][10];

    private static void makeTurn(String name, int[][] monitor, int[][] battlefield) {
        while (true) {
            System.out.println(name + ", please, make your turn");
            System.out.println("  0 1 2 3 4 5 6 7 8 9");
            for (int i = 0; i < monitor.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < monitor[1].length; j++) {
                    if (monitor[j][i] == 0) {
                        System.out.print("- ");
                    } else if (monitor[j][i] == 1) {
                        System.out.print(". ");
                    } else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
            System.out.println("Please enter OX coordinate: ");
            int x = scanner.nextInt();
            System.out.println("Please enter OY coordinate: ");
            int y = scanner.nextInt();

            if (battlefield[x][y] == 1) {
                System.out.println("Hit! Make your turn again");
                monitor[x][y] = 2;
            } else {
                System.out.println("Miss! Your opponents turn");
                monitor[x][y] = 1;
                break;
            }
        }
    }

    public static void game(String playerName1, String playerName2){
        LocationShip.placeShips(playerName1,battlefield1);
        LocationShip.placeShips(playerName2,battlefield2);
        makeTurn(playerName1,monitor1,battlefield2);
        makeTurn(playerName2,monitor2,battlefield1);
    }
}
