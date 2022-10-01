import java.util.Scanner;

public class RunGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Player player1 = new Player();
    private static final Player player2 = new Player();

    public static void main(String[] args) {
        System.out.println("How many time you want game ?");
        int number;
        number = scanner.nextInt();

        do {
            if (number == 0) {
                break;
            }
            game();
            number--;
        } while (number != 0 && Service.playAgain());

    }

    /**
     * this method is the start of the game
     */
    private static void game() {
        System.out.println("Do you want game with opponent(2) or computer(1) ? ");
        int choose = scanner.nextInt();
        if (choose == 1) {
            System.out.println("Player1, please enter your name ?");
            player1.setName(scanner.next());
            Service.gameWithComputer(player1, new Player("Computer"));
        } else {
            System.out.println("Player1, please enter your name ?");
            player1.setName(scanner.next());
            System.out.println("Player2, please enter your name ?");
            player2.setName(scanner.next());
            Service.game(player1, player2);
        }
    }
}
