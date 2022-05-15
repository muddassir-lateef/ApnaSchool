package Entities;

import javax.persistence.*;

@MappedSuperclass
public class User {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;

    @OneToOne
    private LoginDetails loginDetails;

    private String name;

    public int getID() {
        return ID;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
