package version2.BIMClient;

public class splitShowMessage {
    public String showMessage(String message){
        StringBuffer sb = new StringBuffer();
        sb.append("序号\t户型\t联系方式\t\t类型\t订单状态\t租金\t用户名\t地址\t\n\n");
        String[] Str1 = message.split("\n");
        for(int m = 0;m<Str1.length;m++){
            sb.append(m+1+"\t");
                String []Str2 = Str1[m].split("@");
                for(int j = 0;j<Str2.length;j++){
                    String[] Str3 = Str2[j].split("=");
                    sb.append(Str3[1]+"\t");
                    if(j==6){
                        sb.append( "\n");
                    }
                }
            }
        return sb.toString();
    }
}
