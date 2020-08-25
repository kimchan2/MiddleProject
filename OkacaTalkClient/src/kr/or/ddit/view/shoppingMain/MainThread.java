package kr.or.ddit.view.shoppingMain;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import kr.or.ddit.service.IChatMessageService;
import kr.or.ddit.service.IChatRoomService;
import kr.or.ddit.service.IParticipantService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.ChatRoomVO;

public class MainThread extends UnicastRemoteObject implements Runnable{
	Registry reg;
	IChatRoomService icrs;
	IParticipantService ipcs;
	IChatMessageService icms;
	public static boolean brun = true;
	
	protected MainThread() throws RemoteException {

		reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		try {
			icrs = (IChatRoomService) reg.lookup("chatRoom");
			ipcs = (IParticipantService) reg.lookup("participant");
			icms = (IChatMessageService) reg.lookup("chatMessage");
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(brun) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				List<ChatRoomVO> list = (List<ChatRoomVO>)icrs.getMyChatRoom(Session.memVO.getMem_mail());
				if(Session.getMyChatRoomList.size() != list.size()) {
					Session.getMyChatRoomList = list;
					if(Session.crlc != null) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
//								try {
									Session.outer.clear();
//									Session.getMyChatRoomList = icrs.getMyChatRoom(Session.memVO.getMem_mail());
									for(ChatRoomVO vo1 : Session.getMyChatRoomList) {
										List<String> inner = new ArrayList<>();
										try {
											inner = new ArrayList<>(ipcs.getMemMailByCrIndex(vo1.getCr_index()));
											Session.outer.add(inner);
										} catch (RemoteException e) {
											e.printStackTrace();
										}
									}
//								} catch (RemoteException e1) {
//									e1.printStackTrace();
//								}
								Session.crlc.chatroomLoad();
							}
						});
					}
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NullPointerException e1) {}
		}
	}

}
