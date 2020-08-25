package kr.or.ddit.view.shoppingMain.payment;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.session.Session;

public class PaymentCashChargeController implements Initializable {

	@FXML Button Charge_Ok_Btn;
	@FXML Button Charge_No_Btn;

	Registry reg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(Session.IPaddress, 8315);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ChargeOkBtnOnActioin() {
		Node chargeok = null;
		Node chargecate = null;
		
		try {
			chargeok = FXMLLoader.load(getClass().getResource("../cash/ChargeCash.fxml"));
			chargecate = FXMLLoader.load(getClass().getResource("../category/CateAll.fxml"));
			Session.moveConf.getShop_Cate().getChildren().clear();
			Session.moveConf.getShop_Cate().getChildren().add(chargecate);			
			Session.moveConf.getShopping().getChildren().clear();
			Session.moveConf.getShopping().getChildren().add(chargeok);
			Stage stage = (Stage) Charge_Ok_Btn.getScene().getWindow();
			stage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void ChargeNoBtnOnAction() {
		Stage stage = (Stage) Charge_No_Btn.getScene().getWindow();
		stage.close();
	}

}
