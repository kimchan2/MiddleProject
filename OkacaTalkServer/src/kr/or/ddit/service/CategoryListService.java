package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.BookMarkImpl;
import kr.or.ddit.dao.CategoryListImpl;
import kr.or.ddit.vo.CategoryListVO;

public class CategoryListService extends UnicastRemoteObject implements ICategoryListService {

	CategoryListImpl clDao; 
	private static CategoryListService service; 
	
	private CategoryListService() throws RemoteException{
		clDao = CategoryListImpl.getInstance();
	}
	
	public static CategoryListService getInstance() throws RemoteException {
		if(service== null) {
			service = new CategoryListService();
		}
		return service;
	}
	
	@Override
	public CategoryListVO selectCategory(int cl_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryListVO getCategory(int cl_index) throws RemoteException {
		return clDao.getCategory(cl_index);
	}

	@Override
	public int insertCategory(CategoryListVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(int cl_index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCategory(CategoryListVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCategoryCnt() throws RemoteException {
		return clDao.getCategoryCnt();
	}

}
