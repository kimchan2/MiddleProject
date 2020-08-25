package kr.or.ddit.view.logout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.MainThread;

public class logoutController  implements Initializable{

	@FXML Button LogoutOK_Btn;
	@FXML Button LogoutCan_Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML public void logoutOkBtnOnAction() {
		MainThread.brun = false;
//		Session.moveConf.thread.interrupt();
		try {
			if(Session.moveConf.thread != null)
				Session.moveConf.thread = null;
		}catch(NullPointerException e) {e.printStackTrace();}
		Session.memVO = null;
		Node logout = null;
		try {
			logout  = FXMLLoader.load(getClass().getResource("../main/Main.fxml"));
			Session.moveConf.getAnchorMain().getChildren().setAll(logout);
			Stage stage = (Stage) LogoutOK_Btn.getScene().getWindow();
			stage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void logoutCanBtnOnAction() {
		Stage stage = (Stage) LogoutCan_Btn.getScene().getWindow();
		stage.close();
	}

}
