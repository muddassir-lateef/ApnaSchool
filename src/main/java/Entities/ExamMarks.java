package Entities;

import javax.persistence.*;

@Entity
@Table(name = "ExamMarks")
public class ExamMarks {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "marksID", nullable = false)
    private int marksID;
    @Column(name = "studentName", nullable = false)
    private String studentName;
    @Column(name = "percentage", nullable = false)
    private String percentage;
    @Column(name = "subject", nullable = false)
    private String subject;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
