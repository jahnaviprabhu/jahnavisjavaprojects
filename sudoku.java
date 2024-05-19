import java.util.Random;
import java.util.Scanner;

class sudoku {
    private static final int SIZE = 9;
    private static final int SUBGRID = 3;
    private static final int EMPTY = 0;
    private static int[][] solvedBoard;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose difficulty level
        System.out.println("Select difficulty level: easy, medium, hard");
        String level = scanner.nextLine();
        int emptyCells;
        switch (level) {
            case "easy":
                emptyCells = 20;
                break;
            case "medium":
                emptyCells = 40;
                break;
            case "hard":
                emptyCells = 60;
                break;
            default:
                System.out.println("Invalid level. Defaulting to easy.");
                emptyCells = 20;
        }

        int[][] board = generateBoard(emptyCells);
        printBoard(board);

        while (!isSolved(board)) {
            System.out.println("Enter your move (row column number), 'answer' to see the solution, or 'exit' to quit:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("answer")) {
                printBoard(solvedBoard);
                break;
            }

            try {
                String[] parts = input.split(" ");
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int num = Integer.parseInt(parts[2]);

                if (isValidMove(board, row, col, num)) {
                    board[row][col] = num;
                    printBoard(board);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row, column, and number separated by spaces.");
            }
        }

        if (isSolved(board)) {
            System.out.println("Congratulations! You solved the Sudoku puzzle.");
        }

        scanner.close();
    }

    // Generates a Sudoku board
    public static int[][] generateBoard(int emptyCells) {
        int[][] board = new int[SIZE][SIZE];
        fillDiagonalSubgrids(board);
        solveSudoku(board);
        // Store the solved board
        solvedBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, solvedBoard[i], 0, SIZE);
        }
        removeElements(board, emptyCells);
        return board;
    }

    // Fills the diagonal subgrids
    private static void fillDiagonalSubgrids(int[][] board) {
        for (int i = 0; i < SIZE; i += SUBGRID) {
            fillSubgrid(board, i, i);
        }
    }

    // Fills a 3x3 subgrid starting at (row, col)
    private static void fillSubgrid(int[][] board, int row, int col) {
        Random rand = new Random();
        for (int i = 0; i < SUBGRID; i++) {
            for (int j = 0; j < SUBGRID; j++) {
                int num;
                do {
                    num = rand.nextInt(SIZE) + 1;
                } while (!isSafe(board, row + i, col + j, num));
                board[row + i][col + j] = num;
            }
        }
    }

    // Solves the Sudoku puzzle using backtracking
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if placing a number in a specific position is safe
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        return !isInRow(board, row, num) &&
                !isInCol(board, col, num) &&
                !isInSubgrid(board, row - row % SUBGRID, col - col % SUBGRID, num);
    }

    // Checks if a number is in a specific row
    private static boolean isInRow(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    // Checks if a number is in a specific column
    private static boolean isInCol(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    // Checks if a number is in a specific 3x3 subgrid
    private static boolean isInSubgrid(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < SUBGRID; row++) {
            for (int col = 0; col < SUBGRID; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // Removes elements from the board to create a puzzle
    private static void removeElements(int[][] board, int count) {
        Random rand = new Random();
        while (count > 0) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (board[row][col] != EMPTY) {
                board[row][col] = EMPTY;
                count--;
            }
        }
    }

    // Checks if the board is completely solved
    private static boolean isSolved(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if a move is valid
    private static boolean isValidMove(int[][] board, int row, int col, int num) {
        return board[row][col] == EMPTY && isSafe(board, row, col, num);
    }

    // Prints the Sudoku board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % SUBGRID == 0 && row != 0) {
                System.out.println("----+---------+---------+----");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % SUBGRID == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(" ");
                System.out.print(board[row][col] == EMPTY ? " " : board[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
