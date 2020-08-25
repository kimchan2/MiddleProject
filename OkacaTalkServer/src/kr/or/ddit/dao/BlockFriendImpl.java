package kr.or.ddit.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.BlockFriendVO;

public class BlockFriendImpl implements IBlockFriend {

	private SqlMapClient smc;
	private static BlockFriendImpl dao;
	private BlockFriendImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static BlockFriendImpl getInstance() {
		if(dao == null) {
			dao = new BlockFriendImpl();
		}
		
		return dao;	
	}
	
	@Override
	public BlockFriendVO selectBF(BlockFriendVO vo) {
		return null;
	}

	@Override
	public List<BlockFriendVO> getBF(String mem_mail) {
		return null;
	}

	@Override
	public int insertBF(BlockFriendVO vo) {
		return 0;
	}

	@Override
	public int deleteBF(BlockFriendVO vo) {
		return 0;
	}

}
