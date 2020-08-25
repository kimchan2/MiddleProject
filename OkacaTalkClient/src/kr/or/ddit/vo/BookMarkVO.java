package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 즐겨찾기
 * @author PC-22
 *
 */
public class BookMarkVO implements Serializable {

	private String mem_mail;	//이메일
	private String fr_mail ;	//친구아이디
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getFr_mail() {
		return fr_mail;
	}
	public void setFr_mail(String fr_mail) {
		this.fr_mail = fr_mail;
	}
	
}
