package kr.or.ddit.view;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.controller.RmiServer;
import javafx.scene.control.Button;

public class ServerMainMainController implements Initializable {

	@FXML TextArea txtField;
	@FXML AnchorPane Server;
	@FXML Button btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(RmiServer.acceptServer() == 0) {
			txtField.appendText("Server Error");
		}
		else {
			txtField.appendText("Server is started....\n");
		}
		
	}

	@FXML public void btnCloseAction() {
		
		System.exit(0);
		
	}

}
