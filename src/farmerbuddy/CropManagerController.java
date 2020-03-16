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

    
    private void addCropButton()
    {
        if(gb.user==null || gb.user.RoleId!=2)
            return;
        vboxCropManager.getChildren().clear();
        HBox hbox = new HBox();
        hbox.setMinSize(800.0, 100.0);
        hbox.setPrefSize(800.0, 100.0);
        hbox.getStyleClass().add("hb");
        //DropShadow ef = new DropShadow();
        //ef.setOffsetY(1);
        //hbox.setEffect(ef);
        
        hbox.setAlignment(Pos.CENTER);
        JFXButton btn = new JFXButton("Add Crop");
        btn.setOnAction((ActionEvent e)->{
            //OpenDialogBox("AddCrop.fxml");
        });
        hbox.getChildren().add(btn);
        vboxCropManager.getChildren().add(hbox);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO]
        //int i;
        addCropButton();
        for (int i = 0; i < 10; i++) {
            HBox h1 = new HBox();
            h1.setMinSize(850, 200);
            h1.setPrefSize(850, 200);
            h1.getStyleClass().add("shadowHBOX");
            
            VBox v1 = new VBox(0);
            VBox v2 = new VBox(5);

            HBox.setMargin(v1, new Insets(10.0));
            HBox.setMargin(v2, new Insets(10.0));
            v1.setPrefSize(625, 200);
            v1.setAlignment(Pos.CENTER);

            v2.setMinWidth(225);
            v2.setPrefSize(225, 200);
            v2.setAlignment(Pos.CENTER);

            Label l = new Label("Web Devlopment Internship");
            Label l1 = new Label("Vadodara");
            l.setStyle("-fx-font-size: " + 25 + "px;");
            l1.setStyle("-fx-font-size: " + 17 + "px;");

            v1.getChildren().add(l);
            v1.getChildren().add(l1);
            v1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, new Insets(10))));
            v2.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, new Insets(10))));
            JFXButton update = new JFXButton("Update");

            JFXButton del = new JFXButton("Delete");
            JFXButton app = new JFXButton("View");

            del.setButtonType(JFXButton.ButtonType.RAISED);
            app.setButtonType(JFXButton.ButtonType.RAISED);
            update.setButtonType(JFXButton.ButtonType.RAISED);

            update.setPrefSize(115, 35);
            del.setPrefSize(115, 35);
            app.setPrefSize(115, 35);

            v2.getChildren().add(update);
            v2.getChildren().add(del);
            v2.getChildren().add(app);

            h1.getChildren().add(v1);
            h1.getChildren().add(v2);
            addShadowedBox(h1);
            vboxCropManager.getChildren().add(h1);
        }
    }

    private void addShadowedBox(HBox hb) {

        DropShadow e = new DropShadow();
        e.setWidth(10);
        e.setHeight(10);
        e.setOffsetX(0);
        e.setOffsetY(0);
        e.setRadius(2);
        e.setColor(Color.DODGERBLUE);
        hb.setEffect(e);
    }

}