import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char currentPlayer = PLAYER_X;
    private static char[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;
        boolean draw = false;

        while (!gameWon && !draw) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter row (1-3): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter column (1-3): ");
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                gameWon = checkWin();
                draw = checkDraw();

                if (!gameWon && !draw) {
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        printBoard();

        if (gameWon) {
            System.out.println("Congratulations! Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}