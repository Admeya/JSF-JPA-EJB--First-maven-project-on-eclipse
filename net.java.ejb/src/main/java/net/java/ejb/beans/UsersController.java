package net.java.ejb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import net.java.ejb.entities.UserEJB;
import net.java.ejb.entities.UserEntity;

@ManagedBean
@RequestScoped
public class UsersController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @EJB
    private UserEJB userEJB;
    private UserEntity userEntity = new UserEntity();
    private List<UserEntity> userList = new ArrayList<UserEntity>();

    public List<UserEntity> getUserList() {
        userList = userEJB.findUsers();
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    public UserEJB getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UserEJB userEJB) {
        this.userEJB = userEJB;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String viewSuccessPage() {
        return "success.xhtml";
    }

    public String viewFailPage() {
        return "loginfailed.xhtml";
    }

    public String addNewUser() {
        userEntity = userEJB.addNew(userEntity);
        return "success.xhtml";
    }

    public String checkUserExist() {
        List<UserEntity> ue = userEJB.checkUserExist(userEntity);
        userEntity.setUserName(ue.get(0).getUserName());

        if (ue.isEmpty()) {
            return viewFailPage();
        } else {
            return viewSuccessPage();
        }
    }
}
