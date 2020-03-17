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
    private List<Fertilizer> fertilizers;
    private DBContext dbCon = DBContext.getDbContext();
    private Global gb =Global.getGlobal();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fertilizers = (List<Fertilizer>)gb.getDictionary().get("fertilizers_list");
        for(Fertilizer f:fertilizers)
        {
            CheckBox chkTemp = new CheckBox(f.fname);
            chkTemp.setId("fertilizer"+String.valueOf(f.fertilizerId));
            lfertilizers.getItems().add(chkTemp);
        }
    }    
     
}
