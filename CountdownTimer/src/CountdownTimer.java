
	
// to help solve Exception problem, added extends Application & new start method
// Also ran program, then went to "Run Configurations" and under the "Arguments"
// tab added the following: --module-path /Users/jonathanfurminger/Downloads/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml
// and unchecked the "Use the -XstartOnFirstThread..."
// See answer from Praveen here: https://stackoverflow.com/questions/33819052/how-do-i-import-javafx-into-eclipse


import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CountdownTimer extends Application { 
	
	private final long ONE_SECOND = 1000;
	public boolean secondsUpdated = false;

    public static void main(String[] args) throws Exception {

    	launch(args);
    }
	
	@Override
	public void start(Stage stage) throws Exception {
		// initialise variables
		Seconds seconds = new Seconds();
		seconds.setSeconds(5);
			
		// Set up the window
		stage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root, 300, 200);
		
		stage.setTitle("Countdown Timer");
		
		Font myFont = Font.font("Courier New", FontWeight.BOLD, 14);
		
		Text secondsText = new Text();
		secondsText.setText("Hours    Minutes    Seconds");
		secondsText.setX(30);
		secondsText.setY(50);
		secondsText.setFont(myFont);
		
		Text colonsText = new Text();
		colonsText.setText("       :         :");
		colonsText.setX(30);
		colonsText.setY(80);
		colonsText.setFont(myFont);
		
		Text secondsNumber = new Text();
		secondsNumber.setText(Integer.toString(seconds.getSeconds()));
		secondsNumber.setX(210);
		secondsNumber.setY(80);
		secondsNumber.setFont(myFont);
		
		Button startTimerButton = new Button("Start Timer");
		startTimerButton.setLayoutX(100);
		startTimerButton.setLayoutY(120);
				
		root.getChildren().add(secondsText);
		root.getChildren().add(colonsText);
		root.getChildren().add(secondsNumber);
		root.getChildren().add(startTimerButton);
		
		stage.setScene(scene);
		stage.show();

		
		startTimerButton.setOnAction(new EventHandler<ActionEvent>() {

			Timer timer = new Timer();
			TimerTask decreaseSeconds = new SecondsHelper2(seconds);
			
			@Override
			public void handle(ActionEvent event) {

				timer.scheduleAtFixedRate(new SecondsHelper2(seconds), 0, ONE_SECOND);
				
				while(seconds.getSeconds() > 0 && seconds.isSecondsUpdated() == true) {
						secondsNumber.setText(Integer.toString(seconds.getSeconds()));
						seconds.setSecondsUpdated(false);
				}
			}
		});
	}

		

	class SecondsHelper2 extends TimerTask {
		
		private Seconds seconds;
		
		public SecondsHelper2(Seconds seconds) {
			this.seconds = seconds;
			
		}

		// try setting a static variable here that gets decreased every second
		// use the loop above to compare the static variable to a temp variable, looking for a change
		// if the static variable changes then update seconds, update the temp variable & update the Text box
		
		@Override
		public void run() {
			int tempSec = seconds.getSeconds();
			if(tempSec > 0) {
				tempSec--;
				seconds.setSeconds(tempSec);
				seconds.setSecondsUpdated(true);

			} else {
				System.out.print("\007");
				System.out.flush();
			}
		}

	}
	
}

