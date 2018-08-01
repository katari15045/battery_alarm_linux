import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.lang.StringBuilder;

public class Home extends Application{
	
	private static ComboBox<String> comboBox = null;
	private static Stage stage = null;

	@Override
	public void start(Stage stage){
		Home.stage = stage;
		Label labelThreshold = new Label();
		labelThreshold.setText("Threshold : ");
		fillComboBox();
		Label labelNote = new Label();
		labelNote.setText("Note : If Threshold is set to 41 then the 'low battery' warning is popped up when the Battery Level drops below 41% (Included)");
		labelNote.setWrapText(true);
		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setOnAction(new SaveListener(comboBox));		
		Button unInstallButton = new Button();
		unInstallButton.setText("Uninstall");
		unInstallButton.setOnAction(new UnInstallHandler());
		GridPane gridPane = new GridPane();
		gridPane.add(labelThreshold, 0, 0, 1, 1);
		gridPane.add(comboBox, 1, 0, 1, 1);
		gridPane.add(labelNote, 0, 1, 2, 1);
		GridPane.setMargin(labelNote, new Insets(15, 0, 0, 0));
		gridPane.add(saveButton, 0, 2, 2, 1);
		GridPane.setMargin(saveButton, new Insets(30, 0, 0, 0));
		GridPane.setHalignment(saveButton, HPos.CENTER);
		gridPane.add(unInstallButton, 1, 3, 2, 3);
		GridPane.setHalignment(unInstallButton, HPos.RIGHT);
		GridPane.setValignment(unInstallButton, VPos.BOTTOM);
		GridPane.setMargin(unInstallButton, new Insets(180, 0, 0, 0));
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

	public static void close(){
		stage.close();
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

class UnInstallHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent event){
		remCronJob();
		remBaseFile();
		displayAlertDialog();
		Home.close();
	}

	private void displayAlertDialog(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Successful");
		alert.setHeaderText("Uninstalled Successfully");
		alert.show();
	}


	private void remCronJob(){
		String cronCommand = DirChooserHandler.getCronCommand();
		String prevData = Terminal.exec("crontab -l");
		String updatedData = prevData.replace(cronCommand, "");
		TextFile.write(Main.getHomePath() + "/updated_cron.txt", updatedData);
		StringBuilder sb = new StringBuilder();
		sb.append("cat ");
		sb.append(Main.getHomePath());
		sb.append("/updated_cron.txt | crontab -");
		String updatedCommand = sb.toString();
		sb = new StringBuilder();
		sb.append(Main.getHomePath());
		sb.append("/remCronJob.sh");
		String fileName = sb.toString();
		TextFile.write(fileName, updatedCommand);
		Terminal.exec("bash " + fileName);
	}

	private void remBaseFile(){
		String fileName = DirChooserHandler.getHomePathFileLocation();
		StringBuilder sb = new StringBuilder();
		sb.append("rm ");
		sb.append(fileName);
		Terminal.exec(sb.toString());
	}
}








