package kr.or.ddit.view.shoppingMain.shoppingdetail;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import kr.or.ddit.service.ICategoryListService;
import kr.or.ddit.service.IProductListService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.CategoryListVO;
import kr.or.ddit.vo.ProductListVO;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Pagination;

public class ShoppingDetailController implements Initializable {

	@FXML Label Cate_Title_Label;
	@FXML Label Dcate_Title_Label;
	
	@FXML TextField Product_Search_Text;
	
	@FXML Button Product_Highpricesort_Btn;
	@FXML Button Product_Lowpricesort_Btn;
	@FXML Button Product_Latestsort_Btn;
	@FXML Button Product_Popularitysort_Btn;
	@FXML Button Product_Reviewsort_Btn;
	@FXML Button Product_Namesort_Btn;
	
	@FXML ImageView Product_Img1;
	@FXML ImageView Product_Img2;
	@FXML ImageView Product_Img3;
	@FXML ImageView Product_Img4;
	@FXML ImageView Product_Img5;
	@FXML ImageView Product_Img6;
	@FXML ImageView Product_Img7;
	@FXML ImageView Product_Img8;

	@FXML Label Product_Name_Label1;
	@FXML Label Product_Name_Label2;
	@FXML Label Product_Name_Label3;
	@FXML Label Product_Name_Label4;
	@FXML Label Product_Name_Label5;
	@FXML Label Product_Name_Label6;
	@FXML Label Product_Name_Label7;
	@FXML Label Product_Name_Label8;
	
	@FXML Label Product_Price_Label1;
	@FXML Label Product_Price_Label2;
	@FXML Label Product_Price_Label3;
	@FXML Label Product_Price_Label4;
	@FXML Label Product_Price_Label5;
	@FXML Label Product_Price_Label6;
	@FXML Label Product_Price_Label7;
	@FXML Label Product_Price_Label8;
	
	@FXML ImageView Product_Latest_Img;
	@FXML Label Product_LatestPrice_Label;
	
	@FXML AnchorPane detailMain;
	
	public AnchorPane getDetailMain() {
		return detailMain;
	}
	
	@FXML Pagination Prod_Pagination_Btn;

	public void setDetailMain(AnchorPane detailMain) {
		this.detailMain = detailMain;
	}

	private Registry reg;
//	private ICategoryListService icategory;
	private IProductListService iproduct;
	
	ArrayList<CategoryListVO> catelist;
	ArrayList<ProductListVO> prodlist;
	ArrayList<ArrayList<CategoryListVO>> pcatelist;
	
	ArrayList<ProductListVO> byhighpricesortlist;
	ArrayList<ProductListVO> bylowpricesortlist;
	ArrayList<ProductListVO> bylatestsortlist;
	ArrayList<ProductListVO> bypopularitysortlist;
	ArrayList<ProductListVO> byreviewsortlist;
	ArrayList<ProductListVO> bynamesortlist;
	
