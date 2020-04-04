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
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import farmerbuddy.*;
import java.util.Date;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
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
public class CropListObserver {

    private List<Crop> crops = null;
    private VBox verticalBox = null;
    private DBContext dbCon = DBContext.getDbContext();
    private Global gbGlobal = Global.getGlobal();
    private AnchorPane anchorPane = null;
    private FertilizerListObserver fertilizerListObserver = null;
    private List<Fertilizer> fertilizers = null;

    public void setFertilizerObserverList(FertilizerListObserver flo) {
        this.fertilizerListObserver = flo;
    }

    public void setVerticalBox(VBox verticalBox) {
        this.verticalBox = verticalBox;
    }

    public void updateCrop() {
        fillCropList();
        updateVBoxContent();
    }

    private void fillCropList() {
        try {
            if (crops != null) {
                crops.clear();
            }
            Session sess = dbCon.getSession();
            Transaction tr = sess.beginTransaction();
            crops = sess.createQuery("select cr from Crop cr").list();
            tr.commit();
            dbCon.closeSession();;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void updateVBoxContent() {
        if (crops == null || verticalBox == null || crops.isEmpty()) {
            return;
        }
        verticalBox.getChildren().clear();
        crops.stream().map((cr) -> {

            HBox h1 = new HBox();
            h1.setMinSize(810, 200);
            h1.setPrefSize(810, 200);
            h1.getStyleClass().add("shadowHBOX");
            VBox v1 = new VBox(0);
            VBox v2 = new VBox(5);
            HBox.setMargin(v1, new Insets(10.0));
            HBox.setMargin(v2, new Insets(10.0));
            v1.setPrefSize(600, 200);
            v1.setAlignment(Pos.CENTER);
            v2.setMinWidth(200);
            v2.setPrefSize(200, 200);
            v2.setAlignment(Pos.CENTER);

            Label croptype = new Label(cr.CropType);
            croptype.setLayoutX(570);
            croptype.setLayoutY(1);
            croptype.setStyle("-fx-background-color:#2F4F4F;-fx-text-fill: white;-fx-font-size:15px;");
            croptype.setEffect(getDropShadow());
            Label l = new Label("Crop: " + cr.CropName);
            Label l1 = new Label("Crop Price: " + cr.getCropPrice().price + " Last Updated: " + cr.getCropPrice().date.toString());
            l.setStyle("-fx-font-size: " + 20 + "px;");
            l1.setStyle("-fx-font-size: " + 10 + "px;");
            Label cropDeccription = new Label(cr.Description);
            cropDeccription.setStyle("-fx-font-size:12px;");
            AnchorPane innerPane = new AnchorPane();
            innerPane.setPrefSize(600, 150);
            innerPane.setMinSize(600, 150);
            VBox innerVBox = new VBox(l, cropDeccription, l1);
            innerVBox.setAlignment(Pos.CENTER);
            innerPane.setStyle("-fx-padding:10;");

            innerPane.getChildren().add(innerVBox);
            innerPane.getChildren().add(croptype);

            v1.getChildren().add(innerPane);

            v1.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, new Insets(10))));
            v2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(10))));
            v2.setStyle("-fx-background-color:#9ED9CCFF;");
            v1.setStyle("-fx-background-color:#9ED9CCFF;");

            JFXButton update = new JFXButton("Update");
            JFXButton del = new JFXButton("Delete");

            del.setOnAction((ActionEvent ae) -> {
                try {
                    gbGlobal.showMessage("Deleting Crop: " + cr.CropName);
                    Session sess = dbCon.getSession();
                    sess.createSQLQuery("");
                    Transaction tr = sess.beginTransaction();
                    //Crop crTemp = sess.createQuery("select cr from Crop cr join fetch cr.fertilizers where cr.CropId = "+String.valueOf(cr.CropId));
                    //System.out.println(cr.fertilizers.isEmpty());
                    List<Fertilizer> tmepfertilizers = new LinkedList<>(cr.getFertilizers());
                    for (Fertilizer f : tmepfertilizers) {
                        Fertilizer ftemp1 = (Fertilizer) sess.get(Fertilizer.class, f.getFertilizerId());

                        ftemp1.getCrops().remove(cr);
                        cr.fertilizers.remove(ftemp1);
                        sess.saveOrUpdate(ftemp1);
                    }
                    sess.createSQLQuery("delete  from  fertilizer_crop where crops_CropId=" + String.valueOf(cr.CropId)).executeUpdate();

                    tr.commit();

                    tmepfertilizers.clear();// = null;
                    dbCon.closeSession();
                    sess = dbCon.getSession();
                    tr = sess.beginTransaction();
                    //sess.saveOrUpdate(cr);
                    //cr.fertilizers.clear();
                    //sess.delete(cr.getCropPrice());
                    sess.delete(cr);
                    tr.commit();
                    dbCon.closeSession();
                    updateCrop();
                } catch (Exception e) {
                    System.out.println(e);
                    gbGlobal.showMessage("Couldn't Delete Crop");
                }
            });

            JFXButton app = new JFXButton("View");

            app.setOnAction((ActionEvent ev) -> {
                viewDetails(cr);

            });

            del.setButtonType(JFXButton.ButtonType.RAISED);
            app.setButtonType(JFXButton.ButtonType.RAISED);
            update.setButtonType(JFXButton.ButtonType.RAISED);
            update.setPrefSize(115, 35);
            update.setOnAction((ActionEvent) -> {
                showUpdateView(cr);
            });
            del.setPrefSize(115, 35);
            app.setPrefSize(115, 35);
            if (gbGlobal.isUserAdmin()) {
                v2.getChildren().add(update);
                v2.getChildren().add(del);
            }
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

    public void setAnchorPane(AnchorPane an) {
        this.anchorPane = an;
    }

    private void addShadowedBox(HBox hb) {
        hb.setEffect(getDropShadow());
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

    private void viewDetails(Crop cr) {
        if (anchorPane == null) {
            return;
        }
        if (cr == null) {
            gbGlobal.showMessage("Cannot view this crop");
            return;
        }
        gbGlobal.setIncFalg(true);
        JFXDialogLayout content = new JFXDialogLayout();
        content.getStyleClass().add("bgCrop");
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        HBox title = new HBox(new Text("Crop: " + cr.CropName), bText("\t\t\t\t\t\t\tPrice: "), new Text(String.valueOf(cr.getCropPrice().price)));
        content.setHeading(title);
        //content.setBody(new Label("Question Details"));

        AnchorPane an1 = new AnchorPane();
        //an1.getStyleClass().add("paddingForChild");
        an1.setPrefSize(600, 400);
        an1.setMinSize(600, 400);

        addText(an1, "Descripition: ", cr.Description);

        HBox hb = new HBox();
        hb.setMinSize(600, 200);
        VBox v1 = new VBox();
        v1.setMinWidth(600);
        addText(v1, "Rainfall: ", String.valueOf(cr.Rainfall) + " Inches");
        addText(v1, "Temperature: ", String.valueOf(cr.Temperature) + " Degree Celcius");
        JFXMasonryPane panes = new JFXMasonryPane();
        VBox soils = new VBox();
        soils.getChildren().add(bText("List of soils: "));
        for (String s : cr.Soils) {
            soils.getChildren().add(new Text(s));
        }
        VBox regions = new VBox();
        regions.getChildren().add(bText("List of regions:"));
        for (String r : cr.Regions) {
            regions.getChildren().add(new Text(r));
        }

        VBox fertilizersVBox = new VBox();
        if (cr.getFertilizers().isEmpty()) {
            fertilizersVBox.getChildren().add(bText("No fertilizers currently available for this crop"));
        } else {
            fertilizersVBox.getChildren().add(bText("List of fertilizers: Ratio(N,P,K)"));
        }
        cr.getFertilizers().stream().map((f) -> {
            HBox htemp = new HBox();
            Text fname = new Text(f.fname);
            htemp.getChildren().add(fname);
            htemp.getChildren().addAll(bText("\t " + String.valueOf(f.Nitrogen) + "% " + String.valueOf(f.phosphorous) + "% " + String.valueOf(f.potassium) + "% "));
            return htemp;
        }).forEachOrdered((htemp) -> {
            fertilizersVBox.getChildren().add(htemp);
        });
        panes.getChildren().addAll(soils, regions, fertilizersVBox);

        v1.getChildren().add(panes);
        hb.getChildren().add(v1);
        hb.setLayoutY(50);
        an1.getChildren().add(hb);
        gbGlobal.setFadeInDownAnimation(an1);
        content.setBody(an1);

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton closeBtn = new JFXButton("Close");

        closeBtn.setOnAction((ActionEvent event) -> {
            dialog.close();
        });

        anchorPane.getChildren().add(stackPane);
        content.setActions(closeBtn);
        AnchorPane.setTopAnchor(stackPane, Double.valueOf(10));
        AnchorPane.setLeftAnchor(stackPane, (anchorPane.getWidth()) / 6);

        dialog.show();
    }

    private void showUpdateView(Crop cr) {
        if (anchorPane == null) {
            return;
        }
        if (cr == null) {
            gbGlobal.showMessage("Cannot Update this Crop");
            return;
        }
        JFXDialogLayout content = new JFXDialogLayout();
        content.getStyleClass().add("bgCrop");
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        HBox title = new HBox(bText("Update Crop"));
        content.setHeading(title);
        //content.setBody(new Label("Question Details"));

        AnchorPane an1 = new AnchorPane();
        an1.getStyleClass().add("paddingForChild");
        an1.setPrefSize(825, 400);
        an1.setMinSize(825, 400);

        VBox vb1 = new VBox();
        vb1.getStyleClass().add("paddingForChild");
        JFXTextField cname = new JFXTextField(cr.CropName);
        cname.setLabelFloat(true);
        cname.setPromptText("Name of Crop");
        JFXTextField ctype = new JFXTextField(cr.CropType);
        ctype.setLabelFloat(true);
        ctype.setPromptText("Type of Crop");

        HBox name_type = new HBox(cname,ctype);
        name_type.getStyleClass().add("paddingForChild");
        JFXTextField crainfall = new JFXTextField(String.valueOf(cr.Rainfall));
        crainfall.setLabelFloat(true);
        crainfall.setPromptText("Content of Nitrogen in %");
        JFXTextField cprice = new JFXTextField(String.valueOf(cr.getCropPrice().price));
        cprice.setLabelFloat(true);
        cprice.setPromptText("Enter price of fertilizer");
        JFXTextField ctemperature = new JFXTextField(String.valueOf(cr.Temperature));
        ctemperature.setLabelFloat(true);
        ctemperature.setPromptText("Enter price of fertilizer");
        JFXTextArea fdescripition = new JFXTextArea(cr.Description);
        fdescripition.setLabelFloat(true);
        fdescripition.setPromptText("Description");
        String soi = "";
        for (String s : cr.Soils) {
            soi += s + ",";
        }
        JFXTextArea soils = new JFXTextArea(soi);
        soils.setLabelFloat(true);
        soils.setPromptText("Enter Soils Sepparated By Comma");

        String reg = "";
        for (String r : cr.Regions) {
            reg += r + ",";
        }
        JFXTextArea regions = new JFXTextArea(reg);
        regions.setLabelFloat(true);
        regions.setPromptText("Enter Region Separated By Comma");
        
        //rtemp.getStyleClass().add("paddingForChild");
        setWidthForTextArea(fdescripition);
        setWidthForTextArea(soils);
        setWidthForTextArea(regions);
        
        HBox rt = new HBox(crainfall, ctemperature, cprice);
        rt.getStyleClass().add("paddingForChild");
        HBox dsf = new HBox(fdescripition, soils, regions);
        dsf.getStyleClass().add("paddingForChild");
        ListView<CheckBox> lfertilizers = new ListView<>();
        lfertilizers.setPrefHeight(150);
        lfertilizers.setMinHeight(150);
        fertilizers = fertilizerListObserver.getFList();
        addFertilizerToList(lfertilizers, cr);

        //System.out.println(fertilizers.size());
        vb1.getChildren().addAll(name_type, rt, dsf, lfertilizers);
        an1.getChildren().addAll(vb1);
        content.setBody(an1);

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton updateBtn = new JFXButton("Update");
        JFXButton closeBtn = new JFXButton("Close");
        updateBtn.setOnAction((ActionEvent event) -> {
            gbGlobal.showMessage("Updating Crop");
            try {

                cr.CropName = cname.getText();
                cr.CropType = ctype.getText();
                cr.Description = fdescripition.getText();
                cr.Rainfall = Integer.valueOf(crainfall.getText());
                cr.Regions = regions.getText().split(",");
                cr.Soils = soils.getText().split(",");
                cr.Temperature = Integer.valueOf(ctemperature.getText());
                cr.cropPrice.price = Double.valueOf(cprice.getText());
                cr.cropPrice.date = new Date();
                Session sess = dbCon.getSession();
                Transaction tr = sess.beginTransaction();
                List<Fertilizer> lflist = getSelectedFertilizers(lfertilizers, sess, cr);
                if (lflist == null) {
                    gbGlobal.showMessage("Couldn't Update Crop.Please Try Later");
                    return;
                }
                cr.fertilizers.clear();
                cr.fertilizers = lflist;
                sess.saveOrUpdate(cr);
                sess.saveOrUpdate(cr.cropPrice);
                tr.commit();
                dbCon.closeSession();
                dialog.close();
                gbGlobal.showMessage("Updated Crop Successfully.");
                updateCrop();
            } catch (Exception e) {
                gbGlobal.showMessage("Couldn't update fertilizer. Please try again later.");
                System.err.println(e);
            }
        });
        closeBtn.setOnAction((ActionEvent event) -> {
            lfertilizers.getItems().clear();
            dialog.close();
        });

        anchorPane.getChildren().add(stackPane);
        content.setActions(updateBtn, closeBtn);
        AnchorPane.setTopAnchor(stackPane, Double.valueOf(10));
        AnchorPane.setLeftAnchor(stackPane, Double.valueOf(8));

        dialog.show();
    }

    private List<Fertilizer> getSelectedFertilizers(ListView<CheckBox> lfertilizers, Session sess, Crop cr) {
        List<Fertilizer> flist = new LinkedList<>();
        sess.createSQLQuery("delete  from  fertilizer_crop where crops_CropId=" + String.valueOf(cr.CropId)).executeUpdate();
        try {
            cr.getFertilizers().forEach((f) -> {
                Fertilizer ftemp = (Fertilizer) sess.get(Fertilizer.class, f.getFertilizerId());
                ftemp.getCrops().remove(cr);
                //sess.saveOrUpdate(f);
            });
            cr.fertilizers.clear();

            lfertilizers.getItems().stream().filter((chk) -> (chk.isSelected())).map((chk) -> (Fertilizer) sess.get(Fertilizer.class, Integer.valueOf(chk.getId().replaceAll("fertilizer", "")))).map((ftemp) -> {
                ftemp.getCrops().add(cr);
                return ftemp;
            }).forEachOrdered((ftemp) -> {
                flist.add(ftemp);
            });
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return flist;
    }

    private void addFertilizerToList(ListView<CheckBox> lfertilizers, Crop cr) {
        //System.err.println("I was called");
        if (fertilizers == null) {
            return;
        }
        //System.err.println("I was 2 called");
        if (lfertilizers == null) {
            return;
        }
        //System.err.println("I was 3 called");
        lfertilizers.getItems().clear();

        fertilizers.stream().map((f) -> {
            CheckBox chkTemp = new CheckBox(getFertilizerString(f));
            //System.out.println(isSelected(cr, f));
            chkTemp.setSelected(isSelected(cr, f));
            chkTemp.setId("fertilizer" + String.valueOf(f.fertilizerId));
            return chkTemp;
        }).forEachOrdered((chkTemp) -> {
            //System.err.println("");
            lfertilizers.getItems().add(chkTemp);
        });
    }

    private boolean isSelected(Crop cr, Fertilizer f) {
        //f.getCrops().clear();
        if (cr.getFertilizers().stream().anyMatch((fert) -> (fert.getFertilizerId() == f.getFertilizerId()))) {
            return true;
        }
        return false;
    }
    private String getFertilizerString(Fertilizer f)
    {
         return String.format("%s \tRatio(N: %d  P: %d  K: %d  ) ", f.fname,f.Nitrogen,f.phosphorous,f.potassium);
     }
    private DropShadow getDropShadow() {
        DropShadow e = new DropShadow();
        e.setWidth(10);
        e.setHeight(10);
        e.setOffsetX(0);
        e.setOffsetY(0);
        e.setRadius(2);
        e.setColor(Color.DODGERBLUE);
        return e;
    }

    private void setWidthForTextArea(JFXTextArea tarea) {
        tarea.setPrefSize(250, 100);
        tarea.setMinSize(250, 100);
    }

    private boolean isValidNumber(JFXTextField s) {
        if (s.getText() == null) {
            return false;
        }
        for (char c : s.getText().toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
