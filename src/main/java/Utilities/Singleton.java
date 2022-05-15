package Utilities;

import javafx.scene.layout.BorderPane;

public class Singleton {
    private static Singleton single_instance = null;


    BorderPane borderPane;

    private Singleton()
    {

    }
    // Static method to create instance of Utilities.Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }
    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

}
