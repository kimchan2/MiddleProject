package kr.or.ddit.view.shoppingMain.payment;

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
import javafx.scene.control.Label;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class PaymentCompleteController implements Initializable{

	@FXML Label Product_BuyDate_Label;
	@FXML Label Product_Price_Label;
	@FXML Button Order_Confirm_Btn;
	@FXML Button Shopping_Back_Btn;
	@FXML Button Buy_Confirm_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}

	@FXML public void OrderConfirmBtnOnAction() {
		Node orderconfirm = null;
		Node mypagecate = null;
		
		try {
			orderconfirm = FXMLLoader.load(getClass().getResource("../MyPage/MyPageMain.fxml"));
			mypagecate = FXMLLoader.load(getClass().getResource("../MyPage/MyPageCategory.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(orderconfirm);
			Session.moveConf.getShop_Cate().getChildren().clear();
			Session.moveConf.getShop_Cate().getChildren().add(mypagecate);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ShoppingBackBtnOnAction() {
		Node back = null;
		
		try {
			back = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(back);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@FXML public void BuyConfirmBtnOnAction() {
				
		Node paymentConfirm = null;
		
		try {
			paymentConfirm = FXMLLoader.load(getClass().getResource("PaymentConfirm.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(paymentConfirm);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
