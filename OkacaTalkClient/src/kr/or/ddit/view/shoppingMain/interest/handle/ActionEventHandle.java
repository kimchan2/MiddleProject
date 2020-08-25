package kr.or.ddit.view.shoppingMain.interest.handle;

import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kr.or.ddit.service.IFavoriteService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.FavoriteVO;

public class ActionEventHandle implements EventHandler<ActionEvent>  {
	private IFavoriteService ifservice;
	
public ActionEventHandle(IFavoriteService ifs) {
		
	ifservice = ifs;
	}
int cnt  =0;
	@Override
	public void handle(ActionEvent e) {
		FavoriteVO vo = new FavoriteVO();
		Button o = (Button)e.getTarget();
		System.out.println(o.getId());
		String id = o.getId();
		
		String arr[] =  id.split("_");
		//숫자를 잡았다.
		int num = Integer.parseInt(arr[1]);
		
		AnchorPane pane =(AnchorPane)o.getParent();
		
		VBox vbox = (VBox)pane.getParent();
		
		AnchorPane Dpane = (AnchorPane)vbox.lookup("#InterestStack_"+num);
		vo.setMem_mail(Session.memVO.getMem_mail());
		vo.setPl_index(num);
		
		if(id.startsWith("InterDelete_")) {
			cnt = delete(vo);
			if(cnt==1) {
				vbox.getChildren().remove(Dpane);
				}else {
					errMsg("실패!", "실패당!");
				}
		}
	}

	private int delete(FavoriteVO vo) {
		
		try {
			cnt = ifservice.deleteFavoriteVO(vo);
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
