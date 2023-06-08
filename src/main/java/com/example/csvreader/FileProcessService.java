package com.example.csvreader;

import com.example.csvreader.TicketClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessService extends Service<ObservableList<TicketClass>> {

    private File file;

    public FileProcessService(File file) {
        this.file = file;
    }

    @Override
    protected Task<ObservableList<TicketClass>> createTask() {
        return new Task<>() {
            @Override
            protected ObservableList<TicketClass> call() throws Exception {
                BufferedReader reader = null;
                ObservableList<TicketClass> myList = FXCollections.observableArrayList();
                boolean first = true;
                try{
                    updateMessage("Dosya işleniyor");
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()){
                        String[] row = scanner.nextLine().split(",");
                        if(row.length != 6){
                            continue;
                        }
                        if(!first){
                            myList.add(new TicketClass(row[0],row[1],row[2],row[3],row[4],row[5]));
                            updateValue(myList);
                        }else{
                            first = false;
                        }
                    }
                    updateMessage("İşlem tamamlandı.");
                } catch (FileNotFoundException e){
                    updateMessage("Dosya bulunamadı");
                    e.printStackTrace();
                }catch (Exception e){
                    updateMessage("İşlem başarısız");
                    e.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return myList;
            }
        };
    }
}
