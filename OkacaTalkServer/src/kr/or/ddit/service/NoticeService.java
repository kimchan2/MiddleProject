package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.NoticeCmtImpl;
import kr.or.ddit.dao.NoticeImpl;
import kr.or.ddit.vo.NoticeVO;

public class NoticeService extends UnicastRemoteObject implements INoticeService {

	NoticeImpl ntDao; 
	private static NoticeService service; 
	
	private NoticeService() throws RemoteException{
		ntDao = NoticeImpl.getInstance();
	}
	
	public static NoticeService getInstance() throws RemoteException {
		if(service== null) {
			service = new NoticeService();
		}
		return service;
	}
	
	@Override
	public NoticeVO selectNoticeVO(int ntc_index) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> getNoticeVO() throws RemoteException {
		// TODO Auto-generated method stub
		return ntDao.getNoticeVO();
	}

	@Override
	public int insertNoticeVO(NoticeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ntDao.insertNoticeVO(vo);
	}

	@Override
	public int deleteNoticeVO(int ntc_index) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNoticeVO(NoticeVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
