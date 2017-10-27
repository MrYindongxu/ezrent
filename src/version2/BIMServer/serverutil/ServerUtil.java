package version2.BIMServer.serverutil;

import version2.BIMClient.Login;

import java.io.*;
import java.net.Socket;

public class ServerUtil {
    /**
     * 得到指定行数信息的第一个数字
     * 用于实现下订单功能
     *
     * @return
     */
    public static int getLastRowFirstChar() {
        int res = -1;
        try {
            //创建文件输入流对象
            FileInputStream fis = new FileInputStream("src/version2/BIMServer/info/rentMsg.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String info = "";
            String resInfo = "";
            while ((info = br.readLine()) != null) {
                resInfo = info;
            }
            //获取读取到的第一个字符
            char c = resInfo.charAt(0);
            //转换成int类型
            res = Integer.parseInt(c + "");
            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 给客户端发送一个object对象
     *
     * @param socket
     * @param object
     */
    public static void sendObjToClient(Socket socket, Object object) {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(object);
        } catch (Exception e) {
            System.out.println("出现异常");
        }
    }

    /**
     * 得到指定一行
     *
     * @param num
     * @return
     */
    public static String getSpecificRowString(int num) {
        String resInfo = "";
        try {
            FileInputStream fis = new FileInputStream("src/version2/BIMServer/info/rentMsg.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            for (int i = 0; i < num; i++) {
                resInfo = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resInfo;
    }

    /**
     * 将指定的一行进行替换并践行存储
     *
     * @param info
     * @param row
     * @return
     */
    public static String saveStringToSpecificRow(String info, int row) {

        String info1 = "";
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream("src/version2/BIMServer/info/rentMsg.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int nowRow = 1;
            while ((info1 = br.readLine()) != null) {
                if (nowRow == row) {
                    sb.append(info + "\n");//这里需要改进
                    nowRow++;
                    continue;
                }
                sb.append(info1 + "\n");
                nowRow++;
            }
            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将整个文件进行替换
     *
     * @param infoAll
     */
    public static void replaceStringOfTxt(String infoAll) {

        try {
            FileOutputStream fos = new FileOutputStream("src/version2/BIMServer/info/rentMsg.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(infoAll);
            bw.close();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 判定订单是否为已预订
     * @param str2
     * @return
     */
   public static boolean splitSpecificRow(String str2) {
        boolean flag = false;
        String[] str1 = str2.split("@");
        String[] str11 = str1[3].split("=");
        if (str11[1].equals("已预订")) {
            flag = true;
        }
        return flag;
    }

}
