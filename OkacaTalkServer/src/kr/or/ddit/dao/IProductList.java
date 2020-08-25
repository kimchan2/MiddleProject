package kr.or.ddit.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.vo.ProductListVO;

public interface IProductList {

	public ProductListVO selectProductListVO(int pl_index);
	
	public ArrayList<ProductListVO> getProductListVO(int pl_index);
	
	public int insertProductListVO(ProductListVO vo);
	
	public int deleteProductListVO(int pl_index);
	
	public int updateProductListVO(ProductListVO vo);
	
	public ProductListVO getdetailproduct(String pl_image);
	
	// 정렬
	public List<ProductListVO> highpricesortlist(int pl_index);
	
	public List<ProductListVO> lowpricesortlist(int pl_index);
	
	public List<ProductListVO> latestsortlist(int pl_index);
	
	public List<ProductListVO> popularitysortlist(int pl_index);
	
	public List<ProductListVO> reviewsortlist(int pl_index);
	
	public List<ProductListVO> namesortlist(int pl_index);
	
	public ProductListVO getProductListVO2(int pl_index);
	
	public List<ProductListVO> getProdVO(HashMap<String, Object> map);
	
	public List<ProductListVO> getProdAll(int cnt);
	
	public int pageCount(int pl_index);
}
