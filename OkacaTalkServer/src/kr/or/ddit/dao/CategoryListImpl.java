package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.CategoryListVO;

public class CategoryListImpl implements ICategoryList {

	private SqlMapClient smc;
	private static CategoryListImpl dao;
	private CategoryListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static CategoryListImpl getInstance() {
		if(dao == null) {
			dao = new CategoryListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public CategoryListVO selectCategory(int cl_index) {
		return null;
	}

	@Override
	public CategoryListVO getCategory(int cl_index) {
		CategoryListVO List = null;
		
		try {
			List = (CategoryListVO) smc.queryForObject("ProductList.getcategorynumber", cl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return List;
	}

	@Override
	public int insertCategory(CategoryListVO vo) {
		return 0;
	}

	@Override
	public int deleteCategory(int cl_index) {
		return 0;
	}

	@Override
	public int updateCategory(CategoryListVO vo) {
		return 0;
	}

	@Override
	public int getCategoryCnt() {
		int list = 0;
		
		try {
			list = (int)smc.queryForObject("ProductList.getcategory");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
