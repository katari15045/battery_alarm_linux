import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.lang.StringBuilder;

public class Home extends Application{
	
	private static ComboBox<String> comboBox = null;

	@Override
	public void start(Stage stage){
		Label labelThreshold = new Label();
		labelThreshold.setText("Threshold : ");
		fillComboBox();
		Label labelNote = new Label();
		labelNote.setText("Note : If Threshold is set to 41 then the 'low battery' warning is popped up when the Battery Level drops below 41% (Included)");
		labelNote.setWrapText(true);
		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setOnAction(new SaveListener(comboBox));		
		GridPane gridPane = new GridPane();
		gridPane.add(labelThreshold, 0, 0, 1, 1);
		gridPane.add(comboBox, 1, 0, 1, 1);
		gridPane.add(labelNote, 0, 1, 2, 1);
		GridPane.setMargin(labelNote, new Insets(15, 0, 0, 0));
		gridPane.add(saveButton, 0, 2, 2, 1);
		GridPane.setMargin(saveButton, new Insets(30, 0, 0, 0));
		GridPane.setHalignment(saveButton, HPos.CENTER);
		gridPane.setPadding(new Insets(30, 30, 30, 30));
		Scene scene = new Scene(gridPane, 800, 400);
		stage.setScene(scene);
		stage.setTitle("Battery Alarm");
		stage.show();
	}	

	private void fillComboBox(){
		int count = 3;
		comboBox = new ComboBox<>();
		while(count <= 97){
			comboBox.getItems().add(String.valueOf(count));
			count = count+1;
		}	
		comboBox.getSelectionModel().select(String.valueOf(Main.getThreshold()));
	}

	public static void display(){
		launch((String[])null);
	}
}

class SaveListener implements EventHandler<ActionEvent>{
	
	private ComboBox<String> comboBox = null;

	public SaveListener(ComboBox<String> comboBox){
		this.comboBox = comboBox;
	}

	@Override
	public void handle(ActionEvent event){
		String newThreshold = (String)comboBox.getValue();
		DirChooserHandler.storeThreshold(Integer.valueOf(newThreshold));
		displayAlertDialog(newThreshold);		
	}

	private void displayAlertDialog(String newThreshold){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Successful");
		StringBuilder sb = new StringBuilder();
		sb.append("Threshold has been updated to ");
		sb.append(newThreshold);
		sb.append("!");
		alert.setHeaderText(sb.toString());
		alert.show();
	}

}








