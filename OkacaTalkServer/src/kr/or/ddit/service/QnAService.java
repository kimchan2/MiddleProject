package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.QnACmtImpl;
import kr.or.ddit.dao.QnAImpl;
import kr.or.ddit.vo.QnAVO;

public class QnAService extends UnicastRemoteObject implements IQnAService {

	QnAImpl qnaDao; 
	private static QnAService service; 
	
	private QnAService() throws RemoteException{
		qnaDao = QnAImpl.getInstance();
	}
	
	public static QnAService getInstance() throws RemoteException {
		if(service== null) {
			service = new QnAService();
		}
		return service;
	}
	
	@Override
	public QnAVO selectQnAVO(int qa_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnAVO> getQnAVO(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return qnaDao.getQnAVO(map);
	}

	@Override
	public int insertQnAVO(QnAVO vo) {
		// TODO Auto-generated method stub
		return qnaDao.insertQnAVO(vo);
	}

	@Override
	public int deleteQnAVO(int qa_index) {
		// TODO Auto-generated method stub
		return qnaDao.deleteQnAVO(qa_index);
	}

	@Override
	public int updateQnAVO(QnAVO vo) {
		// TODO Auto-generated method stub
		return qnaDao.updateQnAVO(vo);
	}

	@Override
	public List<QnAVO> getQnaVOAll(int cnt) throws RemoteException {
		// TODO Auto-generated method stub
		return qnaDao.getQnaVOAll(cnt);
	}

	@Override
	public int pageCount(String mem_mail) throws RemoteException {
		return qnaDao.pageCount(mem_mail);
	}

	@Override
	public int adminPageCount() throws RemoteException {
		return qnaDao.adminPageCount();
	}

}
