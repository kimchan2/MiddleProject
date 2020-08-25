package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ChatMessageVO;

public interface IChatMessageService extends Remote {

	public ChatMessageVO selectChatMessage(String mem_mail, int cr_index) throws RemoteException;
	
	public List<ChatMessageVO> getChatMessageByCrIndexRecent50(HashMap<String, Integer> map) throws RemoteException;
	
	public List<ChatMessageVO> getChatMessageByCrIndex(int cr_index) throws RemoteException;
	
	public Object insertChatMessageInCrIndex(ChatMessageVO vo) throws RemoteException;
	
	public int deleteChatMessage(int cm_index) throws RemoteException;
	
//	public int updateChatMessage(ChatMessageVO vo);
	
}
