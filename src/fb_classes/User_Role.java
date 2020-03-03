/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_classes;

import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author moxan
 */
@Entity
@Table(name = "UserRoles")
public class User_Role {
    @Id
    public int RoleId;
   
    public String roleName;

    @OneToMany(mappedBy = "role")
    public List<User> users;
    public User_Role() {
    }

    public User_Role(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User_Role{" + "RoleId=" + RoleId + ", roleName=" + roleName + '}';
    }
    
}
