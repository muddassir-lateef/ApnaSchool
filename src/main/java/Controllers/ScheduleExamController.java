package Controllers;

import Entity.Exam;
import Entity.Venue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;


public class ScheduleExamController {

    @FXML
    private Text addVenueLabel;

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> examsComboBox;
    @FXML
    private ComboBox<String> venueComboBox;
    @FXML
    private Button scheduleBtn;
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
        ObservableList<String> options2 = FXCollections.observableArrayList();
        List venues = session.createQuery("FROM Venue").list();
        for (Iterator iter = venues.iterator(); iter.hasNext(); ) {

            Venue venue = (Venue) iter.next();
            options2.add(venue.getId() + ". " + venue.getName());

        }

        trans.commit();

        examsComboBox.setItems(options1);
        venueComboBox.setItems(options2);

    }

    @FXML
    void scheduleBtnClicked(ActionEvent event) {
        String date1 = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (examsComboBox.getValue() != null && venueComboBox.getValue() != null)
        {

            String[] temp1 = examsComboBox.getValue().split(". ");
            int eid=Integer.parseInt(temp1[0]);
            String[] temp2 = venueComboBox.getValue().split(". ");
            int pid=Integer.parseInt(temp2[0]);

            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Exam.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();
            Venue v=session.get(Venue.class,pid);
            Exam e=session.get(Exam.class,eid);
            e.setVenue(v);
            e.setDate(date1);
            session.save(e);
            trans.commit();

        }


    }

}

