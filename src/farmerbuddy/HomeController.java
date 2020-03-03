/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class HomeController implements Initializable {
    private Global gb = Global.getGlobal();
    @FXML
    private JFXTextArea textRA;
    @FXML
    private AnchorPane HomeAnchorPane;
    @FXML
    private AnchorPane ForumAnchorPane;
    @FXML
    private AnchorPane CropsAnchorPane;
    @FXML
    private AnchorPane FertilizerAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*  try {
            // TODO
           // AnchorPane root = FXMLLoader.load(getClass().getResource("HomeMain.fxml"));
           // HomeAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }
    */
      textRA.setText(gb.user.toString());
      changeToForum();
      //changeToCrops();
      //changeToFertilizer();
      //changeToHome();
    }    
    public void changeToForum()
    {   
     try {
            // TODO
            AnchorPane root = FXMLLoader.load(getClass().getResource("ForumManage.fxml"));
            ForumAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }   
    }
    public void changeToHome()
    {
     try {
            // TODO
            AnchorPane root = FXMLLoader.load(getClass().getResource("HomeMain.fxml"));
            HomeAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }      
    }
    public void changeToCrops()
    {
        try {
            // TODO
            AnchorPane root = FXMLLoader.load(getClass().getResource("CropMain.fxml"));
            CropsAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }   
    }
    public void changeToFertilizer()
    {
        try {
            // TODO
            AnchorPane root = FXMLLoader.load(getClass().getResource("FertilizerMain.fxml"));
            FertilizerAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e)
        {
           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }   
    }
   
    
}
