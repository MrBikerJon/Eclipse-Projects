package jonathan.furminger.dotsandboxes;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DotsAndBoxes dotsAndBoxes;
	private int rows = 10;
	private int columns = 10;
	private int width;
	private int height;
	
	public GamePanel(DotsAndBoxes dotsAndBoxes, int rows, int columns) {
		this.dotsAndBoxes = dotsAndBoxes;
		this.rows = rows;
		this.columns = columns;
		width = (columns + 2) * Box.SIZE;
		height = (rows + 2) * Box.SIZE;
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
}
