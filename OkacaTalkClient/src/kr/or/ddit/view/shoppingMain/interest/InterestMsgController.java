package kr.or.ddit.view.shoppingMain.interest;

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
import kr.or.ddit.service.IFavoriteService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.FavoriteVO;
import kr.or.ddit.vo.ProductListVO;

public class InterestMsgController implements Initializable {

	@FXML Button Interest_Ok_Btn;
	@FXML Button Interest_No_Btn;

	Registry reg;
	IProductListService ipl;
	IFavoriteService ifv;
	ProductListVO pvo = new ProductListVO();
	ProductListVO pvo2 = new ProductListVO();
	FavoriteVO fvo = new FavoriteVO();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ipl = (IProductListService) reg.lookup("productList");
			ifv = (IFavoriteService) reg.lookup("favorite");
			pvo2 = ipl.getdetailproduct(Session.curplindex);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@FXML public void InterestOkBtnOnActioin() {
		// 관심상품에 추가한 데이터 관심상품페이지에 넣어주기 pl_index, mem_mail
		fvo.setPl_index(pvo2.getPl_index()); // 상품 인덱스
		fvo.setMem_mail(Session.memVO.getMem_mail()); // 사용자 메일
		int interest = 0;
		
		try {
			interest = ifv.insertFavoriteVO(fvo);
			if(interest == 0) {
				System.out.println("실패요..");
			} else {
				System.out.println("성공!!!!!");
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		
		Node InterestProd;
		Node Cate;
		try {
			Cate = FXMLLoader.load(getClass().getResource("../MyPage/MypageCategory.fxml"));
			InterestProd = FXMLLoader.load(getClass().getResource("ProductInterest2.fxml"));
			Session.moveConf.getShop_Cate().getChildren().clear();
			Session.moveConf.getShop_Cate().getChildren().add(Cate);
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(InterestProd);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = (Stage) Interest_Ok_Btn.getScene().getWindow();
		stage.close();
	}

	@FXML public void InterestNoBtnOnAction() {
		Stage stage = (Stage) Interest_No_Btn.getScene().getWindow();
		stage.close();
	}

}
