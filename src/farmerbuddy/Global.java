/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;
import fb_classes.*;
/**
 *
 * @author moxan
 */
public class Global {
    public User user;
    private static Global gb;
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
        }
        return gb;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
