package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.bind.attachment.AttachmentMarshaller;

import kr.or.ddit.vo.AttachmentVO;

public interface IAttachmentService extends Remote{

	public AttachmentVO selectFile(int acm_index) throws RemoteException;
	
	public List<AttachmentVO> getFile(int acm_index) throws RemoteException;
	
	public int insertQaFile(AttachmentVO vo) throws RemoteException;
	public int insertInqFile(AttachmentVO vo) throws RemoteException;
	public int insertRvFile(AttachmentVO vo) throws RemoteException;
	public String selectQaFile(int qa_index) throws RemoteException;
	
	public int deleteFile(int acm_index) throws RemoteException;
	
	public int updateFile(AttachmentVO vo) throws RemoteException;
	
}
