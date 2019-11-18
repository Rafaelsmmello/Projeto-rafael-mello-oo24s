package br.edu.utfpr.pb.oo24s.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = (VBox) FXMLLoader.load(
         this.getClass()
          .getResource("/fxml/FXMLPrincipal.fxml")
        );
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/fxmlprincipal.css");
        
        primaryStage.setTitle("Sistema Hotel");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}