package kr.or.ddit.view.chat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.regex.Pattern;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.chatting.ChatServer;
import kr.or.ddit.chattingClient.ChatClientImpl;
import kr.or.ddit.service.IChatMessageService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IFileListService;
import kr.or.ddit.service.IFileService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.service.IProfileImgListService;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.FileListVO;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ParticipantVO;
import kr.or.ddit.vo.ProfileImgListVO;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.controls.JFXDrawer;

public class ChatRoomController implements Initializable{

	@FXML public AnchorPane Cr_AnchorPane;
	@FXML TextArea Write_Content_Field;
	@FXML Button Send_File_Btn;
	@FXML Button Send_Btn;
	@FXML TextField Search_In_ChatRoom;
	@FXML ScrollPane Cm_Content_ScrollField;
	@FXML VBox Cm_Content_VBoxField;
	@FXML Text Cr_Name;
	@FXML ImageView Add_Participant_Btn;
	
	// 햄버거 메뉴
	@FXML JFXHamburger ChatRoom_Menu_Btn;
	@FXML JFXDrawer Menu_Drawer;
	
	HamburgerBackArrowBasicTransition hbabt;
	
	private Registry reg;
	private IChatMessageService icms;
	private IParticipantService ips;
	private IMemberService ims;
	private IProfileImgListService ipils;
	private ChatServer server;
	private IFileService ifs;
	private IFileListService ifls;
	private IChatRoomService icrs;
	
	private MemberVO mvo = Session.memVO;
	public ChatRoomVO crvo;
	private List<ChatMessageVO> cmvoList = new ArrayList<>();
	private List<HBox> messages = new ArrayList<>();
	public List<ParticipantVO> partiList = new ArrayList<>();
	public List<MemberVO> partiMemList = new ArrayList<>();
	public List<ProfileImgListVO> profileImagVOList = new ArrayList<>(); 
	
	// 페이징 처리 관련
	ArrayList<ChatMessageVO> recentList = new ArrayList<>();
	ArrayList<ChatMessageVO> previousList = new ArrayList<>();
	HashMap<String, Integer> crIdxAndPage = new HashMap<>();
	int cnt = 0;
	
	public Thread client = null;
	public ChatClientImpl cci;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Cm_Content_ScrollField.setVvalue(Cm_Content_ScrollField.getVmax());
		crvo = Session.currentOpenRoom.get(Session.currentOpenRoom.size()-1);
		Cr_Name.setText(crvo.getCr_name());
		Write_Content_Field.setWrapText(true);
		Cm_Content_VBoxField.getStyleClass().add("chatbox");
		Cm_Content_ScrollField.hbarPolicyProperty().set(ScrollBarPolicy.NEVER); // 가로스크롤 사용안함

		// 햄버거 메뉴 설정
		hbabt = new HamburgerBackArrowBasicTransition(ChatRoom_Menu_Btn);
		hbabt.setRate(-1);
		
		// 친구추가 버튼에 cr_index 툴팁 추가
		Tooltip tt = new Tooltip();
		tt.setText(String.valueOf(crvo.getCr_index()));
		Tooltip.install(Add_Participant_Btn, tt);

