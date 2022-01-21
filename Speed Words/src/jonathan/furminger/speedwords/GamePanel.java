package jonathan.furminger.speedwords;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import jonathan.furminger.mycommonmethods.FileIO;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	private static final int START_X = (WIDTH / 2) - (7 * LetterTile.SIZE / 2);
	private static final int START_Y = (HEIGHT / 2) - (LetterTile.SIZE / 2);
	private static final String FILE_NAME = "/enable1_7.txt";
	
	private SpeedWords speedWords;
	private ArrayList<TileSet> tileSets = new ArrayList<TileSet>();
	private ArrayList<String> sevenLetterWords = new ArrayList<String>();
	private Random rand = new Random();
	private TileSet movingTiles;
	private int mouseX;
	private int mouseY;
	private Dictionary dictionary = new Dictionary();
	
	public GamePanel(SpeedWords speedWords) {
		this.speedWords = speedWords;
		sevenLetterWords = FileIO.readTextFile(this, FILE_NAME);
		restart();
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int mouseButton = e.getButton();
				boolean leftClicked = (mouseButton == MouseEvent.BUTTON1);
				clicked(x, y, leftClicked);
			}
			public void mouseReleased(MouseEvent e) {
				released();
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				dragged(x, y);
			}
		});
	}

	public void paintComponent(Graphics g) {
		
		// draw background
		g.setColor(SpeedWords.TAN);
		g.fillRect(0, 0, WIDTH,  HEIGHT);
		
		// draw all current tile sets
		for(int i = 0; i < tileSets.size(); i++) {
			TileSet tileSet = tileSets.get(i);
			tileSet.draw(g);
			
		// draw moving tiles
		if(movingTiles != null) {
			movingTiles.draw(g);
		}
			
		}
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void restart() {
		tileSets.clear();
		int range = sevenLetterWords.size();
		int choose = rand.nextInt(range);
		String s = sevenLetterWords.get(choose);
		TileSet tileSet = new TileSet(s, START_X, START_Y);
		tileSets.add(tileSet);
		
		checkWord(tileSet);
		repaint();
	}
	
	private void clicked(int x, int y, boolean leftClicked) {
		if (movingTiles == null) {
			mouseX = x;
			mouseY = y;
			
			for(int i = 0; i < tileSets.size() && movingTiles == null; i++) {
				TileSet tileSet = tileSets.get(i);
				if(tileSet.contains(x, y)) {
					if(leftClicked == true) {
						movingTiles = tileSet.removeAndReturn1TileAt(x, y);
						if(tileSet.getNumberOfTiles() == 0) {
							tileSets.remove(i);
						}
						else {
							checkWord(tileSet);
						}
					}
					else {
						movingTiles = tileSet;
						tileSets.remove(i);
				}
			}
		}
		repaint();
		}
	}
	
	private void released() {
		//if dropped on other tiles, connect it to the tiles
		if(movingTiles != null) {
			boolean addedToTiles = false;
			for(int i = 0; i <tileSets.size() && !addedToTiles; i++) {
				TileSet tileSet = tileSets.get(i);
				addedToTiles = tileSet.insertTiles(movingTiles);
				if(addedToTiles) {
					movingTiles = null;
					checkWord(tileSet);
				}
			}
			
		}
		//if not dropped on other tiles, return it to the list of tile sets
		if(movingTiles != null) {
			String s = movingTiles.toString();
			int x = movingTiles.getX();
			int y = movingTiles.getY();
			TileSet newTileSet = new TileSet(s, x, y);
			tileSets.add(0, newTileSet);
			movingTiles = null;
			checkWord(newTileSet);
		}
		repaint();
	}
	
	private void dragged(int x, int y) {
		if(movingTiles != null) {
			int changeX = x - mouseX;
			int changeY = y - mouseY;
			movingTiles.changeXY(changeX, changeY);
			mouseX = x;
			mouseY = y;
			repaint();
		}
	}
	
	private void checkWord(TileSet tileSet) {
		String s = tileSet.toString();
		boolean isAWord = dictionary.isAWord(s);
		if(isAWord) {
			tileSet.setValid(true);
			int points = tileSet.getPoints();
			speedWords.addToScore(points);
		}
		else {
			tileSet.setValid(false);
		}
	}
	
}
