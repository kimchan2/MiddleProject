package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ChatMessageImpl;
import kr.or.ddit.dao.ChatRoomImpl;
import kr.or.ddit.vo.ChatRoomVO;

public class ChatRoomService extends UnicastRemoteObject implements IChatRoomService {

	ChatRoomImpl crDao; 
	private static ChatRoomService service; 
	
	private ChatRoomService() throws RemoteException{
		crDao = ChatRoomImpl.getInstance();
	}
	
	public static ChatRoomService getInstance() throws RemoteException {
		if(service== null) {
			service = new ChatRoomService();
		}
		return service;
	}
	
	@Override
	public ChatRoomVO selectChatRoom(String mem_mail, int cr_index) {
		// TODO Auto-generated method stub
		return crDao.selectChatRoom(mem_mail, cr_index);
	}

	@Override
	public ChatRoomVO getChatRoom(Integer cr_index) {
		// TODO Auto-generated method stub
		return crDao.getChatRoom(cr_index);
	}

	@Override
	public int insertChatRoom(ChatRoomVO vo) {
		// TODO Auto-generated method stub
		return crDao.insertChatRoom(vo);
	}

	@Override
	public void deleteChatRoom(int cr_index) {
		// TODO Auto-generated method stub
		crDao.deleteChatRoom(cr_index);
	}

	@Override
	public void updateChatRoom(ChatRoomVO vo) {
		// TODO Auto-generated method stub
		crDao.updateChatRoom(vo);
	}

	@Override
	public List<ChatRoomVO> getOpenChatRoom() {
		// TODO Auto-generated method stub
		return crDao.getOpenChatRoom();
	}

	@Override
	public List<ChatRoomVO> getMyChatRoom(String mem_mail) {
		return crDao.getMyChatRoom(mem_mail);
	}

}
