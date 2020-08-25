package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.FriendListImpl;
import kr.or.ddit.dao.HideFriendImpl;
import kr.or.ddit.vo.HideFriendVO;

public class HideFriendService extends UnicastRemoteObject implements IHideFriendService {

	HideFriendImpl hrDao; 
	private static HideFriendService service; 
	
	private HideFriendService() throws RemoteException{
		hrDao = HideFriendImpl.getInstance();
	}
	
	public static HideFriendService getInstance() throws RemoteException {
		if(service== null) {
			service = new HideFriendService();
		}
		return service;
	}
	
	@Override
	public HideFriendVO selectHideFriendVO(String mem_mail, String fr_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HideFriendVO> getHideFriendVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertHideFriendVO(HideFriendVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteHideFriendVO(String mem_mail, String fr_mail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
