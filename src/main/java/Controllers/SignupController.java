package Controllers;

import Entity.*;
import Utilities.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//
public class SignupController {

    @FXML
    private ImageView backbtn;

    @FXML
    private TextField namefield;

    @FXML
    private Text promptField;

    @FXML
    private PasswordField pswdfield;

    @FXML
    private TextField regCodeField;

    @FXML
    private Button signupbtn;

    @FXML
    private TextField usernamefield;

    @FXML
    void backBtnClicked(MouseEvent event) {
        Stage stage = (Stage) promptField.getScene().getWindow();
        Main.loadStage(stage,"LoginScreen/login.fxml");
    }
    @FXML
    void SignupBtnClicked(ActionEvent event) {

        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(LoginDetails.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();

        String un = usernamefield.getText();
        String password = pswdfield.getText();
        List usrs = session.createQuery("FROM LoginDetails").list();
        boolean exists = false;
        boolean validReg = false;
        for(Iterator iter = usrs.iterator(); iter.hasNext(); ){
            LoginDetails usr =  (LoginDetails) iter.next();
            if (usr.getUsername().equals(un)){
                exists = true;
            }
        }
        if (exists){
            promptField.setText("Username is already taken");
        }
        else{
            LoginDetails tempLogin = new LoginDetails();
            tempLogin.setUsername(un);
            tempLogin.setPassword(password);
            if (regCodeField.getText().equals("121")){
                //teacher
                validReg = true;
                Teacher teacher = new Teacher();
                teacher.setLoginDetails(tempLogin);
                teacher.setName(namefield.getText());

                session.save(teacher);
                tempLogin.setType("Teacher");
                session.save(tempLogin);

                trans.commit();
            }
            else if (regCodeField.getText().equals("122")){
                //student
                validReg = true;
                Student student = new Student();
                student.setLoginDetails(tempLogin);
                student.setName(namefield.getText());
                session.save(student);
                tempLogin.setType("Student");
                session.save(tempLogin);
                trans.commit();

            }
            else if (regCodeField.getText().equals("123")){
                //staff
                validReg = true;
                Staff staff = new Staff();
                staff.setLoginDetails(tempLogin);
                staff.setName(namefield.getText());
                session.save(staff);
                tempLogin.setType("Staff");
                session.save(tempLogin);

                trans.commit();
            }
            else if (regCodeField.getText().equals("124")){
                //invigilator
                validReg = true;
                Invigilator inv = new Invigilator();
                inv.setLoginDetails(tempLogin);
                inv.setName(namefield.getText());
                session.save(inv);
                tempLogin.setType("Invigilator");
                session.save(tempLogin);

                trans.commit();
            }
            else {
                promptField.setText("Invalid registration Code");
            }
            if(!exists && validReg) {
                promptField.setText("Registration Successful!!");

            }

        }

    }
}
