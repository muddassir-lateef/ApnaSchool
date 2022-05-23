package Controllers;

import Launcher.Main;
import Utilities.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class MainMenuController {

    @FXML
    private AnchorPane centerAnchorpane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button homeBtn;

    @FXML
    private Button studentMenuBtn;

    @FXML
    void HomeBtnPressed(ActionEvent event) {
        loadUI("Home/Home.fxml");

    }
    public void initialize() {


        loadUI("Home/Home.fxml");
    }

    @FXML
    void studentMenuBtnPressed(ActionEvent event) {
        Singleton singleton=Singleton.getInstance();
        singleton.setBorderPane(borderpane);
        loadUI("Student Menu/StudentMenu.fxml");

    }
    private void loadUI(String path){
        // loads an anchor pane of the specified path in the center of borderpane
        Parent root = null;
        try{
            //   root=FXMLLoader.load(getClass().getResource(path));
            URL url= Main.class.getClassLoader().getResource(path);
            root = FXMLLoader.load(url);


        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());

        }

        borderpane.setCenter(root);

    }

}
