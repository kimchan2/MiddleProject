package kr.or.ddit.chatting;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.ChatRoomVO;

public interface ChatServer extends Remote {

	public void addChatRoom(ChatClient client, ChatRoomVO cvo) throws RemoteException;
	
	public void addClient(ChatClient client) throws RemoteException;

	public void disconnect(ChatClient client) throws RemoteException;

	public void say(ChatMessageVO cmvo) throws RemoteException;

}
