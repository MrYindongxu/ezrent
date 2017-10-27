package version2.BIMServer.serverutil;

import version2.BIMClient.Login;
import version2.BIMClient.clienteneity.ForRentMessage;
import version2.BIMClient.clientutil.Request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileOperate {
    //新建一个文件
    File efile1 = new File("src/version2/BIMServer/info/accountMsg.txt");
    public boolean flag = false;
    public boolean isRight = false; //判定分割@符及=号
    static boolean isIn = false;

    /**
     * 实现对账号信息的写入
     *
     * @param str
     */
    public void createfile(String str) {
        //创建类的对象
        FileOperate cf = new FileOperate();
        //判断文件是否创建的方法
        cf.isExit1(efile1);
        //使用FileWriter类实现文件的写入
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(efile1, true);
            bw = new BufferedWriter(fw);
            bw.write(str);
            bw.newLine();
            bw.flush();
            System.out.println("写入完毕！");
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 完成账户的登录和注册
     */
    public boolean readFile(String str) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            //创建一个FileReader对象
            fr = new FileReader("src/version2/BIMServer/info/accountMsg.txt");
            //创建一个BufferedReader对象
            br = new BufferedReader(fr);
            String line = str;
            String[] str1 = line.split("@");
            String str11 = str1[0];
            String str12 = str1[1];
            String str21 = null;
            String str22 = null;
            line = br.readLine();
            while (line != null) {
                String[] str2 = line.split("@");
                str21 = str2[0];
                str22 = str2[1];
                if (str11.equals(str21)) {
                    flag = true;
                }
                if ((str11.equals(str21)) && (str12.equals(str22))) {
                    isRight = true;
                    break;
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isRight;
    }

    public void isExit1(File efile) {
        if (!efile.exists()) {
            try {
                efile.createNewFile();
                System.out.println("账号信息文件已创建");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现储存求租和出租信息的功能
     *
     * @param request
     */
    public static void saveStringToTxt(Request request) {
        File file = new File("src/version2/BIMServer/info/rentMsg.txt");
        int num = ServerUtil.getLastRowFirstChar();
        num++;
        ForRentMessage rent = request.getForRentMessage();
        String address = rent.getAddress();
        String phoneNum = rent.getPhoneNumber();
        String roomType = rent.getRoomType();
        String rentOrNot = rent.getRentOrNot();
        String rentState = rent.getOrdingState();
        String rentMoney = rent.getMoney();
        String name = request.getAccount().getUserName();
        String res = num + "户型=" + roomType + "@联系方式=" + phoneNum + "@类型="
                + rentOrNot + "@订单状态=" + rentState + "@租金=" + rentMoney+"@姓名="+name +"@地址=" + address ;
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(res);
            bw.newLine();
            bw.flush();
            System.out.println("写入成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 操作Info文件，将其中的数据进行拆分后储存到一个集合中
     * 实现在客户端搜索服务端信息的功能
     */
    public static String splitInfo(Request request) {
        /*
         *实现对文件的读入
		 */
        FileReader fr = null;
        BufferedReader br = null;
        String research = request.getResearch();
        ArrayList<String> list = new ArrayList<String>();
        String str = new String();
        try {
            //创建一个FileReader对象
            fr = new FileReader("src/version2/BIMServer/info/rentMsg.txt");
            //创建一个BufferedReader对象
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] str2 = line.split("@");
                String[] str21 = str2[0].split("=");
                String[] str22 = str2[1].split("=");
                String[] str23 = str2[3].split("=");
                String[] str24 = str2[4].split("=");
                if (research.equals(str21[1])) {
                    list.add(line + "\n");
                } else if (research.equals(str22[1])) {
                    list.add(line + "\n");
                } else if (research.equals(str23[1])) {
                    list.add(line + "\n");
                }else if (priceRange(research,str24[1])){
                    list.add(line + "\n");
                }else {
                    return "暂无信息";
                }
                line = br.readLine();
            }
            for (int i = 0; i < list.size(); i++) {
                str += list.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 价格对比
     * @param research
     * @param price
     * @return
     */
    public static boolean priceRange(String research,String price){
        int tar = Integer.parseInt(price);
        int range = Integer.parseInt(research);
        if ((range - 200) < tar && tar < (range + 200)){
            isIn=true;
        }
        return isIn;
    }

    /**
     * 实现对Info文件的读入并将信息展现到控制台
     * 实现功能是：显示所有信息
     */
    public static String showMessage() {
        //创建文件读入流
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String str = null;
        try {
            //创建一个FileReader对象
            fr = new FileReader("src/version2/BIMServer/info/rentMsg.txt");
            //创建一个BufferedReader对象
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回拼接成功后的字符串
        return sb.toString();
    }
}

