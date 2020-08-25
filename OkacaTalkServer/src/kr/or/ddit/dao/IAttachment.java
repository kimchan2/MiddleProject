package kr.or.ddit.dao;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.bind.attachment.AttachmentMarshaller;

import kr.or.ddit.vo.AttachmentVO;

public interface IAttachment {

	public AttachmentVO selectFile(int acm_index);
	
	public List<AttachmentVO> getFile(int acm_index);
	
	public int deleteFile(int acm_index);
	
	public int updateFile(AttachmentVO vo);
	
	public int insertQaFile(AttachmentVO vo);
	

	public int insertInqFile(AttachmentVO vo);

	public int insertRvFile(AttachmentVO vo);
	
	public String selectQaFile(int qa_index);
}
