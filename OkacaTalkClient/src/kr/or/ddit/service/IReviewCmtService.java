package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.ReviewCmtVO;

public interface IReviewCmtService extends Remote {

	public ReviewCmtVO selectReviewCmtVO(int rvc_index);
	
	public List<ReviewCmtVO> getReviewCmtVO(String mem_mail);
	
	public int insertReviewCmtVO(ReviewCmtVO vo);
	
	public int deleteReviewCmtVO(int rvc_index);
	
	public int updateReviewCmtVO(ReviewCmtVO vo);
	
}
