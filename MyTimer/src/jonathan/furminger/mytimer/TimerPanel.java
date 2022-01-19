package jonathan.furminger.mytimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jonathan.furminger.mycommonmethods.FileIO;

public class TimerPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final String ALARM_FILE = "/alarm.wav";
	private int width = 150;
	private int height = 24;
	private String timeString = "00:00:00";
	private long time = 10;
	private Thread timerThread;

	
	public TimerPanel(long time, Font font) {
		setTime(time);
		setFont(font);
		height = font.getSize();
		FontMetrics fm = getFontMetrics(font);
		width = fm.stringWidth(timeString);
	}
		
	public void start() {
		stop();
		timerThread = new Thread(this);
		timerThread.start();
	}
		
	protected void timesUp() {
		Clip clip = FileIO.playClip(this, ALARM_FILE);	
		String message = "Time's up!";
		JOptionPane.showMessageDialog(this,  message);
		clip.stop();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString(timeString, 0, height);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
	
	public void setTime(long time) {
		this.time = time;
		long h = time / 3600;
		long m = (time / 60) % 60;
		long s = time % 60;
		timeString = String.format("%02d:%02d:%02d", h, m, s);
		repaint();
	}
	
	public void run() {
		while(time > 0) {
			time-= 1;
			setTime(time);
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {
				return;
			}
		}
		timesUp();
	}
	
	public void stop() {
		if(timerThread != null) {
		timerThread.interrupt();
		timerThread = null;
		}
	}
	
	public long getTime() {
		return time;
	}
	
}
