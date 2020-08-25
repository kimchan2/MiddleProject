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

public class DcateFoodController implements Initializable {

	@FXML AnchorPane Shop_Dcate;
	@FXML Button Shop_Freshfood_Btn;
	@FXML Button Shop_Healthyfood_Btn;
	@FXML Button Shop_Manufacturefood_Btn;
	@FXML Button Shop_Dailynecessity_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ShopFreshfoodBtnOnAction() {
		Node FreshFood = null;
		Session.curclindex = 8;
		
		try {
			FreshFood = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(FreshFood);
	}

	@FXML public void ShopManufacturefoodBtnOnAction() {
		Node ManufactureFood = null;
		Session.curclindex = 9;
		
		try {
			ManufactureFood = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(ManufactureFood);
	}
	
	@FXML public void ShopHealthyfoodBtnOnAction() {
		Node HealthyFood = null;
		Session.curclindex = 10;
		
		try {
			HealthyFood = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(HealthyFood);
	}


	@FXML public void ShopDailynecessityBtnOnAction() {
		Node DailyNecessity = null;
		Session.curclindex = 11;
		
		try {
			DailyNecessity = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(DailyNecessity);
	}

}
