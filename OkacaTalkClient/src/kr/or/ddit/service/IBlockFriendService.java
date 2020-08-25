package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.BlockFriendVO;

public interface IBlockFriendService extends Remote {

	public BlockFriendVO selectBF(BlockFriendVO vo);
	
	public List<BlockFriendVO> getBF(String mem_mail);
	
	public int insertBF(BlockFriendVO vo);
	
	public int deleteBF(BlockFriendVO vo);
	
//	public int updateBF(BlockFriendVO vo);
	
}
