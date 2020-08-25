package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 채팅 참여자
 * @author PC-22
 *
 */
public class ParticipantVO implements Serializable{

	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getCr_index() {
		return cr_index;
	}
	public void setCr_index(int cr_index) {
		this.cr_index = cr_index;
	}
	public int getPc_online() {
		return pc_online;
	}
	public void setPc_online(int pc_online) {
		this.pc_online = pc_online;
	}
	private String  mem_mail;	//참여자
	private int  cr_index;      //채팅방 번호
	private int  pc_online;     //채팅방온라인여부
	
}
