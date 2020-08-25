package kr.or.ddit.view;

import javax.mail.Session;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMainMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("ServerMain.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Server");
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
