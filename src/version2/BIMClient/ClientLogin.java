package version2.BIMClient;

import version2.BIMClient.clientutil.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientLogin {
    private Socket s;               //创建Socket s
    private BufferedReader br;      //Buffered输入流 br
    private PrintWriter out;        //Print输出流 out
    private boolean flag = true;    //是否在线标记判断

    //客户端开始运行的方法
    public void startRegister(Request request) {
        BufferedReader sbr = null;  //Buffered输入流 sbr
        try {
            //建立Socket,确定主机地址,指定端口8800
            s = new Socket("127.0.0.1", 8800);
            //打开输入/输出流
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            //对象序列化
            ObjectOutputStream oos = new ObjectOutputStream(os);
            //输入流
            br = new BufferedReader(new InputStreamReader(is));
            oos.writeObject(request);
            s.shutdownOutput();
            //反馈
            String reply = null;
            while (!((reply = br.readLine()) == null)) {
                System.out.println(reply);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean startLogin(Request request) {
        boolean flag = false;
        BufferedReader sbr = null;  //Buffered输入流 sbr
        try {
            //建立Socket,确定主机地址,指定端口8800
            s = new Socket("127.0.0.1", 8800);
            //打开输入/输出流
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            //对象序列化
            ObjectOutputStream oos = new ObjectOutputStream(os);
            //输入流
            br = new BufferedReader(new InputStreamReader(is));
            oos.writeObject(request);
            s.shutdownOutput();
            //反馈
            String reply = null;
            while (!((reply = br.readLine()) == null)) {
                //System.out.print("服务器:" + reply);
                if (reply.equals("true")) {
                    flag = true;
                } else if (reply.equals("false")) {
                    flag = false;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public String  startLookAllMsg(Request request) {
        String reply = null ;
        StringBuffer sb=null;
        BufferedReader sbr = null;  //Buffered输入流 sbr
        try {
            //建立Socket,确定主机地址,指定端口8800
            s = new Socket("127.0.0.1", 8800);
            //打开输入/输出流
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            //对象序列化
            ObjectOutputStream oos = new ObjectOutputStream(os);
            //输入流
            br = new BufferedReader(new InputStreamReader(is));
            oos.writeObject(request);
            s.shutdownOutput();
           sb=new StringBuffer();
            //反馈
            while (!((reply = br.readLine()) == null)) {
               sb.append(reply+"\n");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public String  startSendMsg(Request request) {
        StringBuffer sb = null;
        String reply = null;
        BufferedReader sbr = null;  //Buffered输入流 sbr
        try {
            //建立Socket,确定主机地址,指定端口8800
            s = new Socket("127.0.0.1", 8800);
            //打开输入/输出流
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            //对象序列化
            ObjectOutputStream oos = new ObjectOutputStream(os);
            //输入流
            br = new BufferedReader(new InputStreamReader(is));
            oos.writeObject(request);
            s.shutdownOutput();
            //反馈
            sb = new StringBuffer();
            while (!((reply = br.readLine()) == null)) {
                sb.append(reply+"\n");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      return sb.toString();
    }
}
