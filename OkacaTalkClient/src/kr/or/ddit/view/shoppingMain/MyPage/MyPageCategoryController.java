package kr.or.ddit.view.shoppingMain.MyPage;

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
import kr.or.ddit.view.shoppingMain.shoppingdetail.ProductDetailPageController;
import kr.or.ddit.view.shoppingMain.shoppingdetail.ShoppingDetailController;
import kr.or.ddit.vo.OrderListVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MyPageCategoryController implements Initializable {

	@FXML AnchorPane myPageLabel;
	@FXML Button My_Order_History_Btn;
	@FXML Button Interest_Prod_Btn;
	@FXML Button My_Shopping_Basket_Btn;
	@FXML Button Charging_Cash_Btn;
	@FXML Button My_Review_Btn;
	@FXML Button My_Profile_Btn;
	@FXML Button My_Mileage_Btn;
	@FXML Button My_Question_Btn;
	@FXML Button My_Shopping_Cart_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void MyShoppingCartBtnOnAction() {
		Node shoppingCart = null;
		try {
			shoppingCart = FXMLLoader.load(getClass().getResource("../shoppingcart/ShoppingCart2.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(shoppingCart);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void myProfileBtnOnAction() {
		Node UserInfo;
		try {
			UserInfo = FXMLLoader.load(getClass().getResource("../UserInfo/UserInfo.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(UserInfo);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void chargingCashBtnOnAction() {
		Node Chargecash;
		try {
			Chargecash = FXMLLoader.load(getClass().getResource("../cash/ChargeCash.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(Chargecash);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void myMileageBtnOnAction() {
		Node Mileage;
		try {
			Mileage = FXMLLoader.load(getClass().getResource("../mileage/MileageInfo.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(Mileage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void myQuestionBtnOnAction() {
		Node myQuestion = null;
		try {
			myQuestion = FXMLLoader.load(getClass().getResource("../myQna/MyQna.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(myQuestion);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void MyOrderHistoryBtnOnAction() {
		Node myOrder = null;
		try {
			myOrder = FXMLLoader.load(getClass().getResource("MyPageMain.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(myOrder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void InterestProdBtnOnAction() {
		Node interestProd = null;
		
		try {
			interestProd = FXMLLoader.load(getClass().getResource("../interest/ProductInterest2.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(interestProd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void MyReviewBtnOnAction() {
		Node myReview = null;
		
		try {
			myReview = FXMLLoader.load(getClass().getResource("../myreview/MyReview2.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(myReview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

