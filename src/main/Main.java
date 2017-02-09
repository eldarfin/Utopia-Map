package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMap.fxml"));
        primaryStage.setTitle("Utopia");
        primaryStage.setScene(new Scene(root, 1191, 700));
        primaryStage.setMaxHeight(730);
        primaryStage.setMaxWidth(1191);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
