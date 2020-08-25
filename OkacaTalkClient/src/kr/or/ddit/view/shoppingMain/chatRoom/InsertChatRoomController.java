package kr.or.ddit.view.shoppingMain.chatRoom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.session.Session;
import javafx.scene.layout.AnchorPane;

public class InsertChatRoomController implements Initializable{

	@FXML ImageView Insert_Chat_Room_Btn;
	@FXML ImageView Insert_Open_Chat_Room_Btn;
	ListView<String> Friends_List;
	@FXML AnchorPane InsertChatRoomAnchorPane; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@FXML public void insertChatRoomBtnOnMouseClicked() {
		Session.insertChatRoomAnchorPane = InsertChatRoomAnchorPane;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoiceUser.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stage.setTitle("유저 선택");
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void insertOpenChatRoomBtnOnMouseClicked() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoiceOpenChat.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stage.setTitle("오픈채팅 선택");
		stage.setScene(new Scene(root));
		stage.show();
	}

}
