package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.ProductListImpl;
import kr.or.ddit.dao.ProfileImgListImpl;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.ProfileImgListVO;

public class ProfileImgListService extends UnicastRemoteObject implements IProfileImgListService {

	ProfileImgListImpl pflDao; 
	private static ProfileImgListService service; 
	
	private ProfileImgListService() throws RemoteException{
		pflDao = ProfileImgListImpl.getInstance();
	}
	
	public static ProfileImgListService getInstance() throws RemoteException {
		if(service== null) {
			service = new ProfileImgListService();
		}
		return service;
	}
	
	@Override
	public ProfileImgListVO selectProfileImgListVO(int pf_index, String mem_mail) {
		return pflDao.selectProfileImgListVO(pf_index, mem_mail);
	}

	@Override
	public ProfileImgListVO getProfileImgListVO(String mem_mail) {
		return pflDao.getProfileImgListVO(mem_mail);
	}

	@Override
	public Object insertProfileImgListVO(ProfileImgListVO vo) {
		return pflDao.insertProfileImgListVO(vo);
	}

	@Override
	public Object deleteProfileImgListVO(int cr_index) {
		return 0;
	}

	@Override
	public Object updateProfileImgListVO(ProfileImgListVO vo) {
		return 0;
	}

	@Override
	public void saveFile(FileVO vo) throws RemoteException {
		pflDao.saveFile(vo);
	}

}
