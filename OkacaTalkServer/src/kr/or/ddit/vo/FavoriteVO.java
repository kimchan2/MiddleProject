package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 관심상품
 * @author PC-22
 *
 */
public class FavoriteVO implements Serializable {

	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	private int pl_index;       //상품 인덱스
	private String mem_mail;	//이메일
	
}
