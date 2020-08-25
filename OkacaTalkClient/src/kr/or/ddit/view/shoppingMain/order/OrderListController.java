package kr.or.ddit.view.shoppingMain.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import kr.or.ddit.view.shoppingMain.MyPage.MyPageMainController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OrderListController implements Initializable {

	@FXML Label Product_Name_Label;
	@FXML Label Product_Price_Label;
	@FXML Button Order_Confirm_Btn;
	@FXML Label Order_Quantity_Label;
	@FXML Label Order_Price_Label;
	@FXML Button Delivery_Lookup_Btn;
	@FXML Button Order_Cancel_Btn;
	@FXML Button Review_Write_Btn;
	@FXML Button Order_Return_Btn;
	@FXML ImageView ImageView;
	@FXML Label Mypage_Order_Date;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Product_Name_Label.setText(MyPageMainController.plvo.getPl_name());
		Product_Price_Label.setText(Integer.toString(MyPageMainController.odrlist.get(0).getOd_price()));
		Order_Price_Label.setText(Integer.toString(MyPageMainController.odrlist.get(0).getOd_price()));
		Order_Quantity_Label.setText(Integer.toString(MyPageMainController.odrlist.get(0).getOd_quantity()));
		Mypage_Order_Date.setText(MyPageMainController.olvo.get(0).getO_date());
		Image img = new Image(MyPageMainController.plvo.getPl_image());
		ImageView.setImage(img);
	
	}

	@FXML public void orderCancelBtnOnAction() {}

}
