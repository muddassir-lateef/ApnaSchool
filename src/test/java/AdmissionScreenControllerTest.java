
import Entities.Fee;
import Entities.FeeRecord;
import Entities.Student;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class AdmissionScreenControllerTest extends TestCase {

    public void testOnTestButtonClicked()
    {
        Configuration Con = new Configuration();
        Con.configure().addAnnotatedClass(Student.class);
        SessionFactory sf = Con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        //--//

        //Creating student objects and setting its values from data entered into the text fields
        Student tempStudent = new Student();
        tempStudent.setAddress("A");
        tempStudent.setName("A");
        tempStudent.setAge(Integer.valueOf(1));
        tempStudent.setClassID("A");
        tempStudent.setCNIC("A");
        tempStudent.setGuardian_Name("A");
        tempStudent.setPhone_Number("A");
        tempStudent.setScholarShip(0);
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
        Date D = new Date(100,1,1);
        F.setFeeMonth(D);
        Date D1 = new Date(100,2,1);
        F.setDueDate(D1);
        tempStudent.setFeeRecord(F);
        session.save(tempStudent);
        trans.commit();

        int id = tempStudent.getId();
        boolean flag = false;
        if(id > 0)
        {
            flag = true;
        }
        assertEquals(true , flag);
    }
}