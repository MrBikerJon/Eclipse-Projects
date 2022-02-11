package jonathan.furminger.imageresizer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class ImageResizer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String IMAGE_LOAD = "/Load16.gif";
	private static final String IMAGE_SAVE = "/Save16.gif";
	private static final String IMAGE_SCALE = "/Scale16.gif";
	private static final String IMAGE_WIDTH = "/Width16.gif";
	private static final String IMAGE_HEIGHT = "/Height16.gif";
	private static final String IMAGE_CROP = "/Crop16.gif";
	private static final String IMAGE_X = "/x16.gif";
	private static final String IMAGE_Y = "/y16.gif";
	
	private ImagePanel imagePanel = new ImagePanel(this);

	public ImageResizer() {
		initGUI();
		setTitle("Image Resizer");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ImageResizer();
			}
		});

	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Image Resizer");
		add (titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		
		// toolbar
		ImageIcon loadIcon = new ImageIcon(getClass().getResource(IMAGE_LOAD));
		ImageIcon saveIcon = new ImageIcon(getClass().getResource(IMAGE_SAVE));
		ImageIcon scaleIcon = new ImageIcon(getClass().getResource(IMAGE_SCALE));
		ImageIcon widthIcon = new ImageIcon(getClass().getResource(IMAGE_WIDTH));
		ImageIcon heightIcon = new ImageIcon(getClass().getResource(IMAGE_HEIGHT));
		ImageIcon cropIcon = new ImageIcon(getClass().getResource(IMAGE_CROP));
		ImageIcon xIcon = new ImageIcon(getClass().getResource(IMAGE_X));
		ImageIcon yIcon = new ImageIcon(getClass().getResource(IMAGE_Y));
		
		JToolBar toolbar = new JToolBar();
		mainPanel.add(toolbar, BorderLayout.PAGE_START);
		
		JButton loadButton = new JButton(loadIcon);
		loadButton.setToolTipText("Load Image");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		toolbar.add(loadButton);
		
		
		// scale options
		
		// crop options
		
		// image panel
		mainPanel.add(imagePanel, BorderLayout.CENTER);
		
	}
	
	private void load() {
		File file = new File("slidingTilesImage.jpg");
		try {
			BufferedImage image = ImageIO.read(file);
			imagePanel.setImage(image);
		}
		catch (IOException e) {
			String message =  "Could not open the file " + file.getPath();
			JOptionPane.showMessageDialog(this, message);
		}

	}

}
