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
public class QuestionCategory {
    public int QCID;
    public int CategoryId;
    public int QId;

    public QuestionCategory(int CategoryId, int QId) {
        this.CategoryId = CategoryId;
        this.QId = QId;
    }

    public int getQCID() {
        return QCID;
    }

    public void setQCID(int QCID) {
        this.QCID = QCID;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public int getQId() {
        return QId;
    }

    public void setQId(int QId) {
        this.QId = QId;
    }

    @Override
    public String toString() {
        return "QuestionCategory{" + "QCID=" + QCID + ", CategoryId=" + CategoryId + ", QId=" + QId + '}';
    }
    
}
