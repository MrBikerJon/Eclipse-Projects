package jonathan.furminger.wordhunt;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import jonathan.furminger.mycomponents.TitleLabel;

public class WordHuntServer extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private JTextField namesField = new JTextField("Player 1, Player 2");
	private JTextArea logArea = new JTextArea(10, 30);
	private JButton startStopButton = new JButton("Start");
	private ArrayList<String> names;
	

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WordHuntServer();
			}
		});
	}
	
	public WordHuntServer() {
		initGUI();
		setTitle("Word Hunt Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Word Hunt Server");
		add(titleLabel, BorderLayout.PAGE_START);
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		// options panel
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		mainPanel.add(optionsPanel);
		JLabel namesLabel = new JLabel("Invited Players (separate names with commas):");
		optionsPanel.add(namesLabel);
		namesField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		optionsPanel.add(namesField);
		
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		mainPanel.add(scrollPane);
		DefaultCaret caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void run() {
		
	}

}
