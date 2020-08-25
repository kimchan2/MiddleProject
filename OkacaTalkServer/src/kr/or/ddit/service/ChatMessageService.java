package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.ChargeCashImpl;
import kr.or.ddit.dao.ChatMessageImpl;
import kr.or.ddit.vo.ChatMessageVO;

public class ChatMessageService extends UnicastRemoteObject implements IChatMessageService {

	ChatMessageImpl cmDao; 
	private static ChatMessageService service; 
	
	private ChatMessageService() throws RemoteException{
		cmDao = ChatMessageImpl.getInstance();
	}
	
	public static ChatMessageService getInstance() throws RemoteException {
		if(service== null) {
			service = new ChatMessageService();
		}
		return service;
	}
	
	@Override
	public ChatMessageVO selectChatMessage(String mem_mail, int cr_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMessageVO> getChatMessageByCrIndex(int cr_index) {
		// TODO Auto-generated method stub
		return cmDao.getChatMessageByCrIndex(cr_index);
	}

	@Override
	public Object insertChatMessageInCrIndex(ChatMessageVO vo) {
		// TODO Auto-generated method stub
		return cmDao.insertChatMessageInCrIndex(vo);
	}

	@Override
	public int deleteChatMessage(int cm_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChatMessageVO> getChatMessageByCrIndexRecent50(HashMap<String, Integer> map) {
		return cmDao.getChatMessageByCrIndexRecent50(map);
	}

}
