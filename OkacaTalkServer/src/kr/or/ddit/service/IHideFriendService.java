package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.HideFriendVO;

public interface IHideFriendService extends Remote {

	public HideFriendVO selectHideFriendVO(String mem_mail, String fr_mail) throws RemoteException;
	
	public List<HideFriendVO> getHideFriendVO() throws RemoteException;
	
	public int insertHideFriendVO(HideFriendVO vo) throws RemoteException;
	
	public int deleteHideFriendVO(String mem_mail, String fr_mail) throws RemoteException;
	
//	public int updateFriendListVO(HideFriendVO vo);
	
}
