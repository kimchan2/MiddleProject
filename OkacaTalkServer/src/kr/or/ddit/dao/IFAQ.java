package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.FAQVO;

public interface IFAQ {

	public FAQVO selectFAQVO(int faq_index);
	
	public List<FAQVO> getFAQVO();
	
	public int insertFAQVO(FAQVO vo);
	
	public int deleteFAQVO(int faq_index);
	
	public int updateFAQVO(FAQVO vo);
	
}
