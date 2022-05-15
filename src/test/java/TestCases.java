import Entity.Exam;
import Entity.Feedback;
import Entity.Question;
import Entity.Student;
import org.junit.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestCases {

    @Test
    public void Login() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Exam.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        Student stu = (Student)session.createQuery("FROM Student where ID = 3").uniqueResult();
        assertEquals(stu.getName().equals("Student"), true);
    }

    @Test
    public void Signup() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Exam.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Student temp = new Student();
        temp.setName("TestingStudent");
        session.save(temp);
        Student stu = (Student)session.createQuery("FROM Student where name = :tempname").setParameter("tempname", "TestingStudent").uniqueResult();
        assertEquals(stu.getName().equals("TestingStudent"), true);

    }


    @Test
    public void AddQuestion() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Question.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Question temp = new Question();
        temp.setStatement("Testing Question");
        session.save(temp);
        Question stu = (Question) session.createQuery("FROM Question where statement = :tempname").setParameter("tempname", "Testing Question").uniqueResult();
        assertEquals(stu.getStatement().equals("Testing Question"), true);

    }


    @Test
    public void AddFeedback() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Feedback.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Feedback temp = new Feedback();
        temp.setFeedbackStatement("Testing Statement");
        session.save(temp);
        Feedback stu = (Feedback) session.createQuery("FROM Feedback where feedbackStatement = :tempname").setParameter("tempname", "Testing Statement").uniqueResult();
        assertEquals(stu.getFeedbackStatement().equals("Testing Statement"), true);
    }




}
