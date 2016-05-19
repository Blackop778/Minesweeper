package blackop778.minesweeper.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import blackop778.minesweeper.Minesweeper;

@SuppressWarnings("serial")
public class MinesweeperPanel extends JPanel
{

	public MinesweeperPanel()
	{
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(Minesweeper.status.equals("Normal"))
				{
					if(e.getY() > 70)
					{
						Minesweeper.board[e.getX() / 60][(e.getY() - 70) / 60]
								.clicked(SwingUtilities.isLeftMouseButton(e) ? true : false);
						repaint();
					}
				}
				else
				{
					Minesweeper.startGame();
					repaint();
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < 9; i++)
		{
			for(int n = 0; n < 9; n++)
			{
				Minesweeper.board[i][n].drawSquare(g, i * 60, n * 60 + 70);
			}
		}

		g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		if(Minesweeper.status.equals("Normal"))
		{
			g.drawString(String.valueOf(10 - Minesweeper.flags)
					+ " flags left. Right click to reveal a square, Left click to flag.", 10, 60);
		}
		else if(Minesweeper.status.equals("Win"))
		{
			long finalTime = System.currentTimeMillis();
			long timePassed = finalTime - Minesweeper.startTime;
			int seconds = (int) (timePassed / 1000);
			int minutes = seconds / 60;
			seconds = seconds % 60;
			g.drawString("Congratulations, you won in " + String.valueOf(minutes) + ":" + String.valueOf(seconds)
					+ ". Click to play again.", 10, 60);
		}
		else if(Minesweeper.status.equals("Loss"))
		{
			g.drawString("Sorry, you lose. Click to play again.", 10, 60);
		}
	}
}
