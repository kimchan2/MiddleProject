package kr.or.ddit.view.chat;

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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import kr.or.ddit.chatting.ChatServer;
import kr.or.ddit.service.IChatMessageService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.shoppingMain.friendList.FriendListController;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.FriendListVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddUserController implements Initializable{

	@FXML TextField Search_Friend_Text;
	@FXML Button Cancel_Btn;
	@FXML Button Choice_Friend_Btn;
	@FXML ListView<String> Friends_List; 
	@FXML AnchorPane anchorPane;
	
	private Registry reg;
	private IFriendListService ifs;
	private IParticipantService ips;
	private IMemberService ilogin;
	private IChatRoomService ics;
	private IChatMessageService icms;
	private ChatServer server;
	private IMemberService ims;
	private IProfileImgListService ipils;
	private ChatRoomVO crvo;
	
	List<String> friends_email_list = new ArrayList<>();
	List<ParticipantVO> participantList = new ArrayList<>();
	List<FriendListVO> myFriendsListVO = new ArrayList<>();
	ObservableList<String> myFriendsListNick = FXCollections.observableArrayList();
	ObservableList<String> oldList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		crvo = Session.seletedCrvoForAddFriendInChatRoom;
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ilogin = (IMemberService) reg.lookup("member");
			ifs = (IFriendListService) reg.lookup("friendList");
			ips = (IParticipantService) reg.lookup("participant");
			ics = (IChatRoomService) reg.lookup("chatRoom");
			icms = (IChatMessageService) reg.lookup("chatMessage");
			ims = (IMemberService) reg.lookup("member");
			ipils = (IProfileImgListService) reg.lookup("profileImgList");
			server = (ChatServer) reg.lookup("server");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			myFriendsListVO = FXCollections.observableArrayList(ifs.getFriendListVO(Session.memVO.getMem_mail()));
			participantList = ips.getParticipantVO(crvo.getCr_index());
			
			for(int i = 0; i < participantList.size(); i++) {
				int length = myFriendsListVO.size();
				for(int j = 0; j < length; j++) {
					if(participantList.get(i).getMem_mail().equals(myFriendsListVO.get(j).getFr_mail())) {
						myFriendsListVO.remove(j);
						length--;
					}
				}
			}
			for(int i = 0; i < myFriendsListVO.size(); i++) {
				myFriendsListNick.add(ilogin.idSearch(myFriendsListVO.get(i).getFr_mail()).getMem_nick());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("@@@@@@@@@@@@@@@@친구목록 로드 실패 @@@@@@@@@@@@@@@@@@@@@");
		}
		
		Friends_List.setItems(myFriendsListNick);
		oldList = myFriendsListNick;
		
		Search_Friend_Text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue == "") {
					Friends_List.setItems(oldList);
					return;
				}
				ObservableList<String> list3 = FXCollections.observableArrayList();
				for(String str : myFriendsListNick) {
					if(str.contains(newValue)) {
						list3.add(str);
					}
				}
				Friends_List.setItems(list3);
			}
		});
	}

	@FXML public void cancelBtnOnAction() {
		
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		stage.close();
	}

	@FXML public void choiceFriendBtnOnAction() {
		if(Friends_List.getSelectionModel().getSelectedItem() != null && !
				Friends_List.getSelectionModel().getSelectedItem().equals("")) {
			int idx = Friends_List.getSelectionModel().getSelectedIndex();
			FriendListVO flvo = myFriendsListVO.get(idx);
			String friendMail = flvo.getFr_mail();
			
			// participantVO 만들기
			ParticipantVO ppVO = new ParticipantVO();
			ppVO.setCr_index(crvo.getCr_index());
			ppVO.setMem_mail(friendMail);
			ppVO.setPc_online(0);
			
			try {
				ips.insertParticipantVO(ppVO);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ChatMessageVO cmvo = new ChatMessageVO();
			cmvo.setCm_content(Session.memVO.getMem_nick() + "님이 " + myFriendsListNick.get(idx) + "님을 초대하셨습니다." );
			cmvo.setCr_index(crvo.getCr_index());
			cmvo.setMem_mail(Session.memVO.getMem_mail());
			String cnt = Session.memVO.getMem_mail();
			for(ParticipantVO ptcpvo : participantList) {
				if(ptcpvo.getPc_online() == 1)
					cnt += "," + ptcpvo.getMem_mail();
			}
			cmvo.setCm_ischeck(cnt);
			try {
				icms.insertChatMessageInCrIndex(cmvo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			// 세션 아우터 업데이트
			Session.outer.clear();
			try {
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
			
			// 채팅방 친구초대 메시지 뿌려주기
			try {
				server.say(cmvo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Session.chatRoomMap.get(crvo.getCr_index()).writeChatMessage(
				Session.chatRoomMap.get(crvo.getCr_index()).Cm_Content_ScrollField,
				Session.chatRoomMap.get(crvo.getCr_index()).Cm_Content_VBoxField,
				cmvo,
				Session.chatRoomMap.get(crvo.getCr_index()).partiMemList,
				Session.chatRoomMap.get(crvo.getCr_index()).profileImagVOList);
			
			// 리스트 업데이트
			try {
				Session.chatRoomMap.get(crvo.getCr_index()).partiList = ips.getParticipantVO(crvo.getCr_index());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			List<MemberVO> partiMemList = new ArrayList<>(); 
			List<ProfileImgListVO> profileImagVOList = new ArrayList<>(); 
			for(ParticipantVO ippvo : Session.chatRoomMap.get(crvo.getCr_index()).partiList) {
				try {
					partiMemList.add(ims.idSearch(ippvo.getMem_mail()));
					// 참여인원의 프로필 사진 VO 가져오기
					ProfileImgListVO vo = ipils.getProfileImgListVO(ippvo.getMem_mail());
					if( vo != null) {
						profileImagVOList.add(vo);
					}
					else {
						vo = new ProfileImgListVO();
						vo.setPf_addr("1");
						vo.setMem_mail("NotExist");
						profileImagVOList.add(vo);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			Session.chatRoomMap.get(crvo.getCr_index()).profileImagVOList = profileImagVOList;
			Session.chatRoomMap.get(crvo.getCr_index()).partiMemList = partiMemList;
			
			((Stage) anchorPane.getScene().getWindow()).close();
			
			Node chatRoomScene = null;
			try {
				chatRoomScene = new FXMLLoader(getClass().getResource("../shoppingMain/chatRoom/ChatRoomList.fxml")).load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Session.Messenger.getChildren().setAll(chatRoomScene);
		}
	}
	
	void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText("경고");
		alert.setContentText("이미 존재하는 채팅방 입니다.");
		
		alert.showAndWait();
	}
}
