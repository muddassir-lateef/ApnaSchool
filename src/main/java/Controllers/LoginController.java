package Controllers;

import Entity.Invigilator;
import Entity.LoginDetails;
import Entity.Student;
import Exceptions.InvalidLoginException;
import Utilities.Main;
import Utilities.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private Text promptField;

    @FXML
    private PasswordField pswdField;

    @FXML
    private Button signupBtn;

    @FXML
    private TextField usrnameField;

    @FXML
    private Button closeBtn;

    @FXML
    void CloseBtnClicked(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void LoginBtnClicked(ActionEvent event) throws InvalidLoginException {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(LoginDetails.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();

        LoginDetails usrs = (LoginDetails) session.createQuery("FROM LoginDetails U WHERE U.username = :userName AND U.password= :password").setParameter("userName", usrnameField.getText()).setParameter("password",pswdField.getText()).uniqueResult();

        if(usrs!=null)
        {
            promptField.setText("Login Successful!");
            Stage stage = (Stage) promptField.getScene().getWindow();
            if(usrs.getType().equals("Staff"))
            {
                Main.loadStage(stage,"Main Menu (Staff)/staffMenu.fxml");
            }
            else if(usrs.getType().equals("Teacher"))
            {
                Main.loadStage(stage,"Main Menu (Teacher)/teacherMenu.fxml");
            }
            else if(usrs.getType().equals("Student"))
            {
                Singleton obj= Singleton.getInstance();

                List students = session.createQuery("FROM Student").list();
                for (Iterator iter = students.iterator(); iter.hasNext(); ) {

                    Student exm = (Student) iter.next();
                    if(exm.getLoginDetails().getUsername().equals(usrnameField.getText()))
                    {
                        obj.currStud=exm;

                    }

                }
                Main.loadStage(stage,"Main Menu (Student)/studentMenu.fxml");

            }
            else if(usrs.getType().equals("Invigilator"))
            {

                Singleton obj= Singleton.getInstance();

                List inv = session.createQuery("FROM Invigilator").list();
                for (Iterator iter = inv.iterator(); iter.hasNext(); ) {

                    Invigilator exm = (Invigilator) iter.next();
                    if(exm.getLoginDetails().getUsername().equals(usrnameField.getText()))
                    {
                        obj.currInv=exm;

                    }

                }
                Main.loadStage(stage,"Main Menu (Invigilator)/invigilatorMenu.fxml");
            }

        }
        else
        {
            promptField.setText("Login Failed!");
            throw new InvalidLoginException("User does not exist");

        }
        trans.commit();

    }

    @FXML
    void SignUpBtnClicked(ActionEvent event) {
        Stage stage = (Stage) promptField.getScene().getWindow();
        Main.loadStage(stage,"Signup Screen/signup.fxml");
    }




}