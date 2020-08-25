package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ReviewCmtVO;

public class ReviewCmtImpl implements IReviewCmt {

	private SqlMapClient smc;
	private static ReviewCmtImpl dao;
	private ReviewCmtImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ReviewCmtImpl getInstance() {
		if(dao == null) {
			dao = new ReviewCmtImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ReviewCmtVO selectReviewCmtVO(int rvc_index) {
		return null;
	}

	@Override
	public List<ReviewCmtVO> getReviewCmtVO(String mem_mail) {
		return null;
	}

	@Override
	public int insertReviewCmtVO(ReviewCmtVO vo) {
		return 0;
	}

	@Override
	public int deleteReviewCmtVO(int rvc_index) {
		return 0;
	}

	@Override
	public int updateReviewCmtVO(ReviewCmtVO vo) {
		return 0;
	}

}
