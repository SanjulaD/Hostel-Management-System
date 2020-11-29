/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;


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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import DBConnection.DBHandler;
import Model.LeavedStudentDetails;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class All_Student_LeavedController implements Initializable {

    // Initialize observable list to database
    private ObservableList<LeavedStudentDetails> data;

    @FXML
    private TableColumn<LeavedStudentDetails, String> col_id;
    @FXML
    private TableView<LeavedStudentDetails> tableStudent;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_name;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_nsbmid;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_email;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_phonenumber;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_nic;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_address;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_g_name;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_g_tel;
    @FXML
    private TableColumn<LeavedStudentDetails, String> col_l_date;

    /**
     * Initializes the controller class.
     */
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @FXML
    private Button btn_back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM leaved_students");

            while (rs.next()) {
                // get string from db
                data.add(new LeavedStudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nsbmid.setCellValueFactory(new PropertyValueFactory<>("nsbmId"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_g_name.setCellValueFactory(new PropertyValueFactory<>("guardName"));
        col_g_tel.setCellValueFactory(new PropertyValueFactory<>("guardTel"));
        col_l_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }    

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }
    
}
