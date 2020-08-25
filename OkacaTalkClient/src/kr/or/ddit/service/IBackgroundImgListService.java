package kr.or.ddit.service;

import java.rmi.Remote;
import java.util.List;

import kr.or.ddit.vo.BackgroundImgListVO;


public interface IBackgroundImgListService extends Remote {

	public BackgroundImgListVO selectBGI(int bg_index);
	
	public BackgroundImgListVO getBGI(String mem_mail);
	
	public Object insertBGI(BackgroundImgListVO vo);
	
	public Object deleteBGI(int bg_index);
	
//	public Object updateFile(BackgroundImgListVO vo);
	
}
