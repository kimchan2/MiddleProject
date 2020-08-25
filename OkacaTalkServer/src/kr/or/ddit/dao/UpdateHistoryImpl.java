package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.UpdateHistoryVO;

public class UpdateHistoryImpl implements IUpdateHistory {

	private SqlMapClient smc;
	private static UpdateHistoryImpl dao;
	private UpdateHistoryImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static UpdateHistoryImpl getInstance() {
		if(dao == null) {
			dao = new UpdateHistoryImpl();
		}
		
		return dao;	
	}
	
	@Override
	public UpdateHistoryVO selectUpdateHistoryVO(int uh_index) {
		return null;
	}

	@Override
	public List<UpdateHistoryVO> getUpdateHistoryVO() {
		return null;
	}

	@Override
	public int insertUpdateHistoryVO(UpdateHistoryVO vo) {
		return 0;
	}

	@Override
	public int deleteUpdateHistoryVO(int uh_index) {
		return 0;
	}

	@Override
	public int updateUpdateHistoryVO(UpdateHistoryVO vo) {
		return 0;
	}

}
