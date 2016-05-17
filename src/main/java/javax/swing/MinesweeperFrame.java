package javax.swing;

import java.awt.HeadlessException;

@SuppressWarnings("serial")
public class MinesweeperFrame extends JFrame
{

	public MinesweeperFrame() throws HeadlessException
	{
		add(new MinesweeperPanel());
	}
}
