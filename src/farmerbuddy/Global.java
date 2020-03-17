/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import fb_classes.*;
import java.util.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author moxan
 */
public class Global {

    public User user;
    private static Global gb;
    //public Dictionary<String,Object> data;
    public static Map<String, Object> data;

    private Global() {
        //gb = new Global();
    }

    public User getUser() {
        return user;
    }

    public synchronized static Global getGlobal() {
        if (gb == null) {
            gb = new Global();
            //data = new Hashtable<>(0);
            data = new HashMap<>();
        }
        return gb;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getDictionary() {
        return this.data;
    }

    public void showMessage(String message) {
        if(!data.containsKey("rootPane"))
            return;
        AnchorPane rootPane = (AnchorPane) data.get("rootPane");
        JFXSnackbar sna = new JFXSnackbar(rootPane);
        JFXSnackbarLayout snL = new JFXSnackbarLayout(message);
        snL.setStyle("-fx-background-color:white;  ");
        //snL.setPrefWidth(900);
        //snL.setMaxWidth(900);
        snL.setMinHeight(30);
        //snL.setPrefHeight(15);
        
        addShadowedBox(snL);
        sna.enqueue(new JFXSnackbar.SnackbarEvent(snL, Duration.seconds(4), null));
    }
     private void addShadowedBox(JFXSnackbarLayout hb) {

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
