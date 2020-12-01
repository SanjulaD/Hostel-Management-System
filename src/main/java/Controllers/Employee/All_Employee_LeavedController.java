/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Employee;

import DBConnection.DBHandler;
import Model.EmployeeDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import DBConnection.DBHandler;
import Model.LeavedEmployeeDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class All_Employee_LeavedController implements Initializable {

    @FXML
    private TableView<LeavedEmployeeDetails> tableEmployee;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_id;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_name;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_nic;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_phonenumber;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_emgtel;
    @FXML
    private TableColumn<LeavedEmployeeDetails, String> col_leav_date;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<LeavedEmployeeDetails> data;
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM leaved_employee");

            while (rs.next()) {
                // get string from db
                data.add(new LeavedEmployeeDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        col_emgtel.setCellValueFactory(new PropertyValueFactory<>("emgTel"));
        col_leav_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableEmployee.setItems(null);
        tableEmployee.setItems(data);
    }    

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/Employee_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }
    
}
