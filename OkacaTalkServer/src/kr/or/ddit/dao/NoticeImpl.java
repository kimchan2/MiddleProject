package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.NoticeVO;

public class NoticeImpl implements INotice {

	private SqlMapClient smc;
	private static NoticeImpl dao;
	private NoticeImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static NoticeImpl getInstance() {
		if(dao == null) {
			dao = new NoticeImpl();
		}
		
		return dao;	
	}
	
	@Override
	public NoticeVO selectNoticeVO(int ntc_index) {
		return null;
	}

	@Override
	public List<NoticeVO> getNoticeVO() {
		List<NoticeVO> list = null;
		
		try {
			list = smc.queryForList("notice.getnotice");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertNoticeVO(NoticeVO vo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("notice.insertnotice", vo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteNoticeVO(int ntc_index) {
		return 0;
	}

	@Override
	public int updateNoticeVO(NoticeVO vo) {
		return 0;
	}

}
