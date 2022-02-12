package jonathan.furminger.imageresizer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	private File file = new File("");
	private double ratio = 1.0;
	private JTextField scaleWField = new JTextField("1", 5);
	private JTextField scaleHField = new JTextField("1", 5);
	private JTextField cropXField = new JTextField("0", 5);
	private JTextField cropYField = new JTextField("0", 5);
	private JTextField cropWField = new JTextField("0", 5);
	private JTextField cropHField = new JTextField("0", 5);

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
			String className = UIManager.getSystemLookAndFeelClassName();
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
		
		JButton saveButton = new JButton(saveIcon);
		saveButton.setToolTipText("Save Image");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		toolbar.add(saveButton);
		
		toolbar.addSeparator();
		
		// scale options
		JButton scaleButton = new JButton(scaleIcon);
		scaleButton.setToolTipText("Scale Image");
		scaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scale();
			}
		});
		toolbar.add(scaleButton);
		
		JLabel scaleWLabel = new JLabel(widthIcon);
		toolbar.add(scaleWLabel);
		
		scaleWField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedScaleW();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(scaleWField);
		
		JLabel scaleHLabel = new JLabel(heightIcon);
		toolbar.add(scaleHLabel);
		
		scaleHField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedScaleH();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(scaleHField);
		
		toolbar.addSeparator();
		
		// crop options
		JButton cropButton = new JButton(cropIcon);
		cropButton.setToolTipText("Crop Image");
		cropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crop();
			}
		});
		toolbar.add(cropButton);
		
		JLabel cropXLabel = new JLabel(xIcon);
		toolbar.add(cropXLabel);
		
		cropXField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedCropX();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(cropXField);
		
		JLabel cropYLabel = new JLabel(yIcon);
		toolbar.add(cropYLabel);
		
		cropYField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedCropY();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(cropYField);
		
		JLabel cropWLabel = new JLabel(widthIcon);
		toolbar.add(cropWLabel);
		
		cropWField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedCropW();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(cropWField);
		
		JLabel cropHLabel = new JLabel(heightIcon);
		toolbar.add(cropHLabel);
		
		cropHField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedCropH();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		toolbar.add(cropHField);
		
		
		// image panel
		JScrollPane scrollPane = new JScrollPane(imagePanel);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

	}
	
	private void load() {
		JFileChooser chooser = new JFileChooser(file);
		chooser.setFileFilter(new JPGFilter());
		int option = chooser.showOpenDialog(this);
		if(option == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			try {
				BufferedImage image = ImageIO.read(file);
				imagePanel.setImage(image);
				int width = image.getWidth();
				int height = image.getHeight();setScaleFields(width, height);
			}
			catch (IOException e) {
				String message =  "Could not open the file " + file.getPath();
				JOptionPane.showMessageDialog(this, message);
			}
		}
	}
	
	private void save() {
		JFileChooser chooser = new JFileChooser(file);
		int option = chooser.showSaveDialog(this);
		if(option == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			chooser.setSelectedFile(file);
			String fileName = file.getPath();
			String f = fileName.toLowerCase();
			if(!f.endsWith(".jpg")) {
				fileName += ".jpg";
				file = new File(fileName);
			}
			if (file.exists()) {
				String message = "Do you want to overwrite the file " + file.getPath();
				int replace = JOptionPane.showConfirmDialog(this, message, "Replace image?", JOptionPane.YES_NO_OPTION);
				if(replace == JOptionPane.YES_OPTION) {
					writeImage();
				}
			}
			else {
				writeImage();
			}
		}
	}
	
	private void writeImage() {
		BufferedImage image = imagePanel.getImage();
		try {
			ImageIO.write(image, "jpg", file);
		}
		catch (IOException e) {
			String message = "Could not save " + file.getPath();
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	private void setScaleFields(int width, int height) {
		scaleWField.setText("" + width);
		scaleHField.setText("" + height);
		double dWidth = (double) width;
		double dHeight = (double) height;
		ratio = dWidth / dHeight;
	}
	
	private void scale() {
		try {
			String w = scaleWField.getText();
			int newWidth = Integer.parseInt(w);
			String h = scaleHField.getText();
			int newHeight = Integer.parseInt(h);
			
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			
			Graphics g = newImage.createGraphics();
			
			BufferedImage oldImage = imagePanel.getImage();
			
			g.drawImage(oldImage, 0,  0,  newWidth,  newHeight,  this);
			g.dispose();
			imagePanel.setImage(newImage);
		}
		catch (Exception e) {
			String message = "The image could not be scaled.";
			JOptionPane.showMessageDialog(this, message);
		}

		
	}
	
	private void changedScaleW() {
		String s = scaleWField.getText();
		if(isValid(s, 1)) {
			int scaleW = Integer.parseInt(s);
			double dHeight = scaleW / ratio;
			int scaleH = (int) dHeight;
			if(scaleH < 1) {
				scaleH = 1;
			}
			scaleHField.setText("" + scaleH);
		}
		else {
			String message = s + " is not a valid width.";
			JOptionPane.showMessageDialog(this, message);
			scaleWField.requestFocus();
		}
	}
	
	private void changedScaleH() {
		String s = scaleHField.getText();
		if(isValid(s, 1)) {
			int scaleH = Integer.parseInt(s);
			double dWidth = scaleH * ratio;
			int scaleW = (int) dWidth;
			if(scaleW < 1) {
				scaleW = 1;
			}
			scaleWField.setText("" + scaleW);
		}
		else {
			String message = s + " is not a valid height.";
			JOptionPane.showMessageDialog(this, message);
			scaleHField.requestFocus();
		}
	}
	
	private boolean isValid(String s, int minVal) {
		boolean valid = false;
		try {
			int i = Integer.parseInt(s);
			if(i >= minVal) {
				valid = true;
			}	
		}
		catch (NumberFormatException e) {}
		return valid;
	}
	
	private void changedCropX() {
		String s = cropXField.getText();
		if(isValid(s, 0)) {
			int cropX = Integer.parseInt(s);
			imagePanel.setCropX(cropX);
		}
		else {
			String message = "The value " + s + " is invalid.";
			JOptionPane.showMessageDialog(this, message);
			cropXField.requestFocus();
		}
	}
	
	private void changedCropY() {
		String s = cropYField.getText();
		if(isValid(s, 0)) {
			int cropY = Integer.parseInt(s);
			imagePanel.setCropY(cropY);
		}
		else {
			String message = "The value " + s + "is invalid";
			JOptionPane.showMessageDialog(this,  message);
			cropYField.requestFocus();
		}
	}
	
	private void changedCropW() {
		String s = cropWField.getText();
		if(isValid(s, 0)) {
			int cropW = Integer.parseInt(s);
			imagePanel.setCropW(cropW);
		}
		else {
			String message = "The value " + s + " is invalid.";
			JOptionPane.showMessageDialog(this,  message);
			cropWField.requestFocus();
		}
	}
	
	private void changedCropH() {
		String s = cropHField.getText();
		if(isValid(s, 0)) {
			int cropH = Integer.parseInt(s);
			imagePanel.setCropH(cropH);
		}
		else {
			String message = "The value " + s + " is invalid.";
			JOptionPane.showMessageDialog(this, message);
			cropHField.requestFocus();
		}
	}
	
	private void crop() {
		try {
			String s = cropXField.getText();
			int cropX = Integer.parseInt(s);
			s = cropYField.getText();
			int cropY = Integer.parseInt(s);
			s = cropWField.getText();
			int cropW = Integer.parseInt(s);
			s = cropHField.getText();
			int cropH = Integer.parseInt(s);
			
			BufferedImage image = imagePanel.getImage();
			BufferedImage croppedImage = image.getSubimage(cropX,  cropY,  cropW,  cropH);
			imagePanel.setImage(croppedImage);
			setScaleFields(cropW, cropH);
			setCropFields(0, 0, 0, 0);
			imagePanel.resetCrop();
		}
		catch (Exception e) {
			String message = "The image could not be cropped";
			JOptionPane.showMessageDialog(this,  message);
		}

	}
	
	public void setCropFields(int cropX, int cropY, int cropW, int cropH) {
		cropXField.setText("" + cropX);
		cropYField.setText("" + cropY);
		cropWField.setText("" + cropW);
		cropHField.setText("" + cropH);
	}

}
