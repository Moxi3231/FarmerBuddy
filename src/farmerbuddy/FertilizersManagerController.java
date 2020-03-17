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
import org.hibernate.*;
/**
 * FXML Controller class
 *
 * @author moxan
 */
public class FertilizersManagerController implements Initializable {

    @FXML
    private VBox vboxFertilizersManager;

    private Global gb =Global.getGlobal();
    private DBContext dbCon = DBContext.getDbContext();
    private List<Fertilizer> fertilizers;
    /**
     * Initializes the controller class.
     */
    private void updateFertilizerList()
    {
        fillFertilizerList();
        if(fertilizers.isEmpty())
            return;
        gb.getDictionary(). put("fertilizers_list", fertilizers);
        for(Fertilizer ftemp:fertilizers)
        {
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

            Label l = new Label("Fertilizer Name: "+ftemp.fname);
            double fprice = 0;
            if(ftemp.fertilizerPrice!=null)
                fprice = ftemp.fertilizerPrice.price;
            Label lfprice;
            lfprice = new Label("Fertilizer Price: "+ String.valueOf(fprice));
            
            Label l1 = new Label("Contents");
            lfprice.setStyle("-fx-font-size: 25px;");
            l.setStyle("-fx-font-size: " + 25 + "px;");
            l1.setStyle("-fx-font-size: " + 17 + "px;");

            v1.getChildren().add(l);
            v1.getChildren().add(lfprice);
            v1.getChildren().add(l1);
            v1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, new Insets(10))));
            v2.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, new Insets(10))));
            JFXButton update = new JFXButton("Update");

            JFXButton del = new JFXButton("Delete");
            
            del.setButtonType(JFXButton.ButtonType.RAISED);
            update.setButtonType(JFXButton.ButtonType.RAISED);

            update.setPrefSize(115, 35);
            del.setPrefSize(115, 35);
            
            v2.getChildren().add(update);
            v2.getChildren().add(del);
            
            h1.getChildren().add(v1);
            h1.getChildren().add(v2);
            addShadowedBox(h1);
            vboxFertilizersManager.getChildren().add(h1);
        }
    }
    private void fillFertilizerList()
    {
        
        //fertilizers.clear();
        Session sess = dbCon.getSession();
        Transaction tr = sess.beginTransaction();
        fertilizers = (List<Fertilizer>) sess.createQuery("select fert from Fertilizer fert").list();
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateFertilizerList();
        //fertilizers.clear();
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
