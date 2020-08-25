package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.AttachmentImpl;
import kr.or.ddit.vo.AttachmentVO;

public class AttachmentService extends UnicastRemoteObject implements IAttachmentService{

	AttachmentImpl acmDao; 
	private static AttachmentService service; 
	
	private AttachmentService() throws RemoteException{
		acmDao = AttachmentImpl.getInstance();
	}
	
	public static AttachmentService getInstance() throws RemoteException {
		if(service== null) {
			service = new AttachmentService();
		}
		return service;
	}
	
	@Override
	public AttachmentVO selectFile(int acm_index) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttachmentVO> getFile(int acm_index) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteFile(int acm_index) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFile(AttachmentVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertQaFile(AttachmentVO vo) throws RemoteException {
		return acmDao.insertQaFile(vo);
	}

	@Override
	public int insertInqFile(AttachmentVO vo) throws RemoteException {
		return acmDao.insertInqFile(vo);
	}

	@Override
	public int insertRvFile(AttachmentVO vo) throws RemoteException {
		return acmDao.insertRvFile(vo);
	}

	@Override
	public String selectQaFile(int qa_index) throws RemoteException {
		// TODO Auto-generated method stub
		return acmDao.selectQaFile(qa_index);
	}

}
