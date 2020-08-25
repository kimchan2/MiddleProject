package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProductListVO;

public class ProductListImpl implements IProductList {

	private SqlMapClient smc;
	private static ProductListImpl dao;
	private ProductListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ProductListImpl getInstance() {
		if(dao == null) {
			dao = new ProductListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ProductListVO selectProductListVO(int pl_index) {	
		
//		ProductListVO vo = new ProductListVO();
//		try {
//			vo = (ProductListVO) smc.queryForObject("ProductList.getproductlist", pl_index);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public ArrayList<ProductListVO> getProductListVO(int pl_index) {
		
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.getproductlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertProductListVO(ProductListVO vo) {
		return 0;
	}

	@Override
	public int deleteProductListVO(int cr_index) {
		return 0;
	}

	@Override
	public int updateProductListVO(ProductListVO vo) {
		int cnt = 0;
		
		try {
			cnt =  smc.update("ProductList.updateproduct", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	// 정렬
	@Override
	public List<ProductListVO> highpricesortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.highpricesortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<ProductListVO> lowpricesortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.lowpricesortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<ProductListVO> latestsortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.latestsortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<ProductListVO> popularitysortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.popularitysortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public List<ProductListVO> reviewsortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.reviewsortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductListVO> namesortlist(int pl_index) {
		ArrayList<ProductListVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<ProductListVO>) smc.queryForList("ProductList.namesortlist", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ProductListVO getdetailproduct(String pl_image) {
		ProductListVO pvo = new ProductListVO();
		System.out.println(pvo);
		
		System.out.println("데이터 베이스 요청..");
		
		try {
			pvo = (ProductListVO) smc.queryForObject("ProductList.getdetailproduct", pl_image);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("pvo NULL 여부 : " + pvo);
		return pvo;
	}

	public ProductListVO getProductListVO2(int pl_index) {
		
		ProductListVO vo = new ProductListVO();
		
		try {
			vo = (ProductListVO)smc.queryForObject("ProductList.getdetailproduct2", pl_index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	// paging 처리
	@Override
	public List<ProductListVO> getProdVO(HashMap<String, Object> map) {
		List<ProductListVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("ProductList.getProductVO", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<ProductListVO> getProdAll(int cnt) {
		List<ProductListVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("ProductList.getProductVOAll", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int pageCount(int pl_index) {
		int page = 0;
		
		try {
			page = (int) smc.queryForObject("ProductList.pageCount", pl_index);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return page;
	}


}
