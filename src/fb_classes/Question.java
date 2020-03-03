/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.*;

/**
 *
 * @author moxan
 */
@Entity
public class Question {

    @Id
    public int Q_Id;
    //public int UID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true, name = "user_id")
    public User user;
    public String question;

    @OneToOne(cascade = CascadeType.ALL)
    public QuestionCategory qc;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Answer> answers;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionCategory getQc() {
        return qc;
    }

    public void setQc(QuestionCategory qc) {
        this.qc = qc;
    }

    public Question() {
    }

    public int getQ_Id() {
        return Q_Id;
    }

    public void setQ_Id(int Q_Id) {
        this.Q_Id = Q_Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" + "Q_Id=" + Q_Id + ", user=" + user + ", question=" + question + '}';
    }

}
