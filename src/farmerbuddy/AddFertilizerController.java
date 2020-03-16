/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import fb_classes.*;
import java.util.List;
import org.hibernate.*;
/**
 * FXML Controller class
 *
 * @author moxan
 */
public class AddFertilizerController implements Initializable {

    @FXML
    private JFXTextField fertilizer_name;
    @FXML
    private JFXTextField fertilizer_P;
    @FXML
    private JFXTextField fertilizer_N;
    @FXML
    private JFXTextField fertilizer_Potassium;
    @FXML
    private JFXTextArea fertilizer_desc;
    @FXML
    private JFXTextArea fertilizer_soils;
    @FXML
    private JFXTextArea fertilizer_regions;
    @FXML
    private JFXButton addFertBtn;

    /**
     * Initializes the controller class.
     */
    private final DBContext dbCon = DBContext.getDbContext();
    @FXML
    private JFXTextField fertilizer_price;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void addFertilizer()
    {
        Fertilizer ftemp = getFertilizer();
        if(ftemp!=null)
        {
            Session sess = dbCon.getSession();
            Transaction tr = sess.beginTransaction();
            
            //Fertilizer dupF = (Fertilizer) sess.createQuery("select fert from Fertilizer fert where fert.fname= '"+ftemp.fname+"'");
            
            sess.save(ftemp);
            tr.commit();
            dbCon.closeSession();
            dbCon.close();
        }
    }
    public Fertilizer getFertilizer()
    {
        Fertilizer ftemp = new Fertilizer();
        ftemp.Nitrogen = Integer.valueOf(fertilizer_N.getText());
        ftemp.Regions = fertilizer_regions.getText().split(",");
        ftemp.Soils = fertilizer_soils.getText().split(",");
        ftemp.fDescription = fertilizer_desc.getText();
        ftemp.phosphorous = Integer.valueOf(fertilizer_P.getText());
        ftemp.potassium = Integer.valueOf(fertilizer_Potassium.getText());
        ftemp.fname = fertilizer_name.getText();
        if(validateFetilizer(ftemp))
            return ftemp;
        return null;
    }
    public boolean validateFetilizer(Fertilizer f)
    {
        return true;
    }
    
}