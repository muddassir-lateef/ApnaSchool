package Controllers;

import Entity.Venue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AddVenueController {

    @FXML
    private Text addVenueLabel;

    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    void addBtnClicked(ActionEvent event) {
        Configuration con = new Configuration();
        con.configure().addAnnotatedClass(Venue.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session  = sf.openSession();
        Transaction trans = session.beginTransaction();
        Venue venue=new Venue();
        venue.setName(nameField.getText());
        venue.setAddress(addressField.getText());
        session.save(venue);

        trans.commit();
    }

}
