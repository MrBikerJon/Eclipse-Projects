package jonathan.furminger.slidingtiles;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TileButton extends JButton {

	private static final long serialVersionUID = 1L;
	private static int tileSize, maxTiles = 0;
	private ImageIcon imageIcon;
	private int imageId, row, col = 0;
	

public void setImage(ImageIcon imageIcon, int imageId) {
	this.imageIcon = imageIcon;
	this.imageId = imageId;
	if(imageId == maxTiles - 1) {
		setIcon(null);
	}
	else {
		setIcon(imageIcon);
	}
}

public TileButton(ImageIcon imageIcon, int imageId, int row, int col) {
	this.row = row;
	this.col = col;
	setImage(imageIcon, imageId);
	setBackground(Color.white);
	setBorder(null);
	Dimension size = new Dimension(tileSize, tileSize);
	setPreferredSize(size);
	setFocusPainted(false);
}

}
