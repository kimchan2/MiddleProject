package kr.or.ddit.view.shoppingMain.payment;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.service.IOrderDetailService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.OrderDetailVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class PaymentConfirmController implements Initializable {

	@FXML ImageView Order_Product_Img;
	@FXML Label Product_Name_Label;
	@FXML Label Product_Price_Label;
	@FXML Label Order_Quantity_Label;
	@FXML Label Order_Price_Label;
	
	@FXML TextField Buy_Confirmreview_Text;
	@FXML Button Buy_Reviewok_Btn;

	Registry reg;
	
	IProductListService ipl;
	IOrderDetailService iod;
	ProductListVO pvo = new ProductListVO();
	ProductListVO pvo2 = new ProductListVO();
	OrderDetailVO ovo = new OrderDetailVO();
	OrderDetailVO ovo2 = new OrderDetailVO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ipl = (IProductListService) reg.lookup("productList");
			iod = (IOrderDetailService) reg.lookup("orderDetail");
			pvo2 = ipl.getdetailproduct(Session.curplindex);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		settingByProduct(Order_Product_Img, Product_Name_Label, Product_Price_Label, 
						 Order_Quantity_Label, Order_Price_Label, Buy_Confirmreview_Text);
		
	}

	// 구매확정 페이지에 데이터 넣어주기 -> DB에는 안들어감 그래서 어떻게?
	private void settingByProduct(ImageView order_Product_Img, Label product_Name_Label, Label product_Price_Label,
			Label order_Quantity_Label, Label order_Price_Label, TextField Buy_Confirmreview_Text) {
		Image pl_image = null;
		String pl_name = null;
		int pl_price = 0;
		int od_quantity = 0;
		int od_price = 0;
		
		pl_image = new Image(Session.curplindex);	// 상품 이미지
		pl_name = pvo2.getPl_name();				// 상품 이름
		pl_price = pvo2.getPl_price();				// 상품 가격
		od_quantity = ovo.getOd_quantity();			// 상품 수량
		od_price = ovo.getOd_price();				// 주문 금액
		
		order_Product_Img.setImage(pl_image);
		product_Name_Label.setText(pl_name);
		product_Price_Label.setText(pl_price + "원");
		order_Quantity_Label.setText(od_quantity + "개");
		order_Price_Label.setText(od_price + "원");
		Buy_Confirmreview_Text.setText("리뷰 작성");
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(Product_Name_Label.getText());
		Tooltip.install(Product_Name_Label, tt);

		
	}

}
