package kr.or.ddit.view.shoppingMain.friendList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IBookMarkService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BookMarkVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.FriendListVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.Group;

public class FriendListController implements Initializable{

	@FXML AnchorPane Messenger;
	@FXML TextField Chat_Friendsearch_Text;
	@FXML ImageView Chat_Profile_Img;
	@FXML Label lblName;
	@FXML ListView<String> Chat_Birth_List;
	@FXML ListView<String> Chat_Bookmark_List;
	@FXML ListView<String> Chat_Friend_List;
	
	private Registry reg;
	IFriendListService ifl;
	IMemberService ilogin;
	IProfileImgListService ipil;
	IFileService ifile;
	IChatRoomService ics;
	IParticipantService ips;
	IBookMarkService ibms;
	ObservableList<MemberVO> friendList = null;
	ObservableList<MemberVO> friendBirthdayList = FXCollections.observableArrayList();
	ObservableList<MemberVO> friendBookmarkList = FXCollections.observableArrayList();
	ObservableList<String> friendNickList = FXCollections.observableArrayList();
	ObservableList<String> friendBirthdayNickList = FXCollections.observableArrayList();
	ObservableList<String> friendBookmarkNickList = FXCollections.observableArrayList();
	@FXML Group group1;
	@FXML Group group2;
	@FXML Group group3;
	@FXML ImageView friendList_AddFriend;
	@FXML ContextMenu FriendList_Context;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblName.setText(Session.memVO.getMem_name());
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ifl = (IFriendListService) reg.lookup("friendList");
			ilogin = (IMemberService) reg.lookup("member");
			ifile = (IFileService) reg.lookup("file");
			ipil = (IProfileImgListService) reg.lookup("profileImgList");
			ics = (IChatRoomService) reg.lookup("chatRoom");
			ips = (IParticipantService) reg.lookup("participant");
			ibms = (IBookMarkService) reg.lookup("bookMark");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		friendListLoad();
		
