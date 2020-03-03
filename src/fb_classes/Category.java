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
public class Category {
    @Id
    public int CategoryId;
    public String Category;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id")
    public List<QuestionCategory> questionCategories;

    public Category() {
    }

    public Category(int CategoryId, String Category) {
        this.CategoryId = CategoryId;
        this.Category = Category;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    @Override
    public String toString() {
        return "Category{" + "CategoryId=" + CategoryId + ", Category=" + Category + '}';
    }
    
}
