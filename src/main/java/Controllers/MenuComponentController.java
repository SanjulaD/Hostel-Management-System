/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class MenuComponentController implements Initializable {

    double xoffset, yoffset;
            
    @FXML
    private Button btn_student;
    @FXML
    private Button btn_employee;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void student_btn_clicked(MouseEvent event) throws IOException {
        btn_student.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        
        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }

    @FXML
    private void employee_btn_clicked(MouseEvent event) throws IOException {
        btn_employee.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/Employee_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }

}
