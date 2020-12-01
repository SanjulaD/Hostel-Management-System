/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

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
public class Student_MenuController implements Initializable {
    
    double xoffset, yoffset;

    @FXML
    private Button addNewStudent;
    @FXML
    private Button updateStudent;
    @FXML
    private Button deleteStudent;
    @FXML
    private Button allStudentLiving;
    @FXML
    private Button leavedStudent;
    @FXML
    private Button btn_back;
    @FXML
    private Button studentFees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addNewStudentAction(MouseEvent event) throws IOException {
        addNewStudent.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/New_Student.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void updateStudentAction(MouseEvent event) throws IOException {
        updateStudent.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Update_Student.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void deleteStudentAction(MouseEvent event) throws IOException {
        deleteStudent.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Delete_Student.fxml"));
        Scene scene = new Scene(root);        
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void studentFeeAction(MouseEvent event) throws IOException {
        deleteStudent.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_fee.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    @FXML
    private void allStudentAction(MouseEvent event) throws IOException {
        allStudentLiving.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/All_Student_Living.fxml"));
        Scene scene = new Scene(root);
        login.initStyle(StageStyle.TRANSPARENT);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    private void leavedStudentAction(MouseEvent event) throws IOException {
        leavedStudent.getScene().getWindow().hide();

        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/All_Student_Leaved.fxml"));
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



}
