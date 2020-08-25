package kr.or.ddit.vo;

/**
 * 업데이트 히스토리
 * @author PC-22
 *
 */
public class UpdateHistoryVO {

	public int getUh_index() {
		return uh_index;
	}
	public void setUh_index(int uh_index) {
		this.uh_index = uh_index;
	}
	public String getUh_version() {
		return uh_version;
	}
	public void setUh_version(String uh_version) {
		this.uh_version = uh_version;
	}
	public String getUh_content() {
		return uh_content;
	}
	public void setUh_content(String uh_content) {
		this.uh_content = uh_content;
	}
	public String getUh_date() {
		return uh_date;
	}
	public void setUh_date(String uh_date) {
		this.uh_date = uh_date;
	}
	private int  uh_index       ;				//인덱스
	private String  uh_version     ;            //버전 정보
	private String  uh_content     ;            //내용
	private String  uh_date        ;            //일자
	
}
