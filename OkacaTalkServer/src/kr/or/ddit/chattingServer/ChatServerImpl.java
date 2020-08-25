package kr.or.ddit.chattingServer;

import java.rmi.*;
import java.rmi.server.*;

import java.util.*;

import kr.or.ddit.chatting.ChatClient;
import kr.or.ddit.chatting.ChatServer;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
	
	List<ChatClient> clientList = new ArrayList<>();
	List<ChatRoomVO> clientRoomList = new ArrayList<>();
	
	private static ChatServerImpl csi;
	private ChatServerImpl() throws RemoteException{} 

	public static ChatServer getInstance() {
		if(csi == null) {
			try {
				csi = new ChatServerImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return csi;
	}
	
	@Override
	public void addChatRoom(ChatClient client, ChatRoomVO cvo) throws RemoteException{
		if(clientList.indexOf(client) >= 0) {
			clientRoomList.add(cvo);
		}
	}
	
	@Override
	public void addClient(ChatClient client) throws RemoteException{
		clientList.add(client);
		
		// 메시지를 보낸다 -> 서버는 채팅룸 리스트를 뒤져 열려있는 채팅룸이 있으면 메시지를 보낸다
	}
	
	@Override
	public void disconnect(ChatClient client) throws RemoteException{
		int i = clientList.indexOf(client);
    	if (i >= 0){
    		clientList.remove(i);
    		clientRoomList.remove(i);
    	}else {
    		System.out.println("No such a client in Server! ");
    	}
    }
	
	@Override
	public void say(ChatMessageVO cmvo) throws RemoteException {
		
    	for(int i = 0; i < clientRoomList.size(); i++){
    		if(clientRoomList.get(i).getCr_index() == cmvo.getCr_index()) { // 같은 방에
    			   clientList.get(i).printMessage(clientRoomList.get(i), cmvo);
    		}
    	}
    }
}
