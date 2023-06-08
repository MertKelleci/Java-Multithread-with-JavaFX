package com.example.csvreader;

import javafx.beans.property.SimpleStringProperty;

public class TicketClass {
    private SimpleStringProperty Product, Issue, Company, State, ZipCode, ComplaintID;

    public TicketClass(String product, String issue, String company, String state, String zipCode, String complaintID) {
        Product = new SimpleStringProperty(product.trim());
        Issue = new SimpleStringProperty(issue.trim());
        Company = new SimpleStringProperty(company.trim());
        State = new SimpleStringProperty(state.trim());
        ZipCode = new SimpleStringProperty(zipCode.trim());
        ComplaintID = new SimpleStringProperty(complaintID.trim());
    }

    public String getProduct() {
        return Product.get();
    }

    public String getIssue() {
        return Issue.get();
    }

    public String getCompany() {
        return Company.get();
    }

    public String getState() {
        return State.get();
    }

    public String getZipCode() {
        return ZipCode.get();
    }

    public String getComplaintID() {
        return ComplaintID.get();
    }
}
