package com.sharan.admin.adminOptions.viewStudent;

import com.sharan.admin.AdminSection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.*;

import static com.sharan.Main.fileName;

public class ViewStudent extends AdminSection {
    private static final String DB_NAME = "LMSDatabase.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:";
    private static final String TABLE_NAME = "librarianDetails";


    public ObservableList<DataViewer> observableList;


    private Connection connection = null;
    private Statement statement = null;

    @FXML
    protected TableView<DataViewer> tableView;

    @FXML
    private TableColumn<DataViewer, String> id;

    @FXML
    private TableColumn<DataViewer, String> name;

    @FXML
    private TableColumn<DataViewer, String> address;

    @FXML
    private TableColumn<DataViewer, String> city;

    @FXML
    private TableColumn<DataViewer, Integer> contactNo;

    @FXML
    private TableColumn<DataViewer, String> emailAddress;

    @FXML
    public void initialize() {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'UserMaster.fxml'.";
        id.setCellValueFactory((new PropertyValueFactory<>("id")));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        try {
            openConnection();
            getDataFromDatabase();
            closeDatabaseConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME);
        statement = connection.createStatement();
    }


    private void closeDatabaseConnection() {
        try {
            if (!connection.isClosed()) {
                statement.close();
                connection.close();
            } else {
                System.out.println("Error Closing DataBase");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDataFromDatabase() throws Exception {

        observableList = FXCollections.observableArrayList();
        ResultSet resultSet = statement.executeQuery("SELECT ID, Name, Email, Address, City, Contact FROM " + TABLE_NAME);
        while (resultSet.next()) {
            DataViewer dataViewer = new DataViewer();
            dataViewer.id.setValue(resultSet.getString("ID"));
//            System.out.println(dataViewer.getSSPid());
            dataViewer.name.setValue(resultSet.getString("Name"));
//            System.out.println(dataViewer.getSSPname());
            dataViewer.email.setValue(resultSet.getString("Email"));
            dataViewer.address.setValue(resultSet.getString("Address"));
            dataViewer.city.setValue(resultSet.getString("City"));
            dataViewer.contact.set(resultSet.getInt("Contact"));
            observableList.add(dataViewer);
        }
        tableView.setItems(observableList);
    }

    @FXML
    public void handleButtonClickGoBack() throws IOException {
        fileName="../../adminSection.fxml";
        BorderPane pane= FXMLLoader.load(getClass().getResource(fileName));
        viewLibrarian.getChildren().setAll(pane);
    }
}







