package Controllers;

import Entity.Exam;
import Entity.Student;
import Utilities.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EnrolledExamsController {

    @FXML
    private ListView<String> examList;
    @FXML
    public void initialize() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Exam.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Singleton obj= Singleton.getInstance();
        int id=obj.currStud.getID();
       // int id = 3; ///set this from singleton variable
        Student stu = (Student) session.createQuery("FROM Student where ID = : tempID").setParameter("tempID", id).uniqueResult();
        List<Exam> temp = stu.getExams();
        for (int i=0; i<temp.size(); i++){
            examList.getItems().add(temp.get(i).getName());
        }

    }

}
