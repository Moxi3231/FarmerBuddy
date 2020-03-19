/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.*;
import fb_classes.User;
//import com.mysql.jdbc.Connection;
import org.hibernate.*;
import java.sql.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final DBContext dbCon = DBContext.getDbContext();
    @FXML
    private JFXTextField email;
    private final Global gb = Global.getGlobal();
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gb.getDictionary().put("rootPane",rootPane);
        // TODO
        /*    try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/farmer_buddy", "root1", "root");
//here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  ");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
         */
    }

    public void login() {
        try {
            Session sess = dbCon.getSession();
            User u = new User();
            u.Email = email.getText();
            u.Password = password.getText();

            User temp = (User) sess.createQuery(
                    "from User u where u.Email = :email and u.Password= :pass")
                    .setString("email", u.Email).setString("pass", u.Password)
                    .uniqueResult();
            if (temp == null) {
               gb.showMessage("Invalid Credentials");
                return;
            }
            //User temp =(User) sess.get(User.class,  u);
            //System.out.println(temp);
            
            gb.setUser(temp);
            changeToHome();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void changeToHome() {
        AnchorPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            //Stage s=(Stage)rootPane.getScene().getWindow();
            //rootPane.ma 
            rootPane.getChildren().setAll(root);
            if(gb.getUser()!=null)
                gb.showMessage("Welcome "+gb.getUser().UserName);
//System.out.print("Adsdasdasdad");
            // s.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onRegisterClick() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        rootPane.getChildren().setAll(root);

    }
}
