package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ReviewCmtVO;

public interface IReviewCmtService extends Remote {

	public ReviewCmtVO selectReviewCmtVO(int rvc_index) throws RemoteException;
	
	public List<ReviewCmtVO> getReviewCmtVO(String mem_mail) throws RemoteException;
	
	public int insertReviewCmtVO(ReviewCmtVO vo) throws RemoteException;
	
	public int deleteReviewCmtVO(int rvc_index) throws RemoteException;
	
	public int updateReviewCmtVO(ReviewCmtVO vo) throws RemoteException;
	
}
