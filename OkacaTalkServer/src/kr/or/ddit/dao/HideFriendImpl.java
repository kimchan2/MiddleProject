package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.HideFriendVO;

public class HideFriendImpl implements IHideFriend {

	private SqlMapClient smc;
	private static HideFriendImpl dao;
	private HideFriendImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static HideFriendImpl getInstance() {
		if(dao == null) {
			dao = new HideFriendImpl();
		}
		
		return dao;	
	}
	
	@Override
	public HideFriendVO selectHideFriendVO(String mem_mail, String fr_mail) {
		return null;
	}

	@Override
	public List<HideFriendVO> getHideFriendVO() {
		return null;
	}

	@Override
	public int insertHideFriendVO(HideFriendVO vo) {
		return 0;
	}

	@Override
	public int deleteHideFriendVO(String mem_mail, String fr_mail) {
		return 0;
	}

}
