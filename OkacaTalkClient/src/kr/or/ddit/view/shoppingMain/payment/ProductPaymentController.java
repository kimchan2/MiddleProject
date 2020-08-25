package kr.or.ddit.view.shoppingMain.payment;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.impl.ps.CachingShapeRep;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IOrderDetailListService;
import kr.or.ddit.service.IOrderDetailService;
import kr.or.ddit.service.IOrderListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.service.ISavePointService;
import kr.or.ddit.service.IUsingCashService;
import kr.or.ddit.service.IUsingPointService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.OrderDetailListVO;
import kr.or.ddit.vo.OrderDetailVO;
import kr.or.ddit.vo.OrderListVO;
import kr.or.ddit.vo.ProductListVO;
import kr.or.ddit.vo.SavePointVO;
import kr.or.ddit.vo.UsingCashVO;
import kr.or.ddit.vo.UsingPointVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;

public class ProductPaymentController implements Initializable{

	@FXML Button Shop_Back_Btn;
	
	@FXML ImageView Shop_Product_Img;
	@FXML Label Product_Name_Label;
	@FXML Label Product_Quantity_Label;
	@FXML Label Product_Price_Label;
	@FXML Label Product_Sumprice_Label;
	@FXML Label Product_Mileage_Label;
	@FXML TextField Product_UsingMileage_Text;
	@FXML Label Product_Remainmileage_Label;
	@FXML Label Product_Savingmileage_Label;
	@FXML Label Product_Havingcash_Label;
	@FXML Label Product_Remaincash_Label;
	@FXML Button Product_Payment_Btn;
	@FXML Button Mileage_Using_Btn;

	Registry reg;
	private IProductListService iprod;
	private IOrderListService ol;
	private IOrderDetailService od;
	private IMemberService ilogin;
	private IUsingCashService ucash;
	private IUsingPointService upoint;
	private ISavePointService spoint;
	private IOrderDetailListService iodlist;
	
	ProductListVO pvo = new ProductListVO();
	OrderListVO ovo = new OrderListVO();
	OrderListVO orvo = new OrderListVO();
	OrderDetailVO dvo = new OrderDetailVO();
	OrderDetailListVO olvo = new OrderDetailListVO();
	
	String pl_image = Session.curplindex;
	static Integer pl_sumprice = 0;
	static Integer mem_point = 0;
	public static Integer mem_usingpoint = 0;
	static Integer mem_remainpoint = 0;
	static Integer mem_remaincash = 0;
	static Integer mem_havingcash = 0;
	static Integer mem_savingpoint = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			
			iprod = (IProductListService) reg.lookup("productList");
			ol = (IOrderListService) reg.lookup("orderList");
			od = (IOrderDetailService) reg.lookup("orderDetail");
			ilogin = (IMemberService) reg.lookup("member");
			ucash = (IUsingCashService) reg.lookup("usingCash");
			upoint = (IUsingPointService) reg.lookup("usingPoint");
			iodlist = (IOrderDetailListService) reg.lookup("orderDetailList");
			spoint = (ISavePointService) reg.lookup("savePoint");
			
