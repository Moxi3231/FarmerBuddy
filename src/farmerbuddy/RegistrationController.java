/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fb_classes.*;
import java.io.IOException;
//import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.hibernate.*;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class RegistrationController implements Initializable {

    private DBContext dbCon = DBContext.getDbContext();
    private Session sess;
    private Transaction trac;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextArea errorArea;
    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField mobilenumber;

    @FXML
    private JFXTextField region;

    @FXML
    private JFXTextField district;

    @FXML
    private JFXTextField state;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Inserting Dummy Data
        //User u = new User();
        //u.District = "None";
        /*u.Email = "asdja@sfdj.com";
        u.PhoneNum = 784554545;
        u.Region = "None";
        u.UserName = "HelloWorld";
        u.State="gujarat";
        dbCon = new DBContext();
        this.sess = this.dbCon.getSession();
        this.trac = this.sess.beginTransaction();
        this.sess.save(u);
        this.trac.commit();
        dbCon.closeSession();
        dbCon.close();
         */

    }

    public void onBackTOLoginClick() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootPane.getChildren().setAll(root);
        dbCon.close();
        
    }

    public void Register() throws IOException {
        try
        {
            User u = new User();
            //System.out.println(district.getText());
            u.District = district.getText();
            //System.out.print(district.getText());
            u.Email = email.getText();
            u.PhoneNum =mobilenumber.getText();
            u.State = state.getText();
            u.UserName = name.getText();
            u.Region = region.getText();
            u.Password = password.getText();
            //System.out.println(u);
            Session sess1 = dbCon.getSession();
            //Transaction tr = sess1.beginTransaction();
            User_Role ur = new User_Role("Farmer");
            ur.RoleId =1;
            u.role = ur;
            //ur.users.add(u);
            //System.out.print("--");
            sess1.save(u);
            //tr.commit();
            sess1.close();
            //dbCon.close();
            onBackTOLoginClick();
        }
        catch(Exception  e)
        {
        System.out.println(e);
        errorArea.setText("Validation Failed");
//errorArea.setText(e.toString());
        }
    }
}
