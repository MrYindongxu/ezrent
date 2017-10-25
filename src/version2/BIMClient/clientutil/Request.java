package version2.BIMClient.clientutil;

import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clienteneity.ForRentMessage;

import java.io.Serializable;


public class Request implements Serializable {
    private int type;//0登录,1注册
    // 20退出, 21查看所有信息, 22发布信息, 23搜索信息, 24聊天, 25订单, 26信誉积分
    private Account account;
    private String research;
    private ForRentMessage forRentMessage;
    private int orderChoice;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public ForRentMessage getForRentMessage() {
        return forRentMessage;
    }

    public int getOrderChoice() {
        return orderChoice;
    }

    public void setOrderChoice(int orderChoice) {
        this.orderChoice = orderChoice;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setForRentMessage(ForRentMessage forRentMessage){
        this.forRentMessage = forRentMessage;
    }
}