	ProductListVO pv = new ProductListVO();
	ProductListVO pv2 = new ProductListVO();
	List<ProductListVO> prodList = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Product_Latestsort_Btn.setVisible(false);
		// 검색창 엔터 이벤트
		if(Session.isSearch) {Session.orglist = Session.pprodlist.get(Session.curclindex);}
		Product_Search_Text.setOnKeyPressed(KeyEvent->{
			if(KeyEvent.getCode() == KeyCode.ENTER) {
				String searchText = Product_Search_Text.getText().trim();
				ArrayList<ProductListVO> list = new ArrayList<>();
				
				if(!searchText.equals("") && searchText != null) {
					for(int i = 0; i < Session.orglist.size(); i++) {
						if(Session.orglist.get(i).getPl_name().contains(searchText)) {
							list.add(Session.orglist.get(i));
						}
					}
					Session.pprodlist.set(Session.curclindex, list);
					Session.isSearch = false;
					Node node = null;
					try {
						node = new FXMLLoader(getClass().getResource("ShoppingDetail.fxml")).load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					detailMain.getChildren().setAll(node);
					
				}
				else if(searchText.equals("")) {
					Session.pprodlist.set(Session.curclindex, Session.orglist);
					Node node = null;
					try {
						node = new FXMLLoader(getClass().getResource("ShoppingDetail.fxml")).load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					detailMain.getChildren().setAll(node);
					
				}
			}
		});
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			iproduct = (IProductListService) reg.lookup("productList");
			pv = iproduct.getProductListVO2(pv2.getPl_index());
			
			Session.shopDeConf = this;
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// list로 묶어주기
		ImageView[] Arr_Prod_Img = {
				Product_Img1, Product_Img2, Product_Img3, Product_Img4,
				Product_Img5, Product_Img6, Product_Img7, Product_Img8
		};
		
		Label[] Arr_Prod_Name = {
				Product_Name_Label1, Product_Name_Label2, Product_Name_Label3, Product_Name_Label4, 
				Product_Name_Label5, Product_Name_Label6, Product_Name_Label7, Product_Name_Label8
		};
		
		Label[] Arr_Prod_Price = {
				Product_Price_Label1, Product_Price_Label2, Product_Price_Label3, Product_Price_Label4, 
				Product_Price_Label5, Product_Price_Label6, Product_Price_Label7, Product_Price_Label8
		};
		
		// 페이지네이션 초기 세팅
		paginationControl();
		
		settingByCategory(Cate_Title_Label, Dcate_Title_Label);
		settingBybutton(Arr_Prod_Img, Arr_Prod_Name, Arr_Prod_Price);
		
		// 페이지네이션 이벤트
		Prod_Pagination_Btn.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				settingBybutton(Arr_Prod_Img, Arr_Prod_Name, Arr_Prod_Price);
			}
		});
		
		Product_Highpricesort_Btn.setOnAction(e->{
			for(int i = 0; i < Session.pprodlist.get(Session.curclindex).size(); i++) {
				for(int j = 0; j < Session.pprodlist.get(Session.curclindex).size(); j++) {
					if(Session.pprodlist.get(Session.curclindex).get(i).getPl_price() 
							> Session.pprodlist.get(Session.curclindex).get(j).getPl_price()) 
					{
						ProductListVO pdlvo = Session.pprodlist.get(Session.curclindex).get(i);
						Session.pprodlist.get(Session.curclindex).set(i, Session.pprodlist.get(Session.curclindex).get(j));
						Session.pprodlist.get(Session.curclindex).set(j, pdlvo);
					}
				}
			}
			settingBybutton(Arr_Prod_Img, Arr_Prod_Name, Arr_Prod_Price);
		});
		
		Product_Lowpricesort_Btn.setOnAction(e->{
			for(int i = 0; i < Session.pprodlist.get(Session.curclindex).size(); i++) {
				for(int j = 0; j < Session.pprodlist.get(Session.curclindex).size(); j++) {
					if(Session.pprodlist.get(Session.curclindex).get(i).getPl_price() 
							< Session.pprodlist.get(Session.curclindex).get(j).getPl_price()) 
					{
						ProductListVO pdlvo = Session.pprodlist.get(Session.curclindex).get(i);
						Session.pprodlist.get(Session.curclindex).set(i, Session.pprodlist.get(Session.curclindex).get(j));
						Session.pprodlist.get(Session.curclindex).set(j, pdlvo);
					}
				}
			}
			settingBybutton(Arr_Prod_Img, Arr_Prod_Name, Arr_Prod_Price);
		});
		
		Product_Popularitysort_Btn.setOnAction(e->{
			for(int i = 0; i < Session.pprodlist.get(Session.curclindex).size(); i++) {
				for(int j = 0; j < Session.pprodlist.get(Session.curclindex).size(); j++) {
					if(Session.pprodlist.get(Session.curclindex).get(i).getPl_sales()
							> Session.pprodlist.get(Session.curclindex).get(j).getPl_sales()) 
					{
						ProductListVO pdlvo = Session.pprodlist.get(Session.curclindex).get(i);
						Session.pprodlist.get(Session.curclindex).set(i, Session.pprodlist.get(Session.curclindex).get(j));
						Session.pprodlist.get(Session.curclindex).set(j, pdlvo);
					}
				}
			}
			settingBybutton(Arr_Prod_Img, Arr_Prod_Name, Arr_Prod_Price);
		});
		
		Product_Latestsort_Btn.setOnAction(e->{
			Collections.sort(Session.pprodlist.get(Session.curclindex), new Comparator<ProductListVO>() {
				@Override
				public int compare(ProductListVO o1, ProductListVO o2) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						return sdf.parse(o1.getPl_date()).compareTo(sdf.parse(o2.getPl_date()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				}
			});
		});
	}
	
	private void paginationControl() {
		List<ProductListVO> list = (List<ProductListVO>) Session.pprodlist.get(Session.curclindex);// 클릭한 카테고리의 페이지수
		int allPageNum =  (int) Math.ceil( list.size()/(double)8 );
		Prod_Pagination_Btn.setPageCount(allPageNum);
		Prod_Pagination_Btn.setCurrentPageIndex(Session.pageindex);
	}

	// 세부 카테고리 누르면 shoppingDetail로 화면 전환
	@FXML public void dcateTitleLabelOnMouseClicked() {
		changeScene("ShoppingDetail.fxml");
	}

	private FXMLLoader changeScene(String fxmlURL) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		detailMain.getChildren().clear();
		detailMain.getChildren().add(parent);
		return loader;
	}

	// 카테고리 출력
	private void settingByCategory(Label Cate_Title_Label, Label Dcate_Title_Label) {
		// 있던 내용 지워주기
		Cate_Title_Label.setText(null);
		Dcate_Title_Label.setText(null);
		
		int int_catelist = Session.catelist.size();
		
		String cl_category = "";
		String cl_detail_category = "";

		cl_category = Session.catelist.get(Session.curclindex).getCl_category();
		cl_detail_category = Session.catelist.get(Session.curclindex).getCl_detail_category();
		Cate_Title_Label.setText(cl_category);
		Dcate_Title_Label.setText(cl_detail_category);			
	
	}
	
	// 화면에 상품 리스트 출력
	private void settingBybutton(ImageView[] Arr_Prod_Img, Label[] Arr_Prod_Name, Label[] Arr_Prod_Price) {
		
		String pl_name = "";
		int pl_price = 0;
		Image pl_image_img = null;
		Session.pageindex = Prod_Pagination_Btn.getCurrentPageIndex();
		// 화면에 리스트 넣어주기
		int i;
		for(i = Session.pageindex*8 ;
				i < (Session.pageindex+1 == 
						Prod_Pagination_Btn.getPageCount() ? Session.pprodlist.get(Session.curclindex).size() : 
						(Session.pageindex+1) * 8); i++) 
		{	
			pl_name = Session.pprodlist.get(Session.curclindex).get(i).getPl_name();
			pl_price = Session.pprodlist.get(Session.curclindex).get(i).getPl_price();
			pl_image_img = new Image(Session.pprodlist.get(Session.curclindex).get(i).getPl_image());
			
			if(i > 7) {
				Arr_Prod_Name[i-(8*Session.pageindex)].setText(pl_name);
				Arr_Prod_Price[i-(8*Session.pageindex)].setText(pl_price + "원");
				Arr_Prod_Img[i-(8*Session.pageindex)].setImage(pl_image_img);
			}
			else {
				Arr_Prod_Name[i].setText(pl_name);
				Arr_Prod_Price[i].setText(pl_price + "원");
				Arr_Prod_Img[i].setImage(pl_image_img);
			}
		}

		int lastPageProductNum = i % 8;
		if (lastPageProductNum > 0) {
			for (int j = lastPageProductNum; j < 8; j++) {
				Arr_Prod_Name[j].setText("");
				Arr_Prod_Price[j].setText("");
				Arr_Prod_Img[j].setImage(null);
			}
		}
		
		// 글자 잘린 부분 커서 가져다 놓으면 이름 전체 보이기
		Tooltip tt = new Tooltip();
		tt.setText(Product_Name_Label1.getText());
		tt.setText(Product_Name_Label2.getText());
		tt.setText(Product_Name_Label3.getText());
		tt.setText(Product_Name_Label4.getText());
		tt.setText(Product_Name_Label5.getText());
		tt.setText(Product_Name_Label6.getText());
		tt.setText(Product_Name_Label7.getText());
		tt.setText(Product_Name_Label8.getText());
		Tooltip.install(Product_Name_Label1, tt);
		Tooltip.install(Product_Name_Label2, tt);
		Tooltip.install(Product_Name_Label3, tt);
		Tooltip.install(Product_Name_Label4, tt);
		Tooltip.install(Product_Name_Label5, tt);
		Tooltip.install(Product_Name_Label6, tt);
		Tooltip.install(Product_Name_Label7, tt);
		Tooltip.install(Product_Name_Label8, tt);
	}

	// 이미지 클릭하면 상품 상세페이지로 이동
	@FXML public void productImg1OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(0 + (Session.pageindex*8)).getPl_image();
		
		Node productimg1;
		try {
			productimg1 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg2OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(1 + (Session.pageindex*8)).getPl_image();
		
		Node productimg2;
		try {
			productimg2 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg3OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(2 + (Session.pageindex*8)).getPl_image();
		
		Node productimg3;
		try {
			productimg3 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg4OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(3 + (Session.pageindex*8)).getPl_image();
		
		Node productimg4;
		try {
			productimg4 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg4);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg5OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(4 + (Session.pageindex*8)).getPl_image();
		
		Node productimg5;
		try {
			productimg5 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg5);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg6OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(5 + (Session.pageindex*8)).getPl_image();
		
		Node productimg6;
		try {
			productimg6 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg6);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg7OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(6 + (Session.pageindex*8)).getPl_image();
		
		Node productimg7;
		try {
			productimg7 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg7);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void productImg8OnMouseClicked() {
		//changeScene("ProductDetailPage.fxml");
		Session.curplindex = Session.pprodlist.get(Session.curclindex).get(7 + (Session.pageindex*8)).getPl_image();
		
		Node productimg8;
		try {
			productimg8 = FXMLLoader.load(getClass().getResource("ProductDetailPage.fxml"));
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(productimg8);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
