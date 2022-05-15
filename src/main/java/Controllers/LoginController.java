package Controllers;


import Entities.LoginDetails;
import Launcher.Main;
import Utilities.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
    void LoginBtnClicked(ActionEvent event)  {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(LoginDetails.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();

        LoginDetails usrs = (LoginDetails) session.createQuery("FROM LoginDetails U WHERE U.username = :userName AND U.password= :password").setParameter("userName", usrnameField.getText()).setParameter("password",pswdField.getText()).uniqueResult();

        if(usrs!=null && usrs.getType().equals("Staff"))
        {
            promptField.setText("Login Successful!");
            Stage stage = (Stage) promptField.getScene().getWindow();
            Main.loadStage(stage,"Main Menu/MainMenu.fxml");

        }
        else
        {
            promptField.setText("Login Failed!");


        }
        trans.commit();

    }






}