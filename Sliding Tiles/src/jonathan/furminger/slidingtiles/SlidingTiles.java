package jonathan.furminger.slidingtiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class SlidingTiles extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FILENAME = "slidingTilesImage.jpg";
	private static final int IMAGESIZE = 200;
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	
	private int tileSize = 50;
	private int gridSize = 4;

	private BufferedImage image = null;
	private TileButton[][] tile = new TileButton[gridSize][gridSize];
	private JPanel centerPanel = new JPanel();

	public SlidingTiles() {
		try {
			image = ImageIO.read(new File(FILENAME));
		}
		catch (IOException e) {
			String message = "This image file " + FILENAME + " could not be opened";
			JOptionPane.showMessageDialog(this, message);
		}
		
		
		TileButton.setTileSizeAndMaxTiles(tileSize, gridSize * gridSize);
		initGUI();
		setTitle("Sliding Tiles");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {

		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		} catch (Exception e) { }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SlidingTiles();
			}
		});
	}
	
	
	private void initGUI() {
		
		// menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		
		
		// title
		TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		divideImage();
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		JButton scrambleButton = new JButton("Scramble");
		scrambleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		buttonPanel.add(scrambleButton);
	}

	private void divideImage() {
		centerPanel.setLayout(new GridLayout(gridSize, gridSize));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.removeAll();
		int imageId = 0;
		for(int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				int x = col * tileSize;
				int y = row * tileSize;
				BufferedImage subimage = image.getSubimage(x, y, tileSize, tileSize);
				ImageIcon imageIcon = new ImageIcon(subimage);
				tile[row][col] = new TileButton(imageIcon, imageId, row, col);
				tile[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TileButton button = (TileButton) e.getSource();
						clickedTile(button);
					}
				});
				centerPanel.add(tile[row][col]);
				imageId++;
			}
		}
		
		centerPanel.revalidate();
		scramble();
	}

	private void clickedTile(TileButton clickedTile) {
		int row = clickedTile.getRow();
		int col = clickedTile.getCol();
		if(row > 0 && tile[row-1][col].hasNoImage()) {
			clickedTile.swap(tile[row-1][col]);
		} 
		else if(row < gridSize-1 && tile[row+1][col].hasNoImage()) {
			clickedTile.swap(tile[row+1][col]);
			}
		else if(col > 0 && tile[row][col-1].hasNoImage()) {
			clickedTile.swap(tile[row][col-1]);
		}
		else if(col < gridSize-1 && tile[row][col+1].hasNoImage()) {
			clickedTile.swap(tile[row][col+1]);
		}
		if(imagesInOrder()) {
			tile[gridSize-1][gridSize-1].showImage();
		}
		}
	
	private void scramble() {
		int openRow = gridSize-1;
		int openCol = gridSize-1;
		
		Random rand = new Random();
		for(int i = 0; i < 25*gridSize; i++) {
			int direction = rand.nextInt(gridSize);
			switch(direction) {
			case UP:
				if(openRow > 0) {
					tile[openRow][openCol].swap(tile[openRow-1][openCol]);
					openRow--;
				}
				break;
			case DOWN:
				if(openRow < gridSize-1) {
					tile[openRow][openCol].swap(tile[openRow+1][openCol]);
					openRow++;
				}
				break;
			case LEFT:
				if(openCol > 0) {
					tile[openRow][openCol].swap(tile[openRow][openCol-1]);
					openCol--;
				}
				break;
			case RIGHT:
				if(openCol < gridSize-1) {
					tile[openRow][openCol].swap(tile[openRow][openCol+1]);
					openCol++;
				}
				break;
			}
		}
	}
	
	private boolean imagesInOrder() {
		int id = 0;
		boolean inOrder = true;
		
		for(int row = 0; row < gridSize && inOrder; row++) {
			for(int col = 0; col < gridSize && inOrder; col++) {
				int currentId = tile[row][col].getImageId();
				if(currentId != id) {
					inOrder = false;
				}
				id++;
			}
		}
		return inOrder;
	}
	
	private void newGame() {
		int imageId = 0;
		for(int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				int x = col*tileSize;
				int y = row*tileSize;
				BufferedImage subimage = image.getSubimage(x, y, tileSize, tileSize);
				ImageIcon imageIcon = new ImageIcon(subimage);
				tile[row][col].setImage(imageIcon, imageId);
				imageId++;
			}
		}
		scramble();
	}
	
	private void open() {
		JFileChooser chooser = new JFileChooser();
		ImageFileFilter fileFilter = new ImageFileFilter();
		chooser.setFileFilter(fileFilter);
		int option = chooser.showOpenDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedImage newImage = ImageIO.read(file);
				int width = newImage.getWidth();
				int height = newImage.getHeight();
				Graphics g = image.getGraphics();
				g.drawImage(newImage,  0,  0,  IMAGESIZE,  IMAGESIZE,  0,  0,  width,  height,  this);
				g.dispose();
			} catch (IOException e) {
				String message = "File " + file.getPath() + " could not be opened";
				JOptionPane.showMessageDialog(this, message);
			}
		}
	}
	
}