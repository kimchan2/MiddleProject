package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.ObjectUtils;
import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.QnAVO;

public class QnAImpl implements IQnA {

	private SqlMapClient smc;
	private static QnAImpl dao;
	private QnAImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static QnAImpl getInstance() {
		if(dao == null) {
			dao = new QnAImpl();
		}
		
		return dao;	
	}
	
	@Override
	public QnAVO selectQnAVO(int qa_index) {
		return null;
	}

	@Override
	public List<QnAVO> getQnAVO(HashMap<String, Object> map) {
		List<QnAVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("qna.getQnaVO", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertQnAVO(QnAVO vo) {
		int num = 0;
		try {
			num = (int) smc.insert("qna.insertQnAVO", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteQnAVO(int qa_index) {
		int num = 0;
		try {
			num = smc.delete("qna.deleteQnAVO", qa_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updateQnAVO(QnAVO vo) {
		int num = 0;
		try {
			num = smc.update("qna.updateQnAVO", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<QnAVO> getQnaVOAll(int cnt) {
		List<QnAVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("qna.getQnaVOAll", cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int pageCount(String mem_mail) {
		int num = 0;
		try {
			num = (int) smc.queryForObject("qna.pageCount", mem_mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int adminPageCount() {
		int num = 0;
		try {
			num = (int) smc.queryForObject("qna.adminPageCount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
