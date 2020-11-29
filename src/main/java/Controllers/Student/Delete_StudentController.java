/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import DBConnection.DBHandler;
import Model.StudentDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private String leavedID;
    @FXML
    private TextField reg_txt_guardname;
    @FXML
    private TextField reg_txt_guardtel;
    @FXML
    private TextField reg_txt_email;
    @FXML
    private TextField reg_txt_phnmb;
    @FXML
    private TextField reg_txt_address;
    @FXML
    private TableColumn<StudentDetails, String> col_email;
    @FXML
    private TableColumn<StudentDetails, String> col_phonenumber;
    @FXML
    private TableColumn<StudentDetails, String> col_address;
    @FXML
    private TableColumn<StudentDetails, String> col_g_name;
    @FXML
    private TableColumn<StudentDetails, String> col_g_tel;
    @FXML
    private DatePicker dateLeaved;
    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
        reg_txt_nic.setDisable(true);
        reg_txt_id.setDisable(true);
        reg_txt_guardname.setDisable(true);
        reg_txt_guardtel.setDisable(true);
        reg_txt_email.setDisable(true);
        reg_txt_phnmb.setDisable(true);
        reg_txt_address.setDisable(true);
        reg_txt_username.setDisable(true);
        reg_txt_nsbmid.setDisable(true);
    }

    @FXML
    private void deleteStudentButtonAction(MouseEvent event) {
        updateLeavedStudentDB();
        
        String delete = "DELETE from register_students where id = ?";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, reg_txt_id.getText());
            leavedID = reg_txt_id.getText();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Student");
            clearFields();
            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void updateLeavedStudentDB() {
        String query = "INSERT INTO leaved_students (id,name,nsbmid,email,phonenumber,nic,address,guardname,guardtel,leave_date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, reg_txt_id.getText());
            pst.setString(2, reg_txt_username.getText());
            pst.setString(3, reg_txt_nsbmid.getText());
            pst.setString(4, reg_txt_email.getText());
            pst.setString(5, reg_txt_phnmb.getText());
            pst.setString(6, reg_txt_nic.getText());
            pst.setString(7, reg_txt_address.getText());
            pst.setString(8, reg_txt_guardname.getText());
            pst.setString(9, reg_txt_guardtel.getText());
            pst.setString(10, ((TextField)dateLeaved.getEditor()).getText());
            pst.executeUpdate();

            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void autoRefresh() {
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
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_g_name.setCellValueFactory(new PropertyValueFactory<>("guardName"));
        col_g_tel.setCellValueFactory(new PropertyValueFactory<>("guardTel"));

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
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_g_name.setCellValueFactory(new PropertyValueFactory<>("guardName"));
        col_g_tel.setCellValueFactory(new PropertyValueFactory<>("guardTel"));

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
            reg_txt_email.setText(email);
            reg_txt_phnmb.setText(phonenumber);
            reg_txt_nic.setText(nic);
            reg_txt_address.setText(address);
            reg_txt_guardname.setText(g_name);
            reg_txt_guardtel.setText(g_tel);
        }
    }

    private void clearFields() {
        reg_txt_username.setText("");
        reg_txt_nsbmid.setText("");
        reg_txt_email.setText("");
        reg_txt_phnmb.setText("");
        reg_txt_nic.setText("");
        reg_txt_address.setText("");
        reg_txt_guardname.setText("");
        reg_txt_guardtel.setText("");
        dateLeaved.setValue(null);
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
