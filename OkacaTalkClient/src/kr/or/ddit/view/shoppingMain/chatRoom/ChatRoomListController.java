package kr.or.ddit.view.shoppingMain.chatRoom;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;

public class ChatRoomListController implements Initializable{
	
	@FXML TextField Chat_Search_Text;
	@FXML ListView<String> Chat_Chatroom_List;
	@FXML ImageView Insert_Chat_Room_Btn;
	
	private Registry reg;
	IChatRoomService ics;
	IParticipantService ips;
	IMemberService ims;
	
	ObservableList<String> oldList = FXCollections.observableArrayList();
	ObservableList<String> newList = FXCollections.observableArrayList();
	
	List<String> memMailList = new ArrayList<>();
	List<List<String>> memNickList = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ics = (IChatRoomService) reg.lookup("chatRoom");
			ips = (IParticipantService) reg.lookup("participant");
			ims = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// 나의 채팅목록 VO 가져와서 세션에 저장 -> 로그인시 불러오도록 옮김
//		List<ChatRoomVO> getMyChatRoomList = new ArrayList<>();
//		try {
//			Session.getMyChatRoomList = ics.getMyChatRoom(Session.memVO.getMem_mail());
//		} catch (RemoteException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		chatroomLoad();
	}
	
	public void chatroomLoad() {
		MemberVO vo = Session.memVO;
		
		Session.Chat_Chatroom_List = Chat_Chatroom_List;
		if(Session.getMyChatRoomList == null) {
			Session.getMyChatRoomList = new ArrayList<>();
		}
		// 나의 채팅목록 cr_name 가져오기
		ObservableList<String> list1 = FXCollections.observableArrayList();
			
		for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
			list1.add( Session.getMyChatRoomList.get(i).getCr_name() );
		}
			
		oldList = list1;
		Chat_Chatroom_List.setItems(list1);
		// 세션에 리스트 등록
		// 멤버 닉네임 리스트 가져오기
		for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
			ArrayList<String> list = new ArrayList<>();
			memMailList = Session.outer.get(i);
			for(int j = 0; j < memMailList.size(); j++) {
				try {
					list.add(ims.getNickByEmail(memMailList.get(j)));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			memNickList.add(list);
			System.out.println(list.toString());
		}
		
		// 채팅목록 검색 이벤트
		Chat_Search_Text.textProperty().addListener(new ChangeListener<String>(){
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				List<ChatRoomVO> chatSearchList = new ArrayList<>();
				List<String> chatSearchStringList = new ArrayList<>();
				
				if(newValue.trim().equals("")) {
					Chat_Chatroom_List.setItems(oldList);
					return;
				}
				// Session에 채팅 목록 리스트에서 cr_name의 String과 비교
				for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
					if(Session.getMyChatRoomList.get(i).getCr_name().contains(newValue)) {
								chatSearchList.add(Session.getMyChatRoomList.get(i));
					}
				}
				
				// 또는
				// 검색 텍스트를
				// 채팅방 cr_index -> participant에 참여자 email -> nick 가져와서 비교
				boolean chk = false;
				for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
					// 채팅방 참여자 닉네임 가져오기
					for(int j = 0; j < memNickList.get(i).size(); j++) {
						if(memNickList.get(i).get(j).contains(newValue)) {
							if(chatSearchList.size() == 0) {
								chk = true; // 채팅방 제목이 일치하는게 아무것도 없을때 그냥 나가버릴 때를 대비해서 true로 세팅
							}
							for(ChatRoomVO vo : chatSearchList) {
								if(vo.getCr_index() == Session.getMyChatRoomList.get(i).getCr_index()) {
									chk = false;
									break;
								}
								else {chk = true;}
							}
						}
					}
					if(chk) {
						chatSearchList.add(Session.getMyChatRoomList.get(i));
					}
				}
				
				for(int i = 0; i < chatSearchList.size(); i++) {
					chatSearchStringList.add(chatSearchList.get(i).getCr_name());
				}
				ObservableList<String> chatSearchStringObList = FXCollections.observableArrayList(chatSearchStringList);
				Chat_Chatroom_List.setItems(chatSearchStringObList);
				
	         }
		});
		
		ContextMenu cm = new ContextMenu();
		MenuItem m1 = new MenuItem("채팅방 열기");
		MenuItem m2 = new MenuItem("채팅방 나가기");
		
		cm.getItems().add(m1);
		cm.getItems().add(m2);
		
		Chat_Chatroom_List.setContextMenu(cm);
		
		/**
		 * 채팅방 열기
		 */
		m1.setOnAction(e->{
			openChatRoom();
		});
		
		/**
		 * 채팅방 나가기
		 */
		m2.setOnAction(e->{
			int idx = Chat_Chatroom_List.getSelectionModel().getSelectedIndex();
			ChatRoomVO selectedCrvo = Session.getMyChatRoomList.get(idx);
			
			idx = Session.getMyChatRoomList.indexOf(selectedCrvo);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인");
			alert.setHeaderText("채팅방 나가기");
			alert.setContentText("정말로 " + selectedCrvo.getCr_name() + " 채팅방을 나가시겠습니까?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				if(Session.chatRoomMap.get(selectedCrvo.getCr_index()) != null) {
					try {
						((Stage) Session.chatRoomMap.get(selectedCrvo.getCr_index()).Cr_AnchorPane.getScene().getWindow()).close();//KDH
					}catch(Exception ee) {}
				}
				Session.getMyChatRoomList.remove(idx);
				Session.Chat_Chatroom_List.getItems().remove(idx);
				Session.chatRoomMap.remove(selectedCrvo.getCr_index());
				Session.outer.remove(idx);
				
				
				HashMap<String, Object> map = new HashMap<>();
				map.put("mem_mail", vo.getMem_mail());
				map.put("cr_index", selectedCrvo.getCr_index());
				
				try {
					ips.exitChatRoom(map);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				// 채팅방 리스트 다시 불러오기
				Node chatScene = null;
				try {
					chatScene = FXMLLoader.load(getClass().getResource("ChatRoomList.fxml"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				Session.Messenger.getChildren().setAll(chatScene);
			}
			else {
				alert.close();
			}
			
		});
		Session.crlc = this;
	}
	
	@FXML public void insertChatRoomBtnOnMouseClicked() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertChatRoom.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stage.setTitle("새로운 채팅");
		stage.setScene(new Scene(root));
		stage.show();
		
		
	}
/*
	@FXML public void searchBtnOnMouseClicked() {
		String chatSearchText = Chat_Search_Text.getText().trim();
		List<ChatRoomVO> chatSearchList = new ArrayList<>();
		List<String> chatSearchStringList = new ArrayList<>();
		
		if(!chatSearchText.equals("") && chatSearchText != null) {
			
			// Session에 채팅 목록 리스트에서 cr_name의 String과 비교
			for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
				if(Session.getMyChatRoomList.get(i).getCr_name().contains(chatSearchText)) {
							chatSearchList.add(Session.getMyChatRoomList.get(i));
				}
			}
			
			// 또는
			// 검색 텍스트를
			// 채팅방 cr_index -> participant에 참여자 email -> nick 가져와서 비교
			boolean chk = false;
			for(int i = 0; i < Session.getMyChatRoomList.size(); i++) {
				List<String> memMailList = new ArrayList<>();
				List<String> memNickList = new ArrayList<>();
				try {
					// 채팅방 참여자 가져오기
					memMailList = ips.getMemMailByCrIndex(Session.getMyChatRoomList.get(i).getCr_index());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 채팅방 참여자 닉네임 가져오기
				for(int j = 0; j < memMailList.size(); j++) {
					try {
						memNickList.add(ims.getNickByEmail(memMailList.get(j)));
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				for(int j = 0; j < memNickList.size(); j++) {
					if(memNickList.get(j).contains(chatSearchText)) {
						for(ChatRoomVO vo : chatSearchList) {
							if(vo.getCr_index() == Session.getMyChatRoomList.get(i).getCr_index()) {
								chk = false;
								break;
							}
							else {chk = true;}
						}
					}
				}
				if(chk) {chatSearchList.add(Session.getMyChatRoomList.get(i));}
			}
			
			for(int i = 0; i < chatSearchList.size(); i++) {
				chatSearchStringList.add(chatSearchList.get(i).getCr_name());
			}
			ObservableList<String> chatSearchStringObList = FXCollections.observableArrayList(chatSearchStringList); 
			Chat_Chatroom_List.setItems(chatSearchStringObList);
			
		}
		
		if(chatSearchText.equals("")) {
			Chat_Chatroom_List.setItems(oldList);
		}
		
	}*/

	@FXML public void chatSearchTextOnMouseClicked() {
		Chat_Search_Text.clear();
		
	}

	/**
	 * 더블 클릭해서 채팅방 열기
	 * @param event
	 */
	@FXML public void chatChatroomListOnMouseDoubleClicked(MouseEvent event) {
		if(event.getClickCount() > 1) { // 클릭이 두번 이상되면
			openChatRoom();
		}
	}
	
	/**
	 * 키보드 Enter로 채팅방 열기
	 * @param event
	 */
	@FXML public void chatChatroomListOnKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			openChatRoom();
		}
	}
	
	/**
	 * 채팅방 열기 메서드
	 */
	ChatRoomVO vo;
	public void openChatRoom() {
		// 선택한 채팅방의 인덱스를 받아서
		Integer num = Chat_Chatroom_List.getSelectionModel().getSelectedIndex();
		if(num == -1) return;
		// 세션에 채팅목록에서 ChatRoomVO 정보를 가져온다
		vo = Session.getMyChatRoomList.get(num);
		// 세션의 	currentOpenRoom에 저장
		Session.currentOpenRoom.add(vo);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../chat/ChatRoom.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage(StageStyle.DECORATED);
		
		stage.setTitle("채팅");
		stage.setScene(scene);
		stage.show();
		
		// 닫기 버튼으로 창을 닫을 때 세션의 currentOpenRoom, currentOpenRoomAnchorPane에서 삭제
		stage.setOnCloseRequest(e -> {
			chatroomClose();
		});
		
		// ESC로 창을 닫을 때 세션의 currentOpenRoom, currentOpenRoomAnchorPane에서 삭제
		stage.getScene().setOnKeyPressed(KeyEvent -> {
			if(KeyEvent.getCode() == KeyCode.ALT && KeyEvent.getCode() == KeyCode.F4 ||
					KeyEvent.getCode() == KeyCode.ESCAPE) {
				chatroomClose();
			}
		});;
	}
	
	private void chatroomClose() {
		for(int i = 0; i < Session.currentOpenRoom.size(); i++) {
			if(Session.currentOpenRoom.get(i).getCr_index() == vo.getCr_index()) {
				Session.chatRoomMap.get(vo.getCr_index()).cci.isRun = false;	//스레드 종료
				System.out.println(i + "번째 삭제, cr_index : " + Session.currentOpenRoom.get(i).getCr_index());
				Session.currentOpenRoom.remove(i);
				Session.currentOpenRoomAnchorPane.remove(i);
			}
		}
	}

}
