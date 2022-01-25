package jonathan.furminger.framed;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Framed extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Framed();
				}
		});
	}
	
	public Framed() {
		setTitle("Framed");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
