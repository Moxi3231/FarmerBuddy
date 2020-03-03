/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXTreeTableView;
import fb_classes.Question;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class ForumManageController implements Initializable {

    //@FXML
    //private JFXTreeTableView<?> rootTable;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXListView<Label> listView;

    //@FXML
    //private StackPane stackPane;
    @FXML
    private JFXToggleButton toogleBtn;

    @FXML
    private JFXButton detailsBtn;

    @FXML
    private JFXButton addBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Question que = new Question();
        //rootTable = new JFXTreeTableView<Question>();
        listView.setExpanded(Boolean.TRUE);

        dummyQuestion();
    }

    public void dummyQuestion() {
        int i;
        for (i = 0; i < 50; i++) {
            Question q = new Question();
            q.question = randString();
            Label l = new Label(i + " " + q.question);
            l.setId("question" + i);
            listView.getItems().add(l);

        }
        listView.setExpanded(true);
    }

    public String randString() {
        byte[] array = new byte[25]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return array.toString();
        //System.out.println(generatedString);
    }

    public void viewDetailsOnClick() {
        //anchorPane.disableProperty();
        //System.out.println(listView.getSelectionModel().getSelectedItem().getId());
        Label l = listView.getSelectionModel().getSelectedItem();
        
        JFXDialogLayout content = new JFXDialogLayout();
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        
        
        content.setHeading(new Text("Question"));
        content.setBody(new Label("Question Details"));
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton closeBtn = new JFXButton("Return");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                dialog.close();
            }
        });
        anchorPane.getChildren().add(stackPane);
        content.setActions(closeBtn);
	AnchorPane.setTopAnchor(stackPane, (anchorPane.getHeight()) / 3);
	AnchorPane.setLeftAnchor(stackPane, (anchorPane.getWidth() ) / 3);
        
        dialog.show();
    }
}
