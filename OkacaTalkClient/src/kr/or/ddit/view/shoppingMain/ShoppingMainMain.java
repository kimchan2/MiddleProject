package kr.or.ddit.view.shoppingMain;


import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingMainMain extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ShoppingMain.fxml"));
		Parent root = loader.load();
		
		System.out.println("test");
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("쇼핑메인");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
