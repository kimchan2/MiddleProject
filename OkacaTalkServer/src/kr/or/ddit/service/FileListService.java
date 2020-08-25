package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.FavoriteImpl;
import kr.or.ddit.dao.FileListImpl;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.FileListVO;

public class FileListService extends UnicastRemoteObject implements IFileListService {

	FileListImpl flDao; 
	private static FileListService service; 
	
	private FileListService() throws RemoteException{
		flDao = FileListImpl.getInstance();
	}
	
	public static FileListService getInstance() throws RemoteException {
		if(service== null) {
			service = new FileListService();
		}
		return service;
	}
	
	@Override
	public FileListVO selectFileListVO(int f_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileListVO> getFileListVO(int f_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMessageVO insertFileListVO(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return flDao.insertFileListVO(map);
	}

	@Override
	public int deleteFileListVO(int f_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFileListVO(FileListVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FileListVO getFileBYcm_index(int cm_index) throws RemoteException {
		return flDao.getFileBYcm_index(cm_index);
	}

}
