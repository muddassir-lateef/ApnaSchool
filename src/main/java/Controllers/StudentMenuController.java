package Controllers;

import Utilities.Main;
import Utilities.Singleton;
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

public class StudentMenuController {

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button registerExambtn;

    @FXML
    private Button takeExambtn;

    @FXML
    private Button viewEnrolledBtn;

    @FXML
    private Button viewResultsbtn;


    public void logOutBtnClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        Main.loadStage(stage,"LoginScreen/login.fxml");

    }


    @FXML
    void registerExamBtnClicked(ActionEvent event) {
        loadUI("Main Menu (Student)/Register Exam Screen/register.fxml");


    }

    @FXML
    void takeExamBtnClicked(ActionEvent event) {
        loadUI("Main Menu (Student)/Take Exam Screen/selectExam.fxml");
        Singleton obj= Singleton.getInstance();
        obj.borderPane=borderpane;
    }

    @FXML
    void viewEnrolledBtnClicked(ActionEvent event) {
        loadUI("Main Menu (Student)/View Enrolled Screen/enrolledExams.fxml");

    }

    @FXML
    void viewResultsBtnClicked(ActionEvent event) {
        loadUI("Main Menu (Student)/View Results Screen/results.fxml");

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
