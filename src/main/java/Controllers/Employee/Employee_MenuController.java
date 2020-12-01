/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Employee;

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
public class Employee_MenuController implements Initializable {
    
    double xoffset, yoffset;

    @FXML
    private Button btn_back;
    @FXML
    private Button addNewEmployee;
    @FXML
    private Button updateEmployee;
    @FXML
    private Button deleteEmployee;
    @FXML
    private Button EmployeeFees;
    @FXML
    private Button allEmployeeLiving;
    @FXML
    private Button leavedEmployee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addNewEmployeeAction(MouseEvent event) throws IOException {
        addNewEmployee.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/New_Employee.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void updateEmployeeAction(MouseEvent event) throws IOException {
        updateEmployee.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/Update_Employee.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void deleteEmployeeAction(MouseEvent event) throws IOException {
        deleteEmployee.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/Delete_Employee.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void allEmployeeAction(MouseEvent event) throws IOException {
        allEmployeeLiving.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/All_Employee_Living.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void leavedEmployeeAction(MouseEvent event) throws IOException {
        leavedEmployee.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/All_Employee_Leaved.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MenuComponent.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                xoffset = event.getSceneX();
                yoffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                login.setX(event.getScreenX() - xoffset);
                login.setY(event.getScreenY() - yoffset);
            }
        });
    }
    @FXML
    private void employee_feeAction(MouseEvent event) throws IOException {
        EmployeeFees.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Employee/Employee_fee.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
}
