package kr.or.ddit.view.shoppingMain.chatRoom;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.ParticipantVO;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ChoiceOpenChatController implements Initializable{

	@FXML TextField Search_OpenChat_Text;
	@FXML ImageView Search_OpenChat_Btn;
	@FXML ListView<String> OpenChat_List;
	@FXML Button Cancel_Btn;
	@FXML Button Choice_OpenChat_Btn;
	@FXML AnchorPane anchorPane;
	@FXML ImageView Insert_OpenChat_Room_Btn;
	
	private Registry reg;
	private IChatRoomService ics;
	private IParticipantService ips;
	
	List<ChatRoomVO> openChatRoomVOlist = new ArrayList<>();
	ObservableList<String> openChatRoomNMlist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ics = (IChatRoomService) reg.lookup("chatRoom");
			ips = (IParticipantService) reg.lookup("participant");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		} catch (NotBoundException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			openChatRoomVOlist = ics.getOpenChatRoom();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < openChatRoomVOlist.size(); i++) {
			openChatRoomNMlist.add(openChatRoomVOlist.get(i).getCr_name());
		}
		
		OpenChat_List.setItems(openChatRoomNMlist);
		
	}

	@FXML public void searchFriendBtnOnMouseClicked() {
		ObservableList<String> oldList = openChatRoomNMlist;
		ObservableList<String> newList = FXCollections.observableArrayList();
		
		String searchOpenChatText = Search_OpenChat_Text.getText().trim();
		if(!searchOpenChatText.equals("") && searchOpenChatText != null) {
			for(int i = 0; i < openChatRoomNMlist.size(); i++) {
				if(openChatRoomNMlist.get(i).contains(searchOpenChatText)) {
					newList.add(openChatRoomNMlist.get(i));
				}
			}
			OpenChat_List.setItems(newList);
		}
		else {
			OpenChat_List.setItems(oldList);
		}
		
	}

	/**
	 * 해당 오픈채팅방에 참여
	 */
	@FXML public void choiceOpenChatBtnOnAction() {
		if(!OpenChat_List.getSelectionModel().getSelectedItem().equals("") &&
				OpenChat_List.getSelectionModel().getSelectedItem() != null) {
			
			boolean chk = false;
			int idx = OpenChat_List.getSelectionModel().getSelectedIndex();
			ChatRoomVO cvo = openChatRoomVOlist.get(idx);
			ParticipantVO pvo = new ParticipantVO();
			pvo.setCr_index(cvo.getCr_index());
			pvo.setMem_mail(Session.memVO.getMem_mail());
			pvo.setPc_online(0);
			
			chk = openChatExistChk(cvo);
			if(!chk) {
				try {
					ips.insertParticipantVO(pvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Stage stage = (Stage) anchorPane.getScene().getWindow();
				stage.close();
				
				Session.Chat_Chatroom_List.getItems().add(cvo.getCr_name());
				Session.getMyChatRoomList.add(cvo);
				
				Node chatScene = null;
				try {
					chatScene = FXMLLoader.load(getClass().getResource("ChatRoomList.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Session.Messenger.getChildren().setAll(chatScene);
			}
		}
	}

	@FXML public void cancelBtnOnAction() {
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		stage.close();
	}

	@FXML public void insertOpenChatRoomBtnOnMouseClicked() {
		TextInputDialog prompt = new TextInputDialog("이름을 입력해주세요.");
		prompt.setTitle("오픈채팅방 개설");
		prompt.setHeaderText("오픈채팅방 이름을 설정해주세요."); // 출력메시지
		
		// 창을 보이고 입력한 값을 읽어오기
		Optional<String> result = prompt.showAndWait();
		String strResult = null; // 입력한 값이 저장될 변수 선언
		
		// 입력한 값이 있는지 검사(값 입력 후 'OK'버튼 눌렀는지 검사
		if(result.isPresent()) {
			strResult = result.get(); // 값 읽어오기
			
			ChatRoomVO vo = new ChatRoomVO();
			ParticipantVO vo1 = new ParticipantVO();
			int cr_index = 0;
			vo.setMem_mail(Session.memVO.getMem_mail());
			vo.setCr_name("[오픈채팅]" + strResult);
			vo.setCr_isself(0);
			vo.setCr_isopenchat(1);
			try {
				cr_index = ics.insertChatRoom(vo);
				Session.Chat_Chatroom_List.getItems().add(vo.getCr_name());
				Session.getMyChatRoomList.add(vo);
				openChatRoomVOlist.add(vo);
				OpenChat_List.getItems().add(vo.getCr_name());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			vo1.setMem_mail(Session.memVO.getMem_mail());
			vo1.setCr_index(cr_index);
			vo1.setPc_online(0);
			try {
				ips.insertParticipantVO(vo1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Stage stage = (Stage) anchorPane.getScene().getWindow();
			stage.close();
		}
	}
	
	boolean openChatExistChk(ChatRoomVO vo) {
		
		if(openChatRoomVOlist.indexOf(vo) >= 0) {
			warningAlert();
			return true;
		}
		/*List<ChatRoomVO> MyChatRoomList = Session.getMyChatRoomList;
		List<ChatRoomVO> myOpenChatRoomList = new ArrayList<>();
		for(ChatRoomVO vo1 : MyChatRoomList) {
			if(vo1.getCr_name().contains("[오픈채팅]")) {
				myOpenChatRoomList.add(vo1);
			}
		}
		
		for(ChatRoomVO vo1 : myOpenChatRoomList) {
			if(vo.getCr_index() == vo1.getCr_index()) {
				warningAlert();
				return true;
			}
		}*/
		return false;
		
	}
	
	void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText("참여할 수 없습니다.");
		alert.setContentText("이미 참여한 오픈채팅방 입니다.");
		
		alert.showAndWait();
	}

	@FXML public void searchOpenChatTextOnAction() {
		Search_OpenChat_Text.clear();
	}
	
}
