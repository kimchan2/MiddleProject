package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.InquireCmtVO;

public class InquireCmtImpl implements IInquireCmt {

	private SqlMapClient smc;
	private static InquireCmtImpl dao;
	private InquireCmtImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static InquireCmtImpl getInstance() {
		if(dao == null) {
			dao = new InquireCmtImpl();
		}
		
		return dao;	
	}
	
	@Override
	public InquireCmtVO selectInquireCmtVO(int iqc_index, int iq_idex) {
		return null;
	}

	@Override
	public List<InquireCmtVO> getInquireCmtVO(int iq_idex) {
		return null;
	}

	@Override
	public int insertInquireCmtVO(InquireCmtVO vo) {
		return 0;
	}

	@Override
	public int deleteInquireCmtVO(int iqc_index, int iq_idex) {
		return 0;
	}

	@Override
	public int updateInquireCmtVO(InquireCmtVO vo) {
		return 0;
	}

}
