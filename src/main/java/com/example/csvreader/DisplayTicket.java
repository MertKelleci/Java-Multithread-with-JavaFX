package com.example.csvreader;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DisplayTicket {
    private SimpleStringProperty Product1, Issue1, Company1, State1, ZipCode1, ComplaintID1;
    private SimpleStringProperty Product2, Issue2, Company2, State2, ZipCode2, ComplaintID2;
    private SimpleDoubleProperty Comparison;

    public DisplayTicket(TicketClass class1, TicketClass class2, double Comparison)
    {
        this.Product1 = new SimpleStringProperty(class1.getProduct());
        this.Issue1 = new SimpleStringProperty(class1.getIssue());
        this.Company1 = new SimpleStringProperty(class1.getCompany());
        this.State1 = new SimpleStringProperty(class1.getState());
        this.ZipCode1 = new SimpleStringProperty(class1.getZipCode());
        this.ComplaintID1 = new SimpleStringProperty(class1.getComplaintID());

        this.Product2 = new SimpleStringProperty(class2.getProduct());
        this.Issue2 = new SimpleStringProperty(class2.getIssue());
        this.Company2 = new SimpleStringProperty(class2.getCompany());
        this.State2 = new SimpleStringProperty(class2.getState());
        this.ZipCode2 = new SimpleStringProperty(class2.getZipCode());
        this.ComplaintID2 = new SimpleStringProperty(class2.getComplaintID());

        this.Comparison = new SimpleDoubleProperty(Comparison);
    }

    
    public void setComparison(float comparison) {
        this.Comparison = new SimpleDoubleProperty(comparison);
    }

    public double getComparison() {
        return Comparison.get();
    }

    public String getProduct1() {
        return Product1.get();
    }
    public String getIssue1() {
        return Issue1.get();
    }
    public String getCompany1() {
        return Company1.get();
    }

    public String getState1() {
        return State1.get();
    }

    public String getZipCode1() {
        return ZipCode1.get();
    }


    public String getComplaintID1() {
        return ComplaintID1.get();
    }


    public String getProduct2() {
        return Product2.get();
    }

    public String getIssue2() {
        return Issue2.get();
    }

    public String getCompany2() {
        return Company2.get();
    }


    public String getState2() {
        return State2.get();
    }

    public String getZipCode2() {
        return ZipCode2.get();
    }

    public String getComplaintID2() {
        return ComplaintID2.get();
    }
}
