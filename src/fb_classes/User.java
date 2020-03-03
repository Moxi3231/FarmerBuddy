/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author moxan
 */
@Entity
@Table(name = "User")
public class User {

    @Column(unique = true, nullable = false)
    public String Email;
    public String PhoneNum;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int UserId;
    @Column(unique = false, nullable = false)
    public String UserName;
    public String State;
    public String District;
    public String Region;
    public int RoleId;

    @OneToMany(mappedBy = "user")
    public List<Question> questions;

    @OneToMany(mappedBy = "user")
    public List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public User_Role role;
    public String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "User{" + "Email=" + Email + ", PhoneNum=" + PhoneNum + ", UserId=" + UserId + ", UserName=" + UserName + ", State=" + State + ", District=" + District + ", Region=" + Region + ", RoleId=" + RoleId + '}';
    }

    public User() {
        this.RoleId = 1;
    }

    public User(String Email, String PhoneNum, int UserId, String UserName, String State, String District, String Region, int RoleId) {
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.UserId = UserId;
        this.UserName = UserName;
        this.State = State;
        this.District = District;
        this.Region = Region;
        this.RoleId = 1;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }
}
