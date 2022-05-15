package Controllers;

import Entity.Exam;
import Entity.Feedback;
import Entity.Invigilator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ExamFeedbackController {

    @FXML
    private Button ViewBtn;

    @FXML
    private Text addVenueLabel;

    @FXML
    private Button examSelectBtn;

    @FXML
    private ComboBox<String> examsComboBox;

    @FXML
    private TextArea feedbackField;

    @FXML
    private TextField invigilatorField;

    public void initialize() {
        ObservableList<String> options1 = FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Invigilator.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        List exams = session.createQuery("FROM Exam").list();
        for (Iterator iter = exams.iterator(); iter.hasNext(); ) {

            Exam exm = (Exam) iter.next();
            options1.add(exm.getId() + ". " + exm.getName());

        }
        trans.commit();

        examsComboBox.setItems(options1);

    }

    @FXML
    void viewBtnClicked(ActionEvent event) {
        ObservableList<String> options1 = FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Invigilator.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        String[] temp1 = examsComboBox.getValue().split(". ");
        int eid=Integer.parseInt(temp1[0]);

        Exam exam = (Exam)session.createQuery("FROM Exam where Id = :temp").setParameter("temp", eid).uniqueResult();

        List fb=  session.createQuery("FROM Feedback where exam.Id = :temp").setParameter("temp", exam.getId()).list();

        String temp="";
        for (Iterator iter = fb.iterator(); iter.hasNext(); ) {

            temp+='\n';
            Feedback f = (Feedback) iter.next();
            temp += f.getFeedbackStatement();

        }
        invigilatorField.setText(exam.getAssignedInvigilator().getName());
        feedbackField.setText(temp);


    }

}
