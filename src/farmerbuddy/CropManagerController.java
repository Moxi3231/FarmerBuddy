/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import fb_classes.Question;
import fb_classes.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.Transaction;
import fb_classes.*;
/**
 * FXML Controller class
 *
 * @author moxan
 */
public class CropManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Global gb = Global.getGlobal();
    private DBContext dbCon = DBContext.getDbContext();
    @FXML
    private AnchorPane CropAnchorPaneM;
    @FXML
    private VBox vboxCropManager;

    private CropListObserver cropListObserver;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO]
        //int i;
        if(gb.getDictionary().containsKey("cropListObserver"))
        {
            cropListObserver = (CropListObserver) gb.getDictionary().get("cropListObserver");
            cropListObserver.setVerticalBox(vboxCropManager);
            cropListObserver.setAnchorPane(CropAnchorPaneM);
            cropListObserver.updateCrop();
        }
        
    }


}
