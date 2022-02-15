package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.users;

import java.io.IOException;
import java.sql.SQLException;

import alert.alert;
import connection.userQuery;
import javafx.event.ActionEvent;

public class SignupController {
	@FXML
	private TextField signup_user;
	@FXML
	private TextField signup_pass;
	@FXML
	private TextField signup_email;
	@FXML
	private TextField signup_phone;
	@FXML
	private Button signup_btn;
	@FXML
	private Button signup_change;
	@FXML
	private RadioButton rbtn1, rbtn2;
	
	
	// query
	public userQuery userq = new userQuery();

	// Event Listener on Button[#signup_btn].onAction
	@FXML
	public void onClickSignup(ActionEvent event) throws SQLException, IOException {
		users user = null;
		if (rbtn1.isSelected()) {
			user = new users(signup_user.getText(), signup_pass.getText(), signup_email.getText(), signup_phone.getText(), "male");
		}
		else if (rbtn2.isSelected()) {
			user = new users(signup_user.getText(), signup_pass.getText(), signup_email.getText(), signup_phone.getText(), "female");
		}
		if (userq.addUser(user) == 1) {
			new alert().alertSuccess("Successfully!");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Parent root = loader.load();
			HomeController home = loader.getController();
			home.setUser(user);
			Stage window = (Stage) signup_btn.getScene().getWindow();
			window.setScene(new Scene(root));
		}
		else {
			new alert().alertFieldError("Error");
		}
	}
	
	
	
	// Event Listener on Button[#signup_change].onAction
	@FXML
	public void onClickChange(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage window = (Stage) signup_change.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
