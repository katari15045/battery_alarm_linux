import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

public class Home extends Application{

	private static int width = 400;
	private static int height = 150;
	private static String title = "Low Battery";

	@Override
	public void start(Stage stage){
		System.out.println("c, ");
		Label label = new Label();
		label.setText("Battery is Running Low!\n" + Battery.level() + "% remaining.");
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(label);
		Scene scene = new Scene(stackPane, width, height);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
		System.out.println("Staging...");
	}

	public static void display() {
		System.out.print("a, ");
		launch((String[])null);
		System.out.println("b, ");	
	}
}
