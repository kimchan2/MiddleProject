package kr.or.ddit.view.shoppingMain.category;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.shoppingdetail.ShoppingDetailController;
import kr.or.ddit.vo.CategoryListVO;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class DcateBrandController implements Initializable{

	@FXML AnchorPane Shop_Dcate;
	@FXML Button Shop_Womanbrand_Btn;
	@FXML Button Shop_Manbrand_Btn;
	
	private Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	// shoppingDetail 화면에 있는 이미지, 이름, 가격이 전환
	@FXML public void shopWomanbrandBtnOnAction() {
		Node Womanbrand = null;
		Session.curclindex = 0;
		try {
			Womanbrand = FXMLLoader.load(getClass().getResource("../shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Session.cateNum = 1;
		
		// shoppingdetailcontroller 다시 호출 / 메인 쇼핑 비워주기 / controller shoppingmain에 넣어주기
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

		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Manbrand);

		
	}
	
}
