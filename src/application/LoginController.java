package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.users;


import java.io.IOException;
import java.sql.SQLException;

import alert.alert;
import connection.userQuery;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private TextField login_user;
	@FXML
	private PasswordField login_pass;
	@FXML
	private Button login_btn;
	@FXML
	private Button login_change;
	
	
	// query
	public userQuery userq = new userQuery();
	
	

	// Event Listener on Button[#login_btn].onAction
	@FXML
	public void onClickLogin(ActionEvent event) throws SQLException, IOException {
		users user = userq.handleLogin(login_user.getText(), login_pass.getText());
		if (user == null) {
			new alert().alertFieldError("Username or Password invalid!");
		}
		else {
			new alert().alertSuccess("Successfully!");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Parent root = loader.load();
			HomeController home = loader.getController();
			home.setUser(user);
			Stage window = (Stage) login_btn.getScene().getWindow();
			window.setScene(new Scene(root));
		}
	}
	
	
	
	// Event Listener on Button[#login_change].onAction
	@FXML
	public void onclickChange(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
		Stage window = (Stage) login_change.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
