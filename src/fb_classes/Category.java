/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

/**
 *
 * @author moxan
 */
public class Category {
    public int CategoryId;
    public String Category;

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
