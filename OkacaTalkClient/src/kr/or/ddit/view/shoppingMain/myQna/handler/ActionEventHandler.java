package kr.or.ddit.view.shoppingMain.myQna.handler;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.jws.Oneway;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.vo.InquireVO;

public class ActionEventHandler implements EventHandler<ActionEvent> {
	private IInquireService iqservice;
	static String modify = "";
	int cnt = 0;
	public ActionEventHandler(IInquireService iqs) {
		
		iqservice = iqs;
	}


	@Override
	public void handle(ActionEvent e) {
		Button o = (Button)e.getTarget();
		System.out.println(o.getId());
		String id = o.getId();
		String arr[] =  id.split("_");
		//숫자를 잡았다.
		int num = Integer.parseInt(arr[1]);
		
		
		AnchorPane pane =(AnchorPane)o.getParent();
		//텍스트 필드 갖고옴!
		TextField textf=(TextField)pane.lookup("#producttext_"+num);
		
		VBox vbox = (VBox)pane.getParent();
		
		AnchorPane Dpane = (AnchorPane)vbox.lookup("#MyQnaStack_"+num);
		
		
		if(id.startsWith("modify_")) {
			textf.setEditable(true);
		}else if(id.startsWith("Ok_")) {
			InquireVO vo = new InquireVO();
			vo.setIq_index(num);
			vo.setIq_content(textf.getText());
			cnt = OK(vo);
			if(cnt==1) {
				System.out.println("오예");
				textf.setEditable(false);
			}else {
				errMsg("실패!", "실패당!");
			}
			
			
		}else if(id.startsWith("delete_")) {
			cnt = delete(num);
			if(cnt==1) {
			vbox.getChildren().remove(Dpane);
			}else {
				errMsg("실패!", "실패당!");
			}
		}
		
		
		
	}
	
	private int OK(InquireVO vo) {
		int cnt = 0;
		try {
			cnt = iqservice.updateInquireVO(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cnt==1) {
			System.out.println("완료!");
		}else{
			System.out.println("실패!");
		}
		return cnt;
	}
		
		
		
		
	
	private int delete(int num) {
		int cnt = 0;
		
		try {
			cnt = iqservice.deleteInquireVO(num);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cnt==1) {
			System.out.println("완료!");
		}else{
			System.out.println("실패!");
		}
		return cnt;
	}
	
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
