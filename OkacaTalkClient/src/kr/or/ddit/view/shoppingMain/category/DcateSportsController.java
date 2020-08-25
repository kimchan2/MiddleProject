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

public class DcateSportsController implements Initializable{

	@FXML AnchorPane Shop_Dcate;
	@FXML Button Shop_SportsCloth_Btn;
	@FXML Button Shop_Fitness_Btn;
	@FXML Button Shop_Ball_Btn;
	@FXML Button Shop_Golf_Btn;
	@FXML Button Shop_Leisure_Btn;
	@FXML Button Shop_Camping_Btn;

	Registry reg;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ShopSportsClothBtnOnAction() {
		Node SportsCloth = null;
		Session.curclindex = 20;
		
		try {
			SportsCloth = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(SportsCloth);
	}

	@FXML public void ShopFitnessBtnOnAction() {
		Node Fitness = null;
		Session.curclindex = 21;
		
		try {
			Fitness = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Fitness);
	}

	@FXML public void ShopBallBtnOnAction() {
		Node Ball = null;
		Session.curclindex = 22;
		
		try {
			Ball = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Ball);
	}

	@FXML public void ShopGolfBtnOnAction() {
		Node Golf = null;
		Session.curclindex = 23;
		
		try {
			Golf = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Golf);
	}


	@FXML public void ShopLeisureBtnOnAction() {
		Node Leisure = null;
		Session.curclindex = 24;
		
		try {
			Leisure = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Leisure);
	}
	
	@FXML public void ShopCampingBtnOnAction() {
		Node Camping = null;
		Session.curclindex = 25;
		
		try {
			Camping = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Camping);
	}
}
