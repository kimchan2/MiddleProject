package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.QnACmtVO;

public class QnACmtImpl implements IQnACmt {

	private SqlMapClient smc;
	private static QnACmtImpl dao;
	private QnACmtImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static QnACmtImpl getInstance() {
		if(dao == null) {
			dao = new QnACmtImpl();
		}
		
		return dao;	
	}
	
	@Override
	public QnACmtVO selectQnACmtVO(int qa_index) {
		QnACmtVO qcvo = new QnACmtVO();
				
		try {
			qcvo = (QnACmtVO) smc.queryForObject("qnaCmt.selectQnACmtVO", qa_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return qcvo;
	}

	@Override
	public List<QnACmtVO> getQnACmtVO(String mem_mail) {
		return null;
	}

	@Override
	public int insertQnACmtVO(QnACmtVO vo) {
		Integer num = 0;
		
		try {
			num = (Integer) smc.insert("qnaCmt.insertQnACmtVO", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public int deleteQnACmtVO(int qac_index) {
		return 0;
	}

	@Override
	public int updateQnACmtVO(QnACmtVO vo) {
		return 0;
	}

}
