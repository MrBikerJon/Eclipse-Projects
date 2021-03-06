package jonathan.furminger.wordbuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LetterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Color BROWN = new Color(49, 22, 3);
	private static final String IMAGENAME = "/WoodTile.jpg";
	
	private String letter = "";
	private int points = -1;
	private BufferedImage image = null;
	private int size = 40;
	private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);
	private FontMetrics bigFM;
	private FontMetrics smallFM;
	private int column = -1;
	
	

	public LetterPanel(String letter, int points) {
		this.letter = letter;
		this.points = points;
		initPanel();
	}
	
	public LetterPanel() {
		initPanel();
	}
	
	public void paintComponent(Graphics g) {
		if(letter.length() == 0) {
			g.setColor(BROWN);
			g.fillRect(0, 0, size, size);
		}
		else {
			if(image == null) {
				g.setColor(Color.WHITE);
				g.fillRect(0,  0,  size,  size);
			}
			else {
				g.drawImage(image,  0,  0,  this);
			}
			g.setColor(Color.BLACK);
			g.drawRect(0,  0,  size-1,  size-1);
			
			g.setFont(bigFont);
			int letterWidth = bigFM.stringWidth(letter);
			int x = (size-letterWidth)/2;
			int y = size * 3 / 4;
			g.drawString(letter, x, y);
			
			g.setFont(smallFont);
			letterWidth = smallFM.stringWidth("" + points);
			x = size - letterWidth - 2;
			y = size * 17 / 20;
			g.drawString("" + points, x, y);
		}
	}
	
	private void initPanel() {
		if(image == null) { 
			try {
				InputStream input = getClass().getResourceAsStream(IMAGENAME);
				
				image = ImageIO.read(input);
			}
			catch (IOException e) {
				String message = "File " + IMAGENAME + " could not be opened";
				JOptionPane.showMessageDialog(null,  message);
			}
		}
		bigFM = getFontMetrics(bigFont);
		smallFM = getFontMetrics(smallFont);
	}
	
	public Dimension preferredSize() {
		Dimension dimension = new Dimension(size, size);
		return dimension;
	}
	
	public String getLetter() {
		return letter;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getPanelSize() {
		return size;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setEmpty() {
		letter = "";
		points = -1;
		repaint();
	}
	
	public boolean isEmpty() {
		return points == -1;
	}
	
	public void copy(LetterPanel letterPanel2) {
		letter = letterPanel2.getLetter();
		points = letterPanel2.getPoints();
		column = letterPanel2.getColumn();
		repaint();
	}
	
	public void resize(int size) {
		this.size = size;
		bigFont = new Font(Font.DIALOG, Font.BOLD, size*3/4);
		smallFont = new Font(Font.DIALOG, Font.BOLD, size*3/10);
		bigFM = getFontMetrics(bigFont);
		smallFM = getFontMetrics(smallFont);
		repaint();
		revalidate();
	}
}
