package com.example.csvreader;

import javafx.beans.property.SimpleStringProperty;

public class DisplayThreads {
    private SimpleStringProperty ThreadName;
    private SimpleStringProperty Runtime;

    public DisplayThreads(String ThreadName, String Runtime){
        this.ThreadName = new SimpleStringProperty(ThreadName);
        this.Runtime = new SimpleStringProperty(Runtime);
    }

    public String getThreadName() {
        return ThreadName.get();
    }

    public String getRuntime() {
        return Runtime.get();
    }
}
