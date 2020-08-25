package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.dao.ParticipantImpl;
import kr.or.ddit.dao.ProductListImpl;
import kr.or.ddit.vo.ProductListVO;

public class ProductListService extends UnicastRemoteObject implements IProductListService {

	ProductListImpl plDao; 
	private static ProductListService service; 
	
	private ProductListService() throws RemoteException {
		plDao = ProductListImpl.getInstance();
	}
	
	public static ProductListService getInstance() throws RemoteException {
		if(service== null) {
			service = new ProductListService();
		}
		return service;
	}
	
	@Override
	public ProductListVO selectProductListVO(int pl_index) throws RemoteException {
		// TODO Auto-generated method stub
		return plDao.selectProductListVO(pl_index);
	}

	@Override
	public ArrayList<ProductListVO> getProductListVO(int pl_index) throws RemoteException {
		return plDao.getProductListVO(pl_index);
	}

	@Override
	public int insertProductListVO(ProductListVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProductListVO(int cr_index) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProductListVO(ProductListVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return plDao.updateProductListVO(vo);
	}
	
	
	// 정렬
	@Override
	public List<ProductListVO> highpricesortlist(int pl_index) throws RemoteException {
		return plDao.highpricesortlist(pl_index);
	}
	
	@Override
	public List<ProductListVO> lowpricesortlist(int pl_index) throws RemoteException {
		return plDao.lowpricesortlist(pl_index);
	}

	@Override
	public List<ProductListVO> latestsortlist(int pl_index) throws RemoteException {
		return plDao.latestsortlist(pl_index);
	}

	@Override
	public List<ProductListVO> popularitysortlist(int pl_index) throws RemoteException {
		return plDao.popularitysortlist(pl_index);
	}

	@Override
	public List<ProductListVO> reviewsortlist(int pl_index) throws RemoteException {
		return plDao.reviewsortlist(pl_index);
	}

	@Override
	public List<ProductListVO> namesortlist(int pl_index) throws RemoteException {
		return plDao.namesortlist(pl_index);
	}

	@Override
	public ProductListVO getdetailproduct(String pl_image) throws RemoteException {
		return plDao.getdetailproduct(pl_image);
	}
	
	@Override
	public ProductListVO getProductListVO2(int pl_index) throws RemoteException {
		return plDao.getProductListVO2(pl_index);
	}

	@Override
	public List<ProductListVO> getProdVO(HashMap<String, Object> map) throws RemoteException {
		return plDao.getProdVO(map);
	}

	@Override
	public List<ProductListVO> getProdAll(int cnt) throws RemoteException {
		return plDao.getProdAll(cnt);
	}

	@Override
	public int pageCount(int pl_index) throws RemoteException {
		return plDao.pageCount(pl_index);
	}

}
