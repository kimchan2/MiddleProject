package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 채팅방
 * @author PC-22
 *
 */
public class ChatRoomVO implements Serializable{

	public int getCr_index() {
		return cr_index;
	}
	public void setCr_index(int cr_index) {
		this.cr_index = cr_index;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getCr_name() {
		return cr_name;
	}
	public void setCr_name(String cr_name) {
		this.cr_name = cr_name;
	}
	public int getCr_isself() {
		return cr_isself;
	}
	public void setCr_isself(int cr_isself) {
		this.cr_isself = cr_isself;
	}
	public int getCr_isopenchat() {
		return cr_isopenchat;
	}
	public void setCr_isopenchat(int cr_isopenchat) {
		this.cr_isopenchat = cr_isopenchat;
	}
	private int  cr_index  ;        // 채팅방 번호
	private String  mem_mail  ;     // 이메일
	private String  cr_name   ;     // 채팅방이름
	private int  cr_isself ;        // 나와의채팅
	private int cr_isopenchat;
	                                
}
