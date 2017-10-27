package version2.BIMServer.serverutil;

/**
 *详情信息
 * 调用下订单的方法，拆散并赋值给逐个变量。
 * 0户型=三室一厅@联系方式=18293145325@类型=求租@订单状态=已完成@租金=3000@姓名=殷东旭@地址=杭州市拱墅区沈半路328号
 *    0                 1                 2              3            4          5          6
 */
public class RentDetail {
    String roomType;//求租房屋类型0
    String phoneNumber;//电话号码
    String Money;//租金
    String RentOrNot;//求租还是出租
    String address;//求租地址
    HouseTypePics pics;
    public void detail(int num){

        String str = ServerUtil.getSpecificRowString(num);
        String[] str2 = str.split("@");
        String[] str21 = str2[0].split("=");
        roomType=str21[1];
        String[] str22 = str2[1].split("=");
        phoneNumber=str22[1];
        String[] str23 = str2[3].split("=");
        RentOrNot=str23[1];
        String[] str24 = str2[4].split("=");
        Money=str24[1];
        String[] str26 = str2[6].split("=");
        address=str26[1];
        this.pics.housepics(roomType);

    }

}
