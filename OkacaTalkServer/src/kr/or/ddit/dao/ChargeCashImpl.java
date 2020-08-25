package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ChargeCashVO;
import kr.or.ddit.vo.MemberVO;

public class ChargeCashImpl implements IChargeCash{

	private SqlMapClient smc;
	private static ChargeCashImpl dao;
	private ChargeCashImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ChargeCashImpl getInstance() {
		if(dao == null) {
			dao = new ChargeCashImpl();
		}
		
		return dao;	
	}
	
	@Override
	public List<ChargeCashVO> selectChargeCash(String mem_mail) {

		
		List<ChargeCashVO> ccList = null;
		System.out.println(ccList);
		System.out.println("데이터 베이스 요청..");
		try {
			ccList = (List<ChargeCashVO>) smc.queryForList("cash.selectChargeCash", mem_mail );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("ChargeCashVO NULL 여부 : " + ccList);
		return ccList;
		}

	@Override
	public List<ChargeCashVO> getChargeCash(String mem_mail) {
		return null;
	}

	@Override
	public int insertChargeCash(ChargeCashVO ccvo) {

		int cnt = 0;

		try {
			cnt = smc.update("cash.insertChargeCash", ccvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	
	}

	@Override
	public int deleteChargeCash(String mem_mail) {
		return 0;
	}

	@Override
	public int updateChargeCash(ChargeCashVO vo) {
		return 0;
	}

}
