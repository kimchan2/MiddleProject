package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.NoticeVO;

public interface INoticeService extends Remote {

	public NoticeVO selectNoticeVO(int ntc_index) throws RemoteException;
	
	public List<NoticeVO> getNoticeVO() throws RemoteException;
	
	public int insertNoticeVO(NoticeVO vo) throws RemoteException;
	
	public int deleteNoticeVO(int ntc_index) throws RemoteException;
	
	public int updateNoticeVO(NoticeVO vo) throws RemoteException;
	
}
