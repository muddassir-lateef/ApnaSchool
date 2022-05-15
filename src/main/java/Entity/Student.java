package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends User {

    @ManyToMany
    @Column(unique = false)
    private List<Exam> exams;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @OneToMany
    private  List<Result> results;


    public void addExam(Exam e){
        exams.add(e);
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void addResult(Result res){
        this.results.add(res);
    }
}