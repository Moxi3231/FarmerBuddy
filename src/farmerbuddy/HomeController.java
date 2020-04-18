/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import fb_classes.*;

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
    @FXML
    private JFXTextField username;
    @FXML
    private Tab croptab;
    @FXML
    private Tab fertilizertab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gb.getDictionary().put("fertilizerListObserver", new FertilizerListObserver());
        gb.getDictionary().put("cropListObserver", new CropListObserver());
                
            
        addPane(ForumAnchorPane, "ForumManage.fxml");
        addPane(FertilizerAnchorPane, "FertilizersManager.fxml");
        addPane(CropsAnchorPane, "CropManager.fxml");
         if (gb.getUser() != null) {
                    username.setText(gb.getUser().getUserName());
            }
        if (gb.getUser() == null || !gb.isUserAdmin()) {
            mainTab.getTabs().remove(addCrop);
            mainTab.getTabs().remove(addFertilizer);
           
            if(gb.getUser()==null)
            {
                mainTab.getTabs().remove(fertilizertab);
                mainTab.getTabs().remove(croptab);
            }
        } else {

            //changeToAddFertilizer();
            addPane(addFertilizerAnchorPane, "AddFertilizer.fxml");
            //changeToAddCrop();
            addPane(addCropAnchorPane, "AddCrop.fxml");
        }

    }

    public void addPane(AnchorPane pane, String s) {
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
