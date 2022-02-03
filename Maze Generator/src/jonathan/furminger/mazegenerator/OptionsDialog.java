package jonathan.furminger.mazegenerator;

import javax.swing.JDialog;

public class OptionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int rows, cols, type = 0;
	
	public OptionsDialog(int rows, int cols, int type) {
		this.rows = rows;
		this.cols = cols;
		this.type = type;
		
		setTitle("Maze Generator Options");
	}
}
