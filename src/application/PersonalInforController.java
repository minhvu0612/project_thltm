package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.users;

public class PersonalInforController {
	@FXML
	private ImageView avatar;
	@FXML
	private Label username;
	@FXML
	private Label gender;
	@FXML
	private Label phone;
	@FXML
	private Label email;
	@FXML
	private Button back_btn;
	@FXML
	private Button setting_btn;
	
	users user = null;
	
	public void setUser(users u) {
		user = u;
		this.avatar.setImage(new Image(u.getAvatar()));
		this.username.setText(u.getUsername());
		this.email.setText(u.getEmail());
		this.phone.setText(u.getPhone());
		this.gender.setText(u.getGender());
	}

	// Event Listener on Button[#back_btn].onAction
	@FXML
	public void onClickBack(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = loader.load();
		HomeController home = loader.getController();
		home.setUser(user);
		Stage window = (Stage) back_btn.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	// Event Listener on Button[#setting_btn].onAction
	@FXML
	public void onClickSetting(ActionEvent event) {
		
	}
}
