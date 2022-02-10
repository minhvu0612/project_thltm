package alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class alert {
	public void alertFieldError(String message) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.show();
	}
	
	public void alertWarning(String message) {
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText(message);
		a.show();
	}
	
	public void alertSuccess(String message) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText(message);
		a.show();
	}
	
	public void alertLogout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			//System.out.println("You successfully logged out");
			stage.close();
		} 
	}
}
