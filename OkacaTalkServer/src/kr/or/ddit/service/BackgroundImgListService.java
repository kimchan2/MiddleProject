package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.BackgroundImgListImpl;
import kr.or.ddit.vo.BackgroundImgListVO;

public class BackgroundImgListService extends UnicastRemoteObject implements IBackgroundImgListService {

	BackgroundImgListImpl bgDao; 
	private static BackgroundImgListService service; 
	
	private BackgroundImgListService() throws RemoteException{
		bgDao = BackgroundImgListImpl.getInstance();
	}
	
	public static BackgroundImgListService getInstance() throws RemoteException {
		if(service== null) {
			service = new BackgroundImgListService();
		}
		return service;
	}
	
	@Override
	public BackgroundImgListVO selectBGI(int bg_index) {
		return null;
	}

	@Override
	public BackgroundImgListVO getBGI(String mem_mail) {
		return bgDao.getBGI(mem_mail);
	}

	@Override
	public Object insertBGI(BackgroundImgListVO vo) {
		return bgDao.insertBGI(vo);
	}

	@Override
	public Object deleteBGI(int bg_index) {
		return 0;
	}

}
