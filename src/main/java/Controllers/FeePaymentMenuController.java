

package Controllers;

        import Entities.Fee;
        import Entities.FeeRecord;
        import Entities.Student;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.text.Text;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import org.hibernate.cfg.Configuration;

        import java.util.ArrayList;
        import java.util.List;
        import java.text.SimpleDateFormat;
        import java.util.Date;
public class FeePaymentMenuController {

    @FXML
    private TextField feePaymentAmount;

    @FXML
    private Text payAlert;

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

    @FXML
    void payBtnClicked(MouseEvent event) {
        payAlert.setText("Amount paid successfully!");
        payAlert.setVisible(true);
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(FeeRecord.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session= sf.openSession();
        Transaction trans= session.beginTransaction();
        int Amount = Integer.valueOf(feePaymentAmount.getText());
        int stuID = Integer.valueOf(studentIdField.getText());
        Student stu = (Student)session.createQuery("FROM Student where Student.id = :temp").setParameter("temp", stuID).uniqueResult();
        if (stu == null){
            payAlert.setText("No student with the ID entered is found!");
        }
        else {
            FeeRecord tempRecord = new FeeRecord();
            tempRecord.setPaidAmount(Amount);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            tempRecord.setPaidDate(date);
            stu.setFeeRecord(tempRecord);
            session.save(stu);
            session.save(tempRecord);

            trans.commit();
            session.close();
        }
    }

}

