package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ChargeCashVO;
import kr.or.ddit.vo.UsingCashVO;

public class UsingCashImpl implements IUsingCash {

	private SqlMapClient smc;
	private static UsingCashImpl dao;
	private UsingCashImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static UsingCashImpl getInstance() {
		if(dao == null) {
			dao = new UsingCashImpl();
		}
		
		return dao;	
	}
	
	@Override
	public UsingCashVO selectUsingCashVO(int o_index, String mem_mail) {
		return null;
	}

	@Override
	public List<UsingCashVO> getUsingCashVO(String mem_mail) {
		


		
		List<UsingCashVO> ucList = null;
		System.out.println(ucList);
		System.out.println("데이터 베이스 요청..");
		try {
			ucList = (List<UsingCashVO>) smc.queryForList("cash.getUsingCash", mem_mail );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("memVO NULL 여부 : " + ucList);
		return ucList;
		
	}

	@Override
	public int insertUsingCashVO(UsingCashVO vo) {
		return 0;
	}

	@Override
	public int deleteUsingCashVO(int o_index, String mem_mail) {
		return 0;
	}

	@Override
	public Object insertUsingCashListVO(UsingCashVO vo) {
		Object obj = null;
		
		try {
			obj = smc.insert("cash.insertuingcashlist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

}
