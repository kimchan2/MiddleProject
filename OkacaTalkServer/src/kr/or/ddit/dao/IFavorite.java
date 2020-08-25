package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.FavoriteListVO;
import kr.or.ddit.vo.FavoriteVO;

public interface IFavorite {

	public FavoriteVO selectFavoriteVO(int pl_index, String mem_mail);
	
	public List<FavoriteListVO> getFavoriteVO(String mem_mail);
	
	public int insertFavoriteVO(FavoriteVO vo);
	
	public int deleteFavoriteVO(FavoriteVO vo);
	
	public int updateFavoriteVO(FavoriteVO vo);
	
}
