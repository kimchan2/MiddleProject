package kr.or.ddit.view.shoppingMain.MyPage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.service.IOrderDetailListService;
import kr.or.ddit.service.IOrderDetailService;
import kr.or.ddit.service.IOrderListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.shoppingdetail.ProductDetailPageController;
import kr.or.ddit.vo.OrderDetailListVO;
import kr.or.ddit.vo.OrderDetailVO;
import kr.or.ddit.vo.OrderListVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;

public class MyPageMainController implements Initializable{

	@FXML AnchorPane myPageDetail;
	@FXML TableView<OrderDetailListVO> Mypage_Order_Table;
	@FXML TableColumn<OrderDetailListVO, String> Order_Date_Col;
	@FXML TableColumn<OrderDetailListVO, Integer> Order_Index_Col;
	@FXML TableColumn<OrderDetailListVO, String> Order_Name_Col;
	@FXML TableColumn<OrderDetailListVO, Integer> Order_Price_Col;

	Registry reg;
	
	
	IOrderDetailListService iolist;
	IOrderDetailService iod;
	IOrderListService iol;
	IProductListService pol;
	
	public static  ArrayList<OrderListVO> olvo = new ArrayList();
	 OrderDetailListVO odvo = new OrderDetailListVO();
	public static ArrayList<OrderDetailVO> odrlist = new ArrayList();
	public static ProductListVO plvo = new ProductListVO();
	
	// allTableData가 안에 셋팅되야함.
	private ObservableList<OrderDetailListVO> allTableData;
	List<OrderDetailListVO> orderlist = new ArrayList<>(); 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			iolist = (IOrderDetailListService) reg.lookup("orderDetailList");
			iol = (IOrderListService) reg.lookup("orderList");
			iod = (IOrderDetailService) reg.lookup("orderDetail");
			pol = (IProductListService) reg.lookup("productList");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// 유저 정보 가져오기
		String memberInfo = Session.memVO.getMem_mail();
		odvo.setMem_mail(memberInfo);
		
		Order_Index_Col.setCellValueFactory(new PropertyValueFactory<>("o_index"));
		Order_Date_Col.setCellValueFactory(new PropertyValueFactory<>("o_date"));
		Order_Name_Col.setCellValueFactory(new PropertyValueFactory<>("pl_name"));
		Order_Price_Col.setCellValueFactory(new PropertyValueFactory<>("pl_price"));
		
		allTableData = FXCollections.observableArrayList();
		
		try {
			orderlist = (ArrayList<OrderDetailListVO>) iolist.selectOrderDetailList(Session.memVO.getMem_mail());
//			mileage = 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < orderlist.size(); i++) {
			orderlist.get(i).setPl_price(orderlist.get(i).getPl_price() * orderlist.get(i).getOd_quantity());
		}
		allTableData.setAll(orderlist);
		Mypage_Order_Table.setItems(allTableData);
		
		Mypage_Order_Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		          if(event.getClickCount()>1) {
		        	  int Oidx = Mypage_Order_Table.getSelectionModel().getSelectedItems().get(0).getO_index();
		        	  try {
		        		  olvo = (ArrayList<OrderListVO>) iol.getOrderListVO(Oidx);
						odrlist = (ArrayList<OrderDetailVO>) iod.getOrderDetailVO(Oidx);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	  try {
						plvo =  pol.getProductListVO2(odrlist.get(0).getPl_index());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	  
		        	  
		        	  Node OrderList = null;
		  			try {
		  				OrderList = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/shoppingMain/order/OrderList.fxml"));
		  				myPageDetail.getChildren().setAll(OrderList);
		  			} catch (IOException e2) {
		  				e2.printStackTrace();
		  			}
		  			
		          }
		     }
		});
		
		
		
		// (주문일 OrderList(o_date), 주문내역인덱스 OrderList(o_index), 주문목록 상품이름 pl_name, 주문 금액 pl_price)
		//결제가 완료됐다? 그러면  orderlistDETAIL을 인서트 해야하고,
		// 인서트 하게해줘..
//		String o_date = ovo.getO_date();
//		int o_index = ovo.getO_index();
//		String pl_name = ProductDetailPageController.pl_name2;
//		Integer pl_price = ProductDetailPageController.pl_price2;
		
//		Order_Index_Col.setText(Integer.toString(o_index));
//		Order_Date_Col.setText(o_date);
//		Order_Name_Col.setText(pl_name);
//		Order_Price_Col.setText(Integer.toString(pl_price));
	}


//	private void ShowPongPong() {
//		
//		String plidx = 
//
//	}

	

}
