package kr.or.ddit.view.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.service.ICategoryListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.CategoryListVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.layout.AnchorPane;

public class MainMainController implements Initializable{

	@FXML Button Mem_Join_Btn;
	@FXML Button Mem_GoLogin_Btn;
	@FXML AnchorPane AnchorPane;

	private Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		Session.cateNum = 1;
		if(!Session.bloaded){
			
			try {
//				System.setProperty("java.rmi.server.hostname","192.168.44.67");
				reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
				Session.icategory = (ICategoryListService) reg.lookup("categoryList");
				System.out.println("카테고리 리스트 가져오기 시작...");
				Session.catelistCnt = Session.icategory.getCategoryCnt();
				for(int i = 1; i < Session.catelistCnt + 1; i++) {
					Session.catelist.add(Session.icategory.getCategory(i));
				}
				System.out.println("카테고리 리스트 가져오기 완료...");
				
				Session.iproduct = (IProductListService) reg.lookup("productList");
				System.out.println("상품 리스트 가져오기 시작...");
				for(int i =1; i < Session.catelistCnt + 1; i++) {
					Session.prodlist = (ArrayList<ProductListVO>) Session.iproduct.getProductListVO(i);
					Session.pprodlist.add(Session.prodlist);
				}
				System.out.println("상품 리스트 가져오기 완료...");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			Mem_GoLogin_Btn.requestFocus();
			
			Session.bloaded = true;
		}
	}

	@FXML public void memJoinBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/join/Join.fxml"));
		AnchorPane.getChildren().setAll(chatScene);
	}

	@FXML public void memGoLoginBtnOnAction() throws IOException {
		
		Node chatScene = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/login/Login.fxml"));
		AnchorPane.getChildren().setAll(chatScene);
		
	}

}
