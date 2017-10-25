package version2.BIMServer;

import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clientutil.Request;
import version2.BIMServer.serverutil.FileOperate;
import version2.BIMServer.serverutil.ServerUtil;
import version2.ServerChat;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLogin {
    //服务器开始运行方法
    public void stratUp() {
        try {
            //建立一个服务器Socket(ServerSocket),指定端口并开始监听
            ServerSocket serverSocket = new ServerSocket(8800);
            //使用accept()方法等待客户端触发通信
            Socket socket = null;
            //监听一直进行中
            while (true) {
                socket = serverSocket.accept();
                ServerLoginThread loginThread = new ServerLoginThread(socket);
                loginThread.start();
            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
    }
}

//线程类,实现多客户端登录
class ServerLoginThread extends Thread {
    Socket socket = null;
    FileOperate fo = new FileOperate();
    //每启动一个线程,连接对应的Socket
    public ServerLoginThread(Socket socket) {
        this.socket = socket;
    }
    //启动线程,即响应客户请求
    public void run() {
        try {
            //打开输入/输出流
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            //反序列化
            ObjectInputStream ois = new ObjectInputStream(is);
            //获取客户端信息,即从输入流读取信息
            Request request = new Request();
            request = (Request) ois.readObject();
            Account account;
            String password;
            String userName;
            String idnum;
            String replay;
            boolean isRight = false;
            boolean isExit = false;
            switch (request.getType()) {
                case 0:
                    account = request.getAccount();
                    password = account.getPassword();
                    userName = account.getUserName();
                    String str ="userName=" + userName + "@password=" + password;
                    isRight = fo.readFile(str);
                    if (isRight) {
                        replay = "true";
                    } else {
                        replay = "fasle";
                    }
                    os.write(replay.getBytes());
                    break;
                case 1:
                    account = request.getAccount();
                    password = account.getPassword();
                    userName = account.getUserName();
                    idnum = account.getIdnum();
                    str = "userName=" + userName + "@password=" + password + "@idnum=" + idnum;
                    fo.createfile(str);
                    isExit = fo.flag;
                    String reply;
                    if(isExit){
                        reply = "true";
                    }else {
                        reply = "false";
                    }
                    os.write(reply.getBytes());
                    break;
                case 21:
                    replay = FileOperate.showMessage();
                    os.write(replay.getBytes());
                    break;
                case 22:
                    FileOperate.saveStringToTxt(request);
                    break;
                case 23:
                    replay = FileOperate.splitInfo(request);
                    os.write(replay.getBytes());
                    break;
                case 24:
                    new ServerChat().start();
                    replay = "开始聊天!(输入quit结束)";
                    os.write(replay.getBytes());
                    break;
                case 251:
                    int num = request.getOrderChoice();
                    str = ServerUtil.getSpecificRowString(num);
                    String newStr = str.replace("无订单", "已预订");
                    String allInfos = ServerUtil.saveStringToSpecificRow(newStr, num);
                    ServerUtil.replaceStringOfTxt(allInfos);
                    replay = "替换成功";
                    os.write(replay.getBytes());
                    break;
                case 252:
                    int num1 = request.getOrderChoice();
                    String str1 = ServerUtil.getSpecificRowString(num1);
                    String newStr1 = str1.replace("已预订", "无订单");
                    String allInfos1 = ServerUtil.saveStringToSpecificRow(newStr1, num1);
                    ServerUtil.replaceStringOfTxt(allInfos1);
                    replay = "替换成功";
                    os.write(replay.getBytes());
                    break;
                case 253:
                    int num2 = request.getOrderChoice();
                    String str2 = ServerUtil.getSpecificRowString(num2);
                   if (ServerUtil.splitSpecificRow(str2)) {
                        String newStr2 = str2.replace("已预订", "已完成");
                        String allInfos2 = ServerUtil.saveStringToSpecificRow(newStr2, num2);
                        ServerUtil.replaceStringOfTxt(allInfos2);
                        replay = "替换成功";
                        os.write(replay.getBytes());
                   } else {
                        replay = "无订单或订单未预订，不能完成此项订单！";
                        os.write(replay.getBytes());
                    }
                    break;
                case 26:

                    break;
            }

            //关闭资源
            ois.close();
            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}