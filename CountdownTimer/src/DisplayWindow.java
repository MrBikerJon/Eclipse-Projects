import javafx.application.Application; 

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayWindow extends Application {
	

	@Override
	public void start(Stage arg0) throws Exception {
		Stage stage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
		stage.setTitle("Countdown Timer");
		Text text = new Text();
		text.setText("WHOOOOA!!");
		
		root.getChildren().add(text);
		
		stage.setScene(scene);
		stage.show();
	}

}
