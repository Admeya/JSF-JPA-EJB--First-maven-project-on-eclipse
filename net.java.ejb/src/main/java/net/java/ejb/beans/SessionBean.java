package net.java.ejb.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@ManagedBean // аннотация указывающая что это managed bean и поэтому мы его не описываем в faces-config.xml
@SessionScoped // аннотация указывающая что это managed bean является сессионным
public class SessionBean implements Serializable {

    private String userName = null;
    private String userLogin = null;
    private String userPassword = null;
    private String text;
    private Connection connection = null;
    private String errorPrefix = "Обработка данных сессии: ";
    private String lastError = "";
    private int userID = 0; // ID пользователя


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    // Метод используемый нами в качестве простейшей авторизации, проверяет имя и пароль пользователя и возвращает имя
    // страницы на которую произойдет переход
    public String processLogin() {
        try {
            errorPrefix = "Ошибка авторизации: ";
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM users WHERE (usr_login LIKE ?) AND (usr_pass = ?);",
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                statement.setString(1, userLogin.trim());
                statement.setString(2, userPassword);
                ResultSet rs = statement.executeQuery();
                if (rs.first()) {
                    userID = rs.getInt("usr_id");
                    userName = rs.getString("usr_name");
                    rs.close();
                    return "success";
                }
            } else {
                setLastError(errorPrefix + "Пользователь с такой комбинацией логина и пароля не обнаружен!");
                userLogin = null;
                userPassword = null;
                return "loginfailed";
            }
        } catch (Exception e) {
            setLastError(errorPrefix + "Ошибка при обработке запроса к СУБД (" + e.getMessage() + ")");
            return "loginfailed";
        }
        return "index";
    }

    public SessionBean(){
        try {
            errorPrefix = "Инициализация сессии";
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:/storage");
            if (null != dataSource) {
                connection = dataSource.getConnection();
            }
            
        } catch (Exception e) {
            System.out.println(errorPrefix + "Ошибка подключения (" + e.toString() + ")");
        }
    }

}