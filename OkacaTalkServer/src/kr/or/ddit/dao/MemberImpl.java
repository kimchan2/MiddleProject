package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberImpl implements IMember {

	private SqlMapClient smc;
	private static MemberImpl dao;
	private MemberImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static MemberImpl getInstance() {
		if(dao == null) {
			dao = new MemberImpl();
		}
		
		return dao;	
	}
	
	@Override
	public MemberVO selectMemberVO(MemberVO vo) {
		
		MemberVO memVO = null;
		System.out.println(vo);
		System.out.println("데이터 베이스 요청..");
		try {
			memVO = (MemberVO) smc.queryForObject("member.getMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("memVO NULL 여부 : " + memVO);
		return memVO;
	}

	@Override
	public List<MemberVO> getMemberVO() {
		List<MemberVO> list = null;
		
		try {
			list = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Object insertMemberVO(MemberVO vo) {
		
		Object obj = null;
		
		try {
			obj = smc.insert("member.insertMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public int deleteMemberVO(String mem_mail) {
		int cnt = 0;

		try {
			cnt = smc.delete("member.deleteMember", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public Object updateMemberVO(MemberVO vo) {
		Object obj = null;
		
		try {
			obj = smc.update("member.updateMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public MemberVO idSearch2(MemberVO mv) {
		MemberVO memVO = null;
		System.out.println("데이터 베이스 요청..");
		try {
			memVO = (MemberVO) smc.queryForObject("member.getSearchId2", mv);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("memVO NULL 여부 : " + memVO);
		return memVO;
	}

	@Override
	public MemberVO pwSearch(MemberVO mv) {
		MemberVO memVO = null;
		System.out.println("데이터 베이스 요청..");
		try {
			memVO = (MemberVO) smc.queryForObject("member.getSearchPW", mv);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("memVO NULL 여부 : " + memVO);
		return memVO;
	}

	@Override
	public MemberVO idSearch(String mem_mail) {
		MemberVO memVO = null;
		System.out.println(mem_mail);
		System.out.println("idSearch 데이터 베이스 요청..");
		try {
			memVO = (MemberVO) smc.queryForObject("member.getSearchId", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("idSearch 데이터베이스 송신실패...");
		}
		System.out.println("idSearch 데이터베이스 송신...");
		System.out.println("memVO NULL 여부 : " + memVO);
		return memVO;
	}

	@Override
	public List<MemberVO> nickSearch(MemberVO mv) {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			memberList = (List<MemberVO>) smc.queryForList("member.nickSearch", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public String getNickByEmail(String mem_mail) {
		String mem_nick = null;
		
		try {
			mem_nick = (String) smc.queryForObject("member.getNickByEmail", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mem_nick;
	}

	@Override
	public List<MemberVO> getBirthdayMember(String mem_mail) {
		List<MemberVO> list = new ArrayList<>();
		
		try {
			list = (List<MemberVO>) smc.queryForList("member.getBirthdayMember", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int updatemodifyMember(MemberVO mv) {
		int cnt = 0;

		try {
			cnt = smc.update("member.updatemodifyMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int updatecashMember(MemberVO mv) {
		int cnt = 0;

		try {
			cnt = smc.update("member.updatecashMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getBookmarkMember(String mem_mail) {
		List<MemberVO> list = new ArrayList<>();
		
		try {
			list = (List<MemberVO>) smc.queryForList("member.getBookmarkMember", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberVO> getFriendList(String mem_mail) {
		List<MemberVO> list = new ArrayList<>();
		
		try {
			list = (List<MemberVO>) smc.queryForList("member.getFriendList", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateCashPoint(MemberVO mv) {
		int cashpoint = 0;
		
		try {
			cashpoint = smc.update("member.updatecashpointMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cashpoint;
	}

		

}
