package Entity;

import javax.persistence.*;

@Entity
public class Feedback {

    private String feedbackStatement;
    @OneToOne
    private Exam exam;

    @OneToOne
    private Invigilator invigilator;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Invigilator getInvigilator() {
        return invigilator;
    }

    public void setInvigilator(Invigilator invigilator) {
        this.invigilator = invigilator;
    }

    public String getFeedbackStatement() {
        return feedbackStatement;
    }

    public void setFeedbackStatement(String feedbackStatement) {
        this.feedbackStatement = feedbackStatement;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
