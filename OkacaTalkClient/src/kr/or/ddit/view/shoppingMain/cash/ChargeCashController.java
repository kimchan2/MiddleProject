package kr.or.ddit.view.shoppingMain.cash;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.service.IChargeCashService;
import kr.or.ddit.service.IUsingCashService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChargeCashVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.UsingCashVO;
import javafx.scene.control.ListView;


public class ChargeCashController implements  Initializable {

	@FXML Label Product_Name_Label;
	@FXML Button Charge_Money_Btn;
	@FXML TextField Charge_Money_Text;
	@FXML ListView<String> Charge_DateMoney_List;
	@FXML ListView<String> Using_DateMoney_List;
	private Registry reg;
	private IMemberService ijoin;
	private IChargeCashService icash;
	
	private IUsingCashService ucash;

	 MemberVO mv = new MemberVO();
	 List<ChargeCashVO> list = new ArrayList<>();
	 List<UsingCashVO> list2 = new ArrayList<>();
	 ObservableList<String> chargeList = FXCollections.observableArrayList();
	 ObservableList<String> usingList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ijoin = (IMemberService) reg.lookup("member");
			icash = (IChargeCashService) reg.lookup("chargeCash");
			ucash = (IUsingCashService) reg.lookup("usingCash");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			list = icash.selectChargeCash(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(ChargeCashVO vo : list) {
			chargeList.add(vo.toString());
		}
		
		
		Charge_DateMoney_List.setItems(chargeList);
		
		
		try {
			list2 = ucash.getUsingCashVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(UsingCashVO vo : list2) {
			usingList.add(vo.toString());
		}
		
		Using_DateMoney_List.setItems(usingList);
		
		
		
		
	}

	@FXML public void chargeMoneyBtnOnAction() {
		
		
		Integer cash = (Integer.parseInt(Charge_Money_Text.getText()));
		
		Integer cash2 = Session.memVO.getMem_cash() + cash;
		
		 int resultcnt =0 ;
		 
		mv.setMem_cash(cash2);
		mv.setMem_mail(Session.memVO.getMem_mail());
		ChargeCashVO ccvo = new ChargeCashVO();
		ccvo.setMem_mail(Session.memVO.getMem_mail());
		ccvo.setCc_cash(cash);
		try {
			resultcnt =  ijoin.updatecashMember(mv);
			resultcnt = icash.insertChargeCash(ccvo);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		if(resultcnt== 1) {
			infoMsg("성공", "캐시충전에 성공하였습니다.");
			Session.memVO.setMem_cash(cash2);
			
			Node back = null;
			try {
				back = FXMLLoader.load(getClass().getResource("ChargeCash.fxml"));
				Session.moveConf.getShopping().getChildren().clear();
				Session.moveConf.getShopping().getChildren().add(back);

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			errMsg(" 실패 ", " 캐시충전에 실패하였습니다 ");
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
}
