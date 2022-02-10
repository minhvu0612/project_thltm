package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import connection.userQuery;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.users;

public class FriendController {
	@FXML
	private Button new_f;
	@FXML
	private Button my_f;
	@FXML
	private Pane pane;
	@FXML
	private Button back;
	
	// variable
	users user = null;
	List<users> list;
	userQuery userq = new userQuery();
				
	public void setUser(users u) {
		user = u;
	}
	
	// component friends
	public VBox HboxFriends(users user) {
		VBox box = new VBox();
		ImageView img = new ImageView();
		img.setImage(new Image(user.getAvatar()));
		Label name = new Label();
		name.setText(user.getUsername());
		name.setStyle("-fx-text-fill:WHITE; "
				+ "-fx-background-color: rgba(254, 44, 85, 1.0); "
				+ "-fx-padding: 10px; -fx-background-radius: 5px;");
		name.setPrefWidth(275);
		name.setAlignment(Pos.CENTER);
		Button btn = new Button();
		btn.setText("Add friends");
		btn.setPrefWidth(280);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(img);
		box.getChildren().add(name);
		box.getChildren().add(btn);
		box.setPadding(new Insets(10));
		box.setStyle("-fx-border-color: rgba(254, 44, 85, 1.0); -fx-background-color: rgba(0, 0, 0, 0.1);");
		return box;
	}
	
	// load all-friend
	public void loadAllFriends() throws SQLException {
		list = userq.loadAllUser();
		for (int i = 0; i < list.size(); i++) {
			pane.getChildren().add(HboxFriends(list.get(i)));
		}
	}

	// Event Listener on Button[#new_f].onAction
	@FXML
	public void onClickNewF(ActionEvent event) {
		
	}
	// Event Listener on Button[#my_f].onAction
	@FXML
	public void onClickMyF(ActionEvent event) {
		
	}
	// Event Listener on Button[#back].onAction
	@FXML
	public void onClickBack(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = loader.load();
		HomeController home = loader.getController();
		home.setUser(user);
		Stage window = (Stage) back.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
