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
public class Fertilizer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int fertilizerId;
    
    public String fname;
    public int Nitrogen;
    public int phosphorous;
    public int potassium;
    public String fDescription;
    public String[] Regions;
    public String[] Soils;
    
    @OneToOne(cascade = CascadeType.ALL)
    public FertilizerPrice fertilizerPrice;
    
    @ManyToMany
    public List<Crop> crops;
    
    
    public Fertilizer(){
        this.crops = new LinkedList<>();
    }

    public FertilizerPrice getFertilizerPrice()
    {
        return fertilizerPrice;
    }

    public void setFertilizerPrice(FertilizerPrice fertilizerPrice)
    {
        this.fertilizerPrice = fertilizerPrice;
    }

    public int getFertilizerId()
    {
        return fertilizerId;
    }

    public void setFertilizerId(int fertilizerId)
    {
        this.fertilizerId = fertilizerId;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public int getNitrogen()
    {
        return Nitrogen;
    }

    public void setNitrogen(int Nitrogen)
    {
        this.Nitrogen = Nitrogen;
    }

    public int getPhosphorous()
    {
        return phosphorous;
    }

    public void setPhosphorous(int phosphorous)
    {
        this.phosphorous = phosphorous;
    }

    public int getPotassium()
    {
        return potassium;
    }

    public void setPotassium(int potassium)
    {
        this.potassium = potassium;
    }

    public String getfDescription()
    {
        return fDescription;
    }

    public void setfDescription(String fDescription)
    {
        this.fDescription = fDescription;
    }

    public String[] getRegions()
    {
        return Regions;
    }

    public void setRegions(String[] Regions)
    {
        this.Regions = Regions;
    }

    public String[] getSoils()
    {
        return Soils;
    }

    public void setSoils(String[] Soils)
    {
        this.Soils = Soils;
    }

    public List<Crop> getCrops()
    {
        return crops;
    }

    public void setCrops(List<Crop> crops)
    {
        this.crops = crops;
    }

    @Override
    public String toString()
    {
        return "Fertilizer{" + "fertilizerId=" + fertilizerId + ", fname=" + fname + ", Nitrogen=" + Nitrogen + ", phosphorous=" + phosphorous + ", potassium=" + potassium + ", fDescription=" + fDescription + ", Regions=" + Regions + ", Soils=" + Soils + ", crops=" + crops + '}';
    }
    
}
