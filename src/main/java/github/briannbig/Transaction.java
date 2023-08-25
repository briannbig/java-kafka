package github.briannbig;

import java.io.Serializable;

public class Transaction implements Serializable {
    private static int id = 1;

    private String sourceAcc;
    private String destAcc;

    private double amount;

    public Transaction(){}


    public Transaction(String sourceAcc, String destAcc, double amount) {
        this.sourceAcc = sourceAcc;
        this.destAcc = destAcc;
        this.amount = amount;
        id++;
    }

    public Transaction(String sourceAcc, String destAcc) {
        this(sourceAcc, destAcc, 0.0);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Transaction.id = id;
    }

    public String getSourceAcc() {
        return sourceAcc;
    }

    public void setSourceAcc(String sourceAcc) {
        this.sourceAcc = sourceAcc;
    }

    public String getDestAcc() {
        return destAcc;
    }

    public void setDestAcc(String destAcc) {
        this.destAcc = destAcc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + getId() + '\'' +
                "sourceAcc='" + sourceAcc + '\'' +
                ", destAcc='" + destAcc + '\'' +
                ", amount=" + amount +
                '}';
    }
}