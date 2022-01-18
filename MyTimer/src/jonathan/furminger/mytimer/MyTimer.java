package jonathan.furminger.mytimer;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class MyTimer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Font font = new Font(Font.DIALOG, Font.BOLD, 36);
	private TimerPanel timerPanel = new TimerPanel(10L, font);

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MyTimer();
			}
		});
	}
	
	public MyTimer() {
		initGUI();
		
		setTitle("My Timer");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
			TitleLabel titleLabel = new TitleLabel("My Timer");
			add(titleLabel, BorderLayout.PAGE_START);
			
			JPanel centerPanel = new JPanel();
			add(centerPanel, BorderLayout.CENTER);
			
			centerPanel.add(timerPanel);
			
	}
	

}
