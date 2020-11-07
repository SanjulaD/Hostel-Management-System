/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.DBHandler;
import Model.StudentDetails;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Delete_StudentController implements Initializable {

    @FXML
    private TableView<StudentDetails> tableStudent;
    @FXML
    private TableColumn<StudentDetails, String> col_id;
    @FXML
    private TableColumn<StudentDetails, String> col_name;
    @FXML
    private TableColumn<StudentDetails, String> col_nsbmid;
    @FXML
    private TableColumn<StudentDetails, String> col_nic;
    @FXML
    private TextField reg_txt_nic;
    @FXML
    private TextField reg_txt_id;
    @FXML
    private TextField reg_txt_username;
    @FXML
    private TextField reg_txt_nsbmid;
    @FXML
    private Button btn_delete_student;
    @FXML
    private Button btn_refersh;

    // Initialize observable list to database
    private ObservableList<StudentDetails> data;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
    }

    @FXML
    private void deleteStudentButtonAction(MouseEvent event) {
        String delete = "DELETE from register_students where id = ?";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, reg_txt_id.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Student");
            reg_txt_id.setText("");
            reg_txt_username.setText("");
            reg_txt_nsbmid.setText("");
            reg_txt_nic.setText("");
        
            autoRefresh();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void autoRefresh()
    {
       connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_students");

            while (rs.next()) {
                // get string from db
                data.add(new StudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nsbmid.setCellValueFactory(new PropertyValueFactory<>("nsbmId"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        tableStudent.setItems(null);
        tableStudent.setItems(data); 
    }

    @FXML
    private void refreshButtionClickAction(MouseEvent event) {
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_students");

            while (rs.next()) {
                // get string from db
                data.add(new StudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nsbmid.setCellValueFactory(new PropertyValueFactory<>("nsbmId"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        if (student == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String id = student.getId();
            String name = student.getName();
            String nsbmid = student.getNsbmId();
            String email = student.getEmail();
            String phonenumber = student.getPhoneNumber();
            String nic = student.getNIC();
            String address = student.getAddress();
            String g_name = student.getGuardName();
            String g_tel = student.getGuardTel();

            reg_txt_id.setText(id);
            reg_txt_username.setText(name);
            reg_txt_nsbmid.setText(nsbmid);
            reg_txt_nic.setText(nic);
        }
    }

}
