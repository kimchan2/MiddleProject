package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.ProfileImgListVO;

public interface IProfileImgListService extends Remote {

	public ProfileImgListVO selectProfileImgListVO(int pf_index, String mem_mail) throws RemoteException;
	
	public ProfileImgListVO getProfileImgListVO(String mem_mail) throws RemoteException;
	
	public Object insertProfileImgListVO(ProfileImgListVO vo) throws RemoteException;
	
	public Object deleteProfileImgListVO(int cr_index) throws RemoteException;
	
	public Object updateProfileImgListVO(ProfileImgListVO vo) throws RemoteException;
	
	public void saveFile(FileVO vo) throws RemoteException;
	
}
