/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;
import fb_classes.*;
import java.util.Dictionary;
import java.util.Hashtable;
/**
 *
 * @author moxan
 */
public class Global {
    public User user;
    private static Global gb;
    //public Dictionary<String,Object> data;
    private Global()
    {
        //gb = new Global();
    }

    public User getUser() {
        return user;
    }
    public synchronized static Global getGlobal()
    {
        if(gb==null)
        {
            gb = new Global();
            //data = new Hashtable<>(0);
        }
        return gb;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
