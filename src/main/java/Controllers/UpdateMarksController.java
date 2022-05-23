package Controllers;


import Entities.ExamMarks;
import Entities.LoginDetails;
import Entities.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class UpdateMarksController {

    @FXML
    private TextField namefield;

    @FXML
    private TextField percentagefield;

    @FXML
    private Text promptField;


    @FXML
    private TextField subjectfield;

    @FXML
    private Button updatebtn;

    @FXML
    void updateBtnClicked(ActionEvent event) {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(LoginDetails.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();

        ExamMarks tempMarks=new ExamMarks();
        tempMarks.setPercentage(percentagefield.getText());
        tempMarks.setStudentName(namefield.getText());
        tempMarks.setSubject(subjectfield.getText());

        session.save(tempMarks);
        trans.commit();
        promptField.setText("Update Successful!!");



    }

}
