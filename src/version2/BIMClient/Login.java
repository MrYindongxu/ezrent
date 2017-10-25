package version2.BIMClient;
import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clienteneity.CodeInMD5;
import version2.BIMClient.clientutil.Request;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Login extends JFrame{
    boolean isOnline = false;
    public static String accoundId;
    public static Account newAccount;
    private static final long serialVersionUID = -6788045638380819221L;
    //用户名
    private JTextField ulName;
    String userName;
    //密码
    private JPasswordField ulPasswd;
    String pwd;
    //小容器
    private JLabel j1;
    //private JLabel j2;
    private JLabel j3;
    private JLabel j4;
    //小按钮
    private JButton b1;
    private JButton b2;
    /*private JButton b3;*/
    private JComboBox<String> cb2;
    /**
     * 初始化天天吃鸡登录页面
     * */
    public Login(){
        //设置登录窗口标题
        this.setTitle("EZ租");
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
       this.setBounds(0, 0, 355, 265);
        //设置窗体的图标
        Image img0 = new ImageIcon("D:/logo.png").getImage();
        this.setIconImage(img0);
        //窗体大小不能改变
        this.setSize(400, 700);
       this.setResizable(false);
        //居中显示
        this.setLocationRelativeTo(null);
        //窗体显示
        this.setVisible(true);
    }
    /**
     * 窗体组件初始化
     * */
    public void init(){
        //创建一个容器,其中的图片大小和setBounds第三、四个参数要基本一致
        Container container = this.getContentPane();
        j1 = new JLabel();
        //设置背景色
        Image img1 = new ImageIcon("src/version2/BIMServer/info/登录.png").getImage();
        j1.setIcon(new ImageIcon(img1));
        j1.setBounds(0, 0, 400,700);
        //用户名输入框
        ulName = new JTextField();
        ulName.setBounds(150, 510, 150, 20);
        //用户名
        j3 = new JLabel("用户名:");
        j3.setBounds(110, 510, 70, 20);
        j3.setForeground(Color.black);
        //密码输入框
        ulPasswd = new JPasswordField();
        ulPasswd.setBounds(150, 550, 150, 20);
        //找回密码
        j4= new JLabel("密码:");
        j4.setBounds(110, 550, 70, 20);
        j4.setForeground(Color.black);
        //登陆按钮
        b1 = new JButton("账号登录");
        //设置字体和颜色和手形指针
        b1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        b1.setForeground(Color.BLACK);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(80, 610, 90, 20);
        b2 = new JButton("账号注册");
        //设置字体和颜色和手形指针
        b2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        b2.setForeground(Color.BLACK);
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.setBounds(240, 610, 90, 20);
        //给按钮添加
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if("账号登录".equals(cmd)){
                    userName = ulName.getText();
                    pwd = ulPasswd.getText();
                    pwd = CodeInMD5.getMD5(pwd);
                        Request request = new Request();
                        Account account = new Account(userName, pwd);
                        request.setType(0);
                        request.setAccount(account);
                        ClientLogin clientLogin = new ClientLogin();
                        boolean isLogin = clientLogin.startLogin(request);
                        if (isLogin) {
                            accoundId=userName;
                            newAccount = account;
                            JOptionPane.showConfirmDialog(null, "登录成功");
                            setVisible(false);
                            NewGame game = new NewGame();
                            game.setVisible(true);
                        } else {
                            JOptionPane.showConfirmDialog(null, "登录失败");
                        }
                    }
            }

        });
        /**
         * 账号注册鼠标点击事件
         */
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String registerStr = e.getActionCommand();
                if("账号注册".equals(registerStr)){
                    setVisible(false);
                    NewRegister register = new NewRegister();
                    register.setVisible(true);
                }
            }
        });
        //所有组件用容器装载
        j1.add(j3);
        j1.add(j4);
        j1.add(b1);
        j1.add(b2);
        container.add(j1);
        container.add(ulName);
        container.add(ulPasswd);
    }
}
