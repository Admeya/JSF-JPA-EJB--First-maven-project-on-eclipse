package net.java.bin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean // аннотация указывающая что это managed bean и поэтому мы его не описываем в faces-config.xml
@SessionScoped // аннотация указывающая что это managed bean является сессионным
public class MainBean {
    private String userLogin;
    private String password;
    private String text;


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    // Метод используемый нами в качестве простейшей авторизации, проверяет имя и пароль пользователя и возвращает имя
    // страницы на которую произойдет переход
    public String checkUser() {
        if (this.userLogin.equals("user") && this.password.equals("123")) {
            return "success";
        } else {
            return "loginfailed";
        }
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}