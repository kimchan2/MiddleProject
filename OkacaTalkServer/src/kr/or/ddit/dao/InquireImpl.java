package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.InquireJoinVO;
import kr.or.ddit.vo.InquireVO;
import kr.or.ddit.vo.MemberVO;

public class InquireImpl implements IInquire  {

	private SqlMapClient smc;
	private static InquireImpl dao;
	private InquireImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static InquireImpl getInstance() {
		if(dao == null) {
			dao = new InquireImpl();
		}
		
		return dao;	
	}
	
	@Override
	public InquireVO selectInquireVO(int iqcindex, int pl_idex) {
		return null;
	}

	@Override
	public List<InquireVO> getInquireVO(int pl_idex) {
		return null;
	}

	@Override
	public int insertInquireVO(InquireVO vo) {
		return 0;
	}

	@Override
	public int deleteInquireVO(int iq_index) {
		int cnt = 0;
		
		try {
			cnt = smc.update("inquire.deleteInquire" , iq_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("delete 데이터베이스 송신실패...");
		}
		System.out.println("cnt NULL 여부 : " + cnt);
		
		
		return cnt;
	}

	@Override
	public int updateInquireVO(InquireVO vo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("inquire.updateInquire" , vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("update 데이터베이스 송신실패...");
		}
		System.out.println("cnt NULL 여부 : " + cnt);
		
		
		return cnt;
	}

	@Override
	public List<InquireJoinVO> selectInquireVO2(String mem_mail) {
		List<InquireJoinVO> list = new ArrayList<>();
		System.out.println(mem_mail);
		try {
			list = (List<InquireJoinVO>) smc.queryForList("inquire.selectInquire2", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectInquire 데이터베이스 송신실패...");
		}
		System.out.println("selectInquire 데이터베이스 송신...");
		System.out.println("list NULL 여부 : " + list);
		return list;
	}

}
