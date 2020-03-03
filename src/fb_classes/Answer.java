/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;
import javax.persistence.*;
/**
 *
 * @author moxan
 */
@Entity
public class Answer {
    @Id
    public int AID;
    //public int QID;
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Question question;
    
    public String Answer;
    public int UID;
    public boolean isValidated;

    public Answer(int AID, Question QID, String Answer, int UID, boolean isValidated) {
        this.AID = AID;
        this.question = QID;
        this.Answer = Answer;
        this.UID = UID;
        this.isValidated = isValidated;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
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
        return "Answer{" + "AID=" + AID + ", Question=" + question + ", Answer=" + Answer + ", UID=" + UID + ", isValidated=" + isValidated + '}';
    }
    
}
