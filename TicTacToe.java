import java.nio.charset.StandardCharsets;
import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    static boolean gameWon = false;
    static Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
    static List<List<Integer>> winning = new ArrayList<>();
    static char[][] gameBoard;

    public static void main(String[] args) {
        gameBoard = new char[][]{{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameboard();

        initializeWinningConditions();

        while (!gameWon) {
            System.out.println("Enter your placement (1-9): ");
            int position = scan.nextInt();
            System.out.println();

            placePiece(position, "player");

            System.out.print(checkWinner());

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            placePiece(cpuPos, "CPU");

            printGameboard();
            System.out.print(checkWinner());
        }
    }

    public static void printGameboard() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(int position, String typeUser) {
        char symbol = 'X';

        if (typeUser.equals("CPU")) {
            symbol = 'O';
            if (checkSpace(position)) {
                cpuPositions.add(position);
            }
        } else {
            if (checkSpace(position)) {
                playerPositions.add(position);
            }
        }
        if (checkSpace(position)) {
            switch (position) {
                default:
                    System.err.println("Invalid inputs");
                    break;
                case 1:
                    gameBoard[0][0] = symbol;
                    break;
                case 2:
                    gameBoard[0][2] = symbol;
                    break;
                case 3:
                    gameBoard[0][4] = symbol;
                    break;
                case 4:
                    gameBoard[2][0] = symbol;
                    break;
                case 5:
                    gameBoard[2][2] = symbol;
                    break;
                case 6:
                    gameBoard[2][4] = symbol;
                    break;
                case 7:
                    gameBoard[4][0] = symbol;
                    break;
                case 8:
                    gameBoard[4][2] = symbol;
                    break;
                case 9:
                    gameBoard[4][4] = symbol;
                    break;
            }
        } else {
            if (typeUser.equals("player")) {
                System.out.println("Invalid Input try again.");
                System.out.println("Enter your placement (1-9): ");
                position = scan.nextInt();
                System.out.println();
                placePiece(position, typeUser);
            } else {
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                System.out.println(checkWinner());
                if (playerPositions.size() + cpuPositions.size() != 9) {
                    placePiece(cpuPos, "CPU");
                }
            }

        }
    }

    private static boolean checkSpace(int position) {
        switch (position) {
            case 1:
                return gameBoard[0][0] == ' ';
            case 2:
                return gameBoard[0][2] == ' ';
            case 3:
                return gameBoard[0][4] == ' ';
            case 4:
                return gameBoard[2][0] == ' ';
            case 5:
                return gameBoard[2][2] == ' ';
            case 6:
                return gameBoard[2][4] == ' ';
            case 7:
                return gameBoard[4][0] == ' ';
            case 8:
                return gameBoard[4][2] == ' ';
            case 9:
                return gameBoard[4][4] == ' ';
        }
        return false;
    }

    public static String checkWinner() {
        for (List<Integer> l : winning) {
            if (playerPositions.containsAll(l)) {
                gameWon = true;
                return "\nCongratulations you won";
            } else if (cpuPositions.containsAll(l)) {
                gameWon = true;
                System.out.print(cpuPositions);
                return "\nCPU wins! Sorry:(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                gameWon = true;
                printGameboard();
                return "Its a tie";
            }
        }

        return "";
    }

    private static void initializeWinningConditions() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);
    }
}
