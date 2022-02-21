package jonathan.furminger.networking;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "LogIn.txt";
	
	private boolean canceled = false;
	private JTextField ipAddressField = new JTextField(2);
	private JTextField userNameField = new JTextField(2);
	
	public LogInDialog(String appName) {
		setTitle("Log in to " + appName);
		initGUI();
		setModal(true);
		pack();setLocationRelativeTo(null);
		setResizable(false);
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(FILE_NAME)));
			String ipAddress = in.readLine();
			ipAddressField.setText(ipAddress);
			String userName = in.readLine();
			userNameField.setText(userName);
			in.close();
		}
		catch (FileNotFoundException e) {}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "An error was encountered when reading from " + FILE_NAME);
		}
		
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
		String ipAddress = ipAddressField.getText().trim();
		String userName = userNameField.getText().trim();
		if(ipAddress.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please enter a valid IP address");
		}
		else if (userName.length() == 0) {
			JOptionPane.showMessageDialog(this,  "Please enter the user name");
		}
		else {
			canceled = false;
			setVisible(false);
			
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
				out.write(ipAddress);
				out.newLine();
				out.write(userName);
				out.close();
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "An error was encountered when writing to " + FILE_NAME);
			}
		}
	}
	
	public void cancel() {
		canceled = true;
		setVisible(false);
	}
	
	public String getIpAddress() {
		return ipAddressField.getText().trim();
	}
	
	public String getUserName() {
		return userNameField.getText().trim();
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
}
