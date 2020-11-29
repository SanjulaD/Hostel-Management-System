package Controllers.Student;



import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    public class Student_feeController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TextField studentID;
     @FXML
    private TextField studentFee;
    @FXML
    private Button submit;

 @FXML
    private ComboBox month;

 @FXML
    private ComboBox year;
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
     


         ObservableList<String> list = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
         month.setItems(list);
         
         ObservableList<String> list2 = FXCollections.observableArrayList("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
         year.setItems(list2);
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

    @FXML
    private void submitButtonAction(MouseEvent event) {
         String id = studentID.getText();
         if (studentID.equals("")
               ) {
            JOptionPane.showMessageDialog(null, "All Fields Are Required!");
            setTExtRefresh();
        } else {
            
             
            String insert = "INSERT INTO student_fee(studentid,year,fee,month)" + "VALUES(?,?,?,?)";
            connection = handler.connectDB();
            try {
                pst = connection.prepareStatement(insert);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, studentID.getText());
                pst.setString(2, year.getValue().toString() );
                pst.setString(3, studentFee.getText());
               pst.setString(4, month.getValue().toString());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Done!");
                setTExtRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(New_StudentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void setTExtRefresh() {
       
       
        
    }

}
