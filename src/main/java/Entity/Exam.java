package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exam {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int Id;
    private String Name;
    private String Course;
    private String date;

    @OneToOne
    private Venue venue=null;

    @ManyToMany @Column
    private List<Student> stud;

    @OneToOne
    private Teacher assignedTeacher=null;
    @OneToOne
    private Invigilator assignedInvigilator=null;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> question;


    public Invigilator getAssignedInvigilator() {
        return assignedInvigilator;
    }
    public Exam()
    {
        question=new ArrayList<Question>();
    }
    public void setAssignedInvigilator(Invigilator assignedInvigilator) {
        this.assignedInvigilator = assignedInvigilator;
    }

    public String getDate() {
        return date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public List<Question> getQuestion() {
        return question;
    }
    public void addQuestion(Question q) {
        this.question.add(q);
    }
    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }


    public List<Student> getStud() {
        return stud;
    }

    public void addStudent(Student stud) {
        this.stud.add(stud);
    }
}
