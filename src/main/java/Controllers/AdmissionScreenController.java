package Controllers;

import Entities.Fee;
import Entities.FeeRecord;
import Entities.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

import java.util.Date;


public class AdmissionScreenController {

    @FXML
    private Button TestButton;

    @FXML
    private TextField guardianName = null;

    @FXML
    private TextField studentAddress = null;

    @FXML
    private TextField studentAge = null;

    @FXML
    private TextField studentCNIC = null;

    @FXML
    private TextField studentClass = null;

    @FXML
    private TextField studentName = null;

    @FXML
    private TextField studentPhoneNumber = null;

    @FXML
    private TextField studentScholarship = null;

    @FXML
    private Text topText;

    @FXML
    void OnTestButtonClicked(MouseEvent event) {

        //Hibernate Session Factory Commands//


        if (nullFieldCheck() == true) {
//__________________________LINES FOR THE HANDLER START HERE________________________//
            //HIBERNATE COMMANDS//
            Configuration Con = new Configuration();
            Con.configure().addAnnotatedClass(Student.class);
            SessionFactory sf = Con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();
            //--//

            //Creating student objects and setting its values from data entered into the text fields
            Student tempStudent = new Student();
            tempStudent.setAddress(studentAddress.getText());
            tempStudent.setName(studentName.getText());
            tempStudent.setAge(Integer.valueOf(studentAge.getText()));
            tempStudent.setClassID(studentClass.getText());
            tempStudent.setCNIC(studentCNIC.getText());
            tempStudent.setGuardian_Name(guardianName.getText());
            tempStudent.setPhone_Number(studentPhoneNumber.getText());
            tempStudent.setScholarShip(Integer.valueOf(studentScholarship.getText()));
            //--//
            //SAVING AND COMMITTING
            FeeRecord F = new FeeRecord();
            Fee f = new Fee();
            f.setFinesFee(100);
            f.setSecurityFee(100);
            f.setTotalFee(200);
            f.setTutionFee(300);
            f.setTotalFee(700);
            F.setFee(f);
            Date D = new Date(1,1,1);
            F.setFeeMonth(D);
            Date D1 = new Date(1,2,1);
            F.setDueDate(D1);
            tempStudent.setFeeRecord(F);
            session.save(tempStudent);
            trans.commit();
            //--//
//______________LINES FOR THE HANDLER END HERE__________________________//
        } else if (nullFieldCheck() == false) {
            System.out.println("Invalid Entries");
        }
        //---//

        //Saving the Student object


        //---/

        //Committing Transaction

        //---//
    }
    //Checks if all the fields are NOT NULL
    //---//
    public boolean nullFieldCheck() {
        TextField[] textFieldArray =
                {studentPhoneNumber, studentName, studentAge, studentClass,
                 studentCNIC, studentAddress, studentPhoneNumber, studentClass
                };
        for (int i = 0; i < textFieldArray.length; i++) {
            if (textFieldArray[i].getText().isEmpty() == true) {
                return false;
            }
        }
        return true;
    }
    //--//
    @FXML
    public void initialize() {
        studentAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    studentAge.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        studentScholarship.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    studentScholarship.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        studentCNIC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    studentCNIC.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        studentPhoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    studentPhoneNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }


        });

    }





}
