package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MyReviewVO;
import kr.or.ddit.vo.ReviewVO;

public interface IReviewService extends Remote {

	public ReviewVO selectReviewVO(int rv_index) throws RemoteException;
	
	public List<MyReviewVO> getReviewVO(String mem_mail) throws RemoteException; // 나의 리뷰 가져오기
	
	public int insertReviewVO(ReviewVO vo) throws RemoteException;
	
	public int deleteReviewVO(int rv_index) throws RemoteException; // 리뷰 삭제하기
	
	public int updateReviewVO(MyReviewVO vo) throws RemoteException; // 나의 리뷰 수정하기
	
}
