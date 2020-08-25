package kr.or.ddit.chattingClient;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kr.or.ddit.chatting.ChatClient;
import kr.or.ddit.chatting.ChatServer;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.session.Session;
import kr.or.ddit.view.chat.ChatRoomController;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProfileImgListVO;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable{

	private ChatServer server;
	private AnchorPane anchorPane;
	public Boolean newMessageAlert = false;
	public Boolean isRun;
	private ScrollPane Cm_Content_ScrollField; 
	private VBox Cm_Content_VBoxField;
	private ChatMessageVO cmvo;
	private MemberVO mvo;
	private List<MemberVO> partiMemList;
	private List<ProfileImgListVO> profileImagVOList;
	private ChatRoomController chatRoomController;
	public ChatClientImpl(ChatRoomController chatRoomController, AnchorPane anchorPane, ScrollPane Cm_Content_ScrollField, VBox Cm_Content_VBoxField, ChatRoomVO vo,
			List<MemberVO> partiMemList, List<ProfileImgListVO> profileImagVOList, MemberVO mvo) throws RemoteException, Exception {
		
		Registry reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		
		server = (ChatServer) reg.lookup("server");
		
		server.addClient(this);
		server.addChatRoom(this, vo);
		this.anchorPane = anchorPane;
		this.Cm_Content_ScrollField = Cm_Content_ScrollField;
		this.Cm_Content_VBoxField = Cm_Content_VBoxField;
		this.partiMemList = partiMemList;
		this.profileImagVOList = profileImagVOList;
		this.chatRoomController = chatRoomController;
		this.mvo = mvo;
		this.isRun = true;
		
	}

	@Override
	public void run() {
			
	       	try {
       			while(isRun) {
	       			Thread.sleep(30);
		       		if(newMessageAlert) {
		       			Platform.runLater(new Runnable() {
							@Override
							public void run() {
								chatRoomController.writeChatMessage(Cm_Content_ScrollField,
										Cm_Content_VBoxField, cmvo, partiMemList, profileImagVOList);
								//getVmax();
								System.out.println("Platform.runLater 실행 완료...");
								
								// 채팅방에 친구초대를 하면 채팅방을 다시 불러온다
								if(cmvo.getCm_content().contains("님을 초대하셨습니다.")) {
									Node chatRoomScene = null;
									try {
										chatRoomScene = new FXMLLoader(getClass().getResource("../view/chat/ChatRoom.fxml")).load();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									chatRoomController.Cr_AnchorPane.getChildren().setAll(chatRoomScene);
								}
							}
						});
						newMessageAlert = false;
					}
	       		}
       			
       			try {//스레드 종료
					server.disconnect(this);
					System.out.println("채팅 서버와 연결이 끊어졌습니다.");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							((Stage)anchorPane.getScene().getWindow()).close();
						}
					});
				} catch (RemoteException e) {
					e.printStackTrace();
				}

	       }catch(Exception e) {
	    	   e.printStackTrace();
	    	   System.out.println(e);
	       }      
	}
	
	@Override
	public void printMessage(ChatRoomVO cvo, ChatMessageVO cmvo) throws RemoteException {
		if(!this.mvo.getMem_mail().equals(cmvo.getMem_mail())) {
			this.newMessageAlert = true;
			this.cmvo = cmvo;
			System.out.println(cvo.getCr_name() + " 에서 새로운 메시지가 있습니다.");
		}
	}
	
	/*public void getVmax() {
		Cm_Content_ScrollField.setVvalue(0.0);
		Cm_Content_ScrollField.setVvalue(Cm_Content_ScrollField.getVmax());
		System.out.println(Cm_Content_ScrollField.getVmax());
	}*/
}
