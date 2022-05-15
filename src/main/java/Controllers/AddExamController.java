package Controllers;
import Entity.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class AddExamController {

    @FXML
    private TextField courseField;

    @FXML
    private TextField nameField;

    @FXML
    void addBtnClicked(ActionEvent event) {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Exam.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();
        Exam exam=new Exam();
        exam.setCourse(courseField.getText());
        exam.setName (nameField.getText());
        session.save(exam);

        trans.commit();
    }

}