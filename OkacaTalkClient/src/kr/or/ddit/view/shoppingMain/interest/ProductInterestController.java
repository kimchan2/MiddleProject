package kr.or.ddit.view.shoppingMain.interest;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import kr.or.ddit.service.IFavoriteService;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.interest.handle.ActionEventHandle;
import kr.or.ddit.view.shoppingMain.myQna.handler.ActionEventHandler;
import kr.or.ddit.vo.FavoriteListVO;
import kr.or.ddit.vo.FavoriteVO;
import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;

public class ProductInterestController implements Initializable{

	@FXML AnchorPane InterestMain;
	@FXML ScrollPane InterestSC;
	@FXML VBox InterestVBox;

	Registry reg;
	IFavoriteService Ifs;
	IProductListService Ips;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<FavoriteListVO> list = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		map.put("mem_mail", Session.memVO.getMem_mail());
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			Ifs = (IFavoriteService) reg.lookup("favorite");
			Ips = (IProductListService) reg.lookup("productList");
			
//			pllist = Ips.getProductListVO2(pl_index);
			list = Ifs.getFavoriteVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i =0 ; i<list.size(); i++) {
			FavoriteListVO favo = list.get(i);
			
			InterestVBox.getChildren().add (createInterest(favo,  Ips ));
		}
		
	}
	

	public AnchorPane createInterest(final FavoriteListVO favo, IProductListService ips) {
		IProductListService Ips = ips;
		AnchorPane InterestStack = new AnchorPane();
		InterestStack.setPrefWidth(750);
		InterestStack.setPrefHeight(200);
		InterestStack.setId("InterestStack_"+favo.getPl_index());
		
		// 이미지
		 Image pri = new Image(favo.getPl_image());
		ImageView ProdImage = new ImageView();
		ProdImage.setFitWidth(200);
		ProdImage.setFitHeight(150);
		ProdImage.setLayoutX(20);
		ProdImage.setLayoutY(25);
		ProdImage.setImage(pri);
		ProdImage.setId("ProdImage_"+favo.getPl_index());
		ProdImage.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				ProductListVO pllist = new ProductListVO();
				ImageView o = (ImageView)event.getTarget();
				System.out.println(o.getId());
				String id = o.getId();
				
				String arr[] =  id.split("_");
				//숫자를 잡았다.
				int num = Integer.parseInt(arr[1]);
				System.out.println(num);
				try {
					pllist = Ips.getProductListVO2(num);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Session.curplindex = pllist.getPl_image();

				Node back = null;
				try {
					back = FXMLLoader.load(getClass().getResource("../shoppingdetail/ProductDetailPage.fxml"));
					Session.moveConf.getShopping().getChildren().clear();
					Session.moveConf.getShopping().getChildren().add(back);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				
			}
		});
		InterestStack.getChildren().add(ProdImage);
		
		// 제품 이름
		Label ProdName = new Label();
		ProdName.setPrefWidth(320);
		ProdName.setPrefHeight(25);
		ProdName.setLayoutX(250);
		ProdName.setLayoutY(65);
		ProdName.setText(favo.getPl_name());
		ProdName.setAlignment(Pos.CENTER);
		ProdName.setStyle("-fx-font-size: 18");
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(ProdName.getText());
		Tooltip.install(ProdName, tt);
		
		InterestStack.getChildren().add(ProdName);
		
		// 상품 가격
		Label ProdPrice = new Label();
		ProdPrice.setPrefWidth(320);
		ProdPrice.setPrefHeight(25);
		ProdPrice.setLayoutX(250);
		ProdPrice.setLayoutY(115);
		ProdPrice.setText(Integer.toString(favo.getPl_price()));
		ProdPrice.setAlignment(Pos.CENTER);
		ProdPrice.setStyle("-fx-font-size: 18");
		InterestStack.getChildren().add(ProdPrice);
		
		ActionEventHandle handle = new ActionEventHandle(Ifs);
		// 관심 목록 삭제 버튼
		Button InterDelete = new Button();
		InterDelete.setId("InterDelete_"+favo.getPl_index());
		InterDelete.setPrefWidth(110);
		InterDelete.setPrefHeight(70);
		InterDelete.setLayoutX(600);
		InterDelete.setLayoutY(70);
		InterDelete.setText("관심 목록 삭제");
		InterDelete.setTextAlignment(TextAlignment.CENTER);
		InterDelete.setStyle("-fx-font-size: 15");
		InterDelete.setStyle("-fx-border-color: none");
		InterDelete.setStyle("-fx-background-color: f5efa5");
		
		InterDelete.setOnAction(handle);  // action 만들기 .
		InterestStack.getChildren().add(InterDelete);
		
		
		return InterestStack;
		
	}

}
