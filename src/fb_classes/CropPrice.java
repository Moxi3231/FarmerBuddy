/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;
import javax.persistence.*;
import java.util.*;
/**
 *
 * @author moxan
 */
@Entity
public class CropPrice
{
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    public int CPId;
    
    @OneToOne(cascade = CascadeType.ALL)
    public Crop crop;
    
    public double price;
    public Date date;
    public CropPrice(){}

    public int getCPId()
    {
        return CPId;
    }

    public void setCPId(int CPId)
    {
        this.CPId = CPId;
    }

    public Crop getCrop()
    {
        return crop;
    }

    public void setCrop(Crop crop)
    {
        this.crop = crop;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "CropPrice{" + "CPId=" + CPId + ", crop=" + crop + ", price=" + price + ", date=" + date + '}';
    }
    
}
