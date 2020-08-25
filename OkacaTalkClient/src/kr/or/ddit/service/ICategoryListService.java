package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.CategoryListVO;

public interface ICategoryListService extends Remote {

	public CategoryListVO selectCategory(int cl_index);
	
	public CategoryListVO getCategory(int cl_index);
	
	public int insertCategory(CategoryListVO vo);
	
	public int deleteCategory(int cl_index);
	
	public int updateCategory(CategoryListVO vo);
	
	public int getCategoryCnt();
}
