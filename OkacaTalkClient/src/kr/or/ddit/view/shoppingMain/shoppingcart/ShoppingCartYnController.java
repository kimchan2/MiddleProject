package kr.or.ddit.view.shoppingMain.shoppingcart;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.service.IShoppingCartService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ProductListVO;
import kr.or.ddit.vo.ShoppingCartVO;

public class ShoppingCartYnController implements Initializable {

	@FXML Button Cart_Ok_Btn;
	@FXML Button Cart_No_Btn;

	Registry reg;
	IProductListService ipl;
	IShoppingCartService isc;
	ProductListVO pvo = new ProductListVO();
	ProductListVO pvo2 = new ProductListVO();
	ShoppingCartVO svo = new ShoppingCartVO();
	ShoppingCartVO svo2 = new ShoppingCartVO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ipl = (IProductListService) reg.lookup("productList");
			isc = (IShoppingCartService) reg.lookup("shoppingCart");
			pvo2 = ipl.getdetailproduct(Session.curplindex);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@FXML public void CartOkBtnOnAtion() {
		// 선택한 상품을 장바구니에 넣어주기
		svo.setSc_index(svo2.getSc_index()); // 장바구니 인덱스
		svo.setPl_index(pvo2.getPl_index()); // 상품 인덱스
		svo.setMem_mail(Session.memVO.getMem_mail()); // 사용자 메일
		svo.setSc_quantity(Integer.parseInt(Session.quantity)); // 수량
		int cart = 0;
		
		Node ShopCart;
		Node Cate;
		
		try {
			cart = isc.insertShoppingCartVO(svo);
			if(cart == 0) {
				System.out.println("실패!");
			} else {
				System.out.println("성공!");
			}
			
			ShopCart = FXMLLoader.load(getClass().getResource("ShoppingCart2.fxml"));
			Cate = FXMLLoader.load(getClass().getResource("../MyPage/MypageCategory.fxml"));
			Session.moveConf.getShop_Cate().getChildren().clear();
			Session.moveConf.getShop_Cate().getChildren().add(Cate);
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(ShopCart);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) Cart_Ok_Btn.getScene().getWindow();
		stage.close();
	}

	@FXML public void CartNoBtnOnction() {
		Stage stage = (Stage) Cart_No_Btn.getScene().getWindow();
		stage.close();
	}
	
	

}
