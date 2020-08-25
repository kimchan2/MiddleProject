package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LoginController implements Initializable{

	@FXML TextField Write_ID_Text;
	@FXML TextField Write_Pass_Text;
	@FXML Button Mem_Login_Btn;
	@FXML Button Mem_Searchid_Btn;
	@FXML Button Mem_Searchpass_Btn;
	
	
	private Registry reg;
	private IMemberService ilogin;
	
	List<MemberVO> list = new ArrayList<MemberVO>();
	@FXML AnchorPane AnchorPane;
	@FXML Button back;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ilogin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		//로그인 화면에서 회원가입을 눌렀을때.

		
		Write_ID_Text.setText("1");
		Write_Pass_Text.setText("1");
	}
	
	public void login() {
		if (Write_ID_Text.getText().equals("") || Write_Pass_Text.getText().equals("")
				|| Write_ID_Text.getText() == null || Write_Pass_Text.getText() == null) {
			errorMsg("로그인 에러!", "아이디 또는 비밀번호를 입력하지 않았습니다!");
			return;
		}
		
		String mem_mail = Write_ID_Text.getText();
		String mem_pass = Write_Pass_Text.getText();
		MemberVO vo = new MemberVO();
		
		MemberVO mvo = new MemberVO();
		mvo.setMem_mail(mem_mail);
		mvo.setMem_pass(mem_pass);
		
		try {
			vo = (MemberVO)ilogin.selectMemberVO(mvo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		if(vo != null) {
			infoMsg("로그인 성공", "오카카톡에 오신것을 환영합니다! ");
			Session.memVO = vo;
			Session.memVO.setMem_birth(Session.memVO.getMem_birth().substring(0, 11));
			Node chatScene = null;
			try {
				chatScene = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/shoppingMain/ShoppingMain.fxml"));
				AnchorPane.getChildren().setAll(chatScene);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			
		}else {
			errorMsg("로그인 실패!", "로그인에 실패하였습니다.");
		}
	}
	
	// 로그인
	@FXML public void memLoginBtnOnAction() {
		login();
	}

	/**
	 * id 찾기
	 */
	@FXML public void memSearchidBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchId.fxml"));
		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 비밀번호 찾기
	 */
	@FXML public void memSearchpassBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchPass.fxml"));
		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	private void errorMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	private void infoMsg(String headerText, String msg) {
        Alert errAlert = new Alert(AlertType.INFORMATION);
        errAlert.setTitle("정보 확인");
        errAlert.setHeaderText(headerText);
        errAlert.setContentText(msg);
        errAlert.showAndWait();
     }

	@FXML public void WriteIDTextOnKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	@FXML public void WritePassTextOnKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	@FXML public void backBtnOnAction() {
		Node logout = null;
		try {
			logout  = FXMLLoader.load(getClass().getResource("../main/Main.fxml"));
			AnchorPane.getChildren().setAll(logout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
	
	
