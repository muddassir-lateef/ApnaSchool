package Entities;

import javax.persistence.*;

@Entity
@Table(name = "ExamMarks")
public class ExamMarks {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "marksID", nullable = false)
    private int marksID;

}
