package kr.or.ddit.view.shoppingMain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;

public class ShoppingMainMainController implements Initializable{

	
	@FXML Button Shop_Brand_Btn;
	@FXML Button Shop_Cloth_Btn;
	@FXML Button Shop_Food_Btn;
	@FXML Button Shop_Life_Btn;
	@FXML Button Shop_Digital_Btn;
	@FXML Button Shop_Sports_Btn;
	
	@FXML Button Shop_Womanbrand_Btn;
	@FXML Button Shop_Manbrand_Btn;
	
	@FXML Button Mypage_Btn;
	@FXML Button Shopping_Btn;
	
	@FXML AnchorPane Messenger;
	@FXML Button Friend_List_Btn;
	@FXML Label lblName;
	@FXML ImageView profileImg;

	IFriendListService ifl;
	@FXML ListView<String> Chat_Friend_List;
	@FXML Button Configuration_List_Btn;
	@FXML Button Notice_List_Btn;
	@FXML Button Faq_List_Btn;
	@FXML Button Qna_List_Btn;
	@FXML Button Chat_Room_List_Btn;
	@FXML Button Logout_Btn;
	private Registry reg;
	IChatRoomService ics;
	@FXML  AnchorPane AnchorMain;
	
	@FXML AnchorPane Shopping;
	@FXML AnchorPane Shop_Cate;
	@FXML AnchorPane Shop_Cate_All;
	@FXML AnchorPane Shop_Dcate_Brand;
	@FXML AnchorPane Select_Menu_Btn;
	
	public AnchorPane getAnchorMain() {
		return AnchorMain;
	}
	public void setAnchorMain(AnchorPane anchorMain) {
		AnchorMain = anchorMain;
	}
	public AnchorPane getShopping() {
		return Shopping;
	}
	public AnchorPane getShop_Cate() {
		return Shop_Cate;
	}

	private IParticipantService ips;
	public Thread thread;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(Session.memVO.getMem_mail().equals("admin")) {
			Shop_Cate.getChildren().setAll();
			Select_Menu_Btn.getChildren().setAll();
			Shopping.getChildren().setAll();
			try {
				Shopping.getChildren().setAll((Node)new FXMLLoader(getClass().getResource("/kr/or/ddit/view/admin/AdminCrudPage.fxml")).load());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Session.moveConf = this;
		}
		else {
			lblName.setText(Session.memVO.getMem_name());
			Session.Messenger = Messenger;
			
			try {
				reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
				ics = (IChatRoomService) reg.lookup("chatRoom");
				ips = (IParticipantService) reg.lookup("participant");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
			Session.outer.clear();
			try {//KDH
				Session.getMyChatRoomList = ics.getMyChatRoom(Session.memVO.getMem_mail());
				for(ChatRoomVO vo1 : Session.getMyChatRoomList) {
					List<String> inner = new ArrayList<>();
					try {
						inner = new ArrayList<>(ips.getMemMailByCrIndex(vo1.getCr_index()));
						Session.outer.add(inner);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			Node chatScene = null;
			try {
				chatScene = FXMLLoader.load(getClass().getResource("friendList/FriendList.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Messenger.getChildren().setAll(chatScene);
			Session.moveConf = this;
			
			Node shoppingDetail = null;
			try {
				shoppingDetail = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Shopping.getChildren().setAll(shoppingDetail);
			if(thread == null && !Session.memVO.getMem_mail().equals("admin")) {
				try {
					thread = new Thread(new MainThread());
					thread.start();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			MainThread.brun = true;
		}
	}
	public void moveConfiguration() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("configurationList/ConfigurationList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void chatRoomBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("chatRoom/ChatRoomList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void friendListBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("friendList/FriendList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void qnaBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("chatQna/QnaList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void faqBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("faq/FaqList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void noticeBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("notice/NoticeList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}

	@FXML public void configurationBtnOnAction() throws IOException {
		Node chatScene = FXMLLoader.load(getClass().getResource("configurationList/ConfigurationList.fxml"));
		Messenger.getChildren().setAll(chatScene);
	}
	
	
	
	/**
	 * PONGPONG  mypage 연결버튼입니다.
	 * @throws IOException
	 */
	@FXML public void MypageBtnOnAction() throws IOException {
		Node MyPageMain = null;
		Node MyPageCategory = null;
		try {
			MyPageMain = FXMLLoader.load(getClass().getResource("MyPage/MyPageMain.fxml"));
			MyPageCategory = FXMLLoader.load(getClass().getResource("MyPage/MyPageCategory.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shopping.getChildren().setAll(MyPageMain);
		Shop_Cate.getChildren().setAll(MyPageCategory);
		//Session.moveConf = this;
	}
	
	Node shoppingPage = null;

	
	@FXML public void ShoppingBtnOnAction() {
		Node ShoppingCate = null;
		try {
			ShoppingCate = FXMLLoader.load(getClass().getResource("category/CateAll.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Shop_Cate.getChildren().clear();
		Shop_Cate.getChildren().setAll(ShoppingCate);
		Shopping.getChildren().setAll(shoppingPage);
	}

	@FXML public void profileImgOnMouseClicked() throws MalformedURLException {
		
		
	}

	
	@FXML public void shopBrandBtnOnAction() {
		Node brandCate = null;
		
		try {
			brandCate = FXMLLoader.load(getClass().getResource("category/DcateBrand.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(brandCate);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void shopWomanbrandBtnOnAction() {
		Node Womanbrand = null;
		Session.curclindex = 0;
		try {
			Womanbrand = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Session.cateNum = 1;

		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Womanbrand);
	}
	
	@FXML public void shopManbrandBtnOnAction() {
		Node Manbrand = null;
		Session.curclindex = 1;
		try {
			Manbrand = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 카테고리 변환
//		Session.cateNum = 2;

		Session.moveConf.getShopping().getChildren().clear();
		Session.moveConf.getShopping().getChildren().setAll(Manbrand);
	}
	
	@FXML public void ShopClothBtnOnAction() {
		Node clothCate = null;
		try {
			clothCate = FXMLLoader.load(getClass().getResource("category/DcateCloth.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(clothCate);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopFoodBtnOnAction() {
		Node Food = null;
		try {
			Food = FXMLLoader.load(getClass().getResource("category/DcateFood.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Food);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopLifehBtnOnAction() {
		Node Life = null;
		try {
			Life = FXMLLoader.load(getClass().getResource("category/DcateLife.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Life);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopDigitalBtnOnAction() {
		Node Digital = null;
		try {
			Digital = FXMLLoader.load(getClass().getResource("category/DcateDigital.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Digital);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void ShopSportsBtnOnAction() {
		Node Sports = null;
		try {
			Sports = FXMLLoader.load(getClass().getResource("category/DcateSports.fxml"));
			shoppingPage = FXMLLoader.load(getClass().getResource("shoppingdetail/ShoppingDetail.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Shop_Dcate_Brand.getChildren().clear();
		Shop_Dcate_Brand.getChildren().setAll(Sports);
		Shopping.getChildren().setAll(shoppingPage);
	}
	
	@FXML public void logoutBtnOnAction() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../logout/logout.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setTitle("[오카카톡] 로그아웃 확인창");
		stage.setScene(new Scene(root));
		stage.show();
		
	
		
	}

}
