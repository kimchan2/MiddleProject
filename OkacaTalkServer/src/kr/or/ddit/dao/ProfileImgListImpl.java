package kr.or.ddit.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.Util.SqlMapClientFactory;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProfileImgListVO;

public class ProfileImgListImpl implements IProfileImgList {

	private SqlMapClient smc;
	private static ProfileImgListImpl dao;
	private ProfileImgListImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ProfileImgListImpl getInstance() {
		if(dao == null) {
			dao = new ProfileImgListImpl();
		}
		
		return dao;	
	}
	
	@Override
	public ProfileImgListVO selectProfileImgListVO(int pf_index, String mem_mail) {
		
		return null;
	}

	@Override
	public ProfileImgListVO getProfileImgListVO(String mem_mail) {
		
		ProfileImgListVO vo = null;
		System.out.println("데이터 베이스 요청..");
		try {
			vo = (ProfileImgListVO) smc.queryForObject("ProfileImgList.getProfileImg", mem_mail);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 송신실패...");
		}
		System.out.println("데이터베이스 송신...");
		System.out.println("ProfileImgListVO NULL 여부 : " + vo);
		return vo;
	}

	@Override
	public Object insertProfileImgListVO(ProfileImgListVO vo) {
		
		Object obj = null;
		
		try {
			obj = smc.insert("ProfileImgList.insertProfileImgList", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public Object deleteProfileImgListVO(int cr_index) {
		return 0;
	}

	@Override
	public Object updateProfileImgListVO(ProfileImgListVO vo) {
		return 0;
	}

	@Override
	public void saveFile(FileVO vo) {

		FileOutputStream fos = null;
		String dir = "D:/Filedata/뭔진모르겟지만일단저장/";
		
		try {
			fos = new FileOutputStream(dir + vo.getFileName());
			fos.write(vo.getFileData());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일전송 실패...");
		}
		System.out.println("파일전송 완료...");
		
	}

}
