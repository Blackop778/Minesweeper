package blackop778.minesweeper.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import blackop778.minesweeper.Minesweeper;

@SuppressWarnings("serial")
public class MinesweeperPanel extends JPanel
{

	public MinesweeperPanel()
	{

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < 9; i++)
		{
			for(int n = 0; n < 9; n++)
			{
				Minesweeper.board[i][n].drawSquare(g, i * 60, n * 60);
			}
		}
	}
}
