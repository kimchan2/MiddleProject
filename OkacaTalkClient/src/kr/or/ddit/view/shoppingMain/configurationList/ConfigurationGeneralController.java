package kr.or.ddit.view.shoppingMain.configurationList;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;

public class ConfigurationGeneralController implements Initializable {

	@FXML Button Conf_Gen_FontSelect_Btn;
	@FXML RadioButton Conf_Gen_AlertOn_Btn;
	@FXML RadioButton Conf_Gen_AlertOff_Btn;
	@FXML RadioButton Conf_Gen_KeywordOff_Btn;
	@FXML RadioButton Conf_Gen_KeywordOn_Btn;
	@FXML RadioButton Conf_Gen_NewChatAlertOn_Btn;
	@FXML RadioButton Conf_Gen_NewChatAlertOff_Btn;
	@FXML RadioButton Conf_Gen_GroupChatOn_Btn;
	@FXML RadioButton Conf_Gen_GroupChatOff_Btn;
	@FXML Button Conf_Gen_ChatBackup_Btn;
	@FXML Button Conf_Gen_Back_Style_Btn;
	@FXML ScrollBar Conf_Gen_Transparent_Btn;
	@FXML Button Conf_Gen_Withdrawal_Btn;
	@FXML Button Conf_Gen_Can_Btn;
	@FXML ToggleGroup Alert;
	@FXML ToggleGroup Keyword;
	@FXML ToggleGroup NewChatAlert;
	@FXML ToggleGroup GroupChat;
	
	private Registry reg;
	private IMemberService ims;
	MemberVO vo = Session.memVO;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ims = (IMemberService) reg.lookup("member");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void ConfGenWithdrawalBtn() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("확인");
		alert.setHeaderText("회원탈퇴 확인");
		alert.setContentText("정말로 " + " 탈퇴를 하시겠습니까?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {

			int cnt = ims.deleteMemberVO(Session.memVO.getMem_mail());

			try {
				if (cnt < 0) {
					infoMsg("알림", "탈퇴완료");

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/login/Login.fxml"));
					Parent parent = null;
					try {
						parent = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Scene scene = new Scene(parent);
					Stage main1 = (Stage) Conf_Gen_Withdrawal_Btn.getScene().getWindow();
					main1.setScene(scene);
				}

			} catch (Exception e) {
				errMsg("실패", "오류!!!");
				e.printStackTrace();
			}
		}

	}
	
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

}
