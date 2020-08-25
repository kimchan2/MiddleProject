package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ProfileImgListImpl;
import kr.or.ddit.dao.QnACmtImpl;
import kr.or.ddit.vo.QnACmtVO;

public class QnACmtService extends UnicastRemoteObject implements IQnACmtService {

	QnACmtImpl qcDao; 
	private static QnACmtService service; 
	
	private QnACmtService() throws RemoteException{
		qcDao = QnACmtImpl.getInstance();
	}
	
	public static QnACmtService getInstance() throws RemoteException {
		if(service== null) {
			service = new QnACmtService();
		}
		return service;
	}
	
	@Override
	public QnACmtVO selectQnACmtVO(int qa_index) throws RemoteException {
		// TODO Auto-generated method stub
		return qcDao.selectQnACmtVO(qa_index);
	}

	@Override
	public List<QnACmtVO> getQnACmtVO(String mem_mail) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertQnACmtVO(QnACmtVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return qcDao.insertQnACmtVO(vo);
	}

	@Override
	public int deleteQnACmtVO(int qac_index) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateQnACmtVO(QnACmtVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