			pvo = (ProductListVO)iprod.getdetailproduct(pl_image);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		settingByProduct(Shop_Product_Img, Product_Name_Label, Product_Quantity_Label, Product_Price_Label, 
				Product_Sumprice_Label, Product_Mileage_Label, Product_UsingMileage_Text, Product_Remainmileage_Label, 
				Product_Savingmileage_Label, Product_Havingcash_Label, Product_Remaincash_Label);
	}

	private void settingByProduct(ImageView Shop_Product_Img, Label Product_Name_Label, Label Product_Quantity_Label,
			Label Product_Price_Label, Label Product_Sumprice_Label, Label Product_Mileage_Label,
			TextField Product_UsingMileage_Text, Label Product_Remainmileage_Label,
			Label Product_Savingmileage_Label, Label Product_Havingcash_Label, Label Product_Remaincash_Label) {
		
		Image pl_image = new Image(Session.curplindex); // 상품이미지
		String pl_name = pvo.getPl_name(); // 상품 이름
		Integer pl_price = pvo.getPl_price(); // 상품 가격
		String pl_quantity = Session.quantity; // 수량
		pl_sumprice = (pl_price * Integer.parseInt(pl_quantity)); // 상품 총 가격
		mem_point = Session.memVO.getMem_point(); // 마일리지
		if(mem_point < 0) {
			mem_point = 0;
		}
		mem_usingpoint = Integer.parseInt(Product_UsingMileage_Text.getText()); // 사용한 마일리지
		mem_remainpoint = mem_point - mem_usingpoint; // 사용하고 남은 마일리지
		mem_savingpoint = pl_sumprice / 200; // 적립포인트
		mem_havingcash = Session.memVO.getMem_cash(); // 내가 가지고 있는 캐시
		mem_remaincash = (mem_havingcash - pl_sumprice) + mem_usingpoint; // 사고 남은 캐시 
		
		Shop_Product_Img.setImage(pl_image); // 상품이미지
		Product_Name_Label.setText(pl_name); // 상품 이름
		Product_Price_Label.setText(pl_price + "원");  // 상품 가격
		Product_Quantity_Label.setText(pl_quantity + "개"); // 수량
		Product_Sumprice_Label.setText(pl_sumprice + "원"); // 상품 총 가격
		Product_Mileage_Label.setText(mem_point + "point"); // 마일리지
		Product_UsingMileage_Text.setText(Integer.toString(mem_usingpoint)); // 사용한 마일리지
		Product_Remainmileage_Label.setText(mem_remainpoint + "point"); // 사용하고 남은 마일리지
		Product_Savingmileage_Label.setText(mem_savingpoint + "point"); // 적립포인트
		Product_Havingcash_Label.setText(mem_havingcash + "원"); // 내가 가지고 있는 캐시
		Product_Remaincash_Label.setText(mem_remaincash + "원"); // 사고 남은 캐시
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(Product_Name_Label.getText());
		Tooltip.install(Product_Name_Label, tt);
	}
	
	@FXML public void ShopBackBtnOnAction() {
		Node back = null;
		try {
			back = FXMLLoader.load(getClass().getResource("../shoppingdetail/ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(back);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 마일리지 사용하기
	@FXML public void MileageUsingBtnOnAction() {
		mem_usingpoint = Integer.parseInt(Product_UsingMileage_Text.getText());
		
		if(mem_point < mem_usingpoint) {
			errMsg("작업 오류", "사용하려는 마일리지가 보유 마일리지보다 큽니다.");
			// 음수값 안나오게 해야함
			if(mem_point < 0) {
				mem_point = 0;
			}
		}else if(mem_usingpoint > pl_sumprice) {
			errMsg("작업 오류", "사용하려는 마일리지가 결제 금액보다 큽니다.");
		} else {
			if(mem_remainpoint <= 0) {
				errMsg("사용불가", "보유한 마일리지를 초과하여 사용할 수 없습니다.");
			} else {
				mem_remainpoint = (int)mem_remainpoint - mem_usingpoint; // 가지고 있는 마일리지 - 사용한 마일리지
				mem_remaincash = (int)mem_remaincash + mem_usingpoint; // 남은 캐시 + 사용한 마일리지
				
			}
			
//			Product_UsingMileage_Text.setText(Integer.toString(mem_usingpoint));
			Product_Remainmileage_Label.setText(mem_remainpoint + "point");
			Product_Remaincash_Label.setText(mem_remaincash + "원");
			Product_Havingcash_Label.setText(mem_havingcash + "원");
		}
	}
	
	// 결제하기 누르면 나오는 결과
	@FXML public void ProductPaymentBtnOnAction() {
		ProductListVO pdvo = new ProductListVO();
		OrderListVO vo = new OrderListVO();
		MemberVO mvo = new MemberVO();
		UsingCashVO ucvo = new UsingCashVO();
		UsingPointVO upvo = new UsingPointVO();
		SavePointVO spvo = new SavePointVO();
		OrderDetailVO odvo = new OrderDetailVO();
		OrderDetailListVO odlvo = new OrderDetailListVO();
		ArrayList<ProductListVO> plist = new ArrayList<>();
		ArrayList<OrderListVO> olist = new ArrayList<>();
		ArrayList<OrderDetailListVO> odlist = new ArrayList<>();
		
		// OrderList 가져오기
		vo.setMem_mail(Session.memVO.getMem_mail());
		int cnt = 0;
		
		// 보유 캐시 빼주기, 포인트 사용했으면 포인트 빼주기
		mvo.setMem_mail(Session.memVO.getMem_mail());
		mvo.setMem_cash(Session.memVO.getMem_cash());
		mvo.setMem_point(Session.memVO.getMem_point());
		Session.memVO.setMem_cash((Session.memVO.getMem_cash() - pl_sumprice) + mem_usingpoint); // 사용한 캐시
		Session.memVO.setMem_point((Session.memVO.getMem_point() - mem_usingpoint) + mem_savingpoint); // 사용한 마일리지
		int update = 0;
		
		// 마이페이지 캐시충전에 사용내역 넣어주기
		ucvo.setMem_mail(Session.memVO.getMem_mail()); // 사용자 메일
//		ucvo.setO_index(ucvo.getO_index()); // 인덱스 번호
		ucvo.setUc_cash(pl_sumprice + mem_usingpoint); // 사용한 캐시
		ucvo.setUc_date(vo.getO_date()); // 사용한 날짜
		Object cash = null;
		
		
		// 마이페이지 마일리지에 사용내역 넣어주기
		upvo.setMem_mail(Session.memVO.getMem_mail()); // 사용자 메일
//		upvo.setO_index(ucvo.getO_index()); // 인덱스 번호
		upvo.setUp_point(mem_usingpoint); // 사용한 마일리지
		upvo.setUp_date(vo.getO_date()); // 사용한 날짜
		int point = 0;
		
		// 결제 완료하면 주문 내역 추가
		odvo.setO_index(vo.getO_index()); // 주문 내역 인덱스
		odvo.setOd_price(pl_sumprice); // 상품 총 가격
		odvo.setOd_quantity(Integer.parseInt(Session.quantity)); // 상품 수량
		odvo.setPl_index(pvo.getPl_index()); // 상품 인덱스
		Object plus = null;
		
		// 마일리지 적립
		spvo.setMem_mail(Session.memVO.getMem_mail()); // 사용자 메일
		spvo.setO_index(vo.getO_index()); // 주문 내역 인덱스
		spvo.setSp_point(mem_savingpoint); // 적립 마일리지
		int savepoint = 0;
		
		// 구매하고 난 캐시랑 마일리지  db에 저장해주기 -> member.xml에 updatecashpointMember로 만들었음
		mvo.setMem_mail(Session.memVO.getMem_mail());
		mvo.setMem_cash(Session.memVO.getMem_cash());
		mvo.setMem_point(Session.memVO.getMem_point());
		int savecashpoint = 0;
		
		Node paymentok = null;
		
		try {
			cnt = ol.insertOrderListVO(vo); //orderlist에 데이터 들어간 거
			olist = (ArrayList<OrderListVO>) ol.SelectOrderList(Session.memVO.getMem_mail());
			
			update = ilogin.updatecashMember(mvo);
			
			ucvo.setO_index(olist.get(0).getO_index());
			cash = ucash.insertUsingCashListVO(ucvo);
			
			savecashpoint = ilogin.updateCashPoint(mvo);
			
			upvo.setO_index(olist.get(0).getO_index());
			if(upvo.getUp_point() != 0) { // 사용할 마일리지가 0일 때 출력 못하게 하기
				point = upoint.updateUsingPointList(upvo);
			}
			odlist = (ArrayList<OrderDetailListVO>) iodlist.selectOrderDetailList(Session.memVO.getMem_mail());
			
			odvo.setO_index(olist.get(0).getO_index());
			plus = od.insertOrderDetailVO(odvo);
			
			spvo.setO_index(olist.get(0).getO_index());
			savepoint = spoint.insertSavePointVO(spvo);
			Session.memVO.setMem_point(Session.memVO.getMem_point() + spvo.getSp_point());
			
			if(cnt == 0) {
				System.out.println("다시하세요...");
			} else if(update == 0) {
				System.out.println("실패..");
			} else if(cash != null) {
				System.out.println("실패.......");
			} else if(savecashpoint == 0) {
				System.out.println("또 실패야?");
			} else if(point == 0) {
				System.out.println("실패^^");
			} else if(plus != null) {
				System.out.println("실패 다시하세여");
			}  else if(savepoint == 0) {
				System.out.println("다시");
			} else {
				System.out.println("성공!");
			}
			
			paymentok = FXMLLoader.load(getClass().getResource("PaymentComplete.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(paymentok);
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
