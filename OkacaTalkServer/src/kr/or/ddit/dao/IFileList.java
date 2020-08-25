package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.FileListVO;

public interface IFileList {

	public FileListVO selectFileListVO(int f_index);
	
	public List<FileListVO> getFileListVO(int f_index);
	
	public ChatMessageVO insertFileListVO(HashMap<String, Object> map);
	
	public int deleteFileListVO(int f_index);
	
	public int updateFileListVO(FileListVO vo);
	
	public FileListVO getFileBYcm_index(int cm_index);
	
}
