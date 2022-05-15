package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "studentId", nullable = false)
    private int studentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCNIC() {
        return cnic;
    }

    public void setCNIC(String CNIC) {
        this.cnic = CNIC;
    }

    public String getPhone_Number() {
        return phoneNumber;
    }

    public void setPhone_Number(String phone_Number) {
        this.phoneNumber = phone_Number;
    }

    public String getGuardian_Name() {
        return guardianName;
    }

    public void setGuardian_Name(String guardian_Name) {
        this.guardianName = guardian_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String Class) {
        this.classID = Class;
    }

    public int getScholarShip() {
        return ScholarShip;
    }

    public void setScholarShip(int scholarShip) {
        ScholarShip = scholarShip;
    }
    public List<FeeRecord> getFeeRecord() {
        return feeRecord;
    }

    public void setFeeRecord(FeeRecord F) {
        feeRecord.add(F);
    }

    public int getId() {
        return studentId;
    }

    public void setId(int id) {
        this.studentId = id;
    }

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Age", nullable = false)
    private int age;

    @Column(name = "CNIC", nullable = false)
    private String cnic;

    @Column(name = "Phone_Number", nullable = false)
    private String phoneNumber;

    @Column(name = "Guardian_Name", nullable = false)
    private String guardianName;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Class", nullable = false)
    private String classID;

    @Column(name = "ScholarShip", nullable = false)
    private int ScholarShip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentID")
    private List<FeeRecord> feeRecord;

    public Student()
    {
        feeRecord = new ArrayList<FeeRecord>();
    }
}



