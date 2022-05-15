package Controllers;

import Entity.Exam;
import Entity.Result;
import Utilities.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ResultsController {

    @FXML
    private ListView<String> examListView;

    @FXML
    private ListView<String> marksListView;

    public void initialize() {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Result.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();

        Singleton obj= Singleton.getInstance();
        int id = obj.currStud.getID();
        List results = session.createQuery("FROM Result where studentID = :temp").setParameter("temp", id).list();
        for (Iterator iter = results.iterator(); iter.hasNext(); ) {
            Result res = (Result) iter.next();
            Exam ex = (Exam) session.createQuery("FROM Exam where Id = :temp").setParameter("temp", res.getExamID()).uniqueResult();
            int attempted = res.getAttempted();
            int correct = res.getCorrect();
            float perc = ((float)correct/(float)attempted)*100;
            String perStr = Float.toString(perc);
            examListView.getItems().add(ex.getName());
            marksListView.getItems().add(perStr);
        }


    }

}
