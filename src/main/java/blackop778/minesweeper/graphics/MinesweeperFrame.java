package blackop778.minesweeper.graphics;

import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MinesweeperFrame extends JFrame
{

	public MinesweeperFrame() throws HeadlessException
	{
		add(new MinesweeperPanel());
	}
}
