package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ChatMessageVO;

public class ChatMessageImpl implements IChatMessage {

	private SqlMapClient smc;
	private static ChatMessageImpl dao;
	private ChatMessageImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ChatMessageImpl getInstance() {
		if(dao == null) {
			dao = new ChatMessageImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ChatMessageVO selectChatMessage(String mem_mail, int cr_index) {
		return null;
	}

	@Override
	public List<ChatMessageVO> getChatMessageByCrIndex(int cr_index) {
		List<ChatMessageVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("chatMessage.getChatMessageByCrIndex", cr_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Object insertChatMessageInCrIndex(ChatMessageVO vo) {
		Object obj = null;
		try {
			obj = smc.insert("chatMessage.insertChatMessageInCrIndex", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int deleteChatMessage(int cm_index) {
		return 0;
	}

	@Override
	public List<ChatMessageVO> getChatMessageByCrIndexRecent50(HashMap<String, Integer> map) {
		List<ChatMessageVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("chatMessage.getChatMessageByCrIndexRecent50", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
