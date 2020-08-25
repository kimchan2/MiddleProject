package kr.or.ddit.view.shoppingMain.shoppingdetail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingDetailMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ShoppingDetail.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);

		primaryStage.setTitle("ShoppingDetail");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
