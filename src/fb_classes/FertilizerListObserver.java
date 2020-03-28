/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.util.*;
import farmerbuddy.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    private AnchorPane anchorPane = null;
    private DBContext dbCon = DBContext.getDbContext();
    private Global gb = Global.getGlobal();
    private ListView<CheckBox> lfertilizers = null;
    private CropListObserver cropListObserver=null;
    public void updateFertilizers() {
        fillFertilizerList();
        updateFertilizerList();
        updateCheckBox();
        if(cropListObserver!=null)
        {//System.out.println("Isnt null");
            cropListObserver.updateCrop();
        }
        //System.out.println("fb_classes.FertilizerListObserver.updateFertilizerList()");
    }
    public void setCropObserverList(CropListObserver clo)
    {
        this.cropListObserver = clo;
    }
    public void setAnchorPane(AnchorPane ap) {
        this.anchorPane = ap;
    }

    private void updateCheckBox() {
        if (fertilizers == null) {
            return;
        }
        if (lfertilizers == null) {
            return;
        }
        lfertilizers.getItems().clear();
        fertilizers.stream().map((f) -> {
            CheckBox chkTemp = new CheckBox(f.fname);
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
            v1.setPrefSize(855, 200);
            v1.setAlignment(Pos.CENTER);
            Label l = new Label("Fertilizer Name: " + ftemp.fname + " ");
            double fprice = 0;
            if (ftemp.fertilizerPrice != null) {
                fprice = ftemp.fertilizerPrice.price;
            }
            Label lfprice;
            lfprice = new Label("Fertilizer Price: " + String.valueOf(fprice));
            lfprice.setStyle("-fx-font-size: 15px;");
            l.setStyle("-fx-font-size: " + 25 + "px;");
            v1.getChildren().add(l);
            v1.getChildren().add(lfprice);
            HBox hb1 = new HBox();
            hb1.getChildren().addAll(bText("N: "+String.valueOf(ftemp.Nitrogen)+"% "),bText("P: "+String.valueOf(ftemp.phosphorous)+"% "),bText("K: "+ftemp.potassium+"% "));
            hb1.setAlignment(Pos.CENTER);
            v1.getChildren().add(hb1);
            v1.setAlignment(Pos.CENTER);
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
                update.setOnAction((ActionEvent) -> {
                    showUpdateView(ftemp);
                });

                update.setPrefSize(115, 35);
                update.setOnAction((ActionEvent ae) -> {
                    showUpdateView(ftemp);
                });
                del.setPrefSize(115, 35);

                del.setOnAction((event) -> {
                    try {
                        Session sess = dbCon.getSession();
                        Transaction tr = sess.beginTransaction();
                        sess.createSQLQuery("delete from crop_fertilizer where fertilizers_fertilizerId = " + String.valueOf(ftemp.fertilizerId)).executeUpdate();
                        ftemp.getCrops().clear();
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

    private Text bText(String s) {
        Text text = new Text(s);
        //Setting font to the text 
        text.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 15));
        return text;
    }

    private void addText(Pane ap, String s1, String s2) {

        HBox ht = new HBox(bText(s1), new Text(s2));
        ht.setMinSize(600, 15);
        ap.getChildren().addAll(ht);
    }

    private void showUpdateView(Fertilizer fert) {
        if (anchorPane == null) {
            return;
        }
        if (fert == null) {
            gb.showMessage("Cannot view this fertilizer");
            return;
        }
        JFXDialogLayout content = new JFXDialogLayout();
        content.getStyleClass().add("bgFertilizer");
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        HBox title = new HBox(bText("Update Fertilizer"));
        content.setHeading(title);
        //content.setBody(new Label("Question Details"));

        AnchorPane an1 = new AnchorPane();
        an1.getStyleClass().add("paddingForChild");
        an1.setPrefSize(825, 400);
        an1.setMinSize(825, 400);

        VBox vb1 = new VBox();

        JFXTextField fname = new JFXTextField(fert.fname);
        fname.setPromptText("Name of fertilizer");
        JFXTextField ph = new JFXTextField(String.valueOf(fert.phosphorous));
        ph.setPromptText("Content of Phosphorous in %");
        JFXTextField pots = new JFXTextField(String.valueOf(fert.phosphorous));
        ph.setPromptText("Content of Potassium in %");
        JFXTextField nnitr = new JFXTextField(String.valueOf(fert.Nitrogen));
        nnitr.setPromptText("Content of Nitrogen in %");
        JFXTextField fprice = new JFXTextField(String.valueOf(fert.getFertilizerPrice().price));
        fprice.setPromptText("Enter price of fertilizer");

        JFXTextArea fdescripition = new JFXTextArea(fert.fDescription);

        String soi = "";
        for (String s : fert.Soils) {
            soi += s + ",";
        }
        JFXTextArea soils = new JFXTextArea(soi);

        String reg = "";
        for (String r : fert.Regions) {
            reg += r + ",";
        }
        JFXTextArea regions = new JFXTextArea(reg);

        HBox rtemp = new HBox(ph, pots, nnitr);
        vb1.getChildren().addAll(new HBox(fname), rtemp, new HBox(fprice));
        rtemp.getStyleClass().add("paddingForChild");

        setWidthForTextArea(fdescripition);
        setWidthForTextArea(soils);
        setWidthForTextArea(regions);

        HBox dsf = new HBox(fdescripition, soils, regions);
        dsf.getStyleClass().add("paddingForChild");
        vb1.getChildren().add(dsf);
        an1.getChildren().add(vb1);
        content.setBody(an1);

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton updateBtn = new JFXButton("Update");
        JFXButton closeBtn = new JFXButton("Close");
        updateBtn.setOnAction((ActionEvent event) -> {
            gb.showMessage("Updating Fertilizer");

            try {
                //Fertilizer ftemp = new Fertilizer();
                if(isValidNumber(nnitr))
                    fert.Nitrogen = Integer.valueOf(nnitr.getText());
                else
                {
                    gb.showMessage("Enter Valid Value for Nitrogen");
                    return;
                }
                fert.Regions = regions.getText().split(",");
                fert.Soils = soils.getText().split(",");
                fert.fDescription = fdescripition.getText();
                fert.fertilizerPrice.price = Double.valueOf(fprice.getText());
                fert.fertilizerPrice.date = new Date();
                fert.fname = fname.getText();
                fert.phosphorous = Integer.valueOf(ph.getText());
                fert.potassium = Integer.valueOf(pots.getText());

                Session sess = dbCon.getSession();
                Transaction tr = sess.beginTransaction();
                sess.saveOrUpdate(fert);
                sess.saveOrUpdate(fert.fertilizerPrice);
                tr.commit();
                dbCon.closeSession();
                dialog.close();
                gb.showMessage("Fertilizer Updated");
                updateFertilizers();
            } catch (Exception e) {
                gb.showMessage("Couldn't update fertilizer. Please try again later.");
                System.err.println(e);
            }
        });
        closeBtn.setOnAction((ActionEvent event) -> {
            dialog.close();
        });

        anchorPane.getChildren().add(stackPane);
        content.setActions(updateBtn, closeBtn);
        AnchorPane.setTopAnchor(stackPane, Double.valueOf(10));
        AnchorPane.setLeftAnchor(stackPane, Double.valueOf(8));

        dialog.show();
    }
    private boolean isValidNumber(JFXTextField s)
    {
       if(s.getText()==null)
           return false;
       for(char c:s.getText().toCharArray())
       {
           if(!Character.isDigit(c))
               return false;
       }
       
       return true;
    }
    private void setWidthForTextArea(JFXTextArea tarea) {
        tarea.setPrefSize(250, 100);
        tarea.setMinSize(250, 100);
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
