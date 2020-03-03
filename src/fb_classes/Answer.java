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
public class Answer {
    public int AID;
    public int QID;
    public String Answer;
    public int UID;
    public boolean isValidated;

    public Answer(int AID, int QID, String Answer, int UID, boolean isValidated) {
        this.AID = AID;
        this.QID = QID;
        this.Answer = Answer;
        this.UID = UID;
        this.isValidated = isValidated;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public int getQID() {
        return QID;
    }

    public void setQID(int QID) {
        this.QID = QID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public boolean isIsValidated() {
        return isValidated;
    }

    public void setIsValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    @Override
    public String toString() {
        return "Answer{" + "AID=" + AID + ", QID=" + QID + ", Answer=" + Answer + ", UID=" + UID + ", isValidated=" + isValidated + '}';
    }
    
}
