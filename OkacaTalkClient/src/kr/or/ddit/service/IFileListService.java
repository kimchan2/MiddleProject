package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.FileListVO;

public interface IFileListService extends Remote {

	public FileListVO selectFileListVO(int f_index) throws RemoteException;
	
	public List<FileListVO> getFileListVO(int f_index) throws RemoteException;
	
	public ChatMessageVO insertFileListVO(HashMap<String, Object> map) throws RemoteException;
	
	public int deleteFileListVO(int f_index) throws RemoteException;
	
	public int updateFileListVO(FileListVO vo) throws RemoteException;
	
	public FileListVO getFileBYcm_index(int cm_index) throws RemoteException;
	
}
