package jonathan.furminger.gravitywell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jonathan.furminger.mycommonmethods.FileIO;
import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 400;
	private static final int LANDSCAPE_WIDTH = 600;
	private static final int LANDSCAPE_HEIGHT = 360;
	private static final String FILE_NAME = "/landscapePoints.txt";
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 36);
	
	private ScorePanel scorePanel;
	private Polygon landscape = new Polygon();
	private LandingPad[] landingPads = new LandingPad[3];
	private Lander lander = new Lander();

	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
		try {
			ArrayList<String> landingPoints = FileIO.readTextFile(this, FILE_NAME);
			for(int i = 0; i < landingPoints.size(); i++) {
				String coordinates = landingPoints.get(i);
				int commaPosition = coordinates.indexOf(',');
				String xString = coordinates.substring(0, commaPosition);
				int x = Integer.parseInt(xString);
				String yString = coordinates.substring(commaPosition + 2);
				int y = Integer.parseInt(yString);
				landscape.addPoint(x, y);
				
				landingPads[0] = new LandingPad(127, 218, 300, 2);
				landingPads[1] = new LandingPad(299, 397, 93, 1);
				landingPads[2] = new LandingPad(430, 509, 315, 4);
				
				initGUI();
			}
		}
		catch (NumberFormatException e) {
			String message = "The file cannot be converted to numbers.";
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// sky
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LANDSCAPE_WIDTH, LANDSCAPE_HEIGHT);
		
		// landscape
		g.setColor(Color.GREEN);
		g.fillPolygon(landscape);
		
		// landing pads
		for(int i = 0; i < landingPads.length; i++) {
			landingPads[i].draw(g);
		}
		
		// lander
		lander.draw(g);
	}
	
	private void initGUI() {
		setFont(BIG_FONT);
		
		// timer
		
		
		// listeners
	}
	
}
