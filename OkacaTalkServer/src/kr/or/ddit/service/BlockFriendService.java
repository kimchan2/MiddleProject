package kr.or.ddit.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.BlockFriendImpl;
import kr.or.ddit.vo.BlockFriendVO;

public class BlockFriendService extends UnicastRemoteObject implements IBlockFriendService {

	BlockFriendImpl bfDao; 
	private static BlockFriendService service; 
	
	private BlockFriendService() throws RemoteException{
		bfDao = BlockFriendImpl.getInstance();
	}
	
	public static BlockFriendService getInstance() throws RemoteException {
		if(service== null) {
			service = new BlockFriendService();
		}
		return service;
	}
	
	@Override
	public BlockFriendVO selectBF(BlockFriendVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlockFriendVO> getBF(String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBF(BlockFriendVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBF(BlockFriendVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
