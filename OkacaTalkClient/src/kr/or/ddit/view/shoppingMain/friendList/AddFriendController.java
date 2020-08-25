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
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IFriendListService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.FriendListVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddFriendController implements Initializable {

	@FXML TextField AddFriend_text;
	@FXML Button AddFriend_Btn;
	@FXML HBox AddFriend_Vbox;

	Registry reg;
	IFriendListService ifl;
	IMemberService imember;
	IProfileImgListService ipil;
	IChatRoomService ics;
	IParticipantService ips;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ifl = (IFriendListService) reg.lookup("friendList");
			imember = (IMemberService) reg.lookup("member");
			ipil = (IProfileImgListService) reg.lookup("profileImgList");
			ics = (IChatRoomService) reg.lookup("chatRoom");
			ips = (IParticipantService) reg.lookup("participant");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	void print() {
		Label label = new Label();
		label.setText("일치하는 아이디가 없습니다.");
		
		AddFriend_Vbox.getChildren().add(label);
	}
	
	MemberVO vo = null;
	Button btn = new Button();
	void SearchFriend() {
		String id = AddFriend_text.getText();
		if(id.equals("") || id == null) {
			print();
		}
		else {
			try {
				vo = imember.idSearch(AddFriend_text.getText());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if(vo == null) {
				print();
			}
			else {
				ProfileImgListVO pilvo = new ProfileImgListVO();
				ImageView iv = new ImageView();
				try {
					pilvo = ipil.getProfileImgListVO(vo.getMem_mail());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				if(pilvo != null) {
					String dir = pilvo.getPf_addr(); //Session.ServerIPAddress + vo.getPf_addr();
					File file = new File(dir);
					if(file.exists() == false) {
						file = new File("src/image/기본프로필.png");
					}
					Image image = new Image(file.toURI().toString(), 80, 80, false, false, false);
					iv.setImage(image);
				}else {
					File file = new File("src/image/기본프로필.png");
					Image image = new Image(file.toURI().toString(), 80, 80, false, false, false);
					iv.setImage(image);
				}
				AddFriend_Vbox.getChildren().add(iv);
				Label label = new Label();
				label.setPrefWidth(120);
				label.setText(vo.getMem_mail());
				AddFriend_Vbox.getChildren().add(label);
				
				if(vo.getMem_mail().equals(Session.memVO.getMem_mail()))
					btn = IDisself();
				else if(IDFindatFriendList(vo.getMem_mail()))
					btn = IDisallocated();
				else
					btn = IDisnull();
				btn.setPrefWidth(80);
				btn.setPrefHeight(80);
				AddFriend_Vbox.getChildren().add(btn);
				
			}
		}
	}
	Boolean IDFindatFriendList(String mem_mail) {
		for(String fr_mail : Session.friendsMailList) {
			if(fr_mail.equals(mem_mail))
				return true;
		}
		return false;
	}
	
	Button IDisself() {
		Button btn1 = new Button();
		
		btn1.setText("나와의채팅");
		btn1.setOnAction(e->{
			ChatRoomVO cvo = null;
			for(ChatRoomVO cvo1 : Session.getMyChatRoomList) {
				if(cvo1.getCr_isself() == 1) {
					cvo = cvo1;
				}
			}
			int currval = 0;
			if(cvo == null) {
				cvo = new ChatRoomVO();
				cvo.setMem_mail(vo.getMem_mail());
				cvo.setCr_name(vo.getMem_nick());
				cvo.setCr_isself(1);
				cvo.setCr_isopenchat(0);
				
				try {
					currval = ics.insertChatRoom(cvo);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(currval > 0) {
					ParticipantVO pvo = new ParticipantVO();
					pvo.setCr_index(currval);
					pvo.setMem_mail(vo.getMem_mail());
					pvo.setPc_online(0);
					try {
						ips.insertParticipantVO(pvo);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			Stage stage = (Stage) btn1.getScene().getWindow();
			stage.close();
			
			Session.currentOpenRoom.add(cvo);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../chat/ChatRoom.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene scene = new Scene(root);
			stage = new Stage(StageStyle.DECORATED);
			((Stage) AddFriend_Vbox.getScene().getWindow()).close();
			stage.setScene(scene);
			stage.setTitle("채팅");
			stage.setScene(scene);
			stage.show();
		});
		return btn1;
	}
	ChatRoomVO cvo;
	Button IDisallocated() {
		Button btn1 = new Button();
//		btn1.setPrefWidth(100);
//		btn1.setPrefHeight(100);
		btn1.setText("채팅하기");
		btn1.setOnAction(e->{
			cvo = new ChatRoomVO();
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
					pvo1.setMem_mail(vo.getMem_mail());
					pvo1.setPc_online(0);
					try {
						ips.insertParticipantVO(pvo1);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					Session.outer.clear();
					try {
						Session.getMyChatRoomList = ics.getMyChatRoom(Session.memVO.getMem_mail());
						for(ChatRoomVO vo1 : Session.getMyChatRoomList) {
							List<String> inner = new ArrayList<>();
							try {
								inner = new ArrayList<>(ips.getMemMailByCrIndex(vo1.getCr_index()));
								Session.outer.add(inner);
							} catch (RemoteException e1) {
								e1.printStackTrace();
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
					
					// 채팅방 리스트 다시 불러오기
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("../../chat/ChatRoom.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					((Stage) AddFriend_Vbox.getScene().getWindow()).close();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.setTitle("채팅");
					stage.show();
				}
				
			}
			
			// 존재하면 기존에 존재하는 채팅방을 띄움
			else {
				ChatRoomVO cvo = Session.getMyChatRoomList.get(index);
				
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
				((Stage) AddFriend_Vbox.getScene().getWindow()).close();
				stage.setScene(scene);
				stage.setTitle("채팅");
				stage.setScene(scene);
				stage.show();
			}
		});
		
		return btn1;
	}
	Button IDisnull() {
		Button btn1 = new Button();
//		btn1.setPrefWidth(100);
//		btn1.setPrefHeight(100);
		btn1.setText("친구 추가");
		btn1.setOnAction(e->{
			FriendListVO flvo = new FriendListVO();
			flvo.setMem_mail(Session.memVO.getMem_mail());
			flvo.setFr_mail(vo.getMem_mail());
			Object obj = null;
			try {
				obj = ifl.insertFriendListVO(flvo);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if(obj == null) {
				try {
					Session.moveConf.friendListBtnOnAction();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				AddFriend_Vbox.getChildren().clear();
				SearchFriend();
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("친구 추가 실패");
				alert.setContentText("친구추가를 할 수 없습니다.");
				alert.show();
			}
		});
		
		return btn1;
	}
	
	@FXML public void AddFriendBtnOnMouseClick(MouseEvent event) {
		AddFriend_Vbox.getChildren().clear();
		SearchFriend();
	}

	@FXML public void AddFriendtextOnKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			AddFriend_Vbox.getChildren().clear();
			SearchFriend();
		}
	}

}
