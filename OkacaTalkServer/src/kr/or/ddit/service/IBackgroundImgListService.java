package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BackgroundImgListVO;


public interface IBackgroundImgListService extends Remote {

	public BackgroundImgListVO selectBGI(int bg_index) throws RemoteException;
	
	public BackgroundImgListVO getBGI(String mem_mail) throws RemoteException;
	
	public Object insertBGI(BackgroundImgListVO vo) throws RemoteException;
	
	public Object deleteBGI(int bg_index) throws RemoteException;
	
//	public Object updateFile(BackgroundImgListVO vo);
	
}
