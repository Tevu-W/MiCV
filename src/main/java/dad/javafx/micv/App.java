package dad.javafx.micv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	private MainController controller;
	
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		App.primaryStage = primaryStage;
		
		controller = new MainController();
		
		Scene scene = new Scene(controller.getView(), 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("MiCV");
		primaryStage.show();
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