		Session.flc = this;
	}
	
	public void friendListLoad() {
		friendList = null;                                       
		friendBirthdayList = FXCollections.observableArrayList();
		friendBookmarkList = FXCollections.observableArrayList();
		friendNickList = FXCollections.observableArrayList();        
		friendBirthdayNickList = FXCollections.observableArrayList();
		friendBookmarkNickList = FXCollections.observableArrayList();
		
		try {
			friendList = FXCollections.observableArrayList(ilogin.getFriendList(Session.memVO.getMem_mail()));
			friendBirthdayList = FXCollections.observableArrayList(ilogin.getBirthdayMember(Session.memVO.getMem_mail()));
			friendBookmarkList = FXCollections.observableArrayList(ilogin.getBookmarkMember(Session.memVO.getMem_mail()));
			for(MemberVO vo : friendList) {
				friendNickList.add(vo.getMem_nick());
			}
			for(MemberVO vo : friendBirthdayList) {
				friendBirthdayNickList.add(vo.getMem_nick());
			}
			for(MemberVO vo : friendBookmarkList) {
				friendBookmarkNickList.add(vo.getMem_nick());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("@@@@@@@@@@@@@@@@친구목록 로드 실패 @@@@@@@@@@@@@@@@@@@@@");
		}
		
		List<String> friendsMailList = new ArrayList<>();
		for(MemberVO vo : friendList) {
			friendsMailList.add(vo.getMem_mail());
		}
		Session.friendsMailList = friendsMailList;
		
		Chat_Friend_List.setPrefHeight(friendNickList.size() * 24 + 2);
		Chat_Birth_List.setPrefHeight(friendBirthdayNickList.size() * 24 + 2);
		Chat_Bookmark_List.setPrefHeight(friendBookmarkNickList.size() * 24 + 2);
		
		Chat_Friend_List.setItems(friendNickList);
		Chat_Birth_List.setItems(friendBirthdayNickList);
		Chat_Bookmark_List.setItems(friendBookmarkNickList);
		
		group2.setLayoutY(Chat_Birth_List.getPrefHeight() + Chat_Birth_List.getLayoutY());
		group3.setLayoutY(25 + Chat_Bookmark_List.getPrefHeight() + group2.getLayoutY());
		group2.relocate(25, group2.getLayoutY());
		group3.relocate(25, group3.getLayoutY());
		
		ProfileImgListVO vo = null;
		try {
			vo = ipil.getProfileImgListVO(Session.memVO.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(vo != null) {
			String dir = vo.getPf_addr(); //Session.ServerIPAddress + vo.getPf_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본프로필.png");
			}
			Image image = new Image(file.toURI().toString(), 150, 150, false, false, false);
			Chat_Profile_Img.setImage(image);
		}else {
			File file = new File("src/image/기본프로필.png");
			Image image = new Image(file.toURI().toString(), 150, 150, false, false, false);
			Chat_Profile_Img.setImage(image);
		}
		
		
		
		Chat_Friendsearch_Text.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue == "") {
					Chat_Friend_List.setItems(friendNickList);
					return;
				}
				ObservableList<String> list3 = FXCollections.observableArrayList();
				for(String str : friendNickList) {
					if(str.contains(newValue)) {
						list3.add(str);
					}
				}
				Chat_Friend_List.setItems(list3);
			}
		});
		FriendList_Context = new ContextMenu();
		MenuItem m1 = new MenuItem("채팅하기");
		MenuItem m2 = new MenuItem("즐겨찾기 추가/삭제");
		MenuItem m3 = new MenuItem("친구 삭제");
		FriendList_Context.getItems().add(m1);
		FriendList_Context.getItems().add(m2);
		FriendList_Context.getItems().add(m3);
		
		Chat_Friend_List.setContextMenu(FriendList_Context);
		Chat_Birth_List.setContextMenu(FriendList_Context);
		Chat_Bookmark_List.setContextMenu(FriendList_Context);
		
		m1.setOnAction(e->{
			ChatRoomVO cvo;
			cvo = new ChatRoomVO();
			cvo.setMem_mail(Session.memVO.getMem_mail());
			cvo.setCr_name(Session.memVO.getMem_nick() + ", " + Session.selectedFriendListVO.getMem_nick());
			cvo.setCr_isself(0);
			cvo.setCr_isopenchat(0);
			int currval = 0;
			
			Boolean existChk = false;
			String fr_mail = Session.selectedFriendListVO.getMem_mail();
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
				} catch (RemoteException e1) {
					e1.printStackTrace();
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
					pvo1.setMem_mail(Session.selectedFriendListVO.getMem_mail());
					pvo1.setPc_online(0);
					try {
						ips.insertParticipantVO(pvo1);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					try {
						Session.getMyChatRoomList = ics.getMyChatRoom(Session.memVO.getMem_mail());
					} catch (RemoteException e2) {
						e2.printStackTrace();
					}
					Session.outer.clear();
					for(ChatRoomVO vo1 : Session.getMyChatRoomList) {
						List<String> inner = new ArrayList<>();
						try {
							inner = new ArrayList<>(ips.getMemMailByCrIndex(vo1.getCr_index()));
							Session.outer.add(inner);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
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
					
					// 채팅방 리스트 다시 불러오기
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("../../chat/ChatRoom.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.setTitle("채팅");
					stage.show();
				}
				
			}
			
			// 존재하면 기존에 존재하는 채팅방을 띄움
			else {
				cvo = Session.getMyChatRoomList.get(index);
				
				Session.currentOpenRoom.add(cvo);
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../../chat/ChatRoom.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scene scene = new Scene(root);
				Stage stage = new Stage(StageStyle.DECORATED);
				
				stage.setScene(scene);
				stage.setTitle("채팅");
				stage.setScene(scene);
				stage.show();
			}
			
		});
		m2.setOnAction(e->{
			BookMarkVO bmvo = new BookMarkVO();
			bmvo.setMem_mail(Session.memVO.getMem_mail());
			bmvo.setFr_mail(Session.selectedFriendListVO.getMem_mail());
			//북마크리스트에서 친구가 잇는지 확인하고
			boolean bexist = false;
			for(MemberVO memvo : friendBookmarkList) {
				if(memvo.getMem_mail().equals(bmvo.getFr_mail())) {
					bexist = true;
					break;
				}
			}
			//있으면 삭제
			if(bexist) {
				ibms.deleteBF(bmvo);
			}// 없으면 추가
			else {
				ibms.insertBF(bmvo);
			}
			friendListLoad();
		});
		m3.setOnAction(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("삭제하기");
			alert.setContentText(Session.selectedFriendListVO.getMem_mail() + "(" 
			+ Session.selectedFriendListVO.getMem_nick() + ")님을 삭제하시겠습니까?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				int result1 = 0;
				FriendListVO frvo = new FriendListVO();
				frvo.setMem_mail(Session.memVO.getMem_mail());
				frvo.setFr_mail(Session.selectedFriendListVO.getMem_mail());
				try {
					result1 = (int)ifl.deleteFriendListVO(frvo);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(result1 != 0)
					friendListLoad();
			}
		});
	}

	public void setImage(Image image) {
		Chat_Profile_Img.setImage(image);
	}
	
	@FXML public void profileImgOnMouseClicked() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setTitle("프로필화면");
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void ChatFriendsearchTextOnMouseClicked() {
		Chat_Friendsearch_Text.clear();
	}

	@FXML public void ChatFriendsearchTextOnTextChanged() {
		if(Chat_Friendsearch_Text.getText() == "") {
			Chat_Friend_List.setItems(friendNickList);
			return;
		}
		ObservableList<String> list3 = FXCollections.observableArrayList();
		for(String str : friendNickList) {
			if(str.contains(Chat_Friendsearch_Text.getText())) {
				list3.add(str);
			}
		}
		Chat_Friend_List.setItems(list3);
	}
	TableView<String> tb = new TableView<>();
	
	@FXML public void chatFriendListOnMouseDoubleClicked(MouseEvent event) {
		if(event.getClickCount() > 1) {
			ListView<String> selectedListView = (ListView<String>)event.getSource();
			String name = selectedListView.getId().toString();
			int index = selectedListView.getSelectionModel().getSelectedIndex();
			switch(name) {
			case "Chat_Friend_List":
				Session.selectedFriendListVO = friendList.get(index);
				break;
			case "Chat_Birth_List":
				Session.selectedFriendListVO = friendBirthdayList.get(index);
				break;
			case "Chat_Bookmark_List":
				Session.selectedFriendListVO = friendBookmarkList.get(index);
				break;
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
			Parent root = null;
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			
			try {
				root = (Parent)loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			stage.setTitle("프로필화면");
			stage.setScene(new Scene(root));
			stage.show();
		}
		else if(event.isPopupTrigger()) {
			ListView<String> selectedListView = (ListView<String>)event.getSource();
			String name = selectedListView.getId().toString();
			int index = selectedListView.getSelectionModel().getSelectedIndex();
			switch(name) {
			case "Chat_Friend_List":
				Session.selectedFriendListVO = friendList.get(index);
				break;
			case "Chat_Birth_List":
				Session.selectedFriendListVO = friendBirthdayList.get(index);
				break;
			case "Chat_Bookmark_List":
				Session.selectedFriendListVO = friendBookmarkList.get(index);
				break;
			}
		}
	}

	@FXML public void friendListAddFriendOnMouseClicked(MouseEvent event) throws IOException {
		FXMLLoader 	loader 	= new FXMLLoader(getClass().getResource("AddFriend.fxml"));
		Parent 		root 	= loader.load();
		Stage 		stage 	= new Stage();
		
		stage.setTitle("친구추가");
		stage.setScene(new Scene(root));
		stage.show();
	}

}
