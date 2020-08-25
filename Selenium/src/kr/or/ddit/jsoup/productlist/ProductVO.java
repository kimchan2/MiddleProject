package kr.or.ddit.jsoup.productlist;

public class ProductVO {
	private int pl_index; 
	private int cl_index; 
	private String pl_name ; 
	private int pl_price; 
	private String pl_image; 
	private int pl_stock; 
	private String pl_date ; 
	private int pl_sales;
	public int getPl_index() {
		return pl_index;
	}
	public void setPl_index(int pl_index) {
		this.pl_index = pl_index;
	}
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
	@Override
	public String toString() {
		return "ProductList [pl_index=" + pl_index + ", cl_index=" + cl_index + ", pl_name=" + pl_name + ", pl_price="
				+ pl_price + ", pl_image=" + pl_image + ", pl_stock=" + pl_stock + ", pl_date=" + pl_date
				+ ", pl_sales=" + pl_sales + "]";
	}
	
	
}
