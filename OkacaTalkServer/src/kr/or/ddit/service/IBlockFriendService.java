package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BlockFriendVO;

public interface IBlockFriendService extends Remote {

	public BlockFriendVO selectBF(BlockFriendVO vo) throws RemoteException;
	
	public List<BlockFriendVO> getBF(String mem_mail) throws RemoteException;
	
	public int insertBF(BlockFriendVO vo) throws RemoteException;
	
	public int deleteBF(BlockFriendVO vo) throws RemoteException;
	
//	public int updateBF(BlockFriendVO vo);
	
}
