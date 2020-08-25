package kr.or.ddit.vo;

/**
 * 환경설정 정보
 * @author PC-22
 *
 */
public class ConfigurationVO {

	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public int getConf_font() {
		return conf_font;
	}
	public void setConf_font(int conf_font) {
		this.conf_font = conf_font;
	}
	public int getConf_idsearch() {
		return conf_idsearch;
	}
	public void setConf_idsearch(int conf_idsearch) {
		this.conf_idsearch = conf_idsearch;
	}
	public int getConf_notice() {
		return conf_notice;
	}
	public void setConf_notice(int conf_notice) {
		this.conf_notice = conf_notice;
	}
	public int getConf_keyword() {
		return conf_keyword;
	}
	public void setConf_keyword(int conf_keyword) {
		this.conf_keyword = conf_keyword;
	}
	public int getConf_newchat() {
		return conf_newchat;
	}
	public void setConf_newchat(int conf_newchat) {
		this.conf_newchat = conf_newchat;
	}
	public int getConf_birthfr() {
		return conf_birthfr;
	}
	public void setConf_birthfr(int conf_birthfr) {
		this.conf_birthfr = conf_birthfr;
	}
	public int getConf_background() {
		return conf_background;
	}
	public void setConf_background(int conf_background) {
		this.conf_background = conf_background;
	}
	public int getConf_transparency() {
		return conf_transparency;
	}
	public void setConf_transparency(int conf_transparency) {
		this.conf_transparency = conf_transparency;
	}
	private String mem_mail           ;   //이메일
	private int conf_font          ;      //폰트설정
	private int conf_idsearch      ;      //ID검색 허용/비허용
	private int conf_notice        ;      //알림 켜기/끄기
	private int conf_keyword       ;      //키워드 알림
	private int conf_newchat       ;      //새 채팅방 알림
	private int conf_birthfr       ;      //생일인 친구 보기
	private int conf_background    ;      //배경화면 스타일
	private int conf_transparency  ;      //채팅방 투명도
	
}
