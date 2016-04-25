package net.java.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "UserEntity.getAll", query = "SELECT c from UserEntity c"),
        @NamedQuery(name = "UserEntity.getRegisterUser", query = "SELECT c FROM UserEntity c WHERE (c.userLogin = :uLogin AND c.userPassword = :uPass)") })
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private int userId;

    @Column(name = "usr_name")
    private String userName;

    @Column(name = "usr_login")
    private String userLogin;

    @Column(name = "usr_pass")
    private String userPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

// http://pgtk.edu.ru/lections/doku.php?id=java_jsf2_wequipment_1
// http://www.tutorialspoint.com/jsf/jsf_inputtextarea_tag.htm
// http://exadel.com/tutorial/jsf/jsftags-guide.html
// http://faces.javadev.ru/
