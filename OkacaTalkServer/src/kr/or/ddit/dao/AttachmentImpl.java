package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.AttachmentVO;

public class AttachmentImpl implements IAttachment {

	private SqlMapClient smc;
	private static AttachmentImpl dao;
	private AttachmentImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static AttachmentImpl getInstance() {
		if(dao == null) {
			dao = new AttachmentImpl();
		}
		
		return dao;	
	}
	
	@Override
	public AttachmentVO selectFile(int acm_index) {
		return null;
	}

	@Override
	public List<AttachmentVO> getFile(int acm_index) {
		return null;
	}

	@Override
	public int updateFile(AttachmentVO vo) {
		return 0;
	}

	@Override
	public int insertQaFile(AttachmentVO vo) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			num = (int) smc.insert("attach.insertQaFile", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int insertInqFile(AttachmentVO vo) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			num = (int) smc.insert("attach.insertInqFile", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int insertRvFile(AttachmentVO vo) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			num = (int) smc.insert("attach.insertRvFile", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteFile(int acm_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectQaFile(int qa_index) {
		// TODO Auto-generated method stub
		String str = "";
		
		try {
			str = (String) smc.queryForObject("attach.selectQaFile", qa_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}

}
