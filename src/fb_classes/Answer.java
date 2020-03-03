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
public class Answer
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int AID;
    //public int QID;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    public Question question;

    public String Answer;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user;

    //public int UID;
    public boolean isValidated;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Answer(int AID, Question QID, String Answer, boolean isValidated)
    {
        this.AID = AID;
        this.question = QID;
        this.Answer = Answer;
        //this.UID = UID;
        this.isValidated = isValidated;
    }

    public Answer()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public int getAID()
    {
        return AID;
    }

    public void setAID(int AID)
    {
        this.AID = AID;
    }

    public String getAnswer()
    {
        return Answer;
    }

    public void setAnswer(String Answer)
    {
        this.Answer = Answer;
    }

    

    public boolean isIsValidated()
    {
        return isValidated;
    }

    public void setIsValidated(boolean isValidated)
    {
        this.isValidated = isValidated;
    }

    @Override
    public String toString()
    {
        return "Answer{" + "AID=" + AID + ", Question=" + question + ", Answer=" + Answer + ", User=" + user + ", isValidated=" + isValidated + '}';
    }

}
