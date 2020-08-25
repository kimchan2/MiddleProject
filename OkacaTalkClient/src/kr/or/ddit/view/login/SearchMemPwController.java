package kr.or.ddit.view.login;


import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.vo.MemberVO;

public class SearchMemPwController implements Initializable{
	@FXML TextField Pass_Find_Name;
	@FXML TextField Pass_Find_Id;
	@FXML Button Pass_Find_OkBtn;
	@FXML Button Pass_Find_CanBtn;
	
	private Registry reg;
	private IMemberService ilogin;
	
	List<MemberVO> list = new ArrayList<MemberVO>();
	private MemberVO mvo = new MemberVO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry(kr.or.ddit.session.Session.IPaddress, 8315);
			// 설정한 서버를 찾는 메소드 lookup
			ilogin = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	// 비밀 번호 찾기 OK버튼을 누를경우.

	@FXML
	public void passFindOkBtnOnAction() {
		if (Pass_Find_Id.getText().equals("") || Pass_Find_Name.getText().equals("") || Pass_Find_Id.getText() == null
				|| Pass_Find_Name.getText() == null) {
			errMsg("비밀번호 찾기 에러!", "입력하지 않은 정보가 있습니다.!");
			return;
		}

		String mem_mail = Pass_Find_Id.getText();
		String mem_name = Pass_Find_Name.getText();


		MemberVO mv = new MemberVO();
		mv.setMem_mail(mem_mail);
		mv.setMem_name(mem_name);

		try {
			mvo =  ilogin.pwSearch(mv);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		if (mvo!=null) {

			String host = "smtp.naver.com";
			final String user = "vov1252@naver.com";
			final String password = "gkfnshtkznfk333";

			infoMsg("비밀번호찾기 성공!", "귀하의 비밀번호를 이메일로 발송하였습니다");
			Stage stage = (Stage) Pass_Find_OkBtn.getScene().getWindow();
			stage.close();
			System.out.println(Pass_Find_Id.getText() + "로 이메일 발송");

			String to = Pass_Find_Id.getText();

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

				message.setSubject("[JavaMail 임시 비밀번호] 오카카톡");

				message.setText("귀하의 비밀번호는" + mvo.getMem_pass() + "입니다.");

				// send the message
				Transport.send(message);
				System.out.println("message sent successfully...");

			} catch (MessagingException e4) {
				e4.printStackTrace();
			}

		} else {
			errMsg("비밀번호 찾기 에러!", "입력하신 정보와 일치하는 아이디의 비밀번호가 존재하지 않습니다.");
			return;
		}
	}

	@FXML
	public void passFindCanBtnOnAction() {
		Stage stage = (Stage) Pass_Find_CanBtn.getScene().getWindow();
		stage.close();
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
		errAlert.setTitle("오카카톡 비밀번호 찾기");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
