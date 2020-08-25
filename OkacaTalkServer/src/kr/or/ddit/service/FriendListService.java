package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.FileListImpl;
import kr.or.ddit.dao.FriendListImpl;
import kr.or.ddit.vo.FriendListVO;

public class FriendListService extends UnicastRemoteObject implements IFriendListService {

	FriendListImpl frlDao; 
	private static FriendListService service; 
	
	private FriendListService() throws RemoteException{
		frlDao = FriendListImpl.getInstance();
	}
	
	public static FriendListService getInstance() throws RemoteException {
		if(service== null) {
			service = new FriendListService();
		}
		return service;
	}
	
	@Override
	public FriendListVO selectFriendListVO(String mem_mail, String fr_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FriendListVO> getFriendListVO(String mem_mail) {
		return frlDao.getFriendListVO(mem_mail);
	}

	@Override
	public Object insertFriendListVO(FriendListVO vo) {
		return frlDao.insertFriendListVO(vo);
	}

	@Override
	public Object deleteFriendListVO(FriendListVO vo) {
		return frlDao.deleteFriendListVO(vo);
	}

}
