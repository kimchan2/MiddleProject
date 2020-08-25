package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.BookMarkVO;

public class BookMarkImpl implements IBookMark {

	private SqlMapClient smc;
	private static BookMarkImpl dao;
	private BookMarkImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static BookMarkImpl getInstance() {
		if(dao == null) {
			dao = new BookMarkImpl();
		}
		
		return dao;	
	}
	
	@Override
	public BookMarkVO selectBF(BookMarkVO vo) {
		return null;
	}

	@Override
	public List<BookMarkVO> getBF(String mem_mail) {
		List<BookMarkVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("bookmark.getbookmark", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Object insertBF(BookMarkVO vo) {
		Object obj = null;
		
		try {
			obj = smc.insert("bookmark.insertbookmark", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public int deleteBF(BookMarkVO vo) {
		int result = 0;
		try {
			result = smc.delete("bookmark.deletebookmark", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
