/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import fb_classes.User_Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author moxan
 */
public class FarmerBuddy extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root, 900, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //seedM();
        launch(args);
        //seedM();
        DBContext.getDbContext().close();
    }

    public static void seedM() {
        try {
            User_Role ur = new User_Role("Admin");
            User_Role ur1 = new User_Role("Farmer");
            ur.RoleId = 2;
            ur1.RoleId = 1;
            DBContext db = DBContext.getDbContext();
            Session sess = db.getSession();
            Transaction tr = sess.beginTransaction();
            sess.save(ur1);
            sess.save(ur);
            tr.commit();
            sess.close();
            //db.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
