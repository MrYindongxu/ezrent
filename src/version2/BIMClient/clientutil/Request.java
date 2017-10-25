package version2.BIMClient.clientutil;

import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clienteneity.ForRentMessage;

import java.io.Serializable;


public class Request implements Serializable {
    private int type;//0��¼,1ע��
    // 20�˳�, 21�鿴������Ϣ, 22������Ϣ, 23������Ϣ, 24����, 25����, 26��������
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
