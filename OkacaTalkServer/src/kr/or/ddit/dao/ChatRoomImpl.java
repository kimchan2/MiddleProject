package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ChatRoomVO;
import kr.or.ddit.vo.MemberVO;

public class ChatRoomImpl implements IChatRoom {

	private SqlMapClient smc;
	private static ChatRoomImpl dao;
	private ChatRoomImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ChatRoomImpl getInstance() {
		if(dao == null) {
			dao = new ChatRoomImpl();
		}
		return dao;	
	}
	
	@Override
	public ChatRoomVO selectChatRoom(String mem_mail, int cr_index) {
		ChatRoomVO vo = new ChatRoomVO();
		
		try {
			vo.setMem_mail(mem_mail);
			vo.setCr_index(cr_index);
			vo = (ChatRoomVO) smc.queryForObject("chatRoom.selectChatRoom", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public ChatRoomVO getChatRoom(Integer cr_index) {
		ChatRoomVO vo = null;
		
		try {
			vo = (ChatRoomVO) smc.queryForObject("chatRoom.getChatRoom", cr_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int insertChatRoom(ChatRoomVO vo) {
		int num = 0;
		try {
			num = (int) smc.insert("chatRoom.insertChatRoom", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public void deleteChatRoom(int cr_index) {
		try {
			smc.delete("chatRoom.deleteChatRoom", cr_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateChatRoom(ChatRoomVO vo) {
		try {
			smc.update("chatRoom.updateChatRoom", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<ChatRoomVO> getOpenChatRoom() {
		List<ChatRoomVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("chatRoom.getOpenChatRoom");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<ChatRoomVO> getMyChatRoom(String mem_mail) {
		List<ChatRoomVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("chatRoom.getMyChatRoom", mem_mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
