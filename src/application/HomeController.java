package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alert.alert;
import connection.userQuery;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.users;

public class HomeController {
	@FXML
	private VBox sidebar;
	@FXML
	private VBox sidebar_chat;
	@FXML
	private VBox sidebar_group;
	@FXML
	private VBox sidebar_friend;
	@FXML
	private VBox sidebar_setting;
	@FXML
	private VBox sidebar_logout;
	@FXML
	private TextField search_content;
	@FXML
	private VBox list_user;
	@FXML
	private TextField send_mes;
	@FXML
	private VBox chat_pane;
	@FXML
	private HBox user_chat_infor;
	
	
	// variable
	users user = null;
	users currentChat = null;
	userQuery userq = new userQuery();
	
	// load list friends
	List<users> list;
	List<EventHandler<MouseEvent>> listEvent = new ArrayList<EventHandler<MouseEvent>>();
	
	// component friends
	public HBox HboxFriends(users user, int item) {
		HBox box = new HBox();
		ImageView img = new ImageView();
		img.setImage(new Image(user.getAvatar()));
		img.setFitWidth(60);
		img.setFitHeight(60);
		img.getStyleClass().add("child--avatar");
		Label name = new Label();
		name.setText("   " + user.getUsername());
		box.setPadding(new Insets(10, 20, 10, 20));
		box.setAlignment(Pos.CENTER_LEFT);
		box.getStyleClass().add("hbox");
		box.getChildren().add(img);
		box.getChildren().add(name);
		box.setOnMouseClicked(listEvent.get(item));
		return box;
	}
	
	public void setFriend(users user) {
		ImageView img = new ImageView();
		img.setImage(new Image(user.getAvatar()));
		img.setFitWidth(60);
		img.setFitHeight(60);
		img.getStyleClass().add("child--avatar");
		Label name = new Label();
		name.setText("   " + user.getUsername());
		user_chat_infor.setPadding(new Insets(10, 20, 10, 20));
		user_chat_infor.setAlignment(Pos.CENTER_LEFT);
		user_chat_infor.getStyleClass().add("hbox");
	    user_chat_infor.getChildren().add(img);
		user_chat_infor.getChildren().add(name);
	}
	
	
			
	public void setUser(users u) throws SQLException {
		user = u;
		list = userq.loadAllUser();
		for (int i = 0; i < list.size(); i++) {
			users user = list.get(i);
			EventHandler<MouseEvent> handlerChangeUser = event -> {
			    if (user != currentChat) {
			    	chat_pane.getChildren().clear();
				    user_chat_infor.getChildren().clear();
				    setFriend(user);
				    currentChat = user;
			    }
			};
			listEvent.add(handlerChangeUser);
		}
		setFriend(list.get(0));
		this.currentChat = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			list_user.getChildren().add(HboxFriends(list.get(i), i));
		}
	}

	// Event Listener on VBox[#sidebar_chat].onMouseClicked
	@FXML
	public void onClickMess(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseEntered
	@FXML
	public void onER1(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseMoved
	@FXML
	public void onMV1(MouseEvent event) {
		
	}
	// Event Listener on VBox[#sidebar_group].onMouseClicked
	@FXML
	public void onClickGroupChat(MouseEvent event) {
		
	}
	// Event Listener on VBox[#sidebar_friend].onMouseClicked
	@FXML
	public void onClickFriends(MouseEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Friend.fxml"));
		Parent root = loader.load();
		FriendController friend = loader.getController();
		friend.setUser(user);
		friend.loadAllFriends();
		Stage window = (Stage) sidebar_friend.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	// Event Listener on VBox[#sidebar_setting].onMouseClicked
	@FXML
	public void onClickSetting(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonalInfor.fxml"));
		Parent root = loader.load();
		PersonalInforController personal = loader.getController();
		personal.setUser(user);
		Stage window = (Stage) sidebar_setting.getScene().getWindow();
		window.setScene(new Scene(root));
	}
	// Event Listener on VBox[#sidebar_logout].onMouseClicked
	@FXML
	public void onClickLogout(MouseEvent event) throws IOException {
		Stage window = (Stage) sidebar_logout.getScene().getWindow();
		new alert().alertLogout(window);
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void onClickSearch(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void onClickImg(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void onClickFile(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void onClickVideo(MouseEvent event) {
		
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void onClickSendMes(MouseEvent event) {
		if (send_mes.getText() != null && send_mes.getText() != "") {
			HBox div_mes = new HBox();
			div_mes.setPadding(new Insets(10));
			div_mes.setAlignment(Pos.CENTER_RIGHT);
			Label mes = new Label();
			mes.setStyle("-fx-text-fill:WHITE; "
					+ "-fx-background-color: rgba(254, 44, 85, 1.0); "
					+ "-fx-padding: 10px; -fx-background-radius: 5px;");
			mes.setText(send_mes.getText());
			div_mes.getChildren().add(mes);
			div_mes.setLayoutX(530);
			chat_pane.getChildren().add(div_mes);
			System.out.print(send_mes.getText() + "-" + currentChat.getUsername());
			send_mes.setText(null);
		}
	}
}
