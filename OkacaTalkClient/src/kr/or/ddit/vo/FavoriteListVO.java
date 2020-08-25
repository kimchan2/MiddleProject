package kr.or.ddit.vo;

import java.io.Serializable;

public class FavoriteListVO implements Serializable {
	
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
	public int getCl_index() {
		return cl_index;
	}
	public void setCl_index(int cl_index) {
		this.cl_index = cl_index;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public int getPl_price() {
		return pl_price;
	}
	public void setPl_price(int pl_price) {
		this.pl_price = pl_price;
	}
	public String getPl_image() {
		return pl_image;
	}
	public void setPl_image(String pl_image) {
		this.pl_image = pl_image;
	}
	public int getPl_stock() {
		return pl_stock;
	}
	public void setPl_stock(int pl_stock) {
		this.pl_stock = pl_stock;
	}
	public String getPl_date() {
		return pl_date;
	}
	public void setPl_date(String pl_date) {
		this.pl_date = pl_date;
	}
	public int getPl_sales() {
		return pl_sales;
	}
	public void setPl_sales(int pl_sales) {
		this.pl_sales = pl_sales;
	}
	private  int  cl_index ;               ///상품카테고리 인덱스
	private  String  pl_name  ;         ///이름
	private  int  pl_price ;              ///가격
	private  String  pl_image ;         ///이미지
	private  int  pl_stock ;              ///재고
	private  String  pl_date  ;          ///등록일
	private  int  pl_sales ;               ///판매량
}
