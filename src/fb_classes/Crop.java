/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;
import javax.persistence.*;
import java.util.*;
import javax.annotation.processing.Generated;
/**
 *
 * @author moxan
 */
@Entity
public class Crop
{
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    public int CropId;
    
    public String CropName;
    public String CropType;
    public List<String> Soils;
    public List<String> Regions;
    public int Rainfall;
    public int Temperature;
    public String Description;
 
    @ManyToMany
    public List<Fertilizer> fertilizers;

    public Crop()
    {
    }

    public int getCropId()
    {
        return CropId;
    }

    public void setCropId(int CropId)
    {
        this.CropId = CropId;
    }

    public String getCropName()
    {
        return CropName;
    }

    public void setCropName(String CropName)
    {
        this.CropName = CropName;
    }

    public String getCropType()
    {
        return CropType;
    }

    public void setCropType(String CropType)
    {
        this.CropType = CropType;
    }

    public List<String> getSoils()
    {
        return Soils;
    }

    public void setSoils(List<String> Soils)
    {
        this.Soils = Soils;
    }

    public List<String> getRegions()
    {
        return Regions;
    }

    public void setRegions(List<String> Regions)
    {
        this.Regions = Regions;
    }

    public int getRainfall()
    {
        return Rainfall;
    }

    public void setRainfall(int Rainfall)
    {
        this.Rainfall = Rainfall;
    }

    public int getTemperature()
    {
        return Temperature;
    }

    public void setTemperature(int Temperature)
    {
        this.Temperature = Temperature;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    public List<Fertilizer> getFertilizers()
    {
        return fertilizers;
    }

    public void setFertilizers(List<Fertilizer> fertilizers)
    {
        this.fertilizers = fertilizers;
    }

    @Override
    public String toString()
    {
        return "Crop{" + "CropId=" + CropId + ", CropName=" + CropName + ", CropType=" + CropType + ", Soils=" + Soils + ", Regions=" + Regions + ", Rainfall=" + Rainfall + ", Temperature=" + Temperature + ", Description=" + Description + ", fertilizers=" + fertilizers + '}';
    }
    
}
