package Utilities;

import Entity.Exam;
import Entity.Invigilator;
import Entity.Student;
import javafx.scene.layout.BorderPane;

public class Singleton {
    private static Singleton single_instance = null;

    // Declaring a variable of type String
    public Exam exam;
    public Student currStud;
    public Invigilator currInv;
    public BorderPane borderPane;

    private Singleton()
    {

    }
    // Static method
    // Static method to create instance of Utilities.Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }
}
