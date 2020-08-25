package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.FriendListVO;

public interface IFriendListService extends Remote {

	public FriendListVO selectFriendListVO(String mem_mail, String fr_mail) throws RemoteException;
	
	public List<FriendListVO> getFriendListVO(String mem_mail) throws RemoteException;
	
	public Object insertFriendListVO(FriendListVO vo) throws RemoteException;
	
	public Object deleteFriendListVO(FriendListVO vo) throws RemoteException;
	
//	public int updateFriendListVO(FriendListVO vo);
	
}
