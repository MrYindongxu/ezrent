package version2.BIMClient.clienteneity;

import java.io.Serializable;


public class Account implements Serializable {
    private String userName;
    private String password;
    private String idnum;//身份证号码

    public Account() {
    }

    public Account(String userName, String password,String idnum) {
        this.userName = userName;
        this.password = password;
        this.idnum = idnum;
    }

    public Account(String userName, String pwd) {
        this.userName = userName;
        this.password = pwd;
    }

    /*public String getUserName() {
        return userName;
    }*/

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getUserName() {
        return userName;
    }

    public String getIdnum() {
        return idnum;
    }
}
