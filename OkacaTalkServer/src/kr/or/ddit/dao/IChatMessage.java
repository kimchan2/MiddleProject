package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ChatMessageVO;

public interface IChatMessage {

	public ChatMessageVO selectChatMessage(String mem_mail, int cr_index);
	
	public List<ChatMessageVO> getChatMessageByCrIndexRecent50(HashMap<String, Integer> map);
	
	public List<ChatMessageVO> getChatMessageByCrIndex(int cr_index);
	
	public Object insertChatMessageInCrIndex(ChatMessageVO vo);
	
	public int deleteChatMessage(int cm_index);
	
//	public int updateChatMessage(ChatMessageVO vo);
	
}
