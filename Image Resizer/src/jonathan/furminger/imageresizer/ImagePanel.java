package jonathan.furminger.imageresizer;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageResizer imageResizer = null;
	private BufferedImage image = null;
	
	public ImagePanel(ImageResizer imageResizer) {
		this.imageResizer = imageResizer;
	}

}
