package kr.or.ddit.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.service.ICategoryListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.view.chat.ChatRoomController;
import kr.or.ddit.view.main.MainMainController;
import kr.or.ddit.view.shoppingMain.ShoppingMainMainController;
import kr.or.ddit.view.shoppingMain.chatRoom.ChatRoomListController;
import kr.or.ddit.vo.CategoryListVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.view.shoppingMain.friendList.FriendListController;
import kr.or.ddit.view.shoppingMain.friendList.ProfileController;
import kr.or.ddit.view.shoppingMain.shoppingdetail.ShoppingDetailController;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProductListVO;

public class Session {

//	public static String IPaddress = "localhost"; //"localhost"
	public static String IPaddress = "192.168.41.24"; //"localhost"
	public static boolean bloaded = false;
	public static ListView<String> Chat_Chatroom_List; // 채팅 목록(방이름)
	public static List<ChatRoomVO> getMyChatRoomList; // 채팅 목록(ChatRoomVO)
	public static List<String> friendsMailList;
	public static MemberVO memVO;
	public static MainMainController mainConf;
	public static ShoppingMainMainController moveConf;
	public static FriendListController flc;
	public static ProfileController pfc;
	public static String ServerIPAddress = "\\\\192.168.41.24\\"; //"C:\\"; // "\\\\192.168.41.24\\";
	public static ShoppingDetailController shopDeConf;
	public static ICategoryListService icategory;
	public static IProductListService iproduct;
	public static ChatRoomListController crlc;
	
	public static ArrayList<CategoryListVO> catelist = new ArrayList<>();
	public static ArrayList<ProductListVO> prodlist  = new ArrayList<>();
	public static ArrayList<ArrayList<CategoryListVO>> pcatelist = new ArrayList<>();
	public static ArrayList<ArrayList<ProductListVO>> pprodlist = new ArrayList<>();
	public static int catelistCnt;
	public static int curclindex;
	public static int cateNum;
	public static String curplindex;
	public static String quantity;
	
	public static AnchorPane Messenger;
	public static ArrayList<ChatRoomVO> currentOpenRoom = new ArrayList<>(); // 현재 열려있는 채팅방 리스트
	public static List<AnchorPane> currentOpenRoomAnchorPane = new ArrayList<>(); // 현재 열려있는 채팅방 AnchorPane
//	public static List<ChatRoomController> chatRoomList = new ArrayList<>(); //채팅방 컨트롤러 리스트
	public static HashMap<Integer, ChatRoomController> chatRoomMap = new HashMap<>();
	public static ChatRoomVO seletedCrvoForAddFriendInChatRoom; // 친구추가 버튼을 클릭한 채팅방의 crvo정보 저장
	public static ChatRoomVO seletedCrvoForHamburgerMenu; // 햄버거메뉴 버튼을 클릭한 채팅방의 crvo정보 저장
	public static AnchorPane insertChatRoomAnchorPane; // 채팅, 오픈채팅 선택창
	public static AnchorPane MainMainControllerAnchorPane; // 메인화면 AnchorPane
	
	public static MemberVO selectedFriendListVO = null;
	public static List<List<String>> outer = new ArrayList<>(); // 채팅방별 참여자 메일 리스트
	
	public static int pageindex = 0;
	public static ArrayList<ProductListVO> orglist;
	public static boolean isSearch = true;
}
