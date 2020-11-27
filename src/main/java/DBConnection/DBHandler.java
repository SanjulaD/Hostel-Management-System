/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DBHandler {
    
    Connection con = null;

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management", "root", "");
            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
            return null;
        }
    }
    
}
