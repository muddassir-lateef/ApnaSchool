package Controllers;

import Entity.Exam;
import Entity.Student;
import Utilities.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class RegisterExamController {

    @FXML
    private ComboBox<String> examsDropdown;

    @FXML
    private Button selectExamBtn;

    @FXML
    public void initialize() {
        ObservableList<String> options1 = FXCollections.observableArrayList();
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Exam.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        List exams = session.createQuery("FROM Exam").list();
        for (Iterator iter = exams.iterator(); iter.hasNext(); ) {

            Exam exm = (Exam) iter.next();
            options1.add(exm.getId() + ". " + exm.getName());

        }
        trans.commit();

        examsDropdown.setItems(options1);

    }

    @FXML
    void selectExamClicked(ActionEvent event) {
        if(examsDropdown.getValue() != null) {

            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Student.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();


            String[] temp1 = examsDropdown.getValue().split(". ");
            int eid=Integer.parseInt(temp1[0]);
            Exam ex = (Exam)session.createQuery("FROM Exam where Id = :tempID").setParameter("tempID", eid).uniqueResult();
            Singleton obj= Singleton.getInstance();
            int id=obj.currStud.getID();
            //int id = 3; ///set this from singleton variable
            //System.out.println("IDD:  " + id);
            Student stu = (Student) session.createQuery("FROM Student where ID = : tempID").setParameter("tempID", id).uniqueResult();

            stu.addExam(ex);
            ex.addStudent(stu);
            //session.save(stu);
            trans.commit();

        }

    }

}