package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ParticipantVO;

public class ParticipantImpl implements IParticipant {

	private SqlMapClient smc;
	private static ParticipantImpl dao;
	private ParticipantImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ParticipantImpl getInstance() {
		if(dao == null) {
			dao = new ParticipantImpl();
		}
		
		return dao;	
	}
	
	@Override
	public List<ParticipantVO> getParticipantVO(int cr_index) {
		List<ParticipantVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("participant.getParticipantVO", cr_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void insertParticipantVO(ParticipantVO vo){
		try {
			smc.insert("participant.insertParticipant", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int deleteParticipantVO(int cr_index) {
		return 0;
	}

	@Override
	public List<Integer> getCrIndex(String mem_mail) {
		List<Integer> list = new ArrayList<>();

		try {
			list = smc.queryForList("participant.getCrIndex", mem_mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<String> getMemMailByCrIndex(int cr_index) {
		List<String> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("participant.getMemMailByCrIndex", cr_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer exitChatRoom(HashMap<String, Object> map) {
		Integer num = null;
		try {
			num = smc.delete("participant.exitChatRoom", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
