package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ProductListVO;

public interface IProductListService extends Remote {

	public ProductListVO selectProductListVO(int pl_index) throws RemoteException;
	
	public ArrayList<ProductListVO> getProductListVO(int pl_index) throws RemoteException;
	
	public int insertProductListVO(ProductListVO vo) throws RemoteException;
	
	public int deleteProductListVO(int pl_index) throws RemoteException;
	
	public int updateProductListVO(ProductListVO vo) throws RemoteException;
	
	public ProductListVO getdetailproduct(String pl_image) throws RemoteException;
	
	// 정렬
	public List<ProductListVO> highpricesortlist(int pl_index) throws RemoteException;
	
	public List<ProductListVO> lowpricesortlist(int pl_index) throws RemoteException;
	
	public List<ProductListVO> latestsortlist(int pl_index) throws RemoteException;
	
	public List<ProductListVO> popularitysortlist(int pl_index) throws RemoteException;
	
	public List<ProductListVO> reviewsortlist(int pl_index) throws RemoteException;
	
	public List<ProductListVO> namesortlist(int pl_index) throws RemoteException;
	
	//

	public ProductListVO getProductListVO2(int pl_index) throws RemoteException;
	
	public List<ProductListVO> getProdVO(HashMap<String, Object> map) throws RemoteException;
	
	public List<ProductListVO> getProdAll(int cnt) throws RemoteException;
	
	public int pageCount(int pl_index) throws RemoteException;
}
