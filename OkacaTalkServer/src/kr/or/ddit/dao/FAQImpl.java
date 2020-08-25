package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.FAQVO;

public class FAQImpl implements IFAQ {

	private SqlMapClient smc;
	private static FAQImpl dao;
	private FAQImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static FAQImpl getInstance() {
		if(dao == null) {
			dao = new FAQImpl();
		}
		
		return dao;	
	}
	
	@Override
	public FAQVO selectFAQVO(int faq_index) {
		return null;
	}

	@Override
	public List<FAQVO> getFAQVO() {
		List<FAQVO> list = null;
		
		try {
			list = smc.queryForList("faq.getfaq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertFAQVO(FAQVO vo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("faq.insertfaq", vo);
			if(obj == null) {
				cnt = 1;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteFAQVO(int faq_index) {
		return 0;
	}

	@Override
	public int updateFAQVO(FAQVO vo) {
		return 0;
	}

}
