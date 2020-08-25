package kr.or.ddit.view.shoppingMain.myQna;

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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import kr.or.ddit.service.IBackgroundImgListService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.myQna.handler.ActionEventHandler;
import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.InquireVO;
import kr.or.ddit.vo.QnAVO;
import kr.or.ddit.vo.SavePointVO;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MyQnaController implements Initializable{

	@FXML AnchorPane MyQnamain;
	@FXML ScrollPane MyQnaSC;
	@FXML VBox MyQnaVbox;
	private Registry reg;
	IInquireService Iqs;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<InquireJoinVO> list = new ArrayList<>();
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			Iqs = (IInquireService) reg.lookup("inquire");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			list = Iqs.selectInquireVO2(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i =0 ; i<list.size(); i++) {
			InquireJoinVO iqjvo = list.get(i);
			String prname = (String)list.get(i).getPl_name();
			String plimage = (String)list.get(i).getPl_image();
			String iqcont = (String)list.get(i).getIq_content();
			
			MyQnaVbox.getChildren().add (createQna(iqjvo));
		}
	}
	
	public AnchorPane createQna( final InquireJoinVO iqjvo ){
		
		AnchorPane MyQnaStack = new AnchorPane();
		MyQnaStack.setPrefWidth(762);
		MyQnaStack.setPrefHeight(200);
		MyQnaStack.setId("MyQnaStack_"+iqjvo.getIq_index());
		
		/** 
		 * 사진넣는거
		 */
		 Image pri = new Image(iqjvo.getPl_image());
		ImageView productimage = new ImageView();
		productimage.setFitWidth(200);
		productimage.setFitHeight(150);
		productimage.setLayoutX(50);
		productimage.setLayoutY(35);
		productimage.setImage(pri);
		MyQnaStack.getChildren().add(productimage);
		
		/**
		 * 상품제목텍스트
		 */
		Text productname = new Text();
		productname.setWrappingWidth(380);
		productname.setLayoutX(275);
		productname.setLayoutY(50);
		productname.setText(iqjvo.getPl_name());
		MyQnaStack.getChildren().add(productname);
		
		/**
		 * 상품내용텍스트
		 */
		TextField producttext = new TextField();
		producttext.setPrefWidth(380);
		producttext.setPrefHeight(70);
		producttext.setLayoutX(275);
		producttext.setLayoutY(75);
		producttext.setText(iqjvo.getIq_content());
		producttext.setEditable(false);
		producttext.setId("producttext_"+iqjvo.getIq_index());
		MyQnaStack.getChildren().add(producttext);
		
		/**
		 * 댓글커맨드
		 */
		Text qnacomment = new Text();
		qnacomment.setLayoutX(275);
		qnacomment.setLayoutY(175);
		qnacomment.setText("커멘드창입니다.");
		MyQnaStack.getChildren().add(qnacomment);
		ActionEventHandler handler = new ActionEventHandler(Iqs);
		
		Button ok = new Button();
		ok.setId("Ok_"+iqjvo.getIq_index());
		ok.setPrefWidth(52);
		ok.setPrefHeight(31);
		ok.setLayoutX(680);
		ok.setLayoutY(40);
		ok.setText("확인");
		ok.setStyle("-fx-border-color: none");
		ok.setStyle("-fx-background-color: f5efa5");
		ok.setOnAction(handler);
		
		MyQnaStack.getChildren().add(ok);
		
		Button confirm = new Button();
		confirm.setId("modify_"+iqjvo.getIq_index() );
		confirm.setPrefWidth(52);
		confirm.setPrefHeight(31);
		confirm.setLayoutX(680);
		confirm.setLayoutY(95);
		confirm.setText("수정");
		confirm.setStyle("-fx-border-color: none");
		confirm.setStyle("-fx-background-color: f5efa5");
		confirm.setOnAction(handler);
		MyQnaStack.getChildren().add(confirm);
		
		Button delete = new Button();
		delete.setId("delete_"+iqjvo.getIq_index());
		delete.setPrefWidth(52);
		delete.setPrefHeight(31);
		delete.setLayoutX(680);
		delete.setLayoutY(150);
		delete.setText("삭제");
		delete.setStyle("-fx-border-color: none");
		delete.setStyle("-fx-background-color: f5efa5");
		delete.setOnAction(handler);
		MyQnaStack.getChildren().add(delete);
		return  MyQnaStack;
	}
}
