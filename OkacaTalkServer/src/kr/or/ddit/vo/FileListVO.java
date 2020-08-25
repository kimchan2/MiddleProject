package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 채팅방 파일
 * @author PC-22
 *
 */
public class FileListVO implements Serializable{

	public int getF_index() {
		return f_index;
	}
	public void setF_index(int f_index) {
		this.f_index = f_index;
	}
	public int getCm_index() {
		return cm_index;
	}
	public void setCm_index(int cm_index) {
		this.cm_index = cm_index;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_type() {
		return f_type;
	}
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	public String getF_addr() {
		return f_addr;
	}
	public void setF_addr(String f_addr) {
		this.f_addr = f_addr;
	}
	private  int  f_index		;          //파일 인덱스
	private  int  cm_index    ;            //채팅 메시지 인덱스
	private  String  f_name      ;         //파일이름
	private  String  f_type      ;         //파일형식
	private  String  f_addr      ;         //파일주소
	
}
