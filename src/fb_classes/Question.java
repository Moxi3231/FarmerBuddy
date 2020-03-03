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
public class Question {
    public int Q_Id;
   public int UID;
   public String question;

    public Question(int Q_Id, int UID, String question) {
        this.Q_Id = Q_Id;
        this.UID = UID;
        this.question = question;
    }

    public Question(int UID, String question) {
        this.UID = UID;
        this.question = question;
    }

    public Question() {
    }

    public int getQ_Id() {
        return Q_Id;
    }

    public void setQ_Id(int Q_Id) {
        this.Q_Id = Q_Id;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" + "Q_Id=" + Q_Id + ", UID=" + UID + ", question=" + question + '}';
    }
   
}
