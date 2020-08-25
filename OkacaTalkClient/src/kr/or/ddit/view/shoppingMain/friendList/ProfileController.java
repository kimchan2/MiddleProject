package kr.or.ddit.view.shoppingMain.friendList;

import java.awt.Font;
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
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.service.IBackgroundImgListService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.BackgroundImgListVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.control.Alert.AlertType;

public class ProfileController implements Initializable {

	@FXML ImageView Profile_Background_Img;
	@FXML ImageView Profile_Forward_Img;
	@FXML Text Profile_Nick_Text;
	@FXML TextFlow Profile_Message_Text;
	@FXML Button Profile_SelfChat;
	@FXML ImageView profile_Close_Btn;
	@FXML Button Profile_Setting_Btn;
	@FXML Button Profile_Chat;
	private Registry reg;
	IFileService ifile;
	IProfileImgListService ipil;
	IBackgroundImgListService ibil;
	private IChatRoomService ics;
	private IParticipantService ips;
	MemberVO vo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vo = Session.memVO;
		Profile_SelfChat.setVisible(true);
		Profile_Chat.setVisible(false);
		Profile_Setting_Btn.setVisible(true);
		if(Session.selectedFriendListVO != null) {
			vo = Session.selectedFriendListVO;
			Profile_SelfChat.setVisible(false);
			Profile_Chat.setVisible(true);
			Profile_Setting_Btn.setVisible(false);
		}
		lblMessage.setText(vo.getMem_message());
		lblMessage.setAlignment(Pos.CENTER);
		lblMessage.setFont(javafx.scene.text.Font.font("BOLD Italic", 15));
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ifile = (IFileService) reg.lookup("file");
			ipil = (IProfileImgListService) reg.lookup("profileImgList");
			ibil = (IBackgroundImgListService) reg.lookup("backgroundImgList");
			ics = (IChatRoomService) reg.lookup("chatRoom");
			ips = (IParticipantService) reg.lookup("participant");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		Profile_Nick_Text.setText(vo.getMem_nick());
		
		ProfileImgListVO pvo = null;
		try {
			pvo = ipil.getProfileImgListVO(vo.getMem_mail());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(pvo != null) {
			String dir = pvo.getPf_addr();//pvo.getPf_addr(); //Session.ServerIPAddress + vo.getPf_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본프로필.png");
			}
			Image image = new Image(file.toURI().toString(), 150, 150, false, false, false);
			Profile_Forward_Img.setImage(image);
		}
			
		
		BackgroundImgListVO vo2 = null;
		vo2 = ibil.getBGI(vo.getMem_mail());
		if(vo2 != null) {
			String dir = vo2.getBg_addr();
			File file = new File(dir);
			if(file.exists() == false) {
				file = new File("src/image/기본배경이미지.png");
			}
			Image image = new Image(file.toURI().toString(), 400, 500, false, false, false);
			Profile_Background_Img.setImage(image);
		}else {
			File file = new File("src/image/기본배경이미지.png");
			Image image = new Image(file.toURI().toString(), 400, 500, false, false, false);
			Profile_Background_Img.setImage(image);
		}
		ColorAdjust ca = new ColorAdjust();
		ca.setContrast(-0.5);
		Profile_Background_Img.setEffect(ca);
		
		Session.pfc = this;
		Session.selectedFriendListVO = null;
	}
	
	public void setimage(Image image) {
		Profile_Forward_Img.setImage(image);
	}

	@FXML public void profileCloseBtnOnMouseClicked() {
		Stage stage = (Stage) profile_Close_Btn.getScene().getWindow();
		stage.close();
	}

	@FXML public void ProfileSettingBtnOnMouseClicked() throws IOException {
		Stage stage = (Stage) profile_Close_Btn.getScene().getWindow();
		stage.close();
		Session.moveConf.moveConfiguration();
	}

	@FXML public void ProfileForwardImgOnMouseClicked() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileImg.fxml"));
		Parent root = loader.load();
		
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setTitle("프로필 이미지");
		stage.setScene(scene);
		stage.show();
		//컨트롤러 호출하기//KDH
		ProfileImgController pic = loader.<ProfileImgController>getController();
		pic.initData(vo);
	}

	@FXML public void ProfileSelfChatOnMouseClicked() {
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
			Session.getMyChatRoomList.add(cvo);
			try {
				currval = ics.insertChatRoom(cvo);
			} catch (RemoteException e) {
				e.printStackTrace();
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
		}
		
		Stage stage = (Stage) Profile_SelfChat.getScene().getWindow();
		stage.close();
		
		Session.currentOpenRoom.add(cvo);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../chat/ChatRoom.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage = new Stage(StageStyle.DECORATED);
		
		stage.setScene(scene);
		stage.setTitle("채팅");
		stage.setScene(scene);
		stage.show();
	}
	
	IParticipantService ipcs;
	ChatRoomVO cvo;
	@FXML Label lblMessage;
	
	@FXML public void ProfileChatOnMouseClicked() {
		cvo = new ChatRoomVO();
		cvo.setMem_mail(Session.memVO.getMem_mail());
		cvo.setCr_name(Session.memVO.getMem_nick() + ", " + vo.getMem_nick());
		cvo.setCr_isself(0);
		cvo.setCr_isopenchat(0);
		int currval = 0;
		// 채팅방별 참여자 -> shoppingMainMain 으로 옮김
//		List<List<String>> outer = new ArrayList<>(); 
//		for(ChatRoomVO vo1 : Session.getMyChatRoomList) {
//			List<String> inner = new ArrayList<>();
//			try {
//				inner = new ArrayList<>(ips.getMemMailByCrIndex(vo1.getCr_index()));
//				outer.add(inner);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//		}
		
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
				
				// 채팅방 리스트 다시 불러오기
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("../../chat/ChatRoom.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				((Stage) Profile_Chat.getScene().getWindow()).close();
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
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			Stage stage = new Stage(StageStyle.DECORATED);
			((Stage) Profile_Chat.getScene().getWindow()).close();
			stage.setScene(scene);
			stage.setTitle("채팅");
			stage.setScene(scene);
			stage.show();
		}
	}
}
