package kr.or.ddit.view.shoppingMain.category;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import kr.or.ddit.session.Session;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CateAllController implements Initializable{

	@FXML Button Shop_Brand_Btn;
	@FXML Button Shop_Cloth_Btn;
	@FXML Button Shop_Food_Btn;
	@FXML Button Shop_Life_Btn;
	@FXML Button Shop_Digital_Btn;
	@FXML Button Shop_Sports_Btn;
	@FXML AnchorPane Shop_Cate_All;
	@FXML AnchorPane Shop_Cate;
	@FXML AnchorPane Shop_Dcate_Brand;
	@FXML Button Shop_Womanbrand_Btn;
	@FXML Button Shop_Manbrand_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	Node shoppingPage = null;
	
	@FXML public void shopBrandBtnOnAction() {
		Node brandCate = null;
		
		try {
			brandCate = FXMLLoader.load(getClass().getResource("category/DcateBrand.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(brandCate);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
	
	@FXML public void shopWomanbrandBtnOnAction() {
		Node Womanbrand = null;
		Session.curclindex = 0;
		try {
			Womanbrand = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Session.cateNum = 1;
		
//		getShopping().getChildren().clear();
//		getShopping().getChildren().setAll(Womanbrand);
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Womanbrand);
	}
	
	@FXML public void shopManbrandBtnOnAction() {
		Node Manbrand = null;
		Session.curclindex = 1;
		try {
			Manbrand = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 카테고리 변환
//		Session.cateNum = 2;
		
//		getShopping().getChildren().clear();
//		getShopping().getChildren().setAll(Manbrand);
		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Manbrand);
	}
	
	@FXML public void ShopClothBtnOnAction() {
		Node clothCate = null;
		try {
			clothCate = FXMLLoader.load(getClass().getResource("DcateCloth.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(clothCate);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopFoodBtnOnAction() {
		Node Food = null;
		try {
			Food = FXMLLoader.load(getClass().getResource("DcateFood.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Food);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopLifehBtnOnAction() {
		Node Life = null;
		try {
			Life = FXMLLoader.load(getClass().getResource("DcateLife.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Life);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopDigitalBtnOnAction() {
		Node Digital = null;
		try {
			Digital = FXMLLoader.load(getClass().getResource("DcateDigital.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Digital);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopSportsBtnOnAction() {
		Node Sports = null;
		try {
			Sports = FXMLLoader.load(getClass().getResource("DcateSports.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Sports);
		Session.moveConf.getShopping().getChildren().setAll(shoppingPage);
	}
}
