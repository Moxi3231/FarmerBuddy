/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import fb_classes.*;
import com.jfoenix.controls.*;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.AnchorPane;
import org.hibernate.*;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class FertilizersManagerController implements Initializable {

    @FXML
    private VBox vboxFertilizersManager;

    private Global gb = Global.getGlobal();
    private DBContext dbCon = DBContext.getDbContext();
    private FertilizerListObserver fertilizerListObserver;
    @FXML
    private AnchorPane fertilizerManager;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (gb.getDictionary().containsKey("fertilizerListObserver")) {
            fertilizerListObserver = (FertilizerListObserver) gb.getDictionary().get("fertilizerListObserver");
            fertilizerListObserver.setVerticalBox(vboxFertilizersManager);
            fertilizerListObserver.setAnchorPane(fertilizerManager);
            fertilizerListObserver.updateFertilizers();
        }
        if(gb.getDictionary().containsKey("cropListObserver"))
        {
            fertilizerListObserver.setCropObserverList((CropListObserver) gb.getDictionary().get("cropListObserver"));
            }
    }

}
