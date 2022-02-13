package jonathan.furminger.bakersdozen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jonathan.furminger.mycomponents.TitleLabel;

public class BakersDozen extends JFrame {

	private static final long serialVersionUID = 1L;
	private TablePanel tablePanel = new TablePanel();

	public BakersDozen() {
		initGUI();
		setTitle("Baker's Dozen");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} 
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BakersDozen();
			}
		});
			
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Baker's Dozen");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// table panel
		add(tablePanel, BorderLayout.CENTER);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		JButton newButton = new JButton("New Game");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePanel.newGame();
			}
		});
		buttonPanel.add(newButton);
		
		JButton replayButton = new JButton("Replay");
		replayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePanel.replay();
			}
		});
		buttonPanel.add(replayButton);
	}

}
