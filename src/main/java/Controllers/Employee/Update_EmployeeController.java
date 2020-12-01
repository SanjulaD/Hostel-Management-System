/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Employee;

import Controllers.Student.New_StudentController;
import Model.EmployeeDetails;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import DBConnection.DBHandler;
import Model.StudentDetails;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Update_EmployeeController implements Initializable {

    @FXML
    private Button btn_refersh;
    @FXML
    private Button btn_back;
    @FXML
    private TableView<EmployeeDetails> tableEmployee;
    @FXML
    private TableColumn<EmployeeDetails, String> col_id;
    @FXML
    private TableColumn<EmployeeDetails, String> col_name;
    @FXML
    private TableColumn<EmployeeDetails, String> col_phonenumber;
    @FXML
    private TableColumn<EmployeeDetails, String> col_emgtel;

    /**
     * Initializes the controller class.
     */
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    // Initialize observable list to database
    private ObservableList<EmployeeDetails> data;
    
    @FXML
    private Button btn_update_employee;
    @FXML
    private TextField emp_id;
    @FXML
    private TextField reg_txt_emp_emgtel;
    @FXML
    private TextField reg_txt_emp_username;
    @FXML
    private TextField reg_txt_emp_nic;
    @FXML
    private TextField reg_txt_emp_phnmb;
    @FXML
    private TableColumn<EmployeeDetails, String> col_nic;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
    }    

    @FXML
    private void updateEmployeeButtonAction(MouseEvent event) {
        String id = emp_id.getText();
        String name = reg_txt_emp_username.getText();
        String nic = reg_txt_emp_nic.getText();
        String tel = reg_txt_emp_phnmb.getText();
        String emg_tel = reg_txt_emp_emgtel.getText();

        if (name.equals("")
                || id.equals("")
                || nic.equals("")
                || emg_tel.equals("")
                || tel.equals("")) {
            JOptionPane.showMessageDialog(null, "Some Fields are missing!");
        } else {
            String update = "UPDATE register_Employee set id = ?, name = ?, nic = ?,tel = ?,emg_tel = ? where id = '" + id + "' ";
            connection = handler.connectDB();
            try {
                pst = connection.prepareStatement(update);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, emp_id.getText());
                pst.setString(2, reg_txt_emp_username.getText());
                pst.setString(3, reg_txt_emp_nic.getText());
                pst.setString(4, reg_txt_emp_phnmb.getText());
                pst.setString(5, reg_txt_emp_emgtel.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Update Student!!");
                autoRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(New_StudentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    private void refreshButtionClickAction(MouseEvent event) {
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_employee");

            while (rs.next()) {
                // get string from db
                data.add(new EmployeeDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

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

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        EmployeeDetails Employee = tableEmployee.getSelectionModel().getSelectedItem();
        if (Employee == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = Employee.getId();
            String name = Employee.getName();
            String nic = Employee.getNic();
            String tel = Employee.getTel();
            String emg_tel = Employee.getEmgTel();

            emp_id.setText(id);
            reg_txt_emp_username.setText(name);
            reg_txt_emp_nic.setText(nic);
            reg_txt_emp_phnmb.setText(tel);
            reg_txt_emp_emgtel.setText(emg_tel);
        }
    }
    
    private void autoRefresh() {
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_employee");

            while (rs.next()) {
                // get string from db
                data.add(new EmployeeDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

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

        tableEmployee.setItems(null);
        tableEmployee.setItems(data);
    }

}
