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
public class FertilizerPrice
{
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    public int FPId;
    
    @OneToOne
    public Fertilizer fertilizer;
    
    public double price;
    public Date date;
    public FertilizerPrice(){}

    public int getFPId()
    {
        return FPId;
    }

    public void setFPId(int FPId)
    {
        this.FPId = FPId;
    }

    public Fertilizer getFertilizer()
    {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer)
    {
        this.fertilizer = fertilizer;
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
        return "FertilizerPrice{" + "FPId=" + FPId + ", fertilizer=" + fertilizer + ", price=" + price + ", date=" + date + '}';
    }
    
}
