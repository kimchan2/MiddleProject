package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.NoticeCmtVO;

public class NoticeCmtImpl implements INoticeCmt {

	private SqlMapClient smc;
	private static NoticeCmtImpl dao;
	private NoticeCmtImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static NoticeCmtImpl getInstance() {
		if(dao == null) {
			dao = new NoticeCmtImpl();
		}
		
		return dao;	
	}
	
	@Override
	public NoticeCmtVO selectNoticeCmtVO(int ntc_index) {
		return null;
	}

	@Override
	public List<NoticeCmtVO> getNoticeCmtVO() {
		return null;
	}

	@Override
	public int insertNoticeCmtVO(NoticeCmtVO vo) {
		return 0;
	}

	@Override
	public int deleteNoticeCmtVO(int ntc_index) {
		return 0;
	}

	@Override
	public int updateNoticeCmtVO(NoticeCmtVO vo) {
		return 0;
	}	

}
