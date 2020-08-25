package kr.or.ddit.view.login;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;

public class SearchIdController implements Initializable {

	@FXML TextField Id_FInd_Name;
	@FXML TextField Id_Find_Tel;
	@FXML Button Id_Find_Ok_Btn;
	@FXML Button Id_Find_Can_Btn;

	private Registry reg;
	private IMemberService ilogin;

	List<MemberVO> list = new ArrayList<MemberVO>();
	private MemberVO mvo = new MemberVO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			// 설정한 서버를 찾는 메소드 lookup
			ilogin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	// 아이디 찾기 OK버튼을 누를경우
	@FXML
	public void idFindOkBtnOnAction() {
		if (Id_FInd_Name.getText().equals("") || Id_Find_Tel.getText().equals("") || Id_FInd_Name.getText() == null
				|| Id_Find_Tel.getText() == null) {
			errMsg("아이디 찾기 에러!!", "입력하지 않은 정보가 있습니다!");
			return;
		}

		String mem_name = Id_FInd_Name.getText();
		String mem_hp = Id_Find_Tel.getText();

		MemberVO mvo = new MemberVO();

		mvo.setMem_name(mem_name);
		mvo.setMem_hp(mem_hp);

		System.out.println(mvo.getMem_name());

		try {
			mvo = ilogin.idSearch2(mvo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		if(mvo==null) {
			errMsg("아이디 찾기 에러!!", "해당 정보에 해당하는 ID가 존재하지 않습니다.");
		}
		else{
			infoMsg("입력하신 정보에 일치하는 아이디 입니다.", mvo.getMem_mail());
			Stage stage = (Stage) Id_Find_Ok_Btn.getScene().getWindow();
			stage.close();
			// mvo.setMem_name(mname);
			// mvo.setMem_hp(mhp);
		}

	};
		 
		 // 돌아가는 창.
		 @FXML 
	public void idFindCanBtnOnAction() {
		Stage stage = (Stage) Id_Find_Can_Btn.getScene().getWindow();
		stage.close();
	};
		 
		 
		 
		 
	
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	private void infoMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("오카카톡 ID 찾기");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
