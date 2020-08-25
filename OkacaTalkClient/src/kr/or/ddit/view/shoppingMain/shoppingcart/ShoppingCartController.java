package kr.or.ddit.view.shoppingMain.shoppingcart;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.omg.PortableServer.POAPackage.ObjectAlreadyActiveHelper;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import kr.or.ddit.service.IInquireService;
import kr.or.ddit.service.IOrderDetailService;
import kr.or.ddit.service.IOrderListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.service.ISavePointService;
import kr.or.ddit.service.IShoppingCartService;
import kr.or.ddit.service.IUsingCashService;
import kr.or.ddit.service.IUsingPointService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.shoppingcart.handler.ActionEventHandler3;
import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.OrderDetailListVO;
import kr.or.ddit.vo.OrderDetailVO;
import kr.or.ddit.vo.OrderListVO;
import kr.or.ddit.vo.ProductListVO;
import kr.or.ddit.vo.SavePointVO;
import kr.or.ddit.vo.ShoppingCartVO;
import kr.or.ddit.vo.UsingCashVO;
import kr.or.ddit.vo.UsingPointVO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ShoppingCartController implements Initializable {

	@FXML
	AnchorPane ShoppingCartMain;
	@FXML
	ScrollPane ShoppingCartSC;
	@FXML
	Button Product_Selectdelete_Btn;
	@FXML
	Button Product_Buy_Btn;
	@FXML
	VBox ShoppingCartVBox;

	int chcnt = 0;
	//이거 포인트 업데이트 시켜줄꺼야 이거 이용해야됭!!
	int totalusepoint = 0;
	
	Registry reg;
	IShoppingCartService isc;
	IInquireService iq;
	IProductListService pl;
	IOrderListService os;
	IOrderDetailService od;
	IMemberService mo ;
	
	
	
	
	//
	IUsingCashService uc;
	IUsingPointService up;
	ISavePointService sp;
	UsingCashVO ucvo = new UsingCashVO();
	UsingPointVO upvo = new UsingPointVO();
	SavePointVO spvo = new SavePointVO();
	
	//
	ShoppingCartVO scvo = new ShoppingCartVO();
	//SELECT 구문할떄 쓸거얌!!
	ProductListVO prvo = new ProductListVO();
	//update 구문할때 쓸꺼얌!!
	ProductListVO prvo2 = new ProductListVO();
	OrderListVO ovo = new OrderListVO();
	OrderDetailVO odvo = new OrderDetailVO();
	MemberVO movo = new MemberVO();
	OrderListVO ovo2 = new OrderListVO();
	ArrayList<String> scidx = new ArrayList();
	
	@FXML Label OwnCashLabel;
	@FXML Label UseCashLabel;
	@FXML Label OwnPointLabel;
	@FXML Label TotalPriceLabel;
	@FXML TextField UsePoint;
	@FXML AnchorPane ChildAnchorPane;
	@FXML TextField UsePointTextf;
	@FXML Button UsePointBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		List<ShoppingCartVO> list = new ArrayList<>();
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			isc = (IShoppingCartService) reg.lookup("shoppingCart");
			pl = (IProductListService) reg.lookup("productList");
			od = (IOrderDetailService) reg.lookup("orderDetail");
			os = (IOrderListService) reg.lookup("orderList");
			mo = (IMemberService) reg.lookup("member");
			OwnCashLabel.setText(Integer.toString(Session.memVO.getMem_cash()));
			OwnPointLabel.setText(Integer.toString(Session.memVO.getMem_point()));
			
			
			uc = (IUsingCashService) reg.lookup("usingCash");
			up = (IUsingPointService) reg.lookup("usingPoint");
			sp = (ISavePointService) reg.lookup("savePoint");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			list = isc.getShoppingCartVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			ShoppingCartVO scjvo = list.get(i);

			ShoppingCartVBox.getChildren().add(createShoppingCart(scjvo, pl));
		}

		Product_Selectdelete_Btn.setOnAction(e -> {
			if (scidx.size() == 0) {
				errMsg("장바구니 체크해라", "장바구니 체크하세요");
			} else {
				for (int i = 0; i < scidx.size(); i++) {
					ActionEventHandler3.delete(Integer.parseInt(scidx.get(i)));
				}
				Node shoppingCart = null;
				try {
					shoppingCart = FXMLLoader.load(getClass().getResource("ShoppingCart2.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Session.moveConf.getShopping().getChildren().clear();
				Session.moveConf.getShopping().getChildren().add(shoppingCart);
			}
		});

		Product_Buy_Btn.setOnAction(e -> {
			int realsum = 0;
			if (scidx.size() == 0) {
				errMsg("장바구니 체크!", "장바구니 체크하세요");
			} else {
				if (Integer.parseInt(OwnCashLabel.getText()) - Integer.parseInt(UseCashLabel.getText()) < -1) {
					errMsg("금액부족!", "금액이 부족합니다.");
				} else {
					for (int i = 0; i < scidx.size(); i++) {
						int cnt = 0;
						ovo.setMem_mail(Session.memVO.getMem_mail());

						try {
							// 오더리스트를 작성합니다 -> mem_mail을 seq 발생.
							cnt = os.insertOrderListVO(ovo);
							System.out.println(cnt);
							int od_idx = cnt;

							// scvo는 pl인덱스를 구하기 위해 만들었습니다.
							scvo = isc.selectShoppingCartVO(Integer.parseInt(scidx.get(i)));

							odvo.setO_index(od_idx);
							odvo.setPl_index(scvo.getPl_index());

							Button o = (Button) e.getTarget();
							// 버튼의 엄빠는 앵커페인
							AnchorPane pane = (AnchorPane) o.getParent();
							// 앵커패인의 엄빠는 vBox
							VBox vb = (VBox) pane.getParent();
							AnchorPane Spane = (AnchorPane) vb
									.lookup("#ShoppingCartStack_" + Integer.parseInt(scidx.get(i)));
							// 카운트의 가격과 합계 가격을 갖고왔습니다.
							TextField textf = (TextField) Spane.lookup("#Count_" + Integer.parseInt(scidx.get(i)));
							Label sumlabel = (Label) Spane.lookup("#SumPrice_" + Integer.parseInt(scidx.get(i)));
							odvo.setOd_price(Integer.parseInt(sumlabel.getText()));
							// 히히히 요기는? 캐쉬를 세팅합니다 하하하하하하핫

							odvo.setOd_quantity(Integer.parseInt(textf.getText()));
							Object po = null;

							// 널나왔으니 성공!
							po = od.insertOrderDetailVO(odvo);
							System.out.println(po);

							// -------- 이제 productlist에서 꺼내와서 업데이트 시켜줄꺼야. 양이랑 판매량을 올려줄꺼야.

							prvo = pl.getProductListVO2(odvo.getPl_index());

							// 이제 prvo에서 업데이트 시켜줄꺼야.
							prvo2.setPl_stock(prvo.getPl_stock() + odvo.getOd_quantity());
							prvo2.setPl_sales(prvo.getPl_sales() + odvo.getOd_quantity());
							prvo2.setPl_index(prvo.getPl_index());
							cnt = pl.updateProductListVO(prvo2);
							// ----------------- 이제 Session의 값과 DB에 있는 값을 바꿔줄꺼야.

							if (cnt == 1) {
								System.out.println("성공");
							}
							ActionEventHandler3.delete(Integer.parseInt(scidx.get(i)));
							System.out.println("성공!");
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					// 여기다 추가해 슈밤
					Session.memVO
							.setMem_cash(Session.memVO.getMem_cash() - Integer.parseInt(TotalPriceLabel.getText()));
					Session.memVO.setMem_point(Session.memVO.getMem_point()
							- (Integer.parseInt(UseCashLabel.getText()) - Integer.parseInt(TotalPriceLabel.getText()))
							+ (Integer.parseInt(TotalPriceLabel.getText()) / 100) * 2);
					movo.setMem_cash(Session.memVO.getMem_cash());
					movo.setMem_point(Session.memVO.getMem_point());
					movo.setMem_mail(Session.memVO.getMem_mail());
					try {
						int cnt = mo.updateCashPoint(movo);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					infoMsg("구입성공!", "총" + (Integer.parseInt(TotalPriceLabel.getText()) / 100) * 2 + "point가 적립되었습니다.");
					
					Object cnt = null;
					UsingCashVO ucvo = new UsingCashVO();
					ucvo.setMem_mail(Session.memVO.getMem_mail());
					ucvo.setO_index(odvo.getO_index());
					ucvo.setUc_cash(Integer.parseInt(TotalPriceLabel.getText()));
					
					UsingPointVO upvo = new UsingPointVO();
					upvo.setMem_mail(Session.memVO.getMem_mail());
					upvo.setO_index(odvo.getO_index());
					upvo.setUp_point(totalusepoint);
					//
					SavePointVO spvo = new SavePointVO();
					spvo.setMem_mail(Session.memVO.getMem_mail());
					spvo.setO_index(odvo.getO_index());
					spvo.setSp_point(Integer.parseInt(TotalPriceLabel.getText()) / 100 * 2) ;
					try {
						if(upvo.getUp_point()!=0) {
						cnt =up.updateUsingPointList(upvo);
						}
						cnt =uc.insertUsingCashListVO(ucvo);
						cnt =sp.insertSavePointVO(spvo);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
					
					
				}
			}

			Node shoppingCart = null;
			try {
				shoppingCart = FXMLLoader.load(getClass().getResource("ShoppingCart2.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(shoppingCart);
		});
	}

	public AnchorPane createShoppingCart(final ShoppingCartVO scjvo, final IProductListService pl) {

		AnchorPane ShoppingCartStack = new AnchorPane();
		ShoppingCartStack.setPrefWidth(740);
		ShoppingCartStack.setPrefHeight(180);
		ShoppingCartStack.setId("ShoppingCartStack_" + scjvo.getSc_index());

		ActionEventHandler3 handler = new ActionEventHandler3(isc);
		// 체크박스
		CheckBox SelectChk = new CheckBox();
		SelectChk.setPrefWidth(30);
		SelectChk.setPrefHeight(25);
		SelectChk.setLayoutX(30);
		SelectChk.setLayoutY(75);
		SelectChk.setId("SelectChk_" + scjvo.getSc_index());
		SelectChk.setOnMouseClicked(e -> {
			String id = SelectChk.getId();
			String arr[] = id.split("_");
			// 숫자를 잡았다.
			String num = arr[1];
			if (SelectChk.isSelected()) {
				scidx.add(num);
				System.out.println(scidx.size());
			} else {
				scidx.remove(num);
				System.out.println(scidx.size());
			}

		});
		ShoppingCartStack.getChildren().add(SelectChk);

		// 이미지
		try {
			prvo = pl.getProductListVO2(scjvo.getPl_index());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView ProdImage = new ImageView();
		Image img = new Image(prvo.getPl_image());
		ProdImage.setFitWidth(200);
		ProdImage.setFitHeight(150);
		ProdImage.setLayoutX(65);
		ProdImage.setLayoutY(15);
		ProdImage.setImage(img);
		ProdImage.setId("ProdImage_" + scjvo.getPl_index());
		ProdImage.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				ProductListVO pllist = new ProductListVO();
				ImageView o = (ImageView) event.getTarget();
				System.out.println(o.getId());
				String id = o.getId();

				String arr[] = id.split("_");
				// 숫자를 잡았다.
				int num = Integer.parseInt(arr[1]);
				System.out.println(num);

				Session.curplindex = prvo.getPl_image();

				Node back = null;
				try {
					back = FXMLLoader.load(getClass().getResource("../shoppingdetail/ProductDetailPage.fxml"));
					Session.moveConf.getShopping().getChildren().clear();
					Session.moveConf.getShopping().getChildren().add(back);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		ShoppingCartStack.getChildren().add(ProdImage);

		// 제품 이름
		Label ProdName = new Label();
		ProdName.setPrefWidth(280);
		ProdName.setPrefHeight(65);
		ProdName.setLayoutX(275);
		ProdName.setLayoutY(20);
		ProdName.setText(prvo.getPl_name());
		ProdName.setAlignment(Pos.CENTER);
		ProdName.setWrapText(true);
		ProdName.setStyle("-fx-font-size: 20");

		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(ProdName.getText());
		Tooltip.install(ProdName, tt);

		ShoppingCartStack.getChildren().add(ProdName);

		// 제품 가격
		Label ProdPrice = new Label();
		ProdPrice.setPrefWidth(280);
		ProdPrice.setPrefHeight(65);
		ProdPrice.setLayoutX(275);
		ProdPrice.setLayoutY(95);
		ProdPrice.setText(Integer.toString(prvo.getPl_price()));
		ProdPrice.setAlignment(Pos.CENTER);
		ProdPrice.setWrapText(true);
		ProdPrice.setStyle("-fx-font-size: 20");
		ProdPrice.setId("ProdPrice_" + scjvo.getSc_index());
		ShoppingCartStack.getChildren().add(ProdPrice);

		// + 버튼
		Button Plus = new Button();
		Plus.setPrefWidth(26);
		Plus.setPrefHeight(23);
		Plus.setLayoutX(560);
		Plus.setLayoutY(55);
		Plus.setText("+");
		Plus.setTextAlignment(TextAlignment.CENTER);
		Plus.setStyle("-fx-font-size: 12");
		Plus.setStyle("-fx-background-color: white");
		Plus.setId("Plus_" + scjvo.getSc_index());
		Plus.setOnAction(handler);
		ShoppingCartStack.getChildren().add(Plus);

		// 개수 텍스트
		TextField Count = new TextField();
		Count.setPrefWidth(25);
		Count.setPrefHeight(20);
		Count.setLayoutX(585);
		Count.setLayoutY(58);
		Count.setEditable(false);
		Count.setText("0");
		Count.setId("Count_" + scjvo.getSc_index());
		ShoppingCartStack.getChildren().add(Count);

		// - 버튼
		Button Minus = new Button();
		Minus.setPrefWidth(26);
		Minus.setPrefHeight(23);
		Minus.setLayoutX(610);
		Minus.setLayoutY(55);
		Minus.setText("-");
		Minus.setTextAlignment(TextAlignment.CENTER);
		Minus.setStyle("-fx-font-size: 12");
		Minus.setStyle("-fx-background-color: white");
		Minus.setId("Minus_" + scjvo.getSc_index());
		Minus.setOnAction(handler);
		ShoppingCartStack.getChildren().add(Minus);

		// 총 가격
		Label SumPrice = new Label();
		SumPrice.setPrefWidth(70);
		SumPrice.setPrefHeight(25);
		SumPrice.setLayoutX(562);
		SumPrice.setLayoutY(95);
		SumPrice.setText("총 가격");
		SumPrice.setAlignment(Pos.CENTER);
		SumPrice.setStyle("-fx-font-size: 15");
		SumPrice.setId("SumPrice_" + scjvo.getSc_index());

		ShoppingCartStack.getChildren().add(SumPrice);

		// 삭제 버튼
		Button Delete = new Button();
		Delete.setPrefWidth(65);
		Delete.setPrefHeight(60);
		Delete.setLayoutX(650);
		Delete.setLayoutY(57);
		Delete.setText("삭제");
		Delete.setTextAlignment(TextAlignment.CENTER);
		Delete.setStyle("-fx-font-size: 18");
		Delete.setId("Delete_" + scjvo.getSc_index());
		Delete.setOnAction(handler);
		ShoppingCartStack.getChildren().add(Delete);
		return ShoppingCartStack;

	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	private void infoMsg(String headerText, String msg) {
        Alert errAlert = new Alert(AlertType.INFORMATION);
        errAlert.setTitle("정보 확인");
        errAlert.setHeaderText(headerText);
        errAlert.setContentText(msg);
        errAlert.showAndWait();
     }

	@FXML public void UsePointBtnOnAction() {
		
		if(Integer.parseInt(OwnPointLabel.getText())>Integer.parseInt(UsePointTextf.getText())) {
			OwnPointLabel.setText(Integer.toString(Integer.parseInt((OwnPointLabel.getText()))-Integer.parseInt(UsePointTextf.getText())));
			totalusepoint += Integer.parseInt(UsePointTextf.getText());
			
		TotalPriceLabel.setText(Integer.toString(Integer.parseInt((TotalPriceLabel.getText()))-Integer.parseInt(UsePointTextf.getText())));
		}else {
			errMsg("오류", "가지고 있는 마일리지가 적습니다.");
		}
	}

}
