package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.ChatRoomVO;

public interface IChatRoom {
	
	public List<ChatRoomVO> getMyChatRoom(String mem_mail);
	
	public ChatRoomVO selectChatRoom(String mem_mail, int cr_index);
	
	public ChatRoomVO getChatRoom(Integer cr_index);
	
	public List<ChatRoomVO> getOpenChatRoom();
	
	public int insertChatRoom(ChatRoomVO vo);
	
	public void deleteChatRoom(int cr_index);
	
	public void updateChatRoom(ChatRoomVO vo);
	
}
