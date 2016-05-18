package blackop778.minesweeper;

import javax.swing.JFrame;

import blackop778.minesweeper.graphics.MinesweeperFrame;

public abstract class Minesweeper
{
	public static Cell[][] board;

	public static void main(String[] args)
	{
		MinesweeperFrame frame = new MinesweeperFrame();
		frame.setTitle("Minesweeper");
		frame.setSize(540, 540);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		startGame();
	}

	static void startGame()
	{
		boolean[] mine = new boolean[81];
		board = new Cell[9][9];
		for(int i = 0; i < 81; i++)
		{
			mine[i] = i < 10 ? true : false;
		}

		// Scramble the mines
		for(int i = 0; i < mine.length; i++)
		{
			int index = (int) (Math.random() * mine.length);
			boolean temp = mine[i];
			mine[i] = mine[index];
			mine[index] = temp;
		}

		// Fill the board
		int index = 0;
		for(int i = 0; i < 9; i++)
		{
			for(int n = 0; n < 9; n++)
			{
				board[i][n] = new Cell(mine[index] ? "mine" : "empty");
				index++;
			}
		}
	}

	static void checkWin()
	{

	}
}
