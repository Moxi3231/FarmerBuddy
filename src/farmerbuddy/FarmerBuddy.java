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
import fb_classes.*;
import javafx.scene.image.Image;
/**
 *
 * @author moxan
 */
public class FarmerBuddy extends Application {
    
    @Override
    public void start(Stage stage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root, 900, 600);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setTitle("Farmer-Buddy");
        //stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTIMGHFTC-QZllL53Fsm7_UoNRs7PTZkDczxMRbe2djRD-ya619"));
        stage.getIcons().add(new Image(getClass().getResource("fbuddy.jfif").toString()));
        stage.show();
        }catch(Exception e)
        {
            System.err.println(e);
        }
    }

    @Override
    public void stop() throws Exception {
       
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        DBContext.getDbContext().close();
        System.exit(0);
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
            ur.RoleId = UserRoles.UserAdmin;
            ur1.RoleId = UserRoles.UserNormal;
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
