package version2.BIMClient.clienteneity;

import java.io.Serializable;

/**
 * 房客求租类，实现对房客信息的存储，并发送给服务器，利用服务器进行存储
 *
 * @author Administrator
 */
public class ForRentMessage implements Serializable {
    private String Money;//租金
    private String roomType;//求租房屋类型
    private String address;//求租地址
    private String phoneNumber;//电话号码
    private String RentOrNot;//求租还是出租
    private String ordingState;//订单状态
    //常见setter和getter方法

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        this.Money = money;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRentOrNot() {
        return RentOrNot;
    }

    public void setRentOrNot(String rentOrNot) {
        this.RentOrNot = rentOrNot;
    }

    public String getOrdingState() {
        return ordingState;
    }

    public void setOrdingState(String ordingState) {
        this.ordingState = ordingState;
    }
}