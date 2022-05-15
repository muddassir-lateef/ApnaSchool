package Controllers;

import Entity.Exam;
import Entity.Invigilator;
import Entity.Teacher;
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

import java.util.Iterator;
import java.util.List;

public class AssignPersonnelController {

    @FXML
    private ComboBox<String> ExamsComboBox;

    @FXML
    private Text addVenueLabel;

    @FXML
    private Button assignBtn;

    @FXML
    private ComboBox<String> personnelComboBox;

    @FXML
    private Button selectBtn;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    public void initialize() {

        // String table = "parties";
        ObservableList<String> options = FXCollections.observableArrayList();
        // partychoice.setValue("b");
        options.add("Invigilator");
        options.add("Teacher");

        typeComboBox.setItems(options);

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

        ExamsComboBox.setItems(options1);


    }


    @FXML
    void assignBtnClicked(ActionEvent event) {
        if (ExamsComboBox.getValue() != null && personnelComboBox.getValue() != null)
        {

            String[] temp1 = ExamsComboBox.getValue().split(". ");
            int eid=Integer.parseInt(temp1[0]);
            String[] temp2 = personnelComboBox.getValue().split(". ");
            int pid=Integer.parseInt(temp2[0]);

            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Exam.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();


            System.out.println(personnelComboBox.getValue());
            if(typeComboBox.getValue().equals("Teacher"))
            {

                Teacher t=session.get(Teacher.class,pid);
                Exam e=session.get(Exam.class,eid);
                e.setAssignedTeacher(t);
                session.save(e);

            }


            if(typeComboBox.getValue().equals("Invigilator"))
            {

                Invigilator i=session.get(Invigilator.class,pid);
                Exam e=session.get(Exam.class,eid);
                e.setAssignedInvigilator(i);
                session.save(e);

            }



            trans.commit();
        }


    }


    @FXML
    void selectBtnClicked(ActionEvent event) {
        ObservableList<String> options = FXCollections.observableArrayList();
        if (typeComboBox.getValue().equals("Invigilator")) {
            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Invigilator.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();
            List invigilators = session.createQuery("FROM Invigilator").list();
            for (Iterator iter = invigilators.iterator(); iter.hasNext(); ) {

                Invigilator inv = (Invigilator) iter.next();
                options.add(inv.getID()+". "+inv.getName());

            }
            trans.commit();
            personnelComboBox.setItems(options);


        }
        else if(typeComboBox.getValue().equals("Teacher"))
        {
            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Teacher.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();
            List teachers = session.createQuery("FROM Teacher").list();
            for (Iterator iter = teachers.iterator(); iter.hasNext(); ) {

                Teacher teach = (Teacher) iter.next();
                options.add(teach.getID()+". "+teach.getName());

            }
            trans.commit();

            personnelComboBox.setItems(options);

        }
    }

}
