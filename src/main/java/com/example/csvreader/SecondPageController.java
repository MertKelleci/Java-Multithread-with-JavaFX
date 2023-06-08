package com.example.csvreader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class SecondPageController implements Initializable {


    private Stage stage;
    private Scene scene;
    @FXML
    TableView<DisplayTicket> fileTable;
    @FXML
    TableView<DisplayThreads> threadTable;
    @FXML
    TableColumn<DisplayTicket, String> product1Column, issue1Column, company1Column, state1Column, zip1Column, complaint1Column, product2Column, issue2Column, company2Column, state2Column, zip2Column, complaint2Column;
    @FXML
    TableColumn<DisplayTicket, Float> comparisonColumn;

    @FXML
    TableColumn<DisplayThreads, String> threadColumn, execTimeColumn;

    @FXML
    ProgressBar progressBar;

    @FXML
    Button switchPagesButton;

    private ArrayList<TicketProcessService> ticketThreads = new ArrayList<>();

    @FXML
    public void switchPages(ActionEvent event) throws IOException{
        Platform.exit();
//        for(TicketProcessService service : ticketThreads){
//            service.reset();
//        }
//
//        ticketThreads.clear();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
//        scene = new Scene(fxmlLoader.load(), 1280, 720);
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PassDataSingleton data = PassDataSingleton.getInstance();
        product1Column.setCellValueFactory(new PropertyValueFactory<>("Product1"));
        issue1Column.setCellValueFactory(new PropertyValueFactory<>("Issue1"));
        company1Column.setCellValueFactory(new PropertyValueFactory<>("Company1"));
        state1Column.setCellValueFactory(new PropertyValueFactory<>("State1"));
        zip1Column.setCellValueFactory(new PropertyValueFactory<>("ZipCode1"));
        complaint1Column.setCellValueFactory(new PropertyValueFactory<>("ComplaintID1"));

        product2Column.setCellValueFactory(new PropertyValueFactory<>("Product2"));
        issue2Column.setCellValueFactory(new PropertyValueFactory<>("Issue2"));
        company2Column.setCellValueFactory(new PropertyValueFactory<>("Company2"));
        state2Column.setCellValueFactory(new PropertyValueFactory<>("State2"));
        zip2Column.setCellValueFactory(new PropertyValueFactory<>("ZipCode2"));
        complaint2Column.setCellValueFactory(new PropertyValueFactory<>("ComplaintID2"));

        comparisonColumn.setCellValueFactory(new PropertyValueFactory<>("Comparison"));
        threadColumn.setCellValueFactory(new PropertyValueFactory<>("ThreadName"));
        execTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Runtime"));
        progressBar.setVisible(false);
        ThreadControllerSingleton singleton = ThreadControllerSingleton.getInstance();

        singleton.setTotalTickets(data.getTicketList().size());
        for (int i = 0; i<data.getThreadAmount(); i++){
            ticketThreads.add(new TicketProcessService(threadTable, fileTable, "Thread "+(i+1)));
            ticketThreads.get(i).start();
        }
        progressBar.visibleProperty().bind(ticketThreads.get(0).runningProperty());
    }
}
