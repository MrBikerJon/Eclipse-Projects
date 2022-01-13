import javax.swing.JFrame;
import javax.swing.SwingUtilities;
//Command Shift O to import

public class App {

	public static void main(String[] args) {
		
		// The swing method needs to be wrapped using the runnable interface in invokeLater (a thread), see
		// https://www.udemy.com/course/java-swing-complete/learn/lecture/104272?start=0#announcements
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
				// This replaces the code below because have now created a subClass of JFrame...
				//JFrame frame = new JFrame("Hello World");
				
				
				//ALL the code below has been moved into the constructor of the MainFrame subclass
				//make the frame is a good size
//				frame.setSize(600, 500);
//				//make sure application exits when click on close button
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				//now need to make the frame visible...
//				frame.setVisible(true);				
			}
			
		});
		

	}

}
