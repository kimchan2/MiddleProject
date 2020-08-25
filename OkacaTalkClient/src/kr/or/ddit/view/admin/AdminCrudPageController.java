package kr.or.ddit.view.admin;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.service.login.IMemberService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;

public class AdminCrudPageController implements Initializable {

	@FXML AnchorPane an_adminMain;
	@FXML TableView<MemberVO> tableview;
	@FXML TableColumn<MemberVO, String> tb_col_mail;
	@FXML TableColumn<MemberVO, String> tb_col_birth;
	@FXML TableColumn<MemberVO, String> tb_col_nik;
	@FXML TableColumn<MemberVO, String> tb_col_name;
	@FXML TableColumn<MemberVO, String> tb_col_phone;
	@FXML TableColumn<MemberVO, String> tb_col_addr;
	@FXML TableColumn<MemberVO, String> tb_col_message;
	@FXML TableColumn<MemberVO, Integer> tb_col_cash;
	@FXML TableColumn<MemberVO, Integer> tb_col_point;
	
	@FXML Pagination page_allMember;
	@FXML Button memstop;
	
	private Registry reg;
	private IMemberService ims;
	
	private int from, to, itemsForPage;
	private ObservableList<MemberVO> allTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
			ims = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		tb_col_mail.setCellValueFactory(new PropertyValueFactory<>("mem_mail"));
		tb_col_birth.setCellValueFactory(new PropertyValueFactory<>("mem_birth"));
		tb_col_nik.setCellValueFactory(new PropertyValueFactory<>("mem_nick"));
		tb_col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		tb_col_phone.setCellValueFactory(new PropertyValueFactory<>("mem_hp"));
		tb_col_addr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		tb_col_cash.setCellValueFactory(new PropertyValueFactory<>("mem_cash"));
		tb_col_point.setCellValueFactory(new PropertyValueFactory<>("mem_point"));
		tb_col_message.setCellValueFactory(new PropertyValueFactory<>("mem_message"));
		
		allTableData = FXCollections.observableArrayList();
		ArrayList<MemberVO> list = new ArrayList<>();
		
		try {
			list = (ArrayList<MemberVO>) ims.getMemberVO();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allTableData.setAll(list);
		
		tableview.setItems(allTableData);
		tableview.getSelectionModel().getSelectedItem();
		
		itemsForPage = 19;
		
		int totPageCount = allTableData.size() % itemsForPage == 0
				? allTableData.size() / itemsForPage
				: allTableData.size() / itemsForPage + 1;
				
				page_allMember.setPageCount(totPageCount);
				page_allMember.setPageFactory(this::createPage);
				
		tableview.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tableview.getSelectionModel().getSelectedItem() != null ) {
					
				}
			}
		}) ;
	}
	
	private ObservableList<MemberVO> getTableviewData(int from, int to) {
		
		currentPageData = FXCollections.observableArrayList();
		int totSize = allTableData.size();
		for(int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		return currentPageData;
	}
	
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableview.setItems(getTableviewData(from, to));
		
		return tableview;
		
	}

}
