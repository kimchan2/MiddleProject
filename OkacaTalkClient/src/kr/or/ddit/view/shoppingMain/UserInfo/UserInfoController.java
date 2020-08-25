package kr.or.ddit.view.shoppingMain.UserInfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class UserInfoController implements Initializable{

	@FXML TextField User_Name_Text;
	@FXML TextField User_Mail_Text;
	@FXML TextField User_Birth_Text;
	@FXML TextField User_Phone_Text;
	@FXML TextField User_Nick_Text;
	@FXML TextField User_Detailaddr_Text;
	@FXML Button User_Modify_Btn;
	@FXML TextField User_Addr_Text;
	private Registry reg;
	private IMemberService imember;
	MemberVO memVO = Session.memVO;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			imember = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		String[] arr = memVO.getMem_addr().split(",", 2); 
		User_Name_Text.setText(memVO.getMem_name());
		User_Mail_Text.setText(memVO.getMem_mail());
		User_Phone_Text.setText(memVO.getMem_hp());
		User_Nick_Text.setText(memVO.getMem_nick());
		User_Birth_Text.setText(memVO.getMem_birth());
		User_Addr_Text.setText(arr[0]);
		User_Detailaddr_Text.setText(arr[1]);
		
		
		User_Name_Text.setEditable(false);
		User_Name_Text.setEditable(false);
		User_Mail_Text.setEditable(false);
		User_Phone_Text.setEditable(false);
		User_Nick_Text.setEditable(false);
		User_Birth_Text.setEditable(false);
		User_Addr_Text.setEditable(false);
		User_Detailaddr_Text.setEditable(false);
		
	}
	

	
	

	@FXML public void joinOkBtnOnAction() {

		Node UserInfoModify;
		try {
			UserInfoModify = FXMLLoader.load(getClass().getResource("UserInfoModify.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(UserInfoModify);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
