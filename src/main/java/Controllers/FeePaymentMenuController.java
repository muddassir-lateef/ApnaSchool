

package Controllers;

        import Entities.Fee;
        import Entities.FeeRecord;
        import Entities.Student;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import org.hibernate.cfg.Configuration;

        import java.util.ArrayList;
        import java.util.List;

public class FeePaymentMenuController {

    @FXML
    private TextField feePaymentAmount;

    @FXML
    private Button payBtn;

    @FXML
    private TextField studentIdField;

    @FXML
    void PayButtonClicked(MouseEvent event) {
        //HANDLER LINES
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Student.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        Transaction trans= session.beginTransaction();
        int Amount = Integer.valueOf(feePaymentAmount.getText());
        List<Student> StudentList = session.createQuery("FROM Student").getResultList();
        List<FeeRecord> FR = new ArrayList<FeeRecord>();
        FeeRecord FR1 = new FeeRecord();
        Fee F = new Fee();
        for(int i=0;i<StudentList.size();i++)
        {
            if(StudentList.get(i).getId() == Integer.valueOf(studentIdField.getText()))
            {
                Student S = StudentList.get(i);
                FR = S.getFeeRecord();
            }
            FR.get(0).setPaidAmount(Amount);
        }
        session.save(FR.get(0));
        trans.commit();
        //---//
    }
    }

