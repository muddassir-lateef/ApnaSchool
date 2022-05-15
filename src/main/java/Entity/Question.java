package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    private String statement;
    private String correctOption;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options;

public Question()
{
    options=new ArrayList<Option>();
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    public void addOptions(Option op) {
        this.options.add(op);
    }

}
