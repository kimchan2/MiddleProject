package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.MemberImpl;
import kr.or.ddit.dao.NoticeCmtImpl;
import kr.or.ddit.vo.NoticeCmtVO;

public class NoticeCmtService extends UnicastRemoteObject implements INoticeCmtService {

	NoticeCmtImpl ntcDao; 
	private static NoticeCmtService service; 
	
	private NoticeCmtService() throws RemoteException{
		ntcDao = NoticeCmtImpl.getInstance();
	}
	
	public static NoticeCmtService getInstance() throws RemoteException {
		if(service== null) {
			service = new NoticeCmtService();
		}
		return service;
	}
	
	@Override
	public NoticeCmtVO selectNoticeCmtVO(int ntc_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeCmtVO> getNoticeCmtVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNoticeCmtVO(NoticeCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNoticeCmtVO(int ntc_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNoticeCmtVO(NoticeCmtVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
