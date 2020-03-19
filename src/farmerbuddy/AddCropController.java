/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fb_classes.Fertilizer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import org.hibernate.Session;
import org.hibernate.Transaction;
import fb_classes.*;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class AddCropController implements Initializable {

    @FXML
    private JFXTextField crop_name;
    @FXML
    private JFXTextField crop_type;
    @FXML
    private JFXTextArea soils;
    @FXML
    private JFXTextArea regions;
    @FXML
    private JFXTextField crop_rainfall;
    @FXML
    private JFXTextField crop_temperature;
    @FXML
    private JFXTextArea crop_description;
    @FXML
    private JFXTextField crop_price;
    @FXML
    private ListView<CheckBox> lfertilizers;

    private FertilizerListObserver fertilizerListObserver;
    private DBContext dbCon = DBContext.getDbContext();
    private Global gb = Global.getGlobal();
    private CropListObserver cropListObserver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (gb.getDictionary().containsKey("fertilizerListObserver")) {
            fertilizerListObserver = (FertilizerListObserver) gb.getDictionary().get("fertilizerListObserver");
            fertilizerListObserver.setLfertilizers(lfertilizers);
            fertilizerListObserver.updateFertilizers();
        }
        if(gb.getDictionary().containsKey("cropListObserver"))
        {
            cropListObserver = (CropListObserver) gb.getDictionary().get("cropListObserver");
            
        }

    }

    public void addCrop() {
        getCurrentCrop();
        cropListObserver.updateCrop();
    }

    private Crop getCurrentCrop() {
        if (isCropValid()) {

            Crop t = new Crop();
            t.CropName = crop_name.getText();
            t.CropType = crop_type.getText();
            t.Description = crop_description.getText();
            t.Rainfall = Integer.valueOf(crop_rainfall.getText());
            t.Regions = regions.getText().split(",");
            t.Soils = soils.getText().split(",");
            t.Temperature = Integer.valueOf(crop_temperature.getText());
            CropPrice cp = new CropPrice();
            cp.crop = t;
            cp.date = new Date();
            cp.price = Integer.valueOf(crop_price.getText());
            t.cropPrice = cp;
            Session sess = dbCon.getSession();
            Transaction tr = sess.beginTransaction();
            lfertilizers.getItems().stream().filter((ck) -> (ck.isSelected())).map((ck) -> Integer.valueOf(ck.getId().replaceAll("fertilizer", ""))).map((fid) -> (Fertilizer) sess.get(Fertilizer.class, fid)).map((ftemp) -> {
                //System.out.println(ck.getId());
                ftemp.crops.add(t);
                return ftemp;
            }).forEachOrdered((ftemp) -> {
                t.fertilizers.add(ftemp);
                //sess.saveOrUpdate(ftemp);
            });
            sess.save(t);
            tr.commit();
            
            dbCon.closeSession();
            gb.showMessage("Added Crop: "+t.CropName);
        } else {
            gb.showMessage("Validation Failed");
        }

        return null;
    }

    private boolean isCropValid() {
        return !(crop_name.getText() == null || crop_type.getText() == null || crop_price.getText()==null || crop_rainfall.getText()==null);
    }
}
