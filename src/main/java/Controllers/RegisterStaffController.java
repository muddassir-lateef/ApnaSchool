package Controllers;


import Entities.LoginDetails;
import Entities.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class RegisterStaffController {

    @FXML
    private TextField namefield;

    @FXML
    private Text promptField1;
    @FXML
    private Text promptField;

    @FXML
    private PasswordField pswdfield;

    @FXML
    private Button signupbtn;

    @FXML
    private TextField usernamefield;

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
                //staff
                validReg = true;
                Staff staff = new Staff();
                staff.setLoginDetails(tempLogin);
                staff.setName(namefield.getText());
                session.save(staff);
                tempLogin.setType("Staff");
                session.save(tempLogin);

                trans.commit();

            if(!exists && validReg) {
                trans.commit();
                promptField.setText("Registration Successful!!");

            }

        }

    }
}
