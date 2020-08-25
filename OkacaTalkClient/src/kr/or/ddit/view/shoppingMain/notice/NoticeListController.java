package kr.or.ddit.view.shoppingMain.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.NoticeVO;

public class NoticeListController implements Initializable {

	@FXML AnchorPane NoticeMain;
	@FXML ScrollPane NoticeSC;
	@FXML VBox NoticeVBox;
	
	Registry reg;
	private INoticeService ins;
	private List<NoticeVO> list;
	
	@FXML TextArea Nt_List_Content_textarea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ins = (INoticeService) reg.lookup("notice");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			list = ins.getNoticeVO();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			NoticeVBox.getChildren().add(createNotice(list.get(i)));
		}
		
	}
	
	public AnchorPane createNotice(NoticeVO nt) {
		AnchorPane NoticeStack = new AnchorPane();
		NoticeStack.setPrefWidth(400);
		NoticeStack.setPrefHeight(100);
		
		// Notice 제목
		Button Title = new Button(nt.getNt_title());
		Title.setPrefWidth(300);
		Title.setPrefHeight(45);
		Title.setLayoutX(15);
		Title.setLayoutY(15);
//		Title.setStyle("-fx-border-color: gray");
		Title.setStyle("-fx-background-color: #AFFFEE");
		NoticeStack.getChildren().add(Title);
		
		Title.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/shoppingMain/notice/NoticeListDetail.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			Scene scene = new Scene(parent);
			Stage main1 = new Stage();
			main1.setScene(scene);
			main1.show();
			
			TextArea txta = (TextArea) parent.lookup("#Nt_List_Content_textarea");
			txta.setText(nt.getNt_content());
			txta.setEditable(false);
			txta.setWrapText(true);
			
		});

		return NoticeStack;
		
	}
	
	
}
