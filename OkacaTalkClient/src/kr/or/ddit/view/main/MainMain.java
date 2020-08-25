package kr.or.ddit.view.main;

import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		Parent root = loader.load();
		
		System.out.println("메인 화면");
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("OkacaTalk");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e->{
			System.exit(0);
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
