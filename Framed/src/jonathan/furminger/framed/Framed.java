package jonathan.furminger.framed;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

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
		initGUI();
		
		setTitle("Framed");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Framed");
		add(titleLabel, BorderLayout.PAGE_START);
	}

}
