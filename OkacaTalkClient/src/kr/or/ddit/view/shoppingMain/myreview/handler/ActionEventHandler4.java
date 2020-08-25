package kr.or.ddit.view.shoppingMain.myreview.handler;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kr.or.ddit.service.IReviewService;
import kr.or.ddit.vo.MyReviewVO;

public class ActionEventHandler4 implements EventHandler<ActionEvent> {
	
	private IReviewService irservice;
	static String modify = "";
	int cnt = 0;
	
	public ActionEventHandler4(IReviewService irs) {
		irservice = irs;
	}
	
	@Override
	public void handle(ActionEvent event) {
		Button o = (Button)event.getTarget();
		System.out.println(o.getId());
		String id = o.getId();
		System.out.println(id);
		String arr[] = id.split("_");
		
		// 숫자를 잡았다?
		int num = Integer.parseInt(arr[1]);
		
		AnchorPane pane = (AnchorPane)o.getParent();
		TextField textf = (TextField) pane.lookup("#reviewcontent_" + num);
		VBox vBox = (VBox) pane.getParent();
		AnchorPane Dpane = (AnchorPane) vBox.lookup("#MyReviewStack_" + num);
		
		if(id.startsWith("modify_")) {
			textf.setEditable(true);
		} else if(id.startsWith("confirm_")) {
			MyReviewVO vo = new MyReviewVO();
			vo.setRv_index(num);
			vo.setRv_content(textf.getText());
			cnt = Confirm(vo);
			if(cnt == 1) {
				System.out.println("성공");
				textf.setEditable(false);
			} else {
				errMsg("실패", "이유를 찾아봐..");
			}
			
		} else if(id.startsWith("delete_")) {
			cnt = Delete(num);
			if(cnt == 1) {
				vBox.getChildren().remove(Dpane);
			} else {
				errMsg("실패", "실패!");
			}
		}
	}

	private int Confirm(MyReviewVO vo) {
		int cnt = 0;
		
		try {
			cnt = irservice.updateReviewVO(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(cnt == 1) {
			System.out.println("성공!");
		} else {
			System.out.println("실패!");
		}
		
		return cnt;
	}

	private int Delete(int num) {
		int cnt = 0;
		
		try {
			cnt = irservice.deleteReviewVO(num);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(cnt == 1) {
			System.out.println("성공!!");
		} else {
			System.out.println("실패!!");
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
