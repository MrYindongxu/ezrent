package version2.BIMClient;

import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clienteneity.CityMap;
import version2.BIMClient.clienteneity.CodeInMD5;
import version2.BIMClient.clienteneity.idTest;
import version2.BIMClient.clientutil.Request;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRegister extends JFrame {
    private static final long serialVersionUID = -6788045638380819221L;
    // 用户名
    private JTextField ulName;
    String username;
    // 密码
    private JPasswordField ulPasswd;
    String userpassword;
    // 身份证
    private JTextField idNum;
    String idnum;


    // 小容器
    private JLabel j1;//
    private JLabel j2;
    private JLabel j3;
    private JLabel j4;
    //确认地址
    private JLabel jShowAddress;
    // 小按钮
    private JButton bStartRegister;//开始注册按钮
    private JButton bShowAddress;//显示地址按钮

    //创建swing对象
    Swing swing = new Swing();

    /**
     * 初始化EZ租注册页面
     */
    public NewRegister() {
        // 设置登录窗口标题
        this.setTitle("EZ租注册");
        // 采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        // 窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 355, 265);
        // 设置窗体的图标
        Image img0 = new ImageIcon("D:/logo.png").getImage();
        this.setIconImage(img0);
        // 窗体大小不能改变
        this.setSize(400, 400);
        this.setResizable(false);
        // 居中显示
        this.setLocationRelativeTo(null);
        // 窗体显示
        this.setVisible(true);
    }

    /**
     * 窗体组件初始化
     */
    public void init() {
        // 创建一个容器,其中的图片大小和setBounds第三、四个参数要基本一致(需要自己计算裁剪)
        Container container = this.getContentPane();
        // 用户名输入框
        ulName = new JTextField();
        ulName.setBounds(150, 110, 150, 20);
        j1 = new JLabel();
        // 设置背景图片
        Image img1 = new ImageIcon("/*src/version2/BIMServer/info/登录.png*/").getImage();
        j1.setIcon(new ImageIcon(img1));
        j1.setBounds(0, 0, 400, 400);
        // 用户名
        j2 = new JLabel("用户名");
        j2.setBounds(80, 110, 70, 20);
        // 密码输入框
        ulPasswd = new JPasswordField();
        ulPasswd.setBounds(150, 160, 150, 20);
        // 密码
        j3 = new JLabel("密码");
        j3.setBounds(80, 160, 70, 20);
        // 身份证号码输入框
        idNum = new JTextField();
        idNum.setBounds(150, 210, 150, 20);
        // 身份证提示
        j4 = new JLabel("身份证号码");
        j4.setBounds(80, 210, 70, 20);
        //身份证验证框
        jShowAddress = new JLabel();
        jShowAddress.setBounds(10,270,370,20);
        // 开始注册
        // 开始登陆
        bStartRegister = new JButton("开始注册");
        bShowAddress = new JButton("确认地址");
        // 调用方法设置按钮属性
        swing.setjButton(bStartRegister);
        bStartRegister.setBounds(160, 300, 90, 20);
        swing.setjButton(bShowAddress);
        bShowAddress.setBounds(160,240,90,20);
        // 给按钮添加
        bStartRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if ("开始注册".equals(cmd)) {
                    username = ulName.getText();
                    userpassword = ulPasswd.getText();
                    userpassword = CodeInMD5.getMD5(userpassword);
                    idnum = idNum.getText();
                    if (username.length() >= 3) {
                        if (userpassword.length() >= 6) {
                            if (idnum.length() == 18) {
                                setVisible(false);
                                Login login = new Login();
                                login.setVisible(true);
                                Request request = new Request();
                                request.setType(1);//标记注册
                                idTest idtest = new idTest();//实名验证类
                                CityMap.getMap();//实名验证类里的地区获取
                                String id;//身份证ID字符串
                                Account account = new Account(username, userpassword, idnum);
                                request.setAccount(account);
                                ClientLogin clientLogin = new ClientLogin();
                                clientLogin.startRegister(request);
                                JOptionPane
                                        .showMessageDialog(null, "注册成功");
                            } else {
                                JOptionPane
                                        .showMessageDialog(null, "身份证格式输入错误");
                            }
                        } else {
                            JOptionPane.showConfirmDialog(null,
                                    "密码格式输入错误，长度必须大于6位");
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "用户名格式输入错误，长度必须大于3位");
                    }
                }

            }

        });
        bShowAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if("确认地址".equals(cmd)){
                    CityMap.getMap();
                    idnum = idNum.getText();
                    idTest iD = new idTest();
                    if(iD.idLegit(idnum)){
                        jShowAddress.setText(iD.toString());
                    }else{
                        jShowAddress.setText("身份证输入不合法");
                    }



                }
            }
        });

        // 游戏模式
        // 所有组件用容器装载
        j1.add(j2);
        j1.add(j3);
        j1.add(j4);
        j1.add(bStartRegister);
        j1.add(bShowAddress);
        j1.add(jShowAddress);
        container.add(j1);
        container.add(ulName);
        container.add(ulPasswd);
        container.add(idNum);

    }
public static void main(String[] args){
       new NewRegister();
}

}
