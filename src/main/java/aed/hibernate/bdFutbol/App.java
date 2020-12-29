package aed.hibernate.bdFutbol;

import aed.hibernate.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		controller = new Controller();
		
		Scene escena = new Scene(controller.getView_tabPane(), 920, 600);
		
		primaryStage.getIcons().add(new Image("hibernate-icon.png"));
		primaryStage.setScene(escena);
		primaryStage.setTitle("Proyecto Hibernate, Diego Méndez\t");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {

		launch(args);
		
	}

}
