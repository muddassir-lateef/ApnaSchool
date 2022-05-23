

package Controllers;

import Entities.ExamMarks;
import Entities.Fee;
import Entities.FeeRecord;
import Entities.Student;
import Utilities.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ViewResultsController {

    @FXML
    private ListView<String> percentageList;

    @FXML
    private ListView<String> studentList;

    @FXML
    private ListView<String> subjectList;

    public void initialize() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(ExamMarks.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Singleton obj= Singleton.getInstance();
        List results = session.createQuery("FROM ExamMarks").list();
        for (Iterator iter = results.iterator(); iter.hasNext(); ) {
            ExamMarks res = (ExamMarks) iter.next();
            String temp_percentage = res.getPercentage();
            String temp_subject = res.getSubject();
            String temp_stu_name = res.getStudentName();
            percentageList.getItems().add(temp_percentage);
            studentList.getItems().add(temp_stu_name);
            subjectList.getItems().add(temp_subject);
        }


    }

}

