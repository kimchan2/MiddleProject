package kr.or.ddit.view.shoppingMain.faq;

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
import kr.or.ddit.service.IFAQService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.FAQVO;

public class FaqListController implements Initializable{

	@FXML AnchorPane FaqMain;
	@FXML ScrollPane FaqSC;
	@FXML VBox FaqVBox;
	
	Registry reg;
	private IFAQService ifq;
	private List<FAQVO> list;
	
	@FXML TextArea Faq_Content_Text;
	@FXML TextArea Faq_Title_Text;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ifq = (IFAQService) reg.lookup("faq");
			System.out.println("rmi 성!공");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			list = ifq.getFAQVO();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			FaqVBox.getChildren().add(createFaq(list.get(i)));
		}
	}
	
	public AnchorPane createFaq(FAQVO fa) {
		AnchorPane FaqListStack = new AnchorPane();
		FaqListStack.setPrefWidth(340);
		FaqListStack.setPrefHeight(800);
		
		// FAQ 제목
		Button Title = new Button(fa.getFaq_title());
		Title.setPrefWidth(300);
		Title.setPrefHeight(45);
		Title.setLayoutX(15);
		Title.setLayoutY(15);
//		Title.setStyle("-fx-border-color: gray");
		Title.setStyle("-fx-background-color: #AFFFEE");
		FaqListStack.getChildren().add(Title);
		
		Title.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/shoppingMain/faq/FaqContent.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
			TextArea txta = (TextArea) parent.lookup("#Faq_Content_Text");
			txta.setText(fa.getFaq_content());
			txta.setEditable(false);
			
			TextArea txta2 = (TextArea) parent.lookup("#Faq_Title_Text");
			txta2.setText(fa.getFaq_title());
			txta2.setEditable(false);;
		});
		
		return FaqListStack;
		
	}

}
