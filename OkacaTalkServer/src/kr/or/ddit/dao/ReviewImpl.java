package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.MyReviewVO;
import kr.or.ddit.vo.ReviewVO;

public class ReviewImpl implements IReview {

	private SqlMapClient smc;
	private static ReviewImpl dao;
	private ReviewImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ReviewImpl getInstance() {
		if(dao == null) {
			dao = new ReviewImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ReviewVO selectReviewVO(int rv_index) {
		return null;
	}

	@Override
	public List<MyReviewVO> getReviewVO(String mem_mail) {
		List<MyReviewVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("review.getmyreview", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertReviewVO(ReviewVO vo) {
		int review = 0;
		
		try {
			review = smc.update("review.insertmyreview", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return review;
	}

	@Override
	public int deleteReviewVO(int rv_index) {
		int delete = 0;
		
		try {
			delete = smc.delete("review.deletemyreview", rv_index);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delete;
	}

	@Override
	public int updateReviewVO(MyReviewVO vo) {
		int update = 0;
		
		try {
			update = smc.update("review.updatemyreview", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return update;
	}

}
