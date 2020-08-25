package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.ReviewCmtVO;

public interface IReviewCmt {

	public ReviewCmtVO selectReviewCmtVO(int rvc_index);
	
	public List<ReviewCmtVO> getReviewCmtVO(String mem_mail);
	
	public int insertReviewCmtVO(ReviewCmtVO vo);
	
	public int deleteReviewCmtVO(int rvc_index);
	
	public int updateReviewCmtVO(ReviewCmtVO vo);
	
}
