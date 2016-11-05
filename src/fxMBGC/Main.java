package fxMBGC;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Main
 * @author Ville Kankaanp‰‰
 * @version 28.7.2016
 *
 */
public class Main extends Application {
	
	private static MBGCController controller;
	
	/**
	 * Starts the program
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("StartView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.setTitle("My Board Game Collection");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main
	 * @param args not in use
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Sets Main's controller to c
	 * @param c MBGCController
	 */
	public static void setController(MBGCController c) {
		Main.controller = c;
	}
	
	/**
	 * Calls controller's endProgram method
	 */
	@Override
	public void stop() throws IOException{
    	if(controller != null) controller.endProgram();
	}
}
