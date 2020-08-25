package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ReviewCmtImpl;
import kr.or.ddit.dao.ReviewImpl;
import kr.or.ddit.vo.MyReviewVO;
import kr.or.ddit.vo.ReviewVO;

public class ReviewService extends UnicastRemoteObject implements IReviewService {

	ReviewImpl rvDao; 
	private static ReviewService service; 
	
	private ReviewService() throws RemoteException{
		rvDao = ReviewImpl.getInstance();
	}
	
	public static ReviewService getInstance() throws RemoteException {
		if(service== null) {
			service = new ReviewService();
		}
		return service;
	}
	
	@Override
	public ReviewVO selectReviewVO(int rv_index) {
		return null;
	}

	@Override
	public List<MyReviewVO> getReviewVO(String mem_mail) {
		return rvDao.getReviewVO(mem_mail);
	}

	@Override
	public int insertReviewVO(ReviewVO vo) {
		return rvDao.insertReviewVO(vo);
	}

	@Override
	public int deleteReviewVO(int rv_index) {
		return rvDao.deleteReviewVO(rv_index);
	}

	@Override
	public int updateReviewVO(MyReviewVO vo) {
		return rvDao.updateReviewVO(vo);
	}

}
