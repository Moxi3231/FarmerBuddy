/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class HomeController implements Initializable {

    private Global gb = Global.getGlobal();
    @FXML
    private AnchorPane HomeAnchorPane;
    @FXML
    private AnchorPane ForumAnchorPane;
    @FXML
    private AnchorPane CropsAnchorPane;
    @FXML
    private AnchorPane FertilizerAnchorPane;
    @FXML
    private Tab addCrop;
    @FXML
    private Tab addFertilizer;
    @FXML
    private JFXTabPane mainTab;
    @FXML
    private AnchorPane addCropAnchorPane;
    @FXML
    private AnchorPane addFertilizerAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addPane(FertilizerAnchorPane,"FertilizersManager.fxml");
        addPane(ForumAnchorPane,"ForumManage.fxml");
        addPane(CropsAnchorPane,"CropManager.fxml");
        if (gb.user == null || gb.user.RoleId != 2) {
            mainTab.getTabs().remove(addCrop);
            mainTab.getTabs().remove(addFertilizer);
        } else {
            
            //changeToAddFertilizer();
            addPane(addFertilizerAnchorPane, "AddFertilizer.fxml");
            //changeToAddCrop();
            addPane(addCropAnchorPane,"AddCrop.fxml");
        }
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
        //textRA.setText(gb.user.toString());
        
        //        changeToFertilizer();
        //changeToHome();
    }
    public void addPane(AnchorPane pane,String s)
    {
          try {
            // TODO
            AnchorPane root = FXMLLoader.load(getClass().getResource(s));
            pane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
