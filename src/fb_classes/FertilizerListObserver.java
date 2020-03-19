/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import com.jfoenix.controls.JFXButton;
import java.util.*;
import farmerbuddy.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author moxan
 */
public class FertilizerListObserver {

    private List<Fertilizer> fertilizers = null;
    public VBox verticalBox = null;
    //public 
    private DBContext dbCon = DBContext.getDbContext();
    private Global gb = Global.getGlobal();
    private ListView<CheckBox> lfertilizers = null;

    public void updateFertilizers() {
        fillFertilizerList();
        updateFertilizerList();
        updateCheckBox();
        //System.out.println("fb_classes.FertilizerListObserver.updateFertilizerList()");
    }

    private void updateCheckBox() {
        if (fertilizers == null) {
            return;
        }
        if (lfertilizers == null) {
            return;
        }
        fertilizers.stream().map((f) -> {
            CheckBox chkTemp = new CheckBox(f.fname);
            chkTemp.setStyle("-fx-background-color:#008080;");
            chkTemp.setId("fertilizer" + String.valueOf(f.fertilizerId));
            return chkTemp;
        }).forEachOrdered((chkTemp) -> {
            lfertilizers.getItems().add(chkTemp);
        });
    }

    public VBox getVerticalBox() {
        return verticalBox;
    }

    public ListView<CheckBox> getLfertilizers() {
        return lfertilizers;
    }

    public void setLfertilizers(ListView<CheckBox> lfertilizers) {
        this.lfertilizers = lfertilizers;
    }

    public void setVerticalBox(VBox verticalBox) {
        this.verticalBox = verticalBox;
    }

    private void updateFertilizerList() {
        if (verticalBox == null) {
            return;
        }
        if (fertilizers.isEmpty()) {
            return;
        }
        verticalBox.getChildren().clear();
        fertilizers.stream().map((ftemp) -> {
            HBox h1 = new HBox();
            h1.setMinSize(850, 200);
            h1.setPrefSize(850, 200);
            h1.getStyleClass().add("shadowHBOX");
            VBox v1 = new VBox(0);
            VBox v2 = new VBox(5);
            HBox.setMargin(v1, new Insets(10.0));
            v1.setPrefSize(825, 200);
            v1.setAlignment(Pos.CENTER);
            Label l = new Label("Fertilizer Name: " + ftemp.fname +" ");
            double fprice = 0;
            if (ftemp.fertilizerPrice != null) {
                fprice = ftemp.fertilizerPrice.price;
            }
            Label lfprice;
            lfprice = new Label("Fertilizer Price: " + String.valueOf(fprice));
            Label l1 = new Label("Contents");
            lfprice.setStyle("-fx-font-size: 15px;");
            l.setStyle("-fx-font-size: " + 25 + "px;");
            l1.setStyle("-fx-font-size: " + 17 + "px;");
            v1.getChildren().add(l);
            v1.getChildren().add(lfprice);
            v1.getChildren().add(l1);
            v1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, new Insets(10))));
                            v1.setStyle("-fx-background-color:#C2FEDA;");

            if (gb.isUserAdmin()) {
                v1.setPrefSize(625, 200);
                HBox.setMargin(v2, new Insets(10.0));
                v2.setMinWidth(225);
                v2.setPrefSize(225, 200);
                v2.setAlignment(Pos.CENTER);
                v2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(10))));
                v2.setStyle("-fx-background-color:#C2FEDA;");
                v1.setStyle("-fx-background-color:#C2FEDA;");

                JFXButton update = new JFXButton("Update");

                JFXButton del = new JFXButton("Delete");

                del.setButtonType(JFXButton.ButtonType.RAISED);
                update.setButtonType(JFXButton.ButtonType.RAISED);

                update.setPrefSize(115, 35);
                del.setPrefSize(115, 35);

                del.setOnAction((event) -> {
                    try {
                        Session sess = dbCon.getSession();
                        Transaction tr = sess.beginTransaction();
                        sess.delete(ftemp);
                        tr.commit();
                        dbCon.closeSession();
                        gb.showMessage("Removed Fertilizer: " + ftemp.fname);
                        updateFertilizers();
                    } catch (Exception e) {
                        System.err.println(e);
                        gb.showMessage("Couldn't Delete Fertilizer");
                    }
                });

                v2.getChildren().add(update);
                v2.getChildren().add(del);
            }
            h1.getChildren().add(v1);
            if (gb.isUserAdmin()) {
                h1.getChildren().add(v2);
            }
            return h1;
        }).map((h1) -> {
            addShadowedBox(h1);
            return h1;
        }).forEachOrdered((h1) -> {
            verticalBox.getChildren().add(h1);
        });
    }

    private void fillFertilizerList() {

        //fertilizers.clear();
        Session sess = dbCon.getSession();
        fertilizers = null;
        fertilizers = (List<Fertilizer>) sess.createQuery("select fert from Fertilizer fert").list();
        dbCon.closeSession();
        gb.getDictionary().put("fertilizers_list", fertilizers);
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
