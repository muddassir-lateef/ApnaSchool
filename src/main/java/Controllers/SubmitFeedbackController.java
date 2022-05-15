package Controllers;

import Entity.Exam;
import Entity.Feedback;
import Entity.Invigilator;
import Utilities.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class SubmitFeedbackController {

    @FXML
    private ComboBox<String> examsDropdown;

    @FXML
    private Button selectExamBtn;

    @FXML
    private TextArea statementArea;

    @FXML
    private Button submitBtn;

    public void initialize() {
        ObservableList<String> options1 = FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Invigilator.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        Singleton obj= Singleton.getInstance();

        int invID = obj.currInv.getID();  //set this from singleton variable
        List exams = session.createQuery("FROM Exam where assignedInvigilator.ID = :temp").setParameter("temp", invID).list();
        for (Iterator iter = exams.iterator(); iter.hasNext(); ) {

            Exam exm = (Exam) iter.next();
            options1.add(exm.getId() + ". " + exm.getName());

        }
        trans.commit();

        examsDropdown.setItems(options1);

    }

    @FXML
    void selectExamClicked(ActionEvent event) {


    }

    @FXML
    void submitBtnClicked(ActionEvent event) {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Feedback.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        String[] temp1 = examsDropdown.getValue().split(". ");
        int eid=Integer.parseInt(temp1[0]);
        Exam exam = (Exam)session.createQuery("FROM Exam where Id = :temp").setParameter("temp", eid).uniqueResult();

        int invID = 6;  //set this from singeton variable
        Invigilator inv = (Invigilator) session.createQuery("FROM Invigilator where ID = :temp").setParameter("temp", invID).uniqueResult();

        Feedback feedback = new Feedback();
        feedback.setFeedbackStatement(statementArea.getText());
        feedback.setExam(exam);
        feedback.setInvigilator(inv);
        session.save(feedback);
        trans.commit();


    }

}
