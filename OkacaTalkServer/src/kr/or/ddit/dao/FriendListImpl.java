package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.FriendListVO;
import kr.or.ddit.vo.MemberVO;

public class FriendListImpl implements IFriendList {

	private SqlMapClient smc;
	private static FriendListImpl dao;
	private FriendListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static FriendListImpl getInstance() {
		if(dao == null) {
			dao = new FriendListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public FriendListVO selectFriendListVO(String mem_mail, String fr_mail) {
		return null;
	}

	@Override
	public List<FriendListVO> getFriendListVO(String mem_mail) {
		
		List<FriendListVO> vo = null;
		System.out.println("getFriendListVO 데이터 베이스 요청..");
		try {
			vo = (List<FriendListVO>) smc.queryForList("friendList.getfriendlist", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("vo NULL 여부 : " + vo);
		return vo;
		
	}

	@Override
	public Object insertFriendListVO(FriendListVO vo) {
		Object result = null;
		try {
			result = smc.insert("friendList.insertfriendlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	public Object deleteFriendListVO(FriendListVO vo) {
		Object result = null;
		try {
			result = smc.delete("friendList.deletefriendlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

}
