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

import alert.check;
import connection.friendQuery;
import connection.userQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.friends;
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
	List<friends> listMF;
	List<friends> listRF;
	userQuery userq = new userQuery();
	friendQuery friendq = new friendQuery();
				
	public void setUser(users u) {
		user = u;
	}
	
	// component friends
	public VBox HboxFriends(users u) {
		VBox box = new VBox();
		ImageView img = new ImageView();
		img.setImage(new Image(u.getAvatar()));
		img.setFitHeight(200);
		img.setFitWidth(200);
		Label name = new Label();
		name.setText(u.getUsername());
		name.setStyle("-fx-text-fill:WHITE; "
				+ "-fx-background-color: rgba(254, 44, 85, 1.0); "
				+ "-fx-padding: 10px; -fx-background-radius: 5px;");
		name.setPrefWidth(275);
		name.setAlignment(Pos.CENTER);
		Button btn = new Button();
		btn.setText("Add friends");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					friendq.addRFriends(new friends(user.getId(), u.getId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btn.setPrefWidth(280);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(img);
		box.getChildren().add(name);
		box.getChildren().add(btn);
		box.setPadding(new Insets(10));
		box.setStyle("-fx-border-color: rgba(254, 44, 85, 1.0); -fx-background-color: rgba(0, 0, 0, 0.1);");
		return box;
	}
	
	public VBox HboxARFriends(users u) {
		VBox box = new VBox();
		ImageView img = new ImageView();
		img.setImage(new Image(u.getAvatar()));
		img.setFitHeight(200);
		img.setFitWidth(200);
		Label name = new Label();
		name.setText(u.getUsername());
		name.setStyle("-fx-text-fill:WHITE; "
				+ "-fx-background-color: rgba(254, 44, 85, 1.0); "
				+ "-fx-padding: 10px; -fx-background-radius: 5px;");
		name.setPrefWidth(275);
		name.setAlignment(Pos.CENTER);
		Button btn = new Button();
		btn.setText("Accept");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					friendq.addFriends(new friends(user.getId(), u.getId()));
					friendq.removeRFriends(new friends(user.getId(), u.getId()));
					friendq.removeRFriends(new friends(u.getId(), user.getId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btn.setPrefWidth(280);
		Button btn1 = new Button();
		btn1.setText("Delete");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					friendq.removeRFriends(new friends(user.getId(), u.getId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btn1.setPrefWidth(280);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(img);
		box.getChildren().add(name);
		box.getChildren().add(btn);
		box.getChildren().add(btn1);
		box.setPadding(new Insets(10));
		box.setStyle("-fx-border-color: rgba(254, 44, 85, 1.0); -fx-background-color: rgba(0, 0, 0, 0.1);");
		return box;
	}
	
	public VBox HboxRFriends(users u) {
		VBox box = new VBox();
		ImageView img = new ImageView();
		img.setImage(new Image(u.getAvatar()));
		img.setFitHeight(200);
		img.setFitWidth(200);
		Label name = new Label();
		name.setText(u.getUsername());
		name.setStyle("-fx-text-fill:WHITE; "
				+ "-fx-background-color: rgba(254, 44, 85, 1.0); "
				+ "-fx-padding: 10px; -fx-background-radius: 5px;");
		name.setPrefWidth(275);
		name.setAlignment(Pos.CENTER);
		Button btn = new Button();
		btn.setText("Delete");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					friendq.removeRFriends(new friends(user.getId(), u.getId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
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
	public void loadNewFriends() throws SQLException {
		list = userq.loadAllUser();
		listMF = friendq.listMF();
		listRF = friendq.listRF();
		check ck = new check(listMF, listRF);
		for (int i = 0; i < list.size(); i++) {
			int id = list.get(i).getId();
			int user_id = user.getId();
			if (ck.checkInMF(user_id, id) == 1 && ck.checkInRF(user_id, id) == 1 && id != user_id) {
				pane.getChildren().add(HboxFriends(list.get(i)));
			}
		}
	}
	
	// load all-friend
	public void loadRequestFriends() throws SQLException {
		list = userq.loadAllUser();
		listMF = friendq.listMF();
		listRF = friendq.listRF();
		check ck = new check(listMF, listRF);
		for (int i = 0; i < list.size(); i++) {
			int id = list.get(i).getId();
			int user_id = user.getId();
			if (ck.checkSendAndAccept(user_id, id) == 2) {
				pane.getChildren().add(HboxRFriends(list.get(i)));
			}
            if (ck.checkSendAndAccept(user_id, id) == -2) {
            	pane.getChildren().add(HboxARFriends(list.get(i)));
			}
		}
	}

	// Event Listener on Button[#new_f].onAction
	@FXML
	public void onClickNewF(ActionEvent event) throws SQLException {
		pane.getChildren().clear();
		loadNewFriends();
	}
	// Event Listener on Button[#my_f].onAction
	@FXML
	public void onClickMyF(ActionEvent event) throws SQLException {
		pane.getChildren().clear();
		loadRequestFriends();
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
