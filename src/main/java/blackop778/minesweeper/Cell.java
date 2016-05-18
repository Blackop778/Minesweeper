package blackop778.minesweeper;

import java.awt.Color;
import java.awt.Graphics;

public class Cell
{
	private String type;
	private boolean revealed;
	private boolean flagged;

	public Cell(String type)
	{
		this.type = type;
		revealed = false;
		flagged = false;
	}

	public void drawSquare(Graphics g, int x, int y)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x - 60, y - 60, 5, 60);
	}

	public void drawMines(Graphics g, int x, int y)
	{

	}

	public void clicked(boolean left)
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
		}
	}
}
