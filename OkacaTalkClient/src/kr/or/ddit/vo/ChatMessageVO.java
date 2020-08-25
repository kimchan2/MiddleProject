package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 채팅메시지
 * @author PC-22
 *
 */
public class ChatMessageVO implements Serializable{

	public int getCm_index() {
		return cm_index;
	}
	public void setCm_index(int cm_index) {
		this.cm_index = cm_index;
	}
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
	public String getCm_content() {
		return cm_content;
	}
	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}
	public String getCm_time() {
		return cm_time;
	}
	public void setCm_time(String cm_time) {
		this.cm_time = cm_time;
	}
	public String getCm_ischeck() {
		return cm_ischeck;
	}
	public void setCm_ischeck(String cm_ischeck) {
		this.cm_ischeck = cm_ischeck;
	}
	private int cm_index    ;       //채팅 메시지 인덱스
	private String mem_mail    ;    //메시지 소유자
	private int cr_index    ;       //채팅방 번호
	private String cm_content  ;    //채팅내용
	private String cm_time     ;    //보낸시간
	private String cm_ischeck  ;       //미확인수
	
}
