package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.ProfileImgListVO;

public interface IProfileImgList {

	public ProfileImgListVO selectProfileImgListVO(int pf_index, String mem_mail);
	
	public ProfileImgListVO getProfileImgListVO(String mem_mail);
	
	public Object insertProfileImgListVO(ProfileImgListVO vo);
	
	public Object deleteProfileImgListVO(int cr_index);
	
	public Object updateProfileImgListVO(ProfileImgListVO vo);
	
	public void saveFile(FileVO vo);
	
}
