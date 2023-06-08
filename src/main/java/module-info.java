module com.example.csvreader {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csvreader to javafx.fxml;
    exports com.example.csvreader;
//    exports com.example.csvreader.FirstPage;
//    opens com.example.csvreader.FirstPage to javafx.fxml;
//    exports com.example.csvreader.SecondPage;
//    opens com.example.csvreader.SecondPage to javafx.fxml;
}