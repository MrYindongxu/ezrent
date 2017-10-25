package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends Thread{
    private List<ServerThread> clients = null;//存放ServerThread线程的实例

    public void run() {
        this.startUp();
    }

    //服务器启动 startRegister
    public void startUp() {
        ServerSocket ss = null; //创建ServerSocket
        Socket s = null;        //创建Socket
        try {
            //建立ServerSocket,指定端口
            ss = new ServerSocket(2000);
            //线程集合clients,存储方式:ArrayList
            clients = new ArrayList<ServerThread>();
            //让服务器处于监听状态
            while (true) {
                s = ss.accept();
                ServerThread st = new ServerThread(s);
                new Thread(st).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //内部类,线程ServerThread
    private class ServerThread implements Runnable {
        private Socket s = null;        //创建Socket变量 s
        private BufferedReader br;      //Buffered输入流 br
        private PrintWriter out;        //Print输出流 out
        private String name;            //聊天时,显示用户名 name
        private boolean flag = true;    //是否在线标记判断

        public ServerThread(Socket socket) throws IOException {
            //建立Socket
            this.s = socket;
            //打开输入流BufferedReader
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //输出流PrintWriter,自动刷新
            out = new PrintWriter(socket.getOutputStream(), true);
            //按行读取
            String str = br.readLine();
            //用户名
            name = str + "[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "]";
            //将本类ServerThead实例加入ArrayList
            clients.add(this);
            //向本地及其他人发送"上线了"信息提示
            send(name + "上线了");
        }

        //发送字符串信息,String msg
        private void send(String msg) {
            //遍历集合clients,每个线程输出msg
            for (ServerThread st : clients)
                //某线程输出msg
                st.out.println(msg);
        }

        //接收消息方法
        private void receive() throws IOException {
            String str = null;//缓存消息字符串
            //按行读取,直到末尾
            while ((str = br.readLine()) != null) {
                //判断是否在线
                if (str.equalsIgnoreCase("quit")) {
                    stop();
                    //输出失去连接
                    out.println("disconnect");
                    break;
                }
                //发送消息
                send(name + ":" + str);
            }
        }

        //用户下线方法
        private void stop() {
            //移除clients集合中,本类的实例(某客户端)
            clients.remove(this);
            //标记下线
            flag = false;
            //发送某某下线消息
            send(name + "已经下线了");
        }

        //重写Runnable接口的run方法
        @Override
        public void run() {
            try {
                //判断是否在线,如果在线,接收消息
                while (true) {
                    if (!flag) break;
                    receive();
                }
            } catch (SocketException e) {
                //Socket异常时stop
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭资源
                try {
                    if (s != null)
                        s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

