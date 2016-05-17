package blackop778.minesweeper;

import javax.swing.JFrame;
import javax.swing.MinesweeperFrame;

public abstract class Minesweeper
{
	static Cell[][] board;

	public static void main(String[] args)
	{
		MinesweeperFrame frame = new MinesweeperFrame();
		frame.setTitle("Minesweeper");
		frame.setSize(750, 750);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		startGame();
	}

	static void startGame()
	{
		boolean[] mine = new boolean[81];
		for(int i = 0; i < 81; i++)
		{

		}
	}

	static void checkWin()
	{

	}
}
