package kr.or.ddit.view.shoppingMain.myreview;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import kr.or.ddit.service.IReviewService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.myreview.handler.ActionEventHandler4;
import kr.or.ddit.vo.MyReviewVO;
import kr.or.ddit.vo.ReviewVO;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MyReviewController implements Initializable {

	@FXML AnchorPane MyReviewMain;
	@FXML ScrollPane MyReviewSC;
	@FXML VBox MyReviewVBox;
	
	Registry reg;
	IReviewService irs;
	MyReviewVO mrvo = new MyReviewVO();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int cnt = 0;
		List<MyReviewVO> list = new ArrayList<>();
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			irs = (IReviewService) reg.lookup("review");
			list = (List<MyReviewVO>) irs.getReviewVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			mrvo = list.get(i);
			String pl_image = list.get(i).getPl_image();
			String pl_name = list.get(i).getPl_name();
			int pl_price = list.get(i).getPl_price();
			
			MyReviewVBox.getChildren().add(createMyReview(mrvo));
		}
	}
	
	public AnchorPane createMyReview(final MyReviewVO mrvo) {
		AnchorPane MyReviewStack = new AnchorPane();
		MyReviewStack.setPrefWidth(740);
		MyReviewStack.setPrefHeight(330);
		MyReviewStack.setStyle("-fx-background-color: white");
		MyReviewStack.setId("MyReviewStack_" + mrvo.getRv_index());
		
		// 이미지
		ImageView ProdImage = new ImageView();
		Image img = new Image(mrvo.getPl_image());
		ProdImage.setFitWidth(200);
		ProdImage.setFitHeight(150);
		ProdImage.setLayoutX(30);
		ProdImage.setLayoutY(10);
		ProdImage.setImage(img);
		MyReviewStack.getChildren().add(ProdImage);
		
		// 제품 이름
		Label ProdName = new Label();
		ProdName.setPrefWidth(390);
		ProdName.setPrefHeight(60);
		ProdName.setLayoutX(260);
		ProdName.setLayoutY(15);
		ProdName.setText(mrvo.getPl_name());
		ProdName.setAlignment(Pos.CENTER);
		ProdName.setWrapText(true);
		ProdName.setStyle("-fx-font-size: 18");
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(ProdName.getText());
		Tooltip.install(ProdName, tt);
				
		MyReviewStack.getChildren().add(ProdName);
		
		// 제품 가격
		Label ProdPrice = new Label();
		ProdPrice.setPrefWidth(390);
		ProdPrice.setPrefHeight(60);
		ProdPrice.setLayoutX(250);
		ProdPrice.setLayoutY(90);
		ProdPrice.setText(mrvo.getPl_price() + "원");
		ProdPrice.setAlignment(Pos.CENTER);
		ProdPrice.setWrapText(true);
		ProdPrice.setStyle("-fx-font-size: 20");
		MyReviewStack.getChildren().add(ProdPrice);
		
		// 리뷰 내용
		TextField ReviewContent = new TextField();
		ReviewContent.setId("reviewcontent_"+mrvo.getRv_index());
		ReviewContent.setPrefWidth(710);
		ReviewContent.setPrefHeight(70);
		ReviewContent.setLayoutX(15);
		ReviewContent.setLayoutY(175);
		ReviewContent.setText(mrvo.getRv_content());
		ReviewContent.setEditable(false);
		MyReviewStack.getChildren().add(ReviewContent);
		
		ActionEventHandler4 handler4 = new ActionEventHandler4(irs);
		
		// 댓글 내용
		Text ReviewComment = new Text();
//		ReviewComment.setPrefWidth(710);
//		ReviewComment.setPrefHeight(70);
		ReviewComment.setLayoutX(15);
		ReviewComment.setLayoutY(260);	//255
//		ReviewComment.setEditable(false);
		ReviewComment.setText("관리자 댓글");
//		ReviewComment.setOnAction(handler4);
		MyReviewStack.getChildren().add(ReviewComment);
		
		// 수정 버튼
		Button Modify = new Button();
		Modify.setId("modify_" + mrvo.getRv_index());
		Modify.setPrefWidth(65);
		Modify.setPrefHeight(35);
		Modify.setLayoutX(660);
		Modify.setLayoutY(10);
		Modify.setTextAlignment(TextAlignment.CENTER);
		Modify.setText("수정");
		Modify.setStyle("-fx-font-size: 18");
		Modify.setStyle("-fx-background-color: f5efa5");
		Modify.setStyle("-fx-border-color: none");
		Modify.setOnAction(handler4);
		MyReviewStack.getChildren().add(Modify);
		
		// 확인 버튼
		Button Confirm = new Button();
		Confirm.setId("confirm_" + mrvo.getRv_index());
		Confirm.setPrefWidth(65);
		Confirm.setPrefHeight(35);
		Confirm.setLayoutX(660);
		Confirm.setLayoutY(50);
		Confirm.setTextAlignment(TextAlignment.CENTER);
		Confirm.setText("확인");
		Confirm.setStyle("-fx-font-size: 18");
		Confirm.setStyle("-fx-background-color: f5efa5");
		Confirm.setStyle("-fx-border-color: none");
		Confirm.setOnAction(handler4);
		MyReviewStack.getChildren().add(Confirm);
		
		// 삭제 버튼
		Button Delete = new Button();
		Delete.setId("delete_" + mrvo.getRv_index());
		Delete.setPrefWidth(65);
		Delete.setPrefHeight(35);
		Delete.setLayoutX(660);
		Delete.setLayoutY(90);
		Delete.setTextAlignment(TextAlignment.CENTER);
		Delete.setText("삭제");
		Delete.setStyle("-fx-font-size: 18");
		Delete.setStyle("-fx-background-color: f5efa5");
		Delete.setStyle("-fx-border-color: none");
		Delete.setOnAction(handler4);
		MyReviewStack.getChildren().add(Delete);
		
		// 신고 수
		Label Declaration = new Label();
		Declaration.setPrefWidth(65);
		Declaration.setPrefHeight(35);
		Declaration.setLayoutX(660);
		Declaration.setLayoutY(130);
		Declaration.setText("신고수");
		Declaration.setAlignment(Pos.CENTER);
		Declaration.setStyle("-fx-font-size: 18");
		Declaration.setStyle("-fx-background-color: white");
		Declaration.setStyle("-fx-border-color: gray");
		MyReviewStack.getChildren().add(Declaration);
		
		// 추천 수
		Label Recommend = new Label();
		Recommend.setPrefWidth(65);
		Recommend.setPrefHeight(35);
		Recommend.setLayoutX(590);
		Recommend.setLayoutY(130);
		Recommend.setText("추천수");
		Recommend.setAlignment(Pos.CENTER);
		Recommend.setStyle("-fx-font-size: 18");
		Recommend.setStyle("-fx-background-color: white");
		Recommend.setStyle("-fx-border-color: gray");
		MyReviewStack.getChildren().add(Recommend);
		
		return MyReviewStack;
		
	}
	
}
