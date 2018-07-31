import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Home extends Application{

	private static int width = 400;
	private static int height = 125;
	private static String title = "Low Battery";
	private static int level = -1;

	@Override
	public void start(Stage stage){
		Label labelOne = new Label();
		labelOne.setText("Battery is Running Low!");
		labelOne.setFont(Font.font(null, FontWeight.BOLD, 20));
		Label labelTwo = new Label();
		labelTwo.setText(level + "% Remaining.");
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(labelOne, 0, 0);
		gridPane.add(labelTwo, 0, 2);
		Scene scene = new Scene(gridPane, width, height);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public static void display(int level) {
		Home.level = level;
		launch((String[])null);
	}
}
