package kr.or.ddit.view.shoppingMain.shoppingdetail;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.text.ChangedCharSetException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IFavoriteService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductDetailPageController implements Initializable {

	@FXML
	Button Shop_Back_Btn;

	@FXML
	ImageView Product_Img;
	@FXML
	Label Shop_Cate_Label;
	@FXML
	Label Shop_Decate_Label;
	@FXML
	Label Shop_Proname_Label;
	@FXML
	Label Shop_Proprice_Label;
	@FXML
	Label Shop_ProOption_Label;
	@FXML
	Label Shop_Proexplain_Label;

	@FXML
	ComboBox Shop_Quantity_Combo;
	@FXML
	Button Shop_Cart_Btn;
	@FXML
	Button Shop_Buy_Btn;
	@FXML
	Button Shop_Wishcart_Btn;
	@FXML
	Button Shop_Review_Btn;
	@FXML
	Button Shop_Qna_Btn;

	@FXML
	AnchorPane ProductDetail;

	private Registry reg;
	private IProductListService iprod;
	private IFavoriteService ife;
	
	ProductListVO pvo = new ProductListVO();
	String pl_image = Session.curplindex;
	public static String pl_name2 = null;
	public static Integer pl_price2 = 0;

	 ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			iprod = (IProductListService) reg.lookup("productList");
			pvo = (ProductListVO)iprod.getdetailproduct(pl_image);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		settingByProduct(Product_Img, Shop_Cate_Label, Shop_Decate_Label, Shop_Proname_Label, Shop_Proprice_Label,
				Shop_ProOption_Label, Shop_Proexplain_Label);
		
		Shop_Quantity_Combo.setItems(list);
	}
	
	private void settingByProduct(ImageView product_Img, Label shop_Cate_Label, Label shop_Decate_Label,
			Label shop_Proname_Label, Label shop_Proprice_Label, Label shop_ProOption_Label,
			Label shop_Proexplain_Label) {
		// 비워주기
		product_Img.setImage(null);
		shop_Cate_Label.setText(null);
		shop_Decate_Label.setText(null);
		shop_Proname_Label.setText(null);
		shop_Proprice_Label.setText(null);
		// shop_ProOption_Label.setText(null);
		// shop_Proexplain_Label.setText(null);

		String cl_category = "";
		String cl_detail_category = "";
		Image pl_image_img = null;
		String pl_name = "";
		Integer pl_price = 0;
		String pl_option = "";
		String pl_explain = "";
		
		cl_category = Session.catelist.get(Session.curclindex).getCl_category();
		cl_detail_category = Session.catelist.get(Session.curclindex).getCl_detail_category();
		pl_image_img = new Image(Session.curplindex);
		pl_name = pvo.getPl_name();
		pl_price = pvo.getPl_price();
		
		pl_name2 = pl_name;
		pl_price2 = pl_price;
		
		shop_Cate_Label.setText(cl_category);
		shop_Decate_Label.setText(cl_detail_category);
		product_Img.setImage(pl_image_img);
		shop_Proname_Label.setText(pl_name);
		shop_Proprice_Label.setText(pl_price + "원");
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(shop_Proname_Label.getText());
		Tooltip.install(shop_Proname_Label, tt);
	}

	@FXML public void ShopQuantityComboOnAction() {
		String quan = (String)Shop_Quantity_Combo.getSelectionModel().getSelectedItem();
		Session.quantity = quan;
	}
	
	@FXML
	public void ShopBackBtnOnAction() {
		Node back = null;
		try {
			back = FXMLLoader.load(getClass().getResource("ShoppingDetail.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(back);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void ShopBuyBtnOnAction() {
		if((Session.memVO.getMem_cash() + Session.memVO.getMem_point())
		< (pvo.getPl_price() * (Integer.parseInt(Session.quantity)))) {
			errMsg("결제 불가", "보유하신 캐시가 결제 금액보다 적습니다.");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../payment/PaymentCashCharge.fxml"));
			Parent root = null;
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);

			try {
				root = (Parent) loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			stage.setTitle("캐시 충전 이동");
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			Node ShopBuy;
			try {
				ShopBuy = FXMLLoader.load(getClass().getResource("../payment/ProductPayment.fxml"));
				Session.moveConf.getShopping().getChildren().clear();
				Session.moveConf.getShopping().getChildren().add(ShopBuy);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@FXML
	public void ShopCartBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../shoppingcart/ShoppingCartYn.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setTitle("장바구니 이동");
		stage.setScene(new Scene(root));
		stage.show();
	}
/**
 * 여기서부터 관심상품 이동.
 */
	@FXML
	public void ShopWishCartAddBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../interest/InterestMsg.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setTitle("관심 상품 이동");
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	public void ShopReviewBtnOnAction() {
		Node ShopReview;
		try {
			ShopReview = FXMLLoader.load(getClass().getResource("../myreview/MyReview.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(ShopReview);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void ShopQnaBtnOnAction() {
		Node ShopQna;
		Node Cate;
		
		try {
			ShopQna = FXMLLoader.load(getClass().getResource("../myQna/MyQna.fxml"));
			Cate = FXMLLoader.load(getClass().getResource("../MyPage/MypageCategory.fxml"));
			Session.moveConf.getShop_Cate().getChildren().clear();
			Session.moveConf.getShop_Cate().getChildren().add(Cate);
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(ShopQna);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}


}
