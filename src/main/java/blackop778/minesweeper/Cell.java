package blackop778.minesweeper;

import java.awt.Color;
import java.awt.Graphics;

import blackop778.minesweeper.graphics.MinesweeperPanel;

public class Cell
{
	private String type;
	private boolean revealed;
	private boolean flagged;
	private boolean exploded;

	public Cell(String type)
	{
		this.type = type;
		revealed = false;
		flagged = false;
		exploded = false;
	}

	public void drawSquare(Graphics g, int x, int y)
	{
		g.setColor(Color.LIGHT_GRAY);
		// Left
		g.fillRect(x - 60, y - 60, 5, 60);
		// Top
		g.fillRect(x - 60, y - 60, 60, 5);
		g.setColor(Color.GRAY);
		// Right
		g.fillRect(x - 5, y - 55, 5, 55);
		// Bottom
		g.fillRect(x - 55, y - 5, 55, 5);
		if(Minesweeper.revealAll)
		{
			if(flagged && type.equals("mine"))
			{
				g.setColor(Color.BLUE);
				g.fillRect(x - 30, y - 40, 15, 10);
				g.setColor(Color.BLACK);
				g.fillRect(x - 35, y - 40, 5, 25);
			}
			else if(flagged && type.equals("empty"))
			{
				// Draw a mine and red X through it
			}
			else if(!flagged && type.equals("mine"))
			{
				// Draw a mine
			}
		}
		else
		{
			if(flagged)
			{
				g.setColor(Color.BLUE);
				g.fillRect(x - 30, y - 40, 15, 10);
				g.setColor(Color.BLACK);
				g.fillRect(x - 35, y - 40, 5, 25);
			}
		}
	}

	public void clicked(boolean left, MinesweeperPanel panel)
	{
		if(!revealed)
		{
			if(!left)
			{
				if(!flagged)
				{
					flagged = true;
				}
				else
				{
					flagged = false;
				}
			}
			else
			{
				revealed = true;
				if(type.equals("mine"))
				{
					exploded = true;
				}
			}

			panel.repaint();
		}
	}
}
