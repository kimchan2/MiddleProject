package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 배경사진리스트
 * @author PC-22
 *
 */
public class BackgroundImgListVO implements Serializable {

	private int bg_index  ;	//인덱스
	private String mem_mail  ;	//이메일
	private String bg_addr   ;	//사진주소
	private String bg_date   ;	//사진일자
	public int getBg_index() {
		return bg_index;
	}
	public void setBg_index(int bg_index) {
		this.bg_index = bg_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getBg_addr() {
		return bg_addr;
	}
	public void setBg_addr(String bg_addr) {
		this.bg_addr = bg_addr;
	}
	public String getBg_date() {
		return bg_date;
	}
	public void setBg_date(String bg_date) {
		this.bg_date = bg_date;
	}
	
}
