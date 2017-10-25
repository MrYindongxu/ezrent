package version2.BIMClient.clientutil;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientUtil {
    /**
     *给服务器发送一个object对象
     * @param socket
     * @param object
     */
    public static void sendObjToServer(Socket socket, Object object){
        try {
            //创建socket输出流对象
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(object);
        }catch (Exception e){
            System.out.println("出现异常");
        }
    }

    /**
     * 获取服务器端的响应，返回一个object类型的对象
     * @param socket
     * @return
     */
    public Object getObjServer(Socket socket){
        try {
            socket.shutdownOutput();
            InputStream is= socket.getInputStream();
            ObjectInputStream ois=new ObjectInputStream(is);
            Object o =ois.readObject();

            ois.close();
            is.close();
            socket.close();

            return o;

        }catch (Exception e){
        }
        return null;
    }
}
