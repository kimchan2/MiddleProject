package kr.or.ddit.view.shoppingMain.category;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.session.Session;

public class DcateDigitalController implements Initializable {

	@FXML AnchorPane Shop_Dcate;
	@FXML Button Shop_PC_Btn;
	@FXML Button Shop_Monitor_Btn;
	@FXML Button Shop_PCdevice_Btn;
	@FXML Button Shop_Mobile_Btn;
	@FXML Button Shop_Homeappliance_Btn;

	Registry reg;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML public void ShopPCBtnOnAction() {
		Node PC = null;
		Session.curclindex = 15;
		
		try {
			PC = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(PC);
	}
	
	@FXML public void ShopMonitorBtnOnAction() {
		Node Monitor = null;
		Session.curclindex = 16;
		
		try {
			Monitor = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Monitor);
	}
	
	@FXML public void ShopPCdeviceBtnOnAction() {
		Node Device = null;
		Session.curclindex = 17;
		
		try {
			Device = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Device);
	}

	@FXML public void ShopMobileBtnOnAction() {
		Node Mobile = null;
		Session.curclindex = 18;
		
		try {
			Mobile = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Mobile);
	}
	
	@FXML public void ShopHomeapplianceBtnOnAction() {
		Node Home = null;
		Session.curclindex = 19;
		
		try {
			Home = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Home);
	}
}
