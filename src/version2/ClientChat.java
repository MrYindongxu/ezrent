package version2;

import version2.BIMClient.Login;
import version2.BIMClient.NewGame;
import version2.BIMClient.Swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientChat {
    private Socket s;               //创建Socket s
    private BufferedReader br;      //Buffered输入流 br
    static public PrintWriter out;  //Print输出流 out
    private boolean flag = true;    //是否在线标记判断
    public void stratUp() {
        BufferedReader sbr = null;  //Buffered输入流 sbr
        try {
            //建立Socket,确定主机地址,指定端口
            s = new Socket("localhost", 2000);
            //输出流,true表示自动刷新
            out = new PrintWriter(s.getOutputStream(), true);
            //输入流
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //输出本机用户名
            out.println(Login.accoundId);
            //输入流
            sbr = new BufferedReader(new InputStreamReader(System.in));
            //新进程开始
            new Thread(new ClientThread()).start();
            //定义字符串接收读取到的数据
            String str = null;
            //标记退出与输入enter后退出客户端
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
       }
    }
    //接收消息方法
    public String receive() throws IOException {
            //读一行
            String rs = br.readLine();
            //如果返回disconnect,标记退出聊天
            if (rs.equalsIgnoreCase("disconnect")) {
                flag = false;
                System.out.print("点击回车退出");
            }
            //输出读到的字符串
            String info="";
            info+=rs+"\n";
          Swing.j123.append(info);
          return rs;
    }

    //内部类,ClientThread线程
    private class ClientThread implements Runnable {
        //run方法
        @Override
        public void run() {
            //判断是否在线,若果在线,接收消息
            while (true) {
                if (!flag) break;
                try {
                    receive();
                } catch (IOException e) {
                    System.err.println("已断线");
                    return;
                }
            }
        }
    }

}