		// 세션의 currentOpenRoomAnchorPane에 저장
		Session.currentOpenRoomAnchorPane.add(Cr_AnchorPane);
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			icms = (IChatMessageService)reg.lookup("chatMessage");
			ips = (IParticipantService)reg.lookup("participant");
			ims = (IMemberService)reg.lookup("member");
			ipils = (IProfileImgListService)reg.lookup("profileImgList");
			ifs = (IFileService)reg.lookup("file");
			ifls = (IFileListService)reg.lookup("fileList");
			server = (ChatServer)reg.lookup("server");
			icrs = (IChatRoomService) reg.lookup("chatRoom");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		// 채팅방 참여인원 정보 가져오기
		try {
			partiList = ips.getParticipantVO(crvo.getCr_index());
			for(ParticipantVO ippvo : partiList) {
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
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		// 채팅 메시지 정렬
		try {
			// 메시지 전부 불러오기
			// cmvoList = icms.getChatMessageByCrIndex(crvo.getCr_index());

			// 최근 50개 메시지만 불러오기
			crIdxAndPage = new HashMap<>();
			crIdxAndPage.put("cr_index", crvo.getCr_index());
			crIdxAndPage.put("page", 1);
			
			cmvoList = icms.getChatMessageByCrIndexRecent50(crIdxAndPage);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < cmvoList.size(); i++) {
			writeChatMessage(Cm_Content_ScrollField, Cm_Content_VBoxField, cmvoList.get(i), partiMemList, profileImagVOList);
		}
		
		/**
		 * 스크롤이 최상단으로 올라가서 메시지를 다시 불러올 때 이벤트
		 */
		
		Cm_Content_ScrollField.vvalueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)->{
			if(newValue.doubleValue() == 0) {
				// 현재 메시지 리스트를 저장
				recentList = (ArrayList<ChatMessageVO>) cmvoList;
				
				// 페이지 인덱스를 1 증가시킴
				crIdxAndPage.replace("page", crIdxAndPage.get("page")+1);
				
				// 증가된 페이지리스트를 가지고 이전메시지를 가져옴
				try {
					previousList = (ArrayList<ChatMessageVO>) icms.getChatMessageByCrIndexRecent50(crIdxAndPage);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// 메시지를 VBox의 상단부터 넣어 정렬해줌
				for(int i = (previousList.size() - 1); i >= 0; i--) {
					loadingChatMessage(Cm_Content_ScrollField, Cm_Content_VBoxField, previousList.get(i), partiMemList, profileImagVOList);
				}
				
				// cmvoList 업데이트
				List<ChatMessageVO> joined = new ArrayList<>();
				joined.addAll(previousList);
				joined.addAll(recentList);
				cmvoList = joined;
				
				cnt++;
				Cm_Content_ScrollField.setVvalue(1 / (cnt + 1));
			}
		});
		
		// 내부 쓰레드를 만든다
		// 메세지를 받을때 까지 기다리다가
		// 서버에서 받으면 띄움
		client = null;
		cci = null;
		try {
			cci = new ChatClientImpl(this, Cr_AnchorPane, Cm_Content_ScrollField,
   					Cm_Content_VBoxField, crvo, partiMemList, profileImagVOList, mvo);
			client = new Thread(cci);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("쓰레드 생성 실패.");
		}
		
		Session.chatRoomMap.put(crvo.getCr_index(), this);

	}
	
