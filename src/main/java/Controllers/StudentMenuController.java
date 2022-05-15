package Controllers;

import Launcher.Main;
import Utilities.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

public class StudentMenuController {

    @FXML
    private Button admitStudentBtn;

    @FXML
    private Button genIdCardBtn;

    @FXML
    private Button updateMarksBtn;

    @FXML
    private Button payFeeBtn;



    @FXML
    private Button admitStaffBtn;


    @FXML
    void admitStaffBtnPressed(ActionEvent event) {
        loadParentUI("RegisterStaff/RegisterStaff.fxml");
    }
    @FXML
    void admitStudentBtnPressed(ActionEvent event) {
        loadParentUI("AdmissionScreen/AdmissionScreen.fxml");
    }

    @FXML
    void genIdCardBtnPressed(ActionEvent event) {
        loadParentUI("IDCardScreen/IDCardScreen.fxml");

    }

    @FXML
    void updateMarksBtnPressed(ActionEvent event) {
        loadParentUI("UpdateMarks/UpdateMarks.fxml");
    }

    @FXML
    void payFeeBtnPressed(ActionEvent event) {
        loadParentUI("FeePayment/FeePaymentMenu.fxml");

    }


    private void loadParentUI(String path){
        //loads the UI in the center pane i.e. the AnchorPane of the current controller
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
        Singleton singleton=Singleton.getInstance();
        singleton.getBorderPane().setCenter(root);

    }


}
