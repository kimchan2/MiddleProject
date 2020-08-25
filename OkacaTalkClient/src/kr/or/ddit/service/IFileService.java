package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.vo.FileVO;

public interface IFileService extends Remote {

	public void setFile(FileVO file) throws RemoteException;
	
	public FileVO getFile(String path) throws RemoteException;

	public String setFile(FileVO file, Integer cr_index) throws RemoteException; // 채팅방에서 파일전송 할 때
	
	public String setAttachment(int flag, FileVO file, int index) throws RemoteException; // 첨부파일
}
