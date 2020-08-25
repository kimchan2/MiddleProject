package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.UsingCashVO;
import kr.or.ddit.vo.UsingPointVO;

public class UsingPointImpl implements IUsingPoint {

	private SqlMapClient smc;
	private static UsingPointImpl dao;
	private UsingPointImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static UsingPointImpl getInstance() {
		if(dao == null) {
			dao = new UsingPointImpl();
		}
		
		return dao;	
	}
	
	@Override
	public UsingPointVO selectUsingPointVO(int o_index, String mem_mail) {
		return null;
	}

	@Override
	public List<UsingPointVO> getUsingPointVO(String mem_mail) {
		List<UsingPointVO> upList = null;
		System.out.println(upList);
		System.out.println("데이터 베이스 요청..");
		try {
			upList = (List<UsingPointVO>) smc.queryForList("point.getUsingpoint", mem_mail );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("upVO NULL 여부 : " + upList);
		return upList;
		
	
	}

	@Override
	public int insertUsingPointVO(UsingPointVO vo) {
		return 0;
	}

	@Override
	public int deleteUsingPointVO(int o_index, String mem_mail) {
		return 0;
	}

	@Override
	public int updateUsingPointList(UsingPointVO vo) {
		int savepoint = 0;
		
		try {
			savepoint = smc.update("point.insertusingpointlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return savepoint;
	}

}
