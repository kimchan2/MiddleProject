package kr.or.ddit.view.shoppingMain.shoppingcart.handler;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.jws.Oneway;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.service.IShoppingCartService;
import kr.or.ddit.vo.InquireVO;

public class ActionEventHandler3 implements EventHandler<ActionEvent> {
	private static IShoppingCartService iSservice;
	static String modify = "";
	int cnt = 0;

	public ActionEventHandler3(IShoppingCartService isc) {

		iSservice = isc;
	}

	@Override
	public void handle(ActionEvent e) {

		Button o = (Button) e.getTarget();
		System.out.println(o.getId());
		String id = o.getId();
		String arr[] = id.split("_");
		// 숫자를 잡았다.
		int num = Integer.parseInt(arr[1]);

		AnchorPane pane = (AnchorPane) o.getParent();
		// 텍스트 필드 갖고옴!
		TextField textf = (TextField) pane.lookup("#Count_" + num);

		Label sumlabel = (Label) pane.lookup("#SumPrice_" + num);
		VBox vbox = (VBox) pane.getParent();
		//스크롤 페인
		AnchorPane pongpane = (AnchorPane) vbox.lookup("#ChildAnchorPane");
		//하하 usecash label을 갖고왔따.
		Label usecashlabel = (Label) pongpane.lookup("#UseCashLabel");
		
		Label TotalPriceLabel = (Label) pongpane.lookup("#TotalPriceLabel");
		//
		AnchorPane Dpane = (AnchorPane) vbox.lookup("#ShoppingCartStack_" + num);

		Label pricelabel = (Label) Dpane.lookup("#ProdPrice_" + num);

		CheckBox checkbox = (CheckBox) Dpane.lookup("#SelectChk_" + num);

		

		if (id.startsWith("Plus_")) {
			textf.setText(Integer.toString((Integer.parseInt(textf.getText()) + 1)));
			sumlabel.setText(
					Integer.toString((Integer.parseInt(pricelabel.getText()) * Integer.parseInt(textf.getText()))));
			usecashlabel.setText(Integer.toString(Integer.parseInt(usecashlabel.getText()) +Integer.parseInt(pricelabel.getText())));
			TotalPriceLabel.setText(Integer.toString(Integer.parseInt(TotalPriceLabel.getText()) +Integer.parseInt(pricelabel.getText())));
		} else if (id.startsWith("Minus_")) {
			if (!textf.getText().equals("0")) {
				textf.setText(Integer.toString((Integer.parseInt(textf.getText()) + -1)));
				sumlabel.setText(
						Integer.toString((Integer.parseInt(pricelabel.getText()) * Integer.parseInt(textf.getText()))));
				usecashlabel.setText(Integer.toString(Integer.parseInt(usecashlabel.getText()) -Integer.parseInt(pricelabel.getText())));
				TotalPriceLabel.setText(Integer.toString(Integer.parseInt(TotalPriceLabel.getText()) -Integer.parseInt(pricelabel.getText())));
			} else {
				errMsg("숫자이상!", "이 이상 줄일수 없습니다.");
			}
		} else if (id.startsWith("Delete_")) {
			cnt = delete(num);
			if (cnt == 1) {
				vbox.getChildren().remove(Dpane);
			} else if (id.startsWith("Selectdelete")) {

			}

			else {
				errMsg("실패!", "실패당!");
			}
		}

	}

	// private int OK(InquireVO vo) {
	// int cnt = 0;
	// try {
	// cnt = iqservice.updateInquireVO(vo);
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if(cnt==1) {
	// System.out.println("완료!");
	// }else{
	// System.out.println("실패!");
	// }
	// return cnt;
	// }
	//
	//
	//
	//
	//
	static public int delete(int num) {
		int cnt = 0;

		try {
			cnt = iSservice.deleteShoppingCartVO(num);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cnt == 1) {
			System.out.println("완료!");
		} else {
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
