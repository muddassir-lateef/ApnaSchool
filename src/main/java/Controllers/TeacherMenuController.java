package Controllers;

import Utilities.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TeacherMenuController {

    @FXML
    private Button addQuestionBtn;

    @FXML
    private BorderPane borderpane;

    public void logOutBtnClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        Main.loadStage(stage,"LoginScreen/login.fxml");

    }
    @FXML
    void initialize()
    {

    }
    @FXML
    void AddQuestionsClicked(ActionEvent event) {
        loadUI("Main Menu (Teacher)/Add Questions Screen/addQuestions.fxml");
    }
    private void loadUI(String path){
        Parent root = null;
        try{
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
