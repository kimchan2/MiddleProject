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
import kr.or.ddit.session.Session;
import javafx.scene.layout.AnchorPane;

public class DcateClothController implements Initializable{

	@FXML Button Shop_Bag_Btn;
	@FXML Button Shop_Shoes_Btn;
	@FXML Button Shop_Undercloth_Btn;
	@FXML Button Shop_Womancloth_Btn;
	@FXML Button Shop_Mancloth_Btn;
	@FXML Button Shop_Jewelry_Btn;

	@FXML AnchorPane DcateCloth;
	
	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ShopWomanclothBtnOnAction() {
		Node WomanCloth = null;
		Session.curclindex = 2;
		
		try {
			WomanCloth = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(WomanCloth);
	}
	
	@FXML public void ShopManclothBtnOnAction() {
		Node ManCloth = null;
		Session.curclindex = 3;
		
		try {
			ManCloth = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(ManCloth);
	}


	@FXML public void ShopUnderclothBtnOnAction() {
		Node UnderCloth = null;
		Session.curclindex = 4;
		
		try {
			UnderCloth = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(UnderCloth);
	}

	@FXML public void ShopShoesBtnOnAction() {
		Node ShoesCloth = null;
		Session.curclindex = 5;
		
		try {
			ShoesCloth = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(ShoesCloth);
	}

	@FXML public void ShopBagBtnOnAction() {
		Node Bag = null;
		Session.curclindex = 6;
		
		try {
			Bag = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Bag);
	}

	@FXML public void ShopJewelryBtnOnAction() {
		Node Jewelry = null;
		Session.curclindex = 7;
		
		try {
			Jewelry = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Jewelry);
	}
		
}
