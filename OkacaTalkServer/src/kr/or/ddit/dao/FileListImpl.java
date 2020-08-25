package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.ChatMessageVO;
import kr.or.ddit.vo.FileListVO;

public class FileListImpl implements IFileList {

	private SqlMapClient smc;
	private static FileListImpl dao;
	private FileListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static FileListImpl getInstance() {
		if(dao == null) {
			dao = new FileListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public FileListVO selectFileListVO(int f_index) {
		return null;
	}

	@Override
	public List<FileListVO> getFileListVO(int f_index) {
		return null;
	}

	@Override
	public ChatMessageVO insertFileListVO(HashMap<String, Object> map) {
		ChatMessageVO vo = new ChatMessageVO();
		
		try {
			vo = (ChatMessageVO) smc.insert("fileList.insertFileListVO", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int deleteFileListVO(int f_index) {
		return 0;
	}

	@Override
	public int updateFileListVO(FileListVO vo) {
		return 0;
	}

	@Override
	public FileListVO getFileBYcm_index(int cm_index) {
		FileListVO vo = new FileListVO();
		
		try {
			vo = (FileListVO) smc.queryForObject("fileList.getFileBYcm_index", cm_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

}
