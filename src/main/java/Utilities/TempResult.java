package Utilities;

public class TempResult {
    String course;
    String percentage;

    public TempResult(String name,String p){
        course = name;
        percentage = p;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}