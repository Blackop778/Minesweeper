package blackop778.minesweeper;

import javax.swing.JFrame;

import blackop778.minesweeper.graphics.MinesweeperFrame;

public abstract class Minesweeper {
    public static Cell[][] board;
    public static boolean revealAll;
    public static String status;
    public static int flags;
    public static int correctFlags;
    public static long startTime;
    public static int mines;
    public static final int SHUFFLECOUNT = 3;

    public static void main(String[] args) {
	startGame();

	MinesweeperFrame frame = new MinesweeperFrame();
	frame.setTitle("Minesweeper");
	frame.setSize(545, 638);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }

    public static void startGame() {
	boolean[] mine = new boolean[81];
	revealAll = false;
	board = new Cell[9][9];
	status = "Normal";
	flags = 0;
	correctFlags = 0;
	mines = 10;
	startTime = System.currentTimeMillis();
	for (int i = 0; i < 81; i++) {
	    mine[i] = i < mines ? true : false;
	}

	// Scramble the mines
	for (int n = 0; n < SHUFFLECOUNT; n++) {
	    for (int i = 0; i < mine.length; i++) {
		int index = (int) (Math.random() * mine.length);
		boolean temp = mine[i];
		mine[i] = mine[index];
		mine[index] = temp;
	    }
	}

	// Fill the board
	int index = 0;
	for (int i = 0; i < 9; i++) {
	    for (int n = 0; n < 9; n++) {
		board[i][n] = new Cell(mine[index] ? "mine" : "empty", i, n);
		index++;
	    }
	}

	// Have all the cells check their neighbors
	for (Cell[] cells : board) {
	    for (Cell cell : cells) {
		cell.checkNeighbors();
	    }
	}
    }

    static void loss() {
	revealAll = true;
	status = "Loss";
    }
}
