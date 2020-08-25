package kr.or.ddit.view.shoppingMain.UserInfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.ShoppingMainMainController;
import kr.or.ddit.vo.MemberVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UserInfoModifyController implements Initializable {
	private Registry reg;
	private IMemberService ijoin;
	@FXML
	TextField User_Mail_Text;
	@FXML
	TextField User_Pass_Text;
	@FXML
	TextField User_Passchk_Text;
	@FXML
	TextField User_Phone_Text;
	@FXML
	TextField User_Nick_Text;
	@FXML
	TextField User_Addr_Text;
	@FXML
	TextField User_Detailaddr_Text;
	@FXML
	Button User_Modifyconfirm_Btn;
	@FXML
	Button User_Mailchange_Btn;
	@FXML
	TextField User_Newpass_Text;
	@FXML
	Button User_Nickchk_Btn;
	@FXML
	Button User_Modify_Can_Btn;
	@FXML 
	AnchorPane AnchorPane;
	static int chk_cnt = 0;
	int pattern_cnt = 0;
	static int no_cnt = 0;
	boolean result = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ijoin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// 로그인 화면에서 회원가입을 눌렀을때.
	}

	MemberVO mv = new MemberVO();


	@FXML
	public void UserNickchkBtnOnAction() {

		String mem_nick = User_Nick_Text.getText();
		Pattern p999 = Pattern.compile("^[가-힣]*$");
		Matcher m999 = p999.matcher(mem_nick);
		result = m999.matches();

		if (mem_nick.equals("") || mem_nick == null) {
			errMsg("에러!", "닉네임을 입력하지 않으셨습니다!");
			no_cnt++;
			return;
		} else if (result == false) {
			errMsg("에러!", "형식에 맞지 않는 닉네임 입니다!");
			return;
		}

		ArrayList<MemberVO> mList = new ArrayList<>();

		MemberVO mv = new MemberVO();
		mv.setMem_nick(mem_nick);
		// nick search를 불러온다.
		try {
			mList = (ArrayList<MemberVO>) ijoin.nickSearch(mv);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		System.out.println(mList.size());
		if (mList.size() == 0) {
			infoMsg("중복체크", "사용하실 수 있는 닉네임 입니다.");
			chk_cnt++;
			pattern_cnt++;
			System.out.println("닉네임 중복체크" + chk_cnt);
		} else {
			errMsg("중복체크", "이미 사용하고 있는 닉네임 입니다.");
			return;
		}

	}

	@FXML
	public void UserModifyCanBtnOnAction() {

		Node back = null;
		try {
			back = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(back);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


	@FXML
	public void UserModifyOkBtnOnAction() {
		String mem_pass = User_Pass_Text.getText();
		String newmem_pass = User_Newpass_Text.getText();
		String mewmem_passchk = User_Passchk_Text.getText();
		
		
		String addr = User_Addr_Text.getText();
		String Daddr = User_Detailaddr_Text.getText();
		String sumadd = addr + "," + Daddr;
		mv.setMem_addr(sumadd);
		
		

		if (newmem_pass.equals(mewmem_passchk) && Session.memVO.getMem_pass().equals(mem_pass)) {
			pattern_cnt++;
			System.out.println("비밀번호 일치" + pattern_cnt);

		} else {
			errMsg("비밀번호 오류", " 입력하신 비밀번호가 일치하지 않습니다!");
			return;
		}

		/**
		 * 핸드폰 번호 정규식 [0-9]{11}
		 */

		String mem_hp = User_Phone_Text.getText();

		Pattern p5 = Pattern.compile("[0-9]{11}");
		Matcher m5 = p5.matcher(mem_hp);
		result = m5.matches();

		if (result) {
			pattern_cnt++;
			System.out.println("핸드폰 정규화" + pattern_cnt);
		} else if (mem_hp.equals("") || mem_hp == null) {
			errMsg("에러!", "핸드폰 번호를 입력하지 않으셨습니다!");
			no_cnt++;
			return;
		} else if (result == false) {
			errMsg("에러!", "핸드폰 번호를 입력할 땐 '-' 을 사용하지 말아주세요.");
			chk_cnt= 0 ;
			pattern_cnt = 0;
			no_cnt = 0;
			return;
		}

		if (chk_cnt == 1 && pattern_cnt == 3 && no_cnt == 0) {

			String nick = User_Nick_Text.getText();
			Object cnt = 0;

			String mem_mail = Session.memVO.getMem_mail();
			mv.setMem_pass(newmem_pass);
			mv.setMem_hp(mem_hp);
			mv.setMem_nick(nick);
			mv.setMem_mail(mem_mail);

			int resultcnt = 0;

			try {
				resultcnt = ijoin.updatemodifyMember(mv);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			if (resultcnt == 1) {
				infoMsg("성공", "정상적으로 수정되었습니다.");
				mv.setMem_name((Session.memVO.getMem_name()));
				mv.setMem_cash((Session.memVO.getMem_cash()));
				mv.setMem_drop((Session.memVO.getMem_drop()));
				mv.setMem_message((Session.memVO.getMem_message()));
				mv.setMem_birth((Session.memVO.getMem_birth()));
				mv.setMem_point(Session.memVO.getMem_point());

				Session.memVO = mv;
				
				Node logout = null;
				try {
					logout  = FXMLLoader.load(getClass().getResource("../../main/Main.fxml"));
					AnchorPane oo=(AnchorPane)AnchorPane.getParent();
					AnchorPane pp=(AnchorPane)oo.getParent();
					pp.getChildren().clear();
					pp.getChildren().setAll(logout);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				
				
				
			} else {
				errMsg(" 실패 ", " 수정에 실패하였습니다. ");
			}

		} else {
			errMsg("실패!", "제대로 되었나 확인해주세요.");
			chk_cnt= 0 ;
			pattern_cnt = 0;
			no_cnt = 0;

		}
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	private void infoMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("오카카톡 유저 수정");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
}
