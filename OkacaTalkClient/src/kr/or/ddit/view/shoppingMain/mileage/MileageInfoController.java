package kr.or.ddit.view.shoppingMain.mileage;

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
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.service.IChargeCashService;
import kr.or.ddit.service.ISavePointService;
import kr.or.ddit.service.IUsingCashService;
import kr.or.ddit.service.IUsingPointService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChargeCashVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SavePointVO;
import kr.or.ddit.vo.UsingCashVO;
import kr.or.ddit.vo.UsingPointVO;
import javafx.scene.control.ListView;


public class MileageInfoController implements  Initializable {

	private Registry reg;
	ISavePointService spoint ;
	IUsingPointService upoint;

	 List<SavePointVO> list = new ArrayList<>();
	 List<UsingPointVO> list2 = new ArrayList<>();
	 ObservableList<String> chargeList = FXCollections.observableArrayList();
	 ObservableList<String> usingList = FXCollections.observableArrayList();

	@FXML ListView Mileage_DateAccumulate_List;
	@FXML ListView Using_DateAccumulate_List;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			spoint = (ISavePointService) reg.lookup("savePoint");
			upoint = (IUsingPointService) reg.lookup("usingPoint");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			list = spoint.getSavePointVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(SavePointVO vo : list) {
			chargeList.add(vo.toString());
		}
		
		
		Mileage_DateAccumulate_List.setItems(chargeList);
		
		
		try {
			list2 = upoint.getUsingPointVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(UsingPointVO vo : list2) {
			usingList.add(vo.toString());
		}
		
		Using_DateAccumulate_List.setItems(usingList);
		
	}

}
