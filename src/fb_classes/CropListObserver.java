/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;
import com.jfoenix.controls.JFXButton;
import java.util.List;
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
import farmerbuddy.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author moxan
 */
public class CropListObserver {
    private List<Crop> crops = null;
    private VBox verticalBox = null;
    private DBContext dbCon = DBContext.getDbContext();
    private Global gbGlobal = Global.getGlobal();
    
    public void setVerticalBox(VBox verticalBox) {
        this.verticalBox = verticalBox;
    }
    public void updateCrop()
    {
        fillCropList();
        updateVBoxContent();
    }
    private void fillCropList()
    {
        try{
         Session sess = dbCon.getSession();
         Transaction tr = sess.beginTransaction();
         crops = sess.createQuery("select cr from Crop cr").list();
         tr.commit();
         dbCon.closeSession();;
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
    private void updateVBoxContent()
    {
        if(crops==null||verticalBox==null||crops.isEmpty())
            return;
        
        crops.stream().map((cr) -> {
            
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
            Label l = new Label("Crop: "+cr.CropName);
            Label l1 = new Label("Crop Price: "+cr.getCropPrice().price+" Last Updated: "+cr.getCropPrice().date.toString());
            l.setStyle("-fx-font-size: " + 20 + "px;");
            l1.setStyle("-fx-font-size: " + 10 + "px;");
            v1.getChildren().add(l);
            v1.getChildren().add(l1);
            v1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, new Insets(10))));
            v2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(10))));
             v2.setStyle("-fx-background-color:#C2FEDA;");
             v1.setStyle("-fx-background-color:#C2FEDA;");
            
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
            return h1;
        }).map((h1) -> {
            addShadowedBox(h1);
            return h1;
        }).forEachOrdered((h1) -> {
            verticalBox.getChildren().add(h1);
        });
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
