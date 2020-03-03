/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author moxan
 */
@Entity
public class QuestionCategory {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    public int QCID;
    
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Category> category;
    
    //public int CategoryId;
    public int QId;

    public List<Category> getCategory()
    {
        return category;
    }

    public void setCategory(List<Category> category)
    {
        this.category = category;
    }

    public Question getQue()
    {
        return que;
    }

    public void setQue(Question que)
    {
        this.que = que;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Question que;
    
    public QuestionCategory(int QId) {
        //this.CategoryId = CategoryId;
        //this.category = c;
        this.QId = QId;
    }

    public int getQCID() {
        return QCID;
    }

    public void setQCID(int QCID) {
        this.QCID = QCID;
    }

    

    public int getQId() {
        return QId;
    }

    public void setQId(int QId) {
        this.QId = QId;
    }

    @Override
    public String toString() {
        return "QuestionCategory{" + "QCID=" + QCID + ", Category=" + category + ", QId=" + QId + '}';
    }
    
}
