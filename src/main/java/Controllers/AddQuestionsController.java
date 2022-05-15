package Controllers;

import Entity.Exam;
import Entity.Option;
import Entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddQuestionsController {

    @FXML
    private Button addOptionBtn;

    @FXML
    private Button addQuestionBtn;



    @FXML
    private ComboBox<String> correctOptComboBox;

    @FXML
    private ComboBox<String> examsDropdown;

    @FXML
    private TextField optionField;


    @FXML
    private TextArea statementArea;

    private List<Option> options=new ArrayList<Option>();
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
    void addOptionClicked(ActionEvent event) {
        if(!optionField.getText().isEmpty())
        {

            Option opt=new Option(optionField.getText());
            options.add(opt);
        }
        ObservableList<String> corOps=FXCollections.observableArrayList();
        for(int i=0;i<options.size();i++)
        {
            corOps.add(options.get(i).getStatement());

        }
        correctOptComboBox.setItems(corOps);
        optionField.setText(" ");

    }

    @FXML
    void addQuestionClicked(ActionEvent event) {
        if (examsDropdown.getValue() != null && !statementArea.getText().equals("") && options.size()!=0 && correctOptComboBox.getValue()!=null)
        {

            String[] temp1 = examsDropdown.getValue().split(". ");
            int eid=Integer.parseInt(temp1[0]);

            Configuration con = new Configuration();
            con.configure().addAnnotatedClass(Exam.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();
            Exam e=session.get(Exam.class,eid);
            Question q=new Question();
            q.setStatement(statementArea.getText());
            q.setCorrectOption(correctOptComboBox.getValue());

            for(int i=0;i<options.size();i++)
            {
                session.save(options.get(0));
                q.addOptions(options.get(i));

            }
            session.save(q);
           // trans.commit();
            e.addQuestion(q);
           session.save(e);
           trans.commit();
            ObservableList<String> corOps=FXCollections.observableArrayList();
            correctOptComboBox.setItems(corOps);


           options.clear();
           statementArea.setText(" ");
        }



    }


}
