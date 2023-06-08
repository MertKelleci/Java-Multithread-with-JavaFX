package com.example.csvreader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PassDataSingleton {
    private ObservableList<TicketClass> ticketList;
    private String selectedColumn, selectedField, queryText;
    private int threadAmount, threshold;

    private final static PassDataSingleton INSTANCE = new PassDataSingleton();

    private PassDataSingleton() {}

    public static PassDataSingleton getInstance(){
        return INSTANCE;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getThreadAmount() {
        return threadAmount;
    }

    public void setThreadAmount(int threadAmount) {
        this.threadAmount = threadAmount;
    }

    public ObservableList<TicketClass> getTicketList() {
        ObservableList<TicketClass> returnClass = FXCollections.observableArrayList(ticketList);
        return returnClass;
    }

    public void setTicketList(ObservableList<TicketClass> ticketList) {
        this.ticketList = ticketList;
    }

    public String getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(String selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    public String getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(String selectedField) {
        this.selectedField = selectedField;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }
}
