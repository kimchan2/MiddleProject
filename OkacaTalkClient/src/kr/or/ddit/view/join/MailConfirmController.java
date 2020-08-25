package kr.or.ddit.view.join;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MailConfirmController implements Initializable {
	
	private Registry reg;
	private IMemberService ijoin;
	@FXML TextField Join_Mailconfirm_Text;
	@FXML Button Join_Mailcancel_Btn;
	@FXML Button Join_Mailok_Btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ijoin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	@FXML public void joinMailcancelBtnOnAction() {
		Stage stage = (Stage) Join_Mailcancel_Btn.getScene().getWindow();
		stage.close();
	}

	@FXML public void joinMailokBtnOnAction() {
		
		System.out.println(JoinController.NO);
		if(Integer.parseInt(Join_Mailconfirm_Text.getText()) == JoinController.NO) {
			JoinController.chk_cnt++;
			infoMsg("성공!","입력이 완료되었습니다." );
		}else {
			JoinController.no_cnt++;
			infoMsg("실패", "확인에 실패하셨습니다.");
		}
		
		Stage stage = (Stage) Join_Mailok_Btn.getScene().getWindow();
		stage.close();
		
	}
	
	private void infoMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("오카카톡 Id 찾기");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
