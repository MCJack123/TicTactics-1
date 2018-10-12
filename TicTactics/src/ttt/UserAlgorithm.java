import java.util.Scanner;

public class UserAlgorithm extends Algorithm {
    private boolean isX;
    private Scanner in;
    public UserAlgorithm(boolean x) {isX = x; in = new Scanner(System.in);}
    protected UserAlgorithm(boolean x, boolean s) {isX = x; if (s) in = new Scanner(System.in);}
    public void finalize() {in.close();}
    public Move getMove(int[][] board, int boardToPlayOn, String validBoards) {
        Move retval = new Move(0, 0);
        printBoard(board);
        setCurrentBoard(boardToPlayOn);
        if (boardToPlayOn == -1) {
            retval.bIndex = getNumber("What board will be played on?");
            setCurrentBoard(retval.bIndex);
        } else retval.bIndex = boardToPlayOn;
        retval.sIndex = getNumber("Which square will be played?");
        return retval;
    }

    protected int getNumber(String message) {
        System.out.print(message + " [0-8] ");
        return in.nextInt();
    }

    protected void setCurrentBoard(int b) {
        System.out.printf("Current board is %s.\n", convertBoard(b));
    }

    protected void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        System.out.print(getChar(board[i * 3 + k][j * 3 + l]));
                        if (l < 2) System.out.print("|");
                    }
                    if (k < 2) System.out.print("||");
                }
                if (j < 2) System.out.println("\n-+-+-||-+-+-||-+-+-");
            }
            if (i < 2) System.out.println("\n-----++-----++-----\n-----++-----++-----");
        }
        System.out.println();
    }

    protected String getChar(int num) {
        if (num == 0) return " ";
        else if ((num == 1) != isX) return "O";
        return "X";
    }

    protected String convertBoard(int b) {
        String retval = "";
        if (b == -1) return "any board";
        if (b < 3) retval = "top ";
        else if (b > 5) retval = "bottom ";
        else retval = "center ";
        if (b % 3 == 0) retval = retval + "left";
        else if (b % 3 == 1) retval = retval + "middle";
        else retval = retval + "right";
        return retval;
    }

    public static void main(String[] args) {
        UserAlgorithm user = new UserAlgorithm(true);
        TacTic comp = new TacTic(true);
        Game game = new Game(user, comp);
        game.playGame();
        System.out.print("Winner: ");
        System.out.println(game.winMessage);
    }
}
