package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 회원
 * @author PC-22
 *
 */
public class MemberVO implements Serializable {

	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_hp() {
		return mem_hp;
	}
	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public int getMem_cash() {
		return mem_cash;
	}
	public void setMem_cash(int mem_cash) {
		this.mem_cash = mem_cash;
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public String getMem_drop() {
		return mem_drop;
	}
	public void setMem_drop(String mem_drop) {
		this.mem_drop = mem_drop;
	}
	public String getMem_message() {
		return mem_message;
	}
	public void setMem_message(String mem_message) {
		this.mem_message = mem_message;
	}
	private  String mem_mail   ;    //이메일
	private  String mem_birth  ;    //생년월일
	private  String mem_nick   ;    //닉네임
	private  String mem_pass   ;    //비밀번호
	private  String mem_name   ;    //이름
	private  String mem_hp     ;    //전화번호
	private  String mem_addr   ;    //주소
	private  int mem_cash    ;      //캐시
	private  int mem_point   ;      //마일리지
	private  String mem_drop   ;   //탈퇴유예날짜
	private  String mem_message;    //상태메시지
	
}
