package kr.or.ddit.view.join;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.layout.AnchorPane;

public class JoinController implements Initializable {

	@FXML
	Button Join_Ok_Btn;
	@FXML
	TextField Join_Mail_Text;
	@FXML
	TextField Join_Pass_Text;
	@FXML
	TextField Join_Passchk_Text;
	@FXML
	TextField Join_Birth_Text;
	@FXML
	TextField Join_Phone_Text;
	@FXML
	TextField Join_Nick_Text;
	@FXML
	TextField Join_Addr_Text;
	@FXML
	TextField Join_Detailaddr_Text;
	@FXML
	Button Join_Mailchk_Btn;
	@FXML
	Button Join_Nickchk_Btn;
	
	@FXML TextField Join_Name_Text;
	
	@FXML
	Button Join_Mailconfirm_Btn;
	
	public static Integer NO;
	private Registry reg;
	private IMemberService ijoin;

	List<MemberVO> list = new ArrayList<MemberVO>();

	private MemberVO mvo = new MemberVO();

	static int chk_cnt = 0;
	int pattern_cnt = 0;
	static int no_cnt = 0;
	boolean result = false;


	AES256Util aes;
	@FXML Button back;
	@FXML AnchorPane AnchorPane;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry(kr.or.ddit.session.Session.IPaddress, 8315);
			ijoin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// 로그인 화면에서 회원가입을 눌렀을때.
		

	}

	@FXML
	public void joinOkBtnOnAction() {

		String mem_mail = Join_Mail_Text.getText();
		String mem_pass = Join_Pass_Text.getText();
		String mem_pass2 = Join_Passchk_Text.getText();

		if (mem_pass.equals(mem_pass2)) {
			pattern_cnt++;
			System.out.println("비밀번호 일치" + pattern_cnt);

		} else {
			errMsg("비밀번호 오류", " 입력하신 비밀번호가 일치하지 않습니다!");
			return;
		}
		
		/**
		 * 이름 정규식 한글만 가능 /^[가-힣]+$/
		 */

		String mem_name = Join_Name_Text.getText().trim();
		Pattern p3 = Pattern.compile("^[가-힣]*$");
		Matcher m3 = p3.matcher(mem_name);
		result = m3.matches();

		if (result) { 
			pattern_cnt++;
			System.out.println("이름 정규화" + pattern_cnt);
		} else if (mem_name.equals(" ") || mem_name == null) {
			errMsg("에러!", "이름을 입력하지 않으셨습니다.");
			no_cnt++;
			return;
		} else if (result == false) {
			errMsg("에러!", "이름은 한글만 가능합니다.");
			return;
		}
		
		
		/**
		 * 생년월일 ex)941218 ==> '-' 쓰면 안됨 [0-9]{6}
		 */

		String mem_birth = Join_Birth_Text.getText();
		Pattern p4 = Pattern.compile("[0-9]{6}");
		Matcher m4 = p4.matcher(mem_birth);
		result = m4.matches();

		if (result) {
			pattern_cnt++;
			System.out.println("생년월일 정규화" + pattern_cnt);
		} else if (mem_birth.equals("") || mem_birth == null) {
			errMsg("에러!", "생년월일을 입력하지 않으셨습니다.");
			no_cnt++;
			return;
		} else if (result == false) {
			errMsg("에러", "생년월일을 형식에 맞게 입력하여 주십시오");
			return;
		}
		/**
		 * 핸드폰 번호 정규식 [0-9]{11}
		 */

		String mem_hp = Join_Phone_Text.getText();

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
			return;
		}

		
		
		String mem_nick = Join_Nick_Text.getText();
		Pattern p6 = Pattern.compile("^[가-힣]*$");
		Matcher m6 = p6.matcher(mem_nick);
		result = m6.matches();

		if (result) {
			pattern_cnt++;
			System.out.println("닉네임 정규화" + pattern_cnt);
		} else {
			errMsg("에러!", "닉네임은 한글만 사용 할 수 있습니다.");
			return;
		}

		/*
		 * String mem_zipcode = txtf_zipcode.getText(); if (mem_zipcode.equals("") ||
		 * mem_zipcode == null) { errMsg("에러!", "우편번호를 입력하지 않으셨습니다"); no_cnt++; return;
		 * }
		 * 
		 * String mem_addr = txtf_addr1.getText(); if (mem_addr.equals("") || mem_addr
		 * == null) { errMsg("에러!", "우편번호를 입력하지 않으셨습니다"); no_cnt++; return; }
		 */
		System.out.println(chk_cnt + "," + pattern_cnt + "," + no_cnt );
		if (chk_cnt == 3 && pattern_cnt == 7 && no_cnt == 0) {

			Object cnt = 0;
			mvo.setMem_mail(mem_mail);
			mvo.setMem_pass(mem_pass);
			mvo.setMem_birth(mem_birth);
			mvo.setMem_name(mem_name);
			mvo.setMem_hp(mem_hp);
			mvo.setMem_nick(mem_nick);

			/**
			 * 은혜 추가. 주소/우편번호 입력
			 */

				String addr = Join_Addr_Text.getText();
				String Daddr = Join_Detailaddr_Text.getText();
				String sumadd = addr + "," + Daddr;
				mvo.setMem_addr(sumadd);
			 

			try {
				cnt = ijoin.insertMemberVO(mvo);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

			if (cnt == null) {
				infoMsg("회원가입 성공", "어서오세요! 대전의 명물 오카카톡입니다! ");
				Node logout = null;
				try {
					logout  = FXMLLoader.load(getClass().getResource("../main/Main.fxml"));
					AnchorPane.getChildren().setAll(logout);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				errMsg("회원가입 실패 ..", "왜일까요? ");
			}

		} else {
			errMsg("회원가입 실패", "입력하신 정보 중 올바르지 않은 정보가 있습니다.");
			chk_cnt = 0;
			pattern_cnt = 0;
			no_cnt = 0;

		}
	}

	@FXML
	public void joinMailconfirmBtnOnAction() {
		
		
		String to = Join_Mail_Text.getText();
		// NO라는 숫자변수를 보냈씁니다.
		NO = (int) (Math.random() * 10000);
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = ""; // 패스워드
		String password = "";
		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 메일 제목
			message.setSubject("[JavaMail 이메일 확인] 오카카톡");
			// 메일 내용
			message.setText(NO + "의 숫자를 입력하세용.");
			// send the message
			Transport.send(message);
			System.out.println("Success Message Send");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MailConfirm.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.setTitle("[오카카톡] 이메일 확인창");
		stage.setScene(new Scene(root));
		stage.show();

	}

	@FXML
	public void joinMailchkBtnOnAction() {

		String mem_mail = Join_Mail_Text.getText();
		Pattern p1 = Pattern.compile("\\w*@[a-z]*\\.[a-z]{3}");
		Matcher m1 = p1.matcher(mem_mail);
		result = m1.matches();

		
		MemberVO mvo = new MemberVO();

		try {
			mvo = ijoin.idSearch(mem_mail);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		if(mvo!=null || result == false) {
			errMsg("아이디 찾기 에러!!", "해당 정보에 해당하는 ID가 존재하거나 형식에 맞지않습니다.");
			return;
		}
		else if(Join_Mail_Text.getText().equals("") || Join_Mail_Text.getText() == null){
			errMsg("중복체크", "아이디를 입력해주십시오.");
			return;
		}
		else {
			infoMsg("확인", "사용하실 수 있는 아이디 입니다.");
			chk_cnt++;
			pattern_cnt++;
			return;
		}
		//
		// if (result) {
		// pattern_cnt++;
		// System.out.println("아이디 정규화" + pattern_cnt);
		// } else {
		// errMsg("아이디 오류", "형식에 맞지 않는 아이디 입니다!");
		// }

	}

	@FXML
	public void joinNickchkBtnOnAction() {

		String mem_nick = Join_Nick_Text.getText();
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


	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	private void infoMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.INFORMATION);
		errAlert.setTitle("오카카톡 Id 찾기");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
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

	@FXML public void clear2() {
		Join_Mail_Text.clear();
		
	}

	@FXML public void clear3() {
		Join_Birth_Text.clear();
	}

	@FXML public void clear4() {
		Join_Phone_Text.clear();
	}
		
	@FXML public void clear1() {
		Join_Name_Text.clear();
	}

	@FXML public void clear5() {
		Join_Nick_Text.clear();
	}
}
