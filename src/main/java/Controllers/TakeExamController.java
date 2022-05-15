package Controllers;

import Entity.Option;
import Entity.Question;
import Entity.Result;
import Utilities.Main;
import Utilities.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class TakeExamController {

    @FXML
    private Button nextBtn;

    @FXML
    private ComboBox<String> optionComboBox;

    @FXML
    private Label questionNumberField;
    @FXML
    private Label prompt;

    @FXML
    private TextArea statementArea;

    @FXML
    private Button submitBtn;
    List<Question> questions=null;
    int n=0;    //correct attempted
    int correct=0;

    @FXML
    public void initialize() {



       // typeComboBox.setItems(options);


        Singleton obj= Singleton.getInstance();

        questions=obj.exam.getQuestion();
        statementArea.setText(questions.get(n).getStatement());
        questionNumberField.setText((n+1)+" ");
        ObservableList<String> options1 = FXCollections.observableArrayList();
        List<Option> op=questions.get(n).getOptions();
        for(int i=0;i<op.size();i++)
        {
           options1.add(op.get(i).getStatement());
       }
        optionComboBox.setItems(options1);




    }

    @FXML
    void nextBtnClicked(ActionEvent event) {
        Singleton obj= Singleton.getInstance();

        questions=obj.exam.getQuestion();
        if(questions.get(n).getCorrectOption().equals(optionComboBox.getValue()))
        {
            correct++;
        }
        if(questions.size()==n+1)
        {
            prompt.setText("All Questions Answered.");
        }
        else {
            n++;
            questions = obj.exam.getQuestion();
            statementArea.setText(questions.get(n).getStatement());
            questionNumberField.setText((n + 1) + " ");
            ObservableList<String> options1 = FXCollections.observableArrayList();
            List<Option> op = questions.get(n).getOptions();
            for (int i = 0; i < op.size(); i++) {
                options1.add(op.get(i).getStatement());
            }
            optionComboBox.setItems(options1);
        }

    }

    @FXML
    void submitBtnClicked(ActionEvent event) {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Result.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();


        Singleton obj= Singleton.getInstance();
        Result result = new Result();
        if (n==0){
            questions=obj.exam.getQuestion();
            if(questions.get(n).getCorrectOption().equals(optionComboBox.getValue()))
            {
                correct++;
            }
        }
        result.setAttempted(obj.exam.getQuestion().size());
        result.setCorrect(correct);
        result.setExamID(obj.exam.getId());
        result.setStudentID(obj.currStud.getID());
        result.setExamID(obj.exam.getId());
        session.save(result);
        obj.currStud.addResult(result);
        trans.commit();
        loadUI("Main Menu (Student)/View Results Screen/results.fxml");
    }

    private void loadUI(String path){
        Parent root = null;
        try{
            URL url= Main.class.getClassLoader().getResource(path);
            root = FXMLLoader.load(url);

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());

        }
        Singleton obj= Singleton.getInstance();
        obj.borderPane.setCenter(root);
    }

}
