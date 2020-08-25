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
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.session.Session;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class DcateLifeController implements Initializable{

	@FXML AnchorPane Shop_Dcate;
	@FXML Button Shop_Furniture_Btn;
	@FXML Button Shop_Bed_Btn;
	@FXML Button Shop_Interior_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@FXML public void ShopFurnitureBtnOnAction() {
		Node Furniture = null;
		Session.curclindex = 12;
		
		try {
			Furniture = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Furniture);
	}

	@FXML public void ShopBedBtnOnAction() {
		Node Bed = null;
		Session.curclindex = 13;
		
		try {
			Bed = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Bed);
	}

	@FXML public void ShopInteriorBtnOnAction() {
		Node Interior = null;
		Session.curclindex = 14;
		
		try {
			Interior = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Interior);
	}

}
