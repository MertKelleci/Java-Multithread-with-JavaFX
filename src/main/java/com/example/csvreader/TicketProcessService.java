package com.example.csvreader;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;

public class TicketProcessService extends Service<ObservableList<DisplayTicket>> {
    ObservableList<DisplayTicket> displayTickets = FXCollections.observableArrayList();
    ObservableList<TicketClass> tickets = PassDataSingleton.getInstance().getTicketList();
    ThreadControllerSingleton controller = ThreadControllerSingleton.getInstance();
    TableView<DisplayThreads> threadTable;
    TableView<DisplayTicket> ticketTable;
    String threadName, selectedField, selectedColumn, queryText;
    int threshold;
    public TicketProcessService(TableView<DisplayThreads> threadTable, TableView<DisplayTicket> ticketTable, String threadName){
        PassDataSingleton flag = PassDataSingleton.getInstance();
        this.threadTable = threadTable;
        this.threadName = threadName;
        this.ticketTable = ticketTable;
        selectedField = flag.getSelectedField();
        selectedColumn = flag.getSelectedColumn();
        queryText = flag.getQueryText();
        threshold = flag.getThreshold();
    }

    @Override
    protected Task<ObservableList<DisplayTicket>> createTask() {
        return new Task<>() {
            String item1Parameter, item2Parameter, flag;
            @Override
            protected ObservableList<DisplayTicket> call() throws Exception {
                int currentTicket, matchAmount;
                double perc;
                TicketClass myTicket;
                String[] wordList;
                long time = System.nanoTime();
                currentTicket = controller.getTicket();
                while (currentTicket != -1){
                    myTicket = tickets.get(currentTicket);
                    if(selectedField!=null){
                        item1Parameter = controller.getParameter(myTicket,selectedField);
                        if(!(item1Parameter.toLowerCase().contains(queryText.toLowerCase()))){
                            currentTicket = controller.getTicket();
                            continue;
                        }
                    }

                    for (TicketClass secondTicket : tickets){
                        item1Parameter = controller.getParameter(myTicket, selectedColumn).trim().toLowerCase();
                        item2Parameter = controller.getParameter(secondTicket, selectedColumn).trim().toLowerCase();
                        if(item1Parameter.length() < item2Parameter.length()){
                            flag = item1Parameter;
                            item1Parameter = item2Parameter;
                            item2Parameter = flag;
                        }

                        wordList = item2Parameter.split(" ");
                        matchAmount = 0;
                        for(String word: wordList){
                            if(item1Parameter.contains(" "+word.trim()+" ") || item1Parameter.contains(" "+word.trim()) || item1Parameter.contains(word.trim()+" ")){
                                matchAmount++;
                            }
                        }
                        wordList = item1Parameter.split(" ");
                        perc =  matchAmount/(double)wordList.length;
                        perc *= 100;

                        if(perc>=(double) threshold){
                            System.out.println(item1Parameter+"\n"+item2Parameter+"\n"+matchAmount+"\n"+perc+"\n----------");
                            displayTickets.add(new DisplayTicket(myTicket, secondTicket, perc));
                        }
                    }

                    currentTicket = controller.getTicket();
                }

                long duration = (System.nanoTime() - time)/1000000;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (Platform.class){
                            for(DisplayTicket ticket : displayTickets){
                                ticketTable.getItems().add(ticket);
                            }
                        }
                    }
                });
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        threadTable.getItems().add(new DisplayThreads(threadName,"Execution Time: "+duration+"ms"));
                    }
                });
                return displayTickets;
            }
        };
    }
}
