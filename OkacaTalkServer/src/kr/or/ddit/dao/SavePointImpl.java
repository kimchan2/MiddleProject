package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.SavePointVO;
import kr.or.ddit.vo.UsingPointVO;

public class SavePointImpl implements ISavePoint {

	private SqlMapClient smc;
	private static SavePointImpl dao;
	private SavePointImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static SavePointImpl getInstance() {
		if(dao == null) {
			dao = new SavePointImpl();
		}
		
		return dao;	
	}
	
	@Override
	public SavePointVO selectSavePointVO(String mem_mail, int o_index) {
		return null;
	}

	@Override
	public List<SavePointVO> getSavePointVO(String mem_mail) {

		List<SavePointVO> spList = null;
		System.out.println(spList);
		System.out.println("데이터 베이스 요청..");
		try {
			spList = (List<SavePointVO>) smc.queryForList("point.getSavepoint", mem_mail );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("upVO NULL 여부 : " + spList);
		return spList;
		
	
	
	}

	@Override
	public int insertSavePointVO(SavePointVO vo) {
		int save = 0;
		
		try {
			save = smc.update("point.insertsavepoint", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return save;
	}

	@Override
	public int deleteSavePointVO(String mem_mail, int o_index) {
		return 0;
	}

	@Override
	public int updateSavePointVO(SavePointVO vo) {
		return 0;
	}

}
