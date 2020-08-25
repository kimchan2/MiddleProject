package kr.or.ddit.view.shoppingMain.chatRoom;

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
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.chat.ChatRoomController;
import kr.or.ddit.view.shoppingMain.friendList.FriendListController;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.FriendListVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChoiceUserController implements Initializable{

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
	
	List<String> friends_email_list = new ArrayList<>();
	ObservableList<String> oldList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list2 = FXCollections.observableArrayList();
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ilogin = (IMemberService) reg.lookup("member");
			ifs = (IFriendListService) reg.lookup("friendList");
			ips = (IParticipantService) reg.lookup("participant");
			ics = (IChatRoomService) reg.lookup("chatRoom");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			List<FriendListVO> list = new ArrayList<>();
			list = FXCollections.observableArrayList(ifs.getFriendListVO(Session.memVO.getMem_mail()));
			
			for(int i = 0; i < list.size(); i++) {
				list2.add(ilogin.idSearch(list.get(i).getFr_mail()).getMem_nick());
				friends_email_list.add(list.get(i).getFr_mail());
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("@@@@@@@@@@@@@@@@친구목록 로드 실패 @@@@@@@@@@@@@@@@@@@@@");
		}
		
		Friends_List.setItems(list2);
		oldList = list2;
		
		Search_Friend_Text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue == "") {
					Friends_List.setItems(oldList);
					return;
				}
				ObservableList<String> list3 = FXCollections.observableArrayList();
				for(String str : list2) {
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
		//ChatRoomVO existChatRoom = new ChatRoomVO();
		
		if(Friends_List.getSelectionModel().getSelectedItem() != null &&
				Friends_List.getSelectionModel().getSelectedItem() != "") {
			
			try {
				Session.selectedFriendListVO = ilogin.idSearch(friends_email_list.get(Friends_List.getSelectionModel().getSelectedIndex()));
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			MemberVO vo = Session.selectedFriendListVO;
			
			ChatRoomVO cvo = new ChatRoomVO();
			cvo.setMem_mail(Session.memVO.getMem_mail());
			cvo.setCr_name(Session.memVO.getMem_nick() + ", " + vo.getMem_nick());
			cvo.setCr_isself(0);
			cvo.setCr_isopenchat(0);
			int currval = 0;
			
			Boolean existChk = false;
			String fr_mail = vo.getMem_mail();
			String my_mail = Session.memVO.getMem_mail();
			List<String> existList = new ArrayList<>();
			existList.add(fr_mail);
			existList.add(my_mail);
			int index = -1;
			for(int i = 0 ; i < Session.outer.size(); i++) {
				if(Session.outer.get(i).size() == existList.size()) {
					existChk = Session.outer.get(i).containsAll(existList);
					if(existChk){
						index = i;
						break;
					}
				}
			}
			
			// 나와 선택한 친구가 참여자로 있는 채팅방이 존재하지 않는다면
			// 채팅방을 인서트
			if(existChk == false) {
				try {
					currval = ics.insertChatRoom(cvo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if(currval > 0) {
					ParticipantVO pvo = new ParticipantVO();
					pvo.setCr_index(currval);
					pvo.setMem_mail(Session.memVO.getMem_mail());
					pvo.setPc_online(0);
					try {
						ips.insertParticipantVO(pvo);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					
					ParticipantVO pvo1 = new ParticipantVO();
					pvo1.setCr_index(currval);
					pvo1.setMem_mail(vo.getMem_mail());
					pvo1.setPc_online(0);
					try {
						ips.insertParticipantVO(pvo1);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
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
					for(int i = 0 ; i < Session.outer.size(); i++) {
						if(Session.outer.get(i).size() == existList.size()) {
							existChk = Session.outer.get(i).containsAll(existList);
							if(existChk){
								index = i;
								break;
							}
						}
					}
					Session.currentOpenRoom.add(Session.getMyChatRoomList.get(index));
					
					Stage stage = (Stage) anchorPane.getScene().getWindow();
					stage.close();
					((Stage) Session.insertChatRoomAnchorPane.getScene().getWindow()).close();
					
					// 채팅방 띄워주기
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("../../chat/ChatRoom.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Scene scene = new Scene(root);
					Stage stage2 = new Stage();
					stage2.setScene(scene);
					stage2.setTitle("채팅");
					stage2.show();
				}
				
			}
			
			// 존재하면 기존에 존재하는 채팅방을 띄움
			else {
				warningAlert();
				
				// 참여자 선택창, 채팅종류 선택창 닫기
				((Stage) anchorPane.getScene().getWindow()).close();
				((Stage) Session.insertChatRoomAnchorPane.getScene().getWindow()).close();
				
				ChatRoomVO crvo = Session.getMyChatRoomList.get(index);
				
				Session.currentOpenRoom.add(crvo);
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../../chat/ChatRoom.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				Stage stage = new Stage(StageStyle.DECORATED);
				
				stage.setScene(scene);
				stage.setTitle("채팅");
				stage.setScene(scene);
				stage.show();
			}
		}
		
	}
	
	void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText("경고");
		alert.setContentText("이미 존재하는 채팅방 입니다. \n존재하는 채팅방으로 이동합니다.");
		
		alert.showAndWait();
	}
}
