package com.example.csvreader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    File file;
    private Service<ObservableList<TicketClass>> service;
    private Stage stage;
    private Scene scene;
    private int threadAmount = 1, threshold = 0;
    String selectedColumn, selectedField;
    private String[] choiceboxItems = {"Product","Issue","Company","State","ZIP Code","Complaint ID"};

    @FXML
    ProgressBar progressBar;

    @FXML
    ChoiceBox<String> fieldChoicebox;

    @FXML
    TextField fieldRuleText;

    @FXML
    RadioButton productButton, issueButton, companyButton, stateButton, complaintButton, zipButton;

    @FXML
    Spinner<Integer> threadSpinner, comparisonThreshold;

    @FXML
    TableColumn<TicketClass, String> productColumn, issueColumn, companyColumn, stateColumn, complaintColumn, zipColumn;
    @FXML
    Label fileLabel;

    @FXML
    TableView<TicketClass> fileTable;

    @FXML
    Button chooseFile, startButton;

    @FXML
    void getFile(MouseEvent event) {
        file = fileChooser.showOpenDialog(new Stage());
        service = new FileProcessService(file);
        fileTable.itemsProperty().bind(service.valueProperty());
        fileLabel.textProperty().bind(service.messageProperty());
        progressBar.visibleProperty().bind(service.runningProperty());
        if (service.getState() == Service.State.SUCCEEDED){
            service.reset();
            service.start();
        } else if(service.getState() == Service.State.READY){
            service.start();
        }
    }

    @FXML
    void selectColumns(){
        if(productButton.isSelected()){
            selectedColumn = "Product";
        } else if (issueButton.isSelected()) {
            selectedColumn = "Issue";
        } else if (companyButton.isSelected()) {
            selectedColumn = "Company";
        } else if (stateButton.isSelected()) {
            selectedColumn = "State";
        } else if (zipButton.isSelected()) {
            selectedColumn = "ZIP Code";
        } else if (complaintButton.isSelected()) {
            selectedColumn = "Complaint ID";
        }
    }

    public void selectField(ActionEvent event){
        selectedField = fieldChoicebox.getValue();
    }

    @FXML
    public void startProgress(ActionEvent event) throws IOException {
        PassDataSingleton data = PassDataSingleton.getInstance();
        data.setQueryText(fieldRuleText.getText());
        data.setSelectedColumn(selectedColumn);
        data.setSelectedField(selectedField);
        data.setThreadAmount(threadAmount);
        data.setTicketList(service.getValue());
        data.setThreshold(threshold);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPage.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String currentPath = new File("").getAbsolutePath();
        fileChooser.setInitialDirectory(new File(currentPath));
        progressBar.setVisible(false);

        SpinnerValueFactory<Integer> threadValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        threadValueFactory.setValue(1);
        threadSpinner.setValueFactory(threadValueFactory);

        SpinnerValueFactory<Integer> thresholdValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        thresholdValueFactory.setValue(0);
        comparisonThreshold.setValueFactory(thresholdValueFactory);

        threadSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                threadAmount = threadSpinner.getValue();
            }
        });
        comparisonThreshold.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                threshold = comparisonThreshold.getValue();
            }
        });
        fieldChoicebox.getItems().setAll(choiceboxItems);
        fieldChoicebox.setOnAction(this::selectField);
        productColumn.setCellValueFactory(new PropertyValueFactory<>("Product"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("Issue"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("Company"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));
        complaintColumn.setCellValueFactory(new PropertyValueFactory<>("ComplaintID"));
    }
}