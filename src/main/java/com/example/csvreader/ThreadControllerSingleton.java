package com.example.csvreader;

public class ThreadControllerSingleton {

    private static int currentTicket;
    private static int totalTickets;

    private final static ThreadControllerSingleton INSTANCE = new ThreadControllerSingleton();

    private ThreadControllerSingleton(){this.currentTicket = 0;};

    public static ThreadControllerSingleton getInstance(){
        return INSTANCE;
    }

    public static int getTicket(){
        synchronized (ThreadControllerSingleton.class){
            if(currentTicket<totalTickets){
                return currentTicket++;
            }else{
                return -1;
            }
        }
    }

    public static void setTotalTickets(int amount){
        synchronized (ThreadControllerSingleton.class){
            totalTickets = amount;
        }
    }

    public static String getParameter(TicketClass flag, String switchCase){
        synchronized (ThreadControllerSingleton.class){
            switch (switchCase){
                case "Product":
                    return flag.getProduct();
                case "Issue":
                    return flag.getIssue();
                case "Company":
                    return flag.getCompany();
                case "State":
                    return flag.getState();
                case "ZIP Code":
                    return flag.getZipCode();
                case "Complaint ID":
                    return flag.getComplaintID();
                default:
                    return "abc";
            }
        }
    }

}
