import java.util.Scanner;

public class RunGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player1 = new Player();
    private static Player player2 = new Player();

    public static void main(String[] args) {
        System.out.println("Do you want game with opponent(2) or computer(1) ? ");
        int choose = scanner.nextInt();
        if (choose == 1) {
            System.out.println("Player1, please enter your name ?");
            player1.setName(scanner.next());
            Service.gameWithComputer(player1.getName(), "Computer");
        } else {
            System.out.println("Player1, please enter your name ?");
            player1.setName(scanner.nextLine());
            System.out.println("Player2, please enter your name ?");
            player2.setName(scanner.nextLine());
            Service.game(player1.getName(), player2.getName());
        }
    }
}
