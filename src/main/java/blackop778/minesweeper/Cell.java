package blackop778.minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cell {
    private String type;
    private boolean revealed;
    private boolean flagged;
    private boolean exploded;
    private int x;
    private int y;
    private int neighbors;
    private boolean revealedByNeighbor;

    public Cell(String type, int x, int y) {
	this.type = type;
	revealed = false;
	flagged = false;
	exploded = false;
	this.x = x;
	this.y = y;
	revealedByNeighbor = false;
    }

    public void drawSquare(Graphics g, int x, int y) {
	drawCell(g, x, y);
	if (Minesweeper.revealAll) {
	    if (exploded) {
		drawExplosion(g, x, y);
	    }
	    if ((flagged && !exploded) && type.equals("mine")) {
		drawFlag(g, x, y);
	    } else if ((!flagged || exploded) && type.equals("mine")) {
		drawMine(g, x, y);
	    } else if (flagged && type.equals("empty")) {
		drawMine(g, x, y);
		drawX(g, x, y);
	    } else if (revealed) {
		drawMineCount(g, x, y);
	    }
	} else {
	    if (revealed) {
		drawMineCount(g, x, y);
	    } else if (flagged) {
		drawFlag(g, x, y);
	    }
	}
    }

    private void drawFlag(Graphics g, int x, int y) {
	g.setColor(Color.BLUE);
	g.fillRect(x + 30, y + 20, 15, 10);
	g.setColor(Color.BLACK);
	g.fillRect(x + 25, y + 20, 5, 25);
    }

    private void drawMine(Graphics g, int x, int y) {
	g.setColor(Color.BLACK);
	g.fillRect(x + 20, y + 20, 20, 20);
	g.fillRect(x + 27, y + 14, 6, 32);
	g.fillRect(x + 14, y + 27, 32, 6);
	g.fillRect(x + 14, y + 14, 6, 6);
	g.fillRect(x + 40, y + 14, 6, 6);
	g.fillRect(x + 14, y + 40, 6, 6);
	g.fillRect(x + 40, y + 40, 6, 6);
    }

    private void drawX(Graphics g, int x, int y) {
	g.setColor(Color.RED);
	// Top left to bottom right
	g.drawLine(x + 14, y + 14, x + 45, y + 45);
	g.drawLine(x + 15, y + 14, x + 45, y + 44);
	g.drawLine(x + 14, y + 15, x + 44, y + 45);
	// Bottom left to top right
	g.drawLine(x + 14, y + 45, x + 45, y + 14);
	g.drawLine(x + 14, y + 44, x + 46, y + 14);
	g.drawLine(x + 15, y + 45, x + 45, y + 13);
    }

    private void drawMineCount(Graphics g, int x, int y) {
	g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
	switch (neighbors) {
	case -1:
	    g.setColor(Color.RED);
	    break;
	case 0:
	    g.setColor(Color.BLACK);
	    break;
	case 1:
	    g.setColor(Color.BLUE);
	    break;
	case 2:
	    g.setColor(Color.CYAN);
	    break;
	case 3:
	    g.setColor(Color.GREEN);
	    break;
	case 4:
	    g.setColor(Color.YELLOW);
	    break;
	case 5:
	    g.setColor(Color.ORANGE);
	    break;
	case 6:
	    g.setColor(Color.RED);
	    break;
	case 7:
	    g.setColor(Color.PINK);
	    break;
	case 8:
	    g.setColor(Color.MAGENTA);
	    break;
	}
	g.drawString(String.valueOf(neighbors), x + 20, y + 40);
    }

    private void drawCell(Graphics g, int x, int y) {
	g.setColor(Color.LIGHT_GRAY);
	g.fillRect(x, y, 5, 60);
	g.fillRect(x, y, 60, 5);
	g.setColor(Color.GRAY);
	g.fillRect(x + 55, y + 5, 5, 55);
	g.fillRect(x + 5, y + 55, 55, 5);
    }

    private void drawExplosion(Graphics g, int x, int y) {
	g.setColor(Color.RED);
	g.fillRect(x + 5, y + 5, 50, 50);
    }

    public void clicked(boolean left) {
	if (!revealed) {
	    if (!left) {
		if (!flagged) {
		    flagged = true;
		    Minesweeper.flags++;
		    if (type.equals("mine"))
			Minesweeper.correctFlags++;
		    else
			Minesweeper.correctFlags--;
		} else {
		    Minesweeper.flags--;
		    flagged = false;
		    if (type.equals("mine"))
			Minesweeper.correctFlags--;
		    else
			Minesweeper.correctFlags++;
		}

		if (Minesweeper.correctFlags == Minesweeper.mines) {
		    Minesweeper.status = "Win";
		}
	    } else {
		if (!flagged) {
		    revealed = true;
		    if (type.equals("mine")) {
			exploded = true;
			Minesweeper.loss();
		    } else if (neighbors == 0)
			revealNeighbors();
		}
	    }
	}
    }

    public String getType() {
	return type;
    }

    public boolean getFlagged() {
	return flagged;
    }

    // Check how many of our neighbors are mines
    public void checkNeighbors() {
	neighbors = 0;

	if (!type.equals("mine")) {
	    if (x != 0) {
		if (Minesweeper.board[x - 1][y].getType().equals("mine"))
		    neighbors++;
		if (y != 0) {
		    if (Minesweeper.board[x - 1][y - 1].getType().equals("mine"))
			neighbors++;
		}
		if (y != 8) {
		    if (Minesweeper.board[x - 1][y + 1].getType().equals("mine"))
			neighbors++;
		}
	    }

	    if (x != 8) {
		if (Minesweeper.board[x + 1][y].getType().equals("mine"))
		    neighbors++;
		if (y != 0) {
		    if (Minesweeper.board[x + 1][y - 1].getType().equals("mine"))
			neighbors++;
		}
		if (y != 8) {
		    if (Minesweeper.board[x + 1][y + 1].getType().equals("mine"))
			neighbors++;
		}
	    }

	    if (y != 0)
		if (Minesweeper.board[x][y - 1].getType().equals("mine"))
		    neighbors++;

	    if (y != 8)
		if (Minesweeper.board[x][y + 1].getType().equals("mine"))
		    neighbors++;
	} else
	    neighbors = -1;
    }

    private void revealNeighbors() {
	if (!flagged) {
	    revealed = true;

	    if (neighbors == 0 && !revealedByNeighbor) {
		revealedByNeighbor = true;

		if (x != 0) {

		    Minesweeper.board[x - 1][y].revealNeighbors();
		    if (y != 0) {
			Minesweeper.board[x - 1][y - 1].revealNeighbors();
		    }
		    if (y != 8) {
			Minesweeper.board[x - 1][y + 1].revealNeighbors();
		    }
		}

		if (x != 8) {
		    Minesweeper.board[x + 1][y].revealNeighbors();
		    if (y != 0) {
			Minesweeper.board[x + 1][y - 1].revealNeighbors();
		    }
		    if (y != 8) {
			Minesweeper.board[x + 1][y + 1].revealNeighbors();
		    }
		}

		if (y != 0)
		    Minesweeper.board[x][y - 1].revealNeighbors();

		if (y != 8)
		    Minesweeper.board[x][y + 1].revealNeighbors();
	    }
	}
    }
}
