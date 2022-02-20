package jonathan.furminger.networking;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CheckPortAndIPAddress extends JFrame {

	private static final long serialVersionUID = 1L;
	public JTextArea infoArea = new JTextArea(6, 25);
	

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch(Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CheckPortAndIPAddress();
			}
		});
	}
	
	public CheckPortAndIPAddress() {
		initGUI();
		setTitle("Check Port and IP Address");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			// port number
			// creating a server socket at port 0 connects to a random available port
			ServerSocket serverSocket = new ServerSocket(0);
			int port = serverSocket.getLocalPort();
			serverSocket.close();
			infoArea.setText(port + " is an available port number");
			
			// private IP address
			InetAddress host = InetAddress.getLocalHost();
			String hostName = host.getHostName();
			String privateIPAddress = host.getHostAddress();
			infoArea.append("\n" + hostName + " is the host name");
			infoArea.append("\n" + privateIPAddress + " is the private IP address");
			
			// public IP address
			URL url = new URL("http://checkip.amazonaws.com/");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String publicIPAddress = in.readLine();
			in.close();
			infoArea.append("\n" + publicIPAddress + " is the public IP address");
		}
		catch (Exception e) {
			infoArea.append(e.getMessage());
		}
		
	}
	
	private void initGUI() {
		add(infoArea, BorderLayout.CENTER);
		
	}

}
