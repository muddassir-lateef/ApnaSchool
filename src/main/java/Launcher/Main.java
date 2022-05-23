package Launcher;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

import java.net.URL;

public class Main extends Application {
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static void main(String[] args){


        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //  FXMLLoader fxmlLoader = new FXMLLoader(Utilities.Launcher.Main.class.getResource("LoginScreen/login.fxml"));
        URL url=getClass().getClassLoader().getResource("LoginScreen/login.fxml");
        Parent root = FXMLLoader.load(url);
        root.setStyle("-fx-background-radius: 6;-fx-background-color: rgb(256, 256, 256), rgb(256, 256, 256);" );
        stage.initStyle(StageStyle.TRANSPARENT);
      //  root.setStyle("-fx-background-radius: 6;" );
        stage.setTitle("Apna School");
        //  stage.initStyle(StageStyle.UNDECORATED);


        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        stage.show();

    }
    public static void loadStage(Stage stage,String path)
    {
        try {
            URL url= Main.class.getClassLoader().getResource(path);
            Parent root = FXMLLoader.load(url);
            //stage.initStyle(StageStyle.TRANSPARENT);
            root.setStyle("-fx-background-radius: 6;-fx-background-color: rgb(256, 256, 256), rgb(256, 256, 256);" );
            stage.setTitle("Examination System");
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            //move around here
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);

            stage.show();
        }
        catch (Exception e)
        {

        }


    }
}