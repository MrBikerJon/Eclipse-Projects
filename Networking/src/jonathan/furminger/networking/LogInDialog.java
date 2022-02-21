package jonathan.furminger.networking;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private boolean canceled = false;
	private JTextField ipAddressField = new JTextField(2);
	private JTextField userNameField = new JTextField(2);
	
	public LogInDialog(String appName) {
		setTitle("Log in to " + appName);
		initGUI();
		setModal(true);
		pack();setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	private void initGUI() {
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		JLabel ipAddressLabel = new JLabel("IP Address: ");
		mainPanel.add(ipAddressLabel);
		mainPanel.add(ipAddressField);
		JLabel userNameLabel = new JLabel("User Name: ");
		mainPanel.add(userNameLabel);
		mainPanel.add(userNameField);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.PAGE_END);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		buttonPanel.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		buttonPanel.add(cancelButton);
		getRootPane().setDefaultButton(okButton);
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cancel();
			}
		});
	}
	
	public void ok() {
		canceled = false;
		setVisible(false);
	}
	
	public void cancel() {
		canceled = true;
		setVisible(false);
	}
	
}
