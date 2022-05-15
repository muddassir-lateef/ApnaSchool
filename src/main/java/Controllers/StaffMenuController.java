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

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;

public class StaffMenuController {

    @FXML
    private Button addExamBtn;

    @FXML
    private Button addVenueBtn;

    @FXML
    private Button assignPersonelBtn;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button scheduleExamBtn;

    @FXML
    private Button viewCodesBtn;

    @FXML
    private Button viewFeedbackBtn;





    @FXML
    void AddExamsClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/Add Exam Screen/addExam.fxml");

    }

    @FXML
    void AddVenueClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/Add Venue Screen/addVenue.fxml");
    }

    @FXML
    void AssignPersonnelClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/Assign Personnel Screen/assignPersonnel.fxml");

    }

    @FXML
    void ScheduleExamsClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/Schedule Exam Screen/scheduleExam.fxml");

    }

    @FXML
    void viewCodesClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/View Registration Codes Screen/registrationCodes.fxml");

    }

    @FXML
    void viewFeedbackClicked(ActionEvent event) {
        loadUI("Main Menu (Staff)/View Exam FeedBack Screen/examFeedback.fxml");

    }
    private void loadUI(String path){
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

    public void logOutBtnClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        Main.loadStage(stage,"LoginScreen/login.fxml");

    }
}
