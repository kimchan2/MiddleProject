package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 상품카테고리
 * @author PC-22
 *
 */
public class CategoryListVO implements Serializable{

	private int cl_index           ;	//상품카테고리 인덱스
	private String cl_category        ;	//카테고리
	private String cl_detail_category ;	//세부카테고리
	public int getCl_index() {
		return cl_index;
	}
	public void setCl_index(int cl_index) {
		this.cl_index = cl_index;
	}
	public String getCl_category() {
		return cl_category;
	}
	public void setCl_category(String cl_category) {
		this.cl_category = cl_category;
	}
	public String getCl_detail_category() {
		return cl_detail_category;
	}
	public void setCl_detail_category(String cl_detail_category) {
		this.cl_detail_category = cl_detail_category;
	}
	
}
