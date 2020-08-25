package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ChatRoomVO;

public interface IChatRoomService extends Remote {
	
	public List<ChatRoomVO> getMyChatRoom(String mem_mail) throws RemoteException;

	public ChatRoomVO selectChatRoom(String mem_mail, int cr_index) throws RemoteException;
	
	public ChatRoomVO getChatRoom(Integer cr_index) throws RemoteException;
	
	public List<ChatRoomVO> getOpenChatRoom() throws RemoteException;
	
	public int insertChatRoom(ChatRoomVO vo) throws RemoteException;
	
	public void deleteChatRoom(int cr_index) throws RemoteException;
	
	public void updateChatRoom(ChatRoomVO vo) throws RemoteException;
	
}