	/**
	 * 파일 전송버튼을 눌렀을 때
	 * @throws IOException 
	 */
	@FXML public void sendFileBtnOnAction() throws IOException {
		
		FileChooser fc = new FileChooser();
		String saveFileDir = null;
		fc.setTitle("전송할 파일 선택");
		fc.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Files", "*.jpg", "*.png", "*.bmp", "*.mp4", "*.avi",
					"*.txt", "*.zip", "*.hwp")
		);
		
		FileVO fvo = new FileVO();
		FileListVO flvo = new FileListVO();
		File file = fc.showOpenDialog(new Stage());
		
		FileInputStream fin = new FileInputStream(file);
		int len = (int)file.length();
		byte[] data = new byte[len];
		fin.read(data);
		fvo.setFileData(data);
		fvo.setFileName(file.getName());
		fvo.setMemVO(mvo);
		
		saveFileDir = ifs.setFile(fvo, crvo.getCr_index());
		
		flvo.setF_addr(saveFileDir);
		flvo.setF_name(file.getName());
		flvo.setF_type( file.getName().substring( file.getName().lastIndexOf(".")+1, file.getName().length() ) );
		
		HashMap<String, Object> map = new HashMap<>();
		
		if(saveFileDir != null) {
			// 채팅 메시지 vo 만들기
			ChatMessageVO cmvo = new ChatMessageVO();
			cmvo.setMem_mail(mvo.getMem_mail());
			cmvo.setCr_index(crvo.getCr_index());
			cmvo.setCm_content("파일을 전송하였습니다.");
			Write_Content_Field.clear();
			
			cmvo.setCm_ischeck(Session.memVO.getMem_mail());
			
			map.put("cmvo", cmvo);
			map.put("flvo", flvo);
			
			ChatMessageVO res = new ChatMessageVO();
			res = ifls.insertFileListVO(map);
			cmvo.setCm_index(res.getCm_index());
			cmvo.setCm_content(res.getCm_content());
			
			writeChatMessage(Cm_Content_ScrollField, Cm_Content_VBoxField, cmvo, partiMemList, profileImagVOList);
			server.say(cmvo);
		}
		
	}
	
	/**
	 * 메시지 전송버튼을 눌렀을 때
	 */
	@FXML public void sendBtnOnAction() {
		ChatMessageVO cmvo = new ChatMessageVO();
		if(!Write_Content_Field.getText().equals("") && Write_Content_Field.getText() != null) {
			
			cmvo.setMem_mail(mvo.getMem_mail());
			cmvo.setCr_index(crvo.getCr_index());
			cmvo.setCm_content(Write_Content_Field.getText());
			Write_Content_Field.clear();
			
			cmvo.setCm_ischeck(Session.memVO.getMem_mail());
			
			try {
				icms.insertChatMessageInCrIndex(cmvo);
				writeChatMessage(Cm_Content_ScrollField, Cm_Content_VBoxField, cmvo, partiMemList, profileImagVOList);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				server.say(cmvo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("채팅 서버 에러");
			}
		}
		
		
	}

	@FXML public void writeContentFieldOnKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			sendBtnOnAction();
			Write_Content_Field.clear();
			event.consume();
		}
		/*if(event.getCode() == KeyCode.ENTER && event.getCode() == KeyCode.SHIFT) {
			Write_Content_Field.setText(Write_Content_Field.getText() + "\r");
		}*/
	}
	
	/**
	 * 채팅 메시지를 HBox에 정렬
	 * @param Cm_Content_ScrollField
	 * @param Cm_Content_VBoxField
	 * @param cmvo
	 * @param partiMemList
	 * @param profileImagVOList
	 */
	public void writeChatMessage(ScrollPane Cm_Content_ScrollField, VBox Cm_Content_VBoxField, ChatMessageVO cmvo,
			List<MemberVO> partiMemList, List<ProfileImgListVO> profileImagVOList) {
		String msgOwner = null;
		ImageView profileImg = new ImageView();
//		MemberVO selecteduser = new MemberVO();
		for(int j = 0; j < partiMemList.size(); j++) {
			// 해당 메시지의 유저 닉네임 가져오기
			if(cmvo.getMem_mail().equals(partiMemList.get(j).getMem_mail())) {
				msgOwner = partiMemList.get(j).getMem_nick();
				// 해당 메시지의 유저 사진 가져오기
				for(int k = 0; k < profileImagVOList.size(); k++) {
					if(profileImagVOList.get(k).getMem_mail().equals(partiMemList.get(j).getMem_mail())) {
//						selecteduser = partiMemList.get(j);
						File imgFile = new File(profileImagVOList.get(k).getPf_addr());
						if(imgFile.exists() == false) {
							imgFile = new File("src/image/기본프로필.png");
							Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
							profileImg.setImage(img);
						}
						else {
							Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
							profileImg.setImage(img);
						}
						break;
					}
					else {
						File imgFile = new File("src/image/기본프로필.png");
						Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
						profileImg.setImage(img);
					}
				}
				break;
			}
		}
		
//		Session.selectedFriendListVO = selecteduser;
//		profileImg.setOnMouseClicked(e->{
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("../shoppingMain/friendList/Profile.fxml"));
//			Parent root = null;
//			Stage stage = new Stage(StageStyle.UNDECORATED);
//			stage.initModality(Modality.APPLICATION_MODAL);
//			
//			try {
//				root = (Parent)loader.load();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			stage.setTitle("프로필화면");
//			stage.setScene(new Scene(root));
//			stage.show();
//		});
		
		HBox outer = new HBox();
		VBox inner1 = new VBox();
		VBox inner2 = new VBox();
		Label innerName = new Label();
		TextArea innerText = new TextArea();
		outer.setMaxWidth(409);
		HBox.setMargin(innerText, new Insets(10, 0, 10, 0));
		
		innerText.setWrapText(true);
		innerText.setMaxWidth(200);
		innerText.setEditable(false);
		
		// 해당 메시지가 파일을 전송한 메시지인지 확인
		FileListVO flvo = new FileListVO();
		try {
			flvo = ifls.getFileBYcm_index(cmvo.getCm_index());
			System.out.println(flvo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 다운로드 버튼 생성
		ImageView downloadImg = new ImageView();
		if(flvo != null) {
			Tooltip title = new Tooltip(flvo.getF_name());
			File imgFile = new File("src/image/다운로드.png");
			Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
			downloadImg.setImage(img);
			Tooltip.install(downloadImg, title);
			
			downloadImg.setOnMouseClicked(e -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("확인");
				alert.setHeaderText("파일 다운로드");
				alert.setContentText("다운로드 하시겠습니까?");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					// 파일다운받아서 C:\오카카톡 에 저장하기
					try {
						FileVO vo = ifs.getFile("C:/Filedata/ChatRoom" + "/" + crvo.getCr_index() + "/"
								+ cmvo.getMem_mail() + "/" + title.getText());
						FileOutputStream fout = null;
						try {
							FileChooser fc = new FileChooser();
							fc.setTitle("저장할 위치 선택");
							fc.setInitialFileName(title.getText());
							File dir = new File("C:/users/" + System.getProperty("user.name") + "/Documents/오카카톡 받은 파일");
							if(!dir.exists()) {
								dir.mkdirs();
							}
							File initialDirectory = new File(dir.toURI());
							fc.setInitialDirectory(initialDirectory);
							File file = fc.showSaveDialog(new Stage());
							fout = new FileOutputStream(file.toString());
							fout.write(vo.getFileData());
							fout.close();
							if(file != null) {
								alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("확인");
								alert.setHeaderText("다운로드 완료");
								alert.setContentText("선택한 파일의 다운로드가 완료되었습니다.");
								alert.showAndWait();
							}
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (RemoteException e1) { 
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					alert.close();
				}
			});
		}
		if(cmvo.getMem_mail().equals(mvo.getMem_mail())) {
			int cnt = cmvo.getCm_ischeck().split(",").length;
			//outer.getChildren().add(new Text("[" + (partiMemList.size() - cnt) + "] "));
			
			outer.getChildren().add(innerText);
			outer.getChildren().add(downloadImg);
			outer.setAlignment(Pos.CENTER_RIGHT);
			innerText.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			char[] temp = cmvo.getCm_content().toCharArray();
			String content = "";
			int height = 24;
			double length = 0;
			for(char a : temp) {
				content += a;
				String str = a + "";
				if(Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s]*$", str))
					length += 13;
				else if(Pattern.matches("^[0-9]*$", str)) {
					length += 8.333;
				}
				else {
					length += 8.333;
				}
				if(length > 190) {
					height += 16;
					length = 0;
					content += "\n";
				}
			}
			innerText.setPrefHeight(height);
			innerText.setMinHeight(height);
			innerText.setMaxHeight(height);
			innerText.setText(content);
			messages.add(outer);
			
		}
		else {
			innerText.setMaxWidth(200);
			inner1.getChildren().add(profileImg);
			outer.getChildren().add(inner1);
			outer.getChildren().get(0).setLayoutY(0);
			outer.getChildren().add(inner2);
			outer.getChildren().add(downloadImg);
			outer.setAlignment(Pos.CENTER_LEFT);
			innerText.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			
			char[] temp = cmvo.getCm_content().toCharArray();
			String content = "";
			int height = 24;
			double length = 0;
			for(char a : temp) {
				content += a;
				String str = a + "";
				if(Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s]*$", str))
					length += 13;
				else if(Pattern.matches("^[0-9]*$", str)) {
					length += 8.333;
				}
				else {
					length += 8.333;
				}
				if(length > 190) {
					height += 16;
					length = 0;
					content += "\n";
				}
			}
			innerText.setPrefHeight(height);
			innerText.setMinHeight(height);
			innerText.setMaxHeight(height);
			innerName.setText(msgOwner);
//			inner.setText(msgOwner + " : " + content);
			innerText.setText(content);
			inner2.getChildren().add(innerName);
			inner2.getChildren().add(innerText);
			int cnt = cmvo.getCm_ischeck().split(",").length;
			//outer.getChildren().add(new Text("[" + (partiMemList.size() - cnt) + "] "));
			messages.add(outer);
			
		}
		Cm_Content_VBoxField.getChildren().add(outer);
		// 채팅 추가할 때마다 스크롤 내림
		Cm_Content_VBoxField.heightProperty().addListener(obervable -> Cm_Content_ScrollField.setVvalue(1.0));
		
	}
	
	/**
	 * 이전 메시지를 로딩할 때
	 * @param Cm_Content_ScrollField
	 * @param Cm_Content_VBoxField
	 * @param cmvo
	 * @param partiMemList
	 * @param profileImagVOList
	 */
	public void loadingChatMessage(ScrollPane Cm_Content_ScrollField, VBox Cm_Content_VBoxField, ChatMessageVO cmvo,
			List<MemberVO> partiMemList, List<ProfileImgListVO> profileImagVOList) {
		String msgOwner = null;
		ImageView profileImg = new ImageView();
		for(int j = 0; j < partiMemList.size(); j++) {
			// 해당 메시지의 유저 닉네임 가져오기
			if(cmvo.getMem_mail().equals(partiMemList.get(j).getMem_mail())) {
				msgOwner = partiMemList.get(j).getMem_nick();

				// 해당 메시지의 유저 사진 가져오기
				for(int k = 0; k < profileImagVOList.size(); k++) {
					if(profileImagVOList.get(k).getMem_mail().equals(partiMemList.get(j).getMem_mail())) {
						File imgFile = new File(profileImagVOList.get(k).getPf_addr());
						if(imgFile.exists() == false) {
							imgFile = new File("src/image/기본프로필.png");
							Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
							profileImg.setImage(img);
						}
						else {
							Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
							profileImg.setImage(img);
						}
						break;
					}
					else {
						File imgFile = new File("src/image/기본프로필.png");
						Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
						profileImg.setImage(img);
					}
				}
				break;
			}
		}
		
		HBox outer = new HBox();
		VBox inner1 = new VBox();
		VBox inner2 = new VBox();
		Label innerName = new Label();
		TextArea innerText = new TextArea();
		outer.setMaxWidth(409);
		HBox.setMargin(innerText, new Insets(10, 0, 10, 0));
		
		innerText.setWrapText(true);
		innerText.setMaxWidth(200);
		innerText.setEditable(false);
		
		// 해당 메시지가 파일을 전송한 메시지인지 확인
		FileListVO flvo = new FileListVO();
		try {
			flvo = ifls.getFileBYcm_index(cmvo.getCm_index());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 다운로드 버튼 생성
		ImageView downloadImg = new ImageView();
		if(flvo != null) {
			Tooltip title = new Tooltip(flvo.getF_name());
			File imgFile = new File("src/image/다운로드.png");
			Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
			downloadImg.setImage(img);
			Tooltip.install(downloadImg, title);
			
			downloadImg.setOnMouseClicked(e -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("확인");
				alert.setHeaderText("파일 다운로드");
				alert.setContentText("다운로드 하시겠습니까?");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					// 파일다운받아서 C:\오카카톡 에 저장하기
					try {
						FileVO vo = ifs.getFile("C:/Filedata/ChatRoom" + "/" + crvo.getCr_index() + "/"
								+ cmvo.getMem_mail() + "/" + title.getText());
						FileOutputStream fout = null;
						try {
							FileChooser fc = new FileChooser();
							fc.setTitle("저장할 위치 선택");
							fc.setInitialFileName(title.getText());
							File dir = new File("C:/users/" + System.getProperty("user.name") + "/Documents/오카카톡 받은 파일");
							if(!dir.exists()) {
								dir.mkdirs();
							}
							File initialDirectory = new File(dir.toURI());
							fc.setInitialDirectory(initialDirectory);
							File file = fc.showSaveDialog(new Stage());
							fout = new FileOutputStream(file.toString());
							fout.write(vo.getFileData());
							fout.close();
							if(file != null) {
								alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("확인");
								alert.setHeaderText("다운로드 완료");
								alert.setContentText("선택한 파일의 다운로드가 완료되었습니다.");
								alert.showAndWait();
							}
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (RemoteException e1) { 
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					alert.close();
				}
			});
		}
		if(cmvo.getMem_mail().equals(mvo.getMem_mail())) {
			int cnt = cmvo.getCm_ischeck().split(",").length;
			//outer.getChildren().add(new Text("[" + (partiMemList.size() - cnt) + "] "));
			
			outer.getChildren().add(innerText);
			outer.getChildren().add(downloadImg);
			outer.setAlignment(Pos.CENTER_RIGHT);
			innerText.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			char[] temp = cmvo.getCm_content().toCharArray();
			String content = "";
			int height = 24;
			double length = 0;
			for(char a : temp) {
				content += a;
				String str = a + "";
				if(Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s]*$", str))
					length += 13;
				else if(Pattern.matches("^[0-9]*$", str)) {
					length += 8.333;
				}
				else {
					length += 8.333;
				}
				if(length > 190) {
					height += 16;
					length = 0;
					content += "\n";
				}
			}
			innerText.setPrefHeight(height);
			innerText.setMinHeight(height);
			innerText.setMaxHeight(height);
			innerText.setText(content);
			messages.add(outer);
			
		}
		else {
			innerText.setMaxWidth(200);
			inner1.getChildren().add(profileImg);
			outer.getChildren().add(inner1);
			outer.getChildren().get(0).setLayoutY(0);
			outer.getChildren().add(inner2);
			outer.getChildren().add(downloadImg);
			outer.setAlignment(Pos.CENTER_LEFT);
			innerText.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			
			char[] temp = cmvo.getCm_content().toCharArray();
			String content = "";
			int height = 24;
			double length = 0;
			for(char a : temp) {
				content += a;
				String str = a + "";
				if(Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s]*$", str))
					length += 13;
				else if(Pattern.matches("^[0-9]*$", str)) {
					length += 8.333;
				}
				else {
					length += 8.333;
				}
				if(length > 190) {
					height += 16;
					length = 0;
					content += "\n";
				}
			}
			innerText.setPrefHeight(height);
			innerText.setMinHeight(height);
			innerText.setMaxHeight(height);
			innerName.setText(msgOwner);
//			inner.setText(msgOwner + " : " + content);
			innerText.setText(content);
			inner2.getChildren().add(innerName);
			inner2.getChildren().add(innerText);
			int cnt = cmvo.getCm_ischeck().split(",").length;
//			outer.getChildren().add(new Text("[" + (partiMemList.size() - cnt) + "] "));
			messages.add(outer);
			
		}
		Cm_Content_VBoxField.getChildren().add(0, outer);
	}

	@FXML public void addParticipantBtnOnMouseClicked() {
		// 세션에 친구추가 버튼을 클릭한 채팅방의 crvo정보 저장
		Session.seletedCrvoForAddFriendInChatRoom = crvo;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
		Parent root = null;
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		try {
			root = (Parent)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stage.setTitle("유저 선택");
		stage.setScene(new Scene(root));
		stage.show();
		
	}
	public boolean isTitleChanged = false;
	@FXML public void chatRoomMenuBtnOnMouseClicked() {
		hbabt.setRate(hbabt.getRate() * -1);
		hbabt.play();
		Session.seletedCrvoForHamburgerMenu = crvo;
		VBox vbox = new VBox();
		Menu_Drawer.setSidePane(vbox);
		// vbox.setLayoutY(100);
		// 프로필 이미지 만들고
		// 닉네임
		Button btn = new Button("채팅방 이름 수정");
		btn.setPadding(new Insets(10, 10, 10 ,10));
		vbox.getChildren().add(btn);
		
		// 채팅방 이름 수정 버튼에 액션 주기
		btn.setOnAction(e->{
			TextInputDialog prompt = new TextInputDialog("수정하고 싶은 채팅방 이름을 입력해주세요.");
			prompt.setTitle("채팅방 이름 수정.");
			prompt.setHeaderText("채팅방 이름을 설정해주세요."); // 출력메시지
			
			// 창을 보이고 입력한 값을 읽어오기
			Optional<String> result = prompt.showAndWait();
			String strResult = null; // 입력한 값이 저장될 변수 선언
			ChatRoomVO vo = new ChatRoomVO();
			vo.setCr_index(crvo.getCr_index());
			// 입력한 값이 있는지 검사(값 입력 후 'OK'버튼 눌렀는지 검사
			if(result.isPresent()) {
				strResult = result.get(); // 값 읽어오기
				vo.setCr_name(strResult);
				try {
					isTitleChanged = true;
					icrs.updateChatRoom(vo);
					System.out.println("채팅방 이름 수정 완료");
					Cr_Name.setText(strResult);
					
					// 세션에 ChatRoomVO 정보 업데이트
					Session.getMyChatRoomList = icrs.getMyChatRoom(Session.memVO.getMem_mail());
					// 메신저에 채팅룸 리스트를 다시 로딩
					Node root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("../shoppingMain/chatRoom/ChatRoomList.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Session.Messenger.getChildren().addAll(root);
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		for(int i = 0; i < partiList.size(); i++) {
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(10, 0, 10, 0));
			ImageView profileImg = new ImageView();
			File imgFile = new File(profileImagVOList.get(i).getPf_addr());
			if(imgFile.exists() == false) {
				imgFile = new File("src/image/기본프로필.png");
				Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
				profileImg.setImage(img);
			}
			else {
				Image img = new Image(imgFile.toURI().toString(), 30, 30, false, false, false);
				profileImg.setImage(img);
			}
			Text nick = new Text(partiMemList.get(i).getMem_nick());
			
			hbox.getChildren().addAll(profileImg, nick);
			vbox.getChildren().add(hbox);
		}
		
		if(Menu_Drawer.isOpened()) {
			Menu_Drawer.close();
			vbox.getChildren().clear();
			System.out.println("햄버거 메뉴 닫힘");
		} else {
			Menu_Drawer.open();
			System.out.println("햄버거 메뉴 열림");
		}
		
	}
	
	public void setCrvo(ChatRoomVO crvo) {
		this.crvo = crvo;
	}
}
