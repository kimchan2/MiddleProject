package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 프로필 사진 리스트
 * @author PC-22
 *
 */
public class ProfileImgListVO implements Serializable {

	public int getPf_index() {
		return pf_index;
	}
	public void setPf_index(int pf_index) {
		this.pf_index = pf_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getPf_addr() {
		return pf_addr;
	}
	public void setPf_addr(String pf_addr) {
		this.pf_addr = pf_addr;
	}
	public String getPf_date() {
		return pf_date;
	}
	public void setPf_date(String pf_date) {
		this.pf_date = pf_date;
	}
	private int pf_index ;				//인덱스
	private String mem_mail ;           //이메일
	private String pf_addr  ;           //사진주소
	private String pf_date  ;           //일자
	
}
