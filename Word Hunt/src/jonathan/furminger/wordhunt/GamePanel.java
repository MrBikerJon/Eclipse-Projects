package jonathan.furminger.wordhunt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WordHunt wordHunt;
	private int width = LetterTile.SIZE * 5;
	private int height = LetterTile.SIZE * 5;
	
	private static final Font FONT = new Font("Dialog", Font.BOLD, 50);
	private LetterTile[][] tiles = new LetterTile[5][5];
	private FontMetrics fm;
	
	private static final Color HIGHLIGHT = new Color(255, 255, 0, 120);
	private static final BasicStroke WIDE_STROKE = new BasicStroke(55.0f, 
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	private boolean allowInput = false;
	private int row = -1;
	private int col = -1;
	private GeneralPath selectedPath = new GeneralPath();
	private String path = "";
	
	public GamePanel(WordHunt wordHunt) {
		this.wordHunt = wordHunt;
		fm = getFontMetrics(FONT);
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				tiles[row][col] = new LetterTile("", fm, row, col);
			}
		}
		initGUI();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		// selected path
		g.setColor(HIGHLIGHT);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(WIDE_STROKE);
		g2D.draw(selectedPath);
		System.out.println("drawn selected path");
		
		// letter tiles
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				tiles[row][col].draw(g);;
			}
		}
	}
	
	private void initGUI() {
		setFont(FONT);
		
		// listeners
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int button = e.getButton();
				if(allowInput && button == MouseEvent.BUTTON1) {
					int x = e.getX();
					int y = e.getY();
					startSelection(x, y);
				}
			}
		});
	}
	
	public void setLetterTiles(String letters) {
		int i = 0;
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				char character = letters.charAt(i);
				String letter = "" + character;
				if(character == 'Q') {
					letter = "Qu";
				}
				tiles[row][col] = new LetterTile(letter, fm, row, col);
				i++;
			}
		}
		repaint();
	}
	
	public void allowInput(boolean allow) {
		allowInput = allow;
	}
	
	public boolean isInputAllowed() {
		return allowInput;
	}
	
	private void startSelection(int x, int y) {
		row = y / LetterTile.SIZE;
		col = x / LetterTile.SIZE;
		System.out.println("row = " + row + " col = " + col);

		tiles[row][col].select();
		int centerX = tiles[row][col].getCenterX();
		int centerY = tiles[row][col].getCenterY();
		System.out.println("centerX = " + centerX + " centerY = " + centerY);
		
		selectedPath.moveTo(centerX, centerY);
		repaint();
	}

}
