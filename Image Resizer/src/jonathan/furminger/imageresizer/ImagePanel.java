package jonathan.furminger.imageresizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ImageResizer imageResizer = null;
	private BufferedImage image = null;
	
	public ImagePanel(ImageResizer imageResizer) {
		this.imageResizer = imageResizer;
	}
	
	public Dimension getPreferredSize() {
		Dimension size;
		if(image == null) {
			size = new Dimension(640, 480);
		}
		else {
			int width = image.getWidth();
			int height = image.getHeight();
			size = new Dimension(width, height);
		}
		return size;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0,  0,  getWidth(),  getHeight());
		g.drawImage(image, 0, 0, null);
	}

}
