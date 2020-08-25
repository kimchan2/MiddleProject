package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MyReviewVO;
import kr.or.ddit.vo.ReviewVO;

public interface IReview {

	public ReviewVO selectReviewVO(int rv_index);
	
	public List<MyReviewVO> getReviewVO(String mem_mail); // 나의 리뷰 가져오기
	
	public int insertReviewVO(ReviewVO vo);
	
	public int deleteReviewVO(int rv_index);  // 리뷰 삭제하기
	
	public int updateReviewVO(MyReviewVO vo); // 리뷰 수정하기
	
}
