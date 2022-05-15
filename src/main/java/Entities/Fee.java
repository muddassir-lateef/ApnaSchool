package Entities;

import javax.persistence.*;

@Entity
@Table(name = "Fee")
public class Fee {

    public int getFeeRecordId() {
        return feeId;
    }

    public void setFeeRecordId(int feeRecordId) {
        this.feeId = feeRecordId;
    }

    public int getSecurityFee() {
        return securityFee;
    }

    public void setSecurityFee(int securityFee) {
        this.securityFee = securityFee;
    }

    public int getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(int tutionFee) {
        this.tutionFee = tutionFee;
    }

    public int getFinesFee() {
        return finesFee;
    }

    public void setFinesFee(int finesFee) {
        this.finesFee = finesFee;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @Column(name = "securityFee", nullable = true)
    private int securityFee;

    @Column(name = "tutionFee", nullable = true)
    private int tutionFee;

    @Column(name = "finesFee", nullable = true)
    private int finesFee;

    @Column(name = "totalFee", nullable = true)
    private int totalFee;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FeeId", nullable = false)
    private int feeId;


}
