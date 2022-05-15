package Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FeeRecord")
public class FeeRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feeRecordId", nullable = false)
    private int feeRecordId;

    @Column(name = "paidDate", nullable = true)
    private Date paidDate;

    @Column (name = "dueDate", nullable = false)
    private Date dueDate;

    @Column (name = "feeMonth", nullable = false)
    private Date feeMonth;

    @Column(name = "paidAmount" , nullable = true)
    private int paidAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FeeID")
    private Fee fee;

    public int getId() {
        return feeRecordId;
    }

    public int getFeeRecordId() {
        return feeRecordId;
    }

    public void setFeeRecordId(int feeRecordId) {
        this.feeRecordId = feeRecordId;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getFeeMonth() {
        return feeMonth;
    }

    public void setFeeMonth(Date feeMonth) {
        this.feeMonth = feeMonth;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount += paidAmount;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public void setId(int id) {
        this.feeRecordId = feeRecordId;
    }
}
