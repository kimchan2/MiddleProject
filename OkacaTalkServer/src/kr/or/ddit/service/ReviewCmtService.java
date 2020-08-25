package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.QnAImpl;
import kr.or.ddit.dao.ReviewCmtImpl;
import kr.or.ddit.vo.ReviewCmtVO;

public class ReviewCmtService extends UnicastRemoteObject implements IReviewCmtService {

	ReviewCmtImpl rvcDao; 
	private static ReviewCmtService service; 
	
	private ReviewCmtService() throws RemoteException{
		rvcDao = ReviewCmtImpl.getInstance();
	}
	
	public static ReviewCmtService getInstance() throws RemoteException {
		if(service== null) {
			service = new ReviewCmtService();
		}
		return service;
	}
	
	@Override
	public ReviewCmtVO selectReviewCmtVO(int rvc_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewCmtVO> getReviewCmtVO(String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReviewCmtVO(ReviewCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReviewCmtVO(int rvc_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReviewCmtVO(ReviewCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
