/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import DBConnection.DBHandler;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class New_EmployeeController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TextField reg_txt_emp_username;
    @FXML
    private TextField reg_txt_emp_phnmb;
    @FXML
    private TextField reg_txt_emp_nic;
    @FXML
    private Button btn_reg_employee;
    @FXML
    private TextField reg_txt_emp_emgtel;

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
    private void registerButtonAction(MouseEvent event) {
        String name = reg_txt_emp_username.getText();
        String nic = reg_txt_emp_nic.getText();
        String tel = reg_txt_emp_emgtel.getText();
        String emgTel = reg_txt_emp_phnmb.getText();

        if (name.equals("")
                || nic.equals("")
                || tel.equals("")
                || emgTel.equals("")) {
            JOptionPane.showMessageDialog(null, "All Fields Are Required!");
            setTExtRefresh();
        } else {
            String insert = "INSERT INTO register_Employee(name,nic,tel,emg_tel)" + "VALUES(?,?,?,?)";
            connection = handler.connectDB();
            try {
                pst = connection.prepareStatement(insert);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, reg_txt_emp_username.getText());
                pst.setString(2, reg_txt_emp_nic.getText());
                pst.setString(3, reg_txt_emp_emgtel.getText());
                pst.setString(4, reg_txt_emp_phnmb.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registered!");
                setTExtRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(New_EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void setTExtRefresh() {
        reg_txt_emp_username.setText("");
        reg_txt_emp_nic.setText("");
        reg_txt_emp_emgtel.setText("");
        reg_txt_emp_phnmb.setText("");
    }

}
