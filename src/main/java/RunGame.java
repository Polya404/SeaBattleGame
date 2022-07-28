import java.util.Scanner;

public class RunGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player1 = new Player();
    private static Player player2 = new Player();

    public static void main(String[] args) {
        System.out.println("Player1, please enter your name ?");
        player1.setName(scanner.nextLine());
        System.out.println("Player2, please enter your name ?");
        player2.setName(scanner.nextLine());
        Service.game(player1.getName(), player2.getName());
    }
}
