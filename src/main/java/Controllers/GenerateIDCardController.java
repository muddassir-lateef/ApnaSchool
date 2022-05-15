package Controllers;

import Entities.Student;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLineAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class GenerateIDCardController {

    private Session session;
    private Student stu;

    @FXML
    private Rectangle IDCard;

    @FXML
    private TextField IDEnteredField;

    @FXML
    private Label addressField;

    @FXML
    private Label contactField;

    @FXML
    private TextField cardPathField;

    @FXML
    private Button generateBtn;

    @FXML
    private Label nameField;

    @FXML
    private Label studentIDField;

    @FXML
    private Text topText;

    @FXML
    private Label IDtag;

    @FXML
    private Label nameTag;

    @FXML
    private Label addressTag;

    @FXML
    private Label contactTag;

    @FXML
    private Label invalidIDLabel;

    @FXML
    private Button downloadBtn;

    @FXML
    public void initialize() {
        Configuration Con = new Configuration();
        Con.configure();
        SessionFactory sf = Con.buildSessionFactory();
        session = sf.openSession();
        Transaction trans = session.beginTransaction();
    }

    @FXML
    void generateBtnClicked(ActionEvent event) {

        int studentID = (Integer.valueOf(IDEnteredField.getText()));
        Student tempStudent = (Student) session.createQuery("FROM Student U WHERE U.id = :stuID").setParameter("stuID", studentID).uniqueResult();
        if(tempStudent != null) {
            this.stu = tempStudent;
            invalidIDLabel.setVisible(false);
            IDCard.setVisible(true);
            IDtag.setVisible(true);
            nameTag.setVisible(true);
            addressTag.setVisible(true);
            contactTag.setVisible(true);
            studentIDField.setText(String.valueOf(tempStudent.getId()));
            studentIDField.setVisible(true);
            nameField.setText(tempStudent.getName());
            nameField.setVisible(true);
            addressField.setText(tempStudent.getAddress());
            addressField.setVisible(true);
            contactField.setText(tempStudent.getPhone_Number());
            contactField.setVisible(true);
            cardPathField.setVisible(true);
            downloadBtn.setVisible(true);
        }
        else{
            downloadBtn.setVisible(false);
            invalidIDLabel.setText("Invalid Student ID");
            invalidIDLabel.setVisible(true);
            IDtag.setVisible(false);
            IDCard.setVisible(false);
            nameTag.setVisible(false);
            addressTag.setVisible(false);
            contactTag.setVisible(false);
            studentIDField.setVisible(false);
            nameField.setVisible(false);
            addressField.setVisible(false);
            contactField.setVisible(false);
            cardPathField.setVisible(false);
        }
    }

    @FXML
    void downloadBtnClicked(MouseEvent event) throws java.io.FileNotFoundException {
        //saving to resources/StudentIDCards
        String dest = "C:\\Users\\DELL\\IdeaProjects\\SchoolManagementSystem\\src\\main\\resources\\StudentIDCards\\card.pdf";
        //String dest = "card.pdf";
        if (cardPathField != null) {
            dest = cardPathField.getText();
            PdfWriter writer = new PdfWriter(dest);
            // Creating a PdfDocument
            //PdfDocument pdfDoc = new PdfDocument(writer);
            // Creating a PdfDocument object
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Creating a new page
            PdfPage pdfPage = pdfDoc.addNewPage();

            Document document = new Document(pdfDoc);

            /*document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));*/

            float [] pointColumnWidths = {200F, 200F};
            Table table = new Table(pointColumnWidths);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            //table.setVerticalAlignment(VerticalAlignment.MIDDLE);
            // Populating row 1 and adding it to the table
            Cell c1 = new Cell();                        // Creating cell 1
            c1.add("Name");                              // Adding name to cell 1
            c1.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(38, 129, 255)));      // Setting background color
            c1.setBorder(Border.NO_BORDER);              // Setting border
            c1.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
            table.addCell(c1);                           // Adding cell 1 to the table

            Cell c2 = new Cell();
            c2.add(this.stu.getName());
            c2.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(120, 176, 255)));
            c2.setBorder(Border.NO_BORDER);
            c2.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c2);

            // Populating row 2 and adding it to the table
            Cell c3 = new Cell();
            c3.add("Id");
            c3.setBackgroundColor(Color.WHITE);
            c3.setBorder(Border.NO_BORDER);
            c3.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c3);

            Cell c4 = new Cell();
            c4.add(String.valueOf(this.stu.getId()));
            c4.setBackgroundColor(Color.WHITE);
            c4.setBorder(Border.NO_BORDER);
            c4.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c4);

            // Populating row 3 and adding it to the table
            Cell c5 = new Cell();
            c5.add("Address");
            c5.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(38, 129, 255)));
            c5.setBorder(Border.NO_BORDER);
            c5.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c5);

            Cell c6 = new Cell();
            c6.add(this.stu.getAddress());
            c6.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(120, 176, 255)));
            c6.setBorder(Border.NO_BORDER);
            c6.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c6);

            Cell c7 = new Cell();
            c7.add("Contact");
            c7.setBackgroundColor(Color.WHITE);
            c7.setBorder(Border.NO_BORDER);
            c7.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c7);

            Cell c8 = new Cell();
            c8.add(this.stu.getPhone_Number());
            c8.setBackgroundColor(Color.WHITE);
            c8.setBorder(Border.NO_BORDER);
            c8.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c8);

            document.add(table);

            document.close();

        }


    }
}
