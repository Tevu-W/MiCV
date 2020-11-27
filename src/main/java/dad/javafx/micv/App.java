package dad.javafx.micv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		controller = new MainController();
		
		Scene scene = new Scene(controller.getView(), 700, 500);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("MiCV");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
