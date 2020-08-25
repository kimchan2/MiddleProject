package kr.or.ddit.view.shoppingMain.configurationList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class ConfigurationListController implements Initializable {

	@FXML Button Conf_Main_Btn;
	@FXML Button Conf_Profile_Btn;
	@FXML Button Conf_Friend_Btn;
	@FXML Button Conf_Update_Btn;
	@FXML Button Conf_Info_Btn;
	@FXML AnchorPane Conf_Stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Node chatScene = null;
		try {
			chatScene = FXMLLoader.load(getClass().getResource("ConfigurationProfile.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conf_Stage.getChildren().setAll(chatScene);
	}

	@FXML public void ConfMainBtnOnMouseClicked() throws IOException {
		
		Conf_Profile_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-background-color: lightblue");
		Conf_Friend_Btn.setStyle("-fx-background-color: white");
		Conf_Update_Btn.setStyle("-fx-background-color: white");
		Conf_Info_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-border-color: black");
		Conf_Profile_Btn.setStyle("-fx-border-color: black");
		Conf_Friend_Btn.setStyle("-fx-border-color: black");
		Conf_Update_Btn.setStyle("-fx-border-color: black");
		Conf_Info_Btn.setStyle("-fx-border-color: black");
		
		Node chatScene = FXMLLoader.load(getClass().getResource("ConfigurationGeneralController.fxml"));
		Conf_Stage.getChildren().setAll(chatScene);
	}

	@FXML public void ConfProfileBtnOnMouseClicked() throws IOException {
		Conf_Profile_Btn.setStyle("-fx-background-color: lightblue");
		Conf_Main_Btn.setStyle("-fx-background-color: white");
		Conf_Friend_Btn.setStyle("-fx-background-color: white");
		Conf_Update_Btn.setStyle("-fx-background-color: white");
		Conf_Info_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-border-color: black");
		Conf_Profile_Btn.setStyle("-fx-border-color: black");
		Conf_Friend_Btn.setStyle("-fx-border-color: black");
		Conf_Update_Btn.setStyle("-fx-border-color: black");
		Conf_Info_Btn.setStyle("-fx-border-color: black");
		
		
		Node chatScene = FXMLLoader.load(getClass().getResource("ConfigurationProfile.fxml"));
		Conf_Stage.getChildren().setAll(chatScene);
	}

	@FXML public void ConfFriendBtnMouseClicked() {
		
		Conf_Profile_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-background-color: white");
		Conf_Friend_Btn.setStyle("-fx-background-color: lightblue");
		Conf_Update_Btn.setStyle("-fx-background-color: white");
		Conf_Info_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-border-color: black");
		Conf_Profile_Btn.setStyle("-fx-border-color: black");
		Conf_Friend_Btn.setStyle("-fx-border-color: black");
		Conf_Update_Btn.setStyle("-fx-border-color: black");
		Conf_Info_Btn.setStyle("-fx-border-color: black");
		
	}

	@FXML public void ConfUpdateBtnOnMouseClicked() {
		
		Conf_Profile_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-background-color: white");
		Conf_Friend_Btn.setStyle("-fx-background-color: white");
		Conf_Update_Btn.setStyle("-fx-background-color: lightblue");
		Conf_Info_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-border-color: black");
		Conf_Profile_Btn.setStyle("-fx-border-color: black");
		Conf_Friend_Btn.setStyle("-fx-border-color: black");
		Conf_Update_Btn.setStyle("-fx-border-color: black");
		Conf_Info_Btn.setStyle("-fx-border-color: black");
		
	}

	@FXML public void ConfInfoBtnOnMouseClicked() {
		
		Conf_Profile_Btn.setStyle("-fx-background-color: white");
		Conf_Main_Btn.setStyle("-fx-background-color: white");
		Conf_Friend_Btn.setStyle("-fx-background-color: white");
		Conf_Update_Btn.setStyle("-fx-background-color: white");
		Conf_Info_Btn.setStyle("-fx-background-color: lightblue");
		Conf_Main_Btn.setStyle("-fx-border-color: black");
		Conf_Profile_Btn.setStyle("-fx-border-color: black");
		Conf_Friend_Btn.setStyle("-fx-border-color: black");
		Conf_Update_Btn.setStyle("-fx-border-color: black");
		Conf_Info_Btn.setStyle("-fx-border-color: black");
	}

}
