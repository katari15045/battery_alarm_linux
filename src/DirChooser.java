import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.lang.StringBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DirChooser extends Application{

	private static String labelText = "Choose the Installation Directory.";
	private static String buttonText = "Browse";
	private static int width = 600;
	private static int height = 300;
	private static String title = "Install";

	@Override
	public void start(Stage stage){
		Label label = new Label();
		label.setText(labelText);
		label.setFont(Font.font(null, FontWeight.BOLD, 25));
		Button button = new Button();
		button.setText(buttonText);
		button.setLineSpacing(90);
		GridPane.setHalignment(button, HPos.CENTER);
		button.setOnAction(new DirChooserHandler(stage));
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(30, 30, 30, 30));
		gridPane.add(label, 0, 0);
		gridPane.add(button, 0, 2);
		GridPane.setMargin(button, new Insets(30, 0, 0, 0));
		Scene scene = new Scene(gridPane, width, height);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public static void display(){
		launch((String[])null);
	}

	public static void close(Stage stage){
		stage.close();
	}

}

class DirChooserHandler implements EventHandler<ActionEvent>{
	private Stage stage = null;
	private String title = "Choose a Directory";
	
	public DirChooserHandler(Stage stage){
		this.stage = stage;
	}	

	@Override 
	public void handle(ActionEvent actionEvent){
		DirectoryChooser dc = new DirectoryChooser();
		dc.setTitle(title);
		File selDir = dc.showDialog(stage);
		if(selDir != null){	
			DirChooser.close(stage);
			String newDir = selDir.getAbsolutePath() + "/Battery_Alarm";
			createNewDir(newDir);
			Main.setHomePath(newDir);
			displayAlertDialog();
			Main.postInstall();
		}
	}

	private void createNewDir(String dir){
		StringBuilder sb = new StringBuilder();
		sb.append("mkdir ");
		sb.append(dir);
		Terminal.exec(sb.toString());
	}

	private void displayAlertDialog(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Successful");
		alert.setHeaderText("Installation Complete!");
		alert.show();
	}
}













