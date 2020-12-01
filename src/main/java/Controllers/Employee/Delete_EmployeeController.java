/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import DBConnection.DBHandler;
import Model.EmployeeDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Delete_EmployeeController implements Initializable {

    private String leavedID;
            
    @FXML
    private Button btn_update_employee;
    @FXML
    private Button btn_refersh;
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
    private TableView<EmployeeDetails> tableEmployee;
    @FXML
    private TableColumn<EmployeeDetails, String> col_id;
    @FXML
    private TableColumn<EmployeeDetails, String> col_name;
    @FXML
    private TableColumn<EmployeeDetails, String> col_nic;
    @FXML
    private TableColumn<EmployeeDetails, String> col_phonenumber;
    @FXML
    private TableColumn<EmployeeDetails, String> col_emgtel;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    
    // Initialize observable list to database
    private ObservableList<EmployeeDetails> data;
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @FXML
    private DatePicker dateLeaved;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
        emp_id.setDisable(true);
        reg_txt_emp_username.setDisable(true);
        reg_txt_emp_nic.setDisable(true);
        reg_txt_emp_phnmb.setDisable(true);
        reg_txt_emp_emgtel.setDisable(true);
    }    

    @FXML
    private void deleteEmployeeButtonAction(MouseEvent event) {
        updateLeavedEmployeeDB();
        
        String delete = "DELETE from register_employee where id = ?";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, emp_id.getText());
            leavedID = emp_id.getText();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Student");
            clearFields();
            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void clearFields() {
        emp_id.setText("");
        reg_txt_emp_username.setText("");
        reg_txt_emp_nic.setText("");
        reg_txt_emp_phnmb.setText("");
        reg_txt_emp_emgtel.setText("");
        dateLeaved.setValue(null);
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
    
    private void updateLeavedEmployeeDB() {
        String query = "INSERT INTO leaved_employee (id,name,nic,tel,emg_tel,leave_date) VALUES(?,?,?,?,?,?)";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, emp_id.getText());
            pst.setString(2, reg_txt_emp_username.getText());
            pst.setString(3, reg_txt_emp_nic.getText());
            pst.setString(4, reg_txt_emp_phnmb.getText());
            pst.setString(5, reg_txt_emp_emgtel.getText());
            pst.setString(6, ((TextField)dateLeaved.getEditor()).getText());
            pst.executeUpdate();

            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
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
