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
            LocationShip.clearConsole();
        }
    }

    public static void game(String playerName1, String playerName2) {
        LocationShip.placeShips(playerName1, battlefield1);
        LocationShip.placeShips(playerName2, battlefield2);
        while (true) {
            makeTurn(playerName1, monitor1, battlefield2);
            if (isWinCondition(playerName1, playerName2)) {
                break;
            }
            makeTurn(playerName2, monitor2, battlefield1);
            if (isWinCondition(playerName1, playerName2)) {
                break;
            }
        }
    }

    private static boolean isWinCondition(String name1, String name2) {
        int count1 = 0;
        for (int[] ints : monitor1) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    count1++;
                }
            }
        }
        int count2 = 0;
        for (int[] ints : monitor2) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    count2++;
                }
            }
        }
        if (count1 >= 10) {
            System.out.println(name1 + " WIN");
            return true;
        }
        if (count2 >= 10) {
            System.out.println(name2 + " WIN");
            return true;
        }
        return false;
    }

    protected static boolean isAvailable(int x, int y, int deck, int rotation, int[][] battlefield){
        // out of bound check
        if(rotation == 1){
            if(y + deck > battlefield.length){
                return false;
            }
        }
        if (rotation==2){
            if(x + deck > battlefield[0].length){
                return false;
            }
        }

        //neighbours check without diagonals
        while (deck !=0){
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation==1){
                    yi = i;
                }else {
                    xi = i;
                }
                if (x+1 + xi < battlefield.length && x +1 + xi >= 0){
                    if (battlefield[x + 1 + xi][y + yi] != 0){
                        return false;
                    }
                }
                if (x-1 + xi < battlefield.length && x -1 + xi >= 0){
                    if (battlefield[x - 1 + xi][y + yi] != 0){
                        return false;
                    }
                }
                if (y+1 + yi < battlefield.length && y +1 + yi >= 0){
                    if (battlefield[x + xi][y + 1 + yi] != 0){
                        return false;
                    }
                }
                if (y-1 + yi < battlefield.length && y -1 + yi >= 0){
                    if (battlefield[x + xi][y - 1 + yi] != 0){
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }
}
