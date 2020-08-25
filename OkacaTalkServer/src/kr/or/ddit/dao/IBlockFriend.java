package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BlockFriendVO;

public interface IBlockFriend {

	public BlockFriendVO selectBF(BlockFriendVO vo);
	
	public List<BlockFriendVO> getBF(String mem_mail);
	
	public int insertBF(BlockFriendVO vo);
	
	public int deleteBF(BlockFriendVO vo);
	
//	public int updateBF(BlockFriendVO vo);
	
}
