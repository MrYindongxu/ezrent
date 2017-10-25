package version2.BIMClient;

import version2.BIMClient.clienteneity.Account;
import version2.BIMClient.clienteneity.ForRentMessage;
import version2.BIMClient.clientutil.Request;
import version2.ClientChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {
    //创建一个实现对信息特定输出的一个对象
    splitShowMessage ssm = new splitShowMessage();
    //新建一个容器，用于储存所有组件
    JPanel newJPanel = new JPanel();
    //用于聊天室传递信息
    public static JTextArea j123;
    public JPanel check(){
        newJPanel.setBounds(0,0,800,600);
        Request request = new Request();
        request.setType(21);
        ClientLogin clientLogin = new ClientLogin();
        String message = clientLogin.startLookAllMsg(request);
        String finalMessage = ssm.showMessage(message);
        // 新建一个框体用于显示信息
        JTextArea JAra = new JTextArea(finalMessage);
        //新建一个显示详情的JLabel
        JLabel jDetail = new JLabel("详情");
        //新建一个输入数字的JTextField
        JTextField jNum = new JTextField();
        //添加一个按钮，用于当输入数字后，点击按钮可显示详情
        JButton JNewDetail = new JButton("点击查看");

        JScrollPane jsp = new JScrollPane(JAra);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //设置各组件的属性
        jsp.setBounds(10, 10, 780, 300);
        JAra.setSize(780, 300);
        jDetail.setBounds(120,320,40,25);
        jDetail.setFont(new Font("楷体",Font.PLAIN,15));
        jNum.setBounds(170,320,40,25);
        // 设置字体和颜色
        JAra.setFont(new Font("楷体", Font.PLAIN, 15));
        // 设置边框
        JAra.setBorder(BorderFactory.createLineBorder(Color.black));
        // 设置lblTime的坐标
        JAra.setLocation(10, 10);
        newJPanel.add(jDetail);
        newJPanel.add(jNum);
        newJPanel.add(jsp);
       return newJPanel;
    }
    /**
     *用来发布求租和出租信息
     */
    public JPanel sendMessage(final String str){
        newJPanel.setBounds(0,0,400,400);
        JLabel j1 = new JLabel("请输入户型：");
        final JTextField roomType = new JTextField();
        roomType.setBounds(180, 50, 200, 25);
        final JLabel j2 = new JLabel("请输入地址：");
        final JTextField address = new JTextField();
        address.setBounds(180, 100, 200, 25);
        final JLabel j3 = new JLabel("请输入联系方式：");
        final JTextField phoneNumber = new JTextField();
        phoneNumber.setBounds(180, 150, 200, 25);
        final JLabel j4 = new JLabel("请输入租金:");
        final JTextField rentMoney = new JTextField();
        rentMoney.setBounds(180,200,200,25);
        JButton b1 = new JButton("确认发布");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if ("确认发布".equals(cmd)) {
                    Request request = new Request();
                    request.setType(22);
                    ForRentMessage forRentMessage = new ForRentMessage();
                    forRentMessage.setMoney(rentMoney.getText());
                    forRentMessage.setRentOrNot(str);
                    request.setAccount(Login.newAccount);
                    forRentMessage.setRoomType(roomType .getText());
                    forRentMessage.setAddress(address.getText());
                    forRentMessage.setPhoneNumber(phoneNumber.getText()) ;
                    forRentMessage.setOrdingState("无订单");
                    request.setForRentMessage(forRentMessage);
                    ClientLogin clientLogin = new ClientLogin();
                    clientLogin.startSendMsg(request);
                    JOptionPane.showMessageDialog(null, "发布成功");
                }
            }
        });
        //设置字体和颜色和手形指针
        b1.setFont(new Font("楷体", Font.PLAIN, 15));
        b1.setForeground(Color.BLACK);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(200, 250, 100, 20);
        j1.setSize(200, 25);
        j2.setSize(200, 25);
        j3.setSize(200, 25);
        j4.setSize(200, 25);
        j1.setFont(new Font("楷体", Font.BOLD, 15));
        j2.setFont(new Font("楷体", Font.BOLD, 15));
        j3.setFont(new Font("楷体", Font.BOLD, 15));
        j4.setFont(new Font("楷体", Font.BOLD, 15));
        j1.setLocation(50, 50);
        j2.setLocation(50, 100);
        j3.setLocation(50, 150);
        j4.setLocation(50, 200);
        newJPanel.add(j1);
        newJPanel.add(j2);
        newJPanel.add(j3);
        newJPanel.add(j4);
        newJPanel.add(roomType);
        newJPanel.add(address);
        newJPanel.add(phoneNumber);
        newJPanel.add(rentMoney);
        newJPanel.add(b1);
        return newJPanel;
    }
    /**
     * 用来设置按钮的各种属性
     * @param jButton
     */
    public void setjButton(JButton jButton){
        // 设置字体和颜色和手形指针
        jButton.setFont(new Font("宋体", Font.PLAIN, 12));
        jButton.setForeground(Color.BLACK);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * 实现客户端聊天的方法
     * @return
     */
    public JPanel chat(){
        newJPanel.setBounds(0,0,800,600);
        j123 = new JTextArea();
        JScrollPane jsp1 = new JScrollPane(j123);
        jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp1.setBounds(10, 10, 790, 390);
        final JTextField chatMessage = new JTextField();
        JScrollPane jsp2 = new JScrollPane(chatMessage);
        jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp2.setBounds(10, 10, 790, 100);
        chatMessage.setBounds(180, 150, 200, 25);
        final ClientChat clientChat = new ClientChat();
        Request request = new Request();
        request.setType(24);
        ClientLogin clientLogin = new ClientLogin();
        clientLogin.startSendMsg(request);//需整理
        clientChat.stratUp();
        JButton b1 = new JButton("确认发送");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if ("确认发送".equals(cmd)) {
                    String info = chatMessage.getText();
                    ClientChat.out.println(info);
                    chatMessage.setText("");
                }
            }
        });
        //设置字体和颜色和手形指针
        b1.setFont(new Font("楷体", Font.PLAIN, 15));
        b1.setForeground(Color.BLACK);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setBounds(680, 510, 100, 20);
        j123.setSize(790, 490);
        chatMessage.setSize(773, 100);
        chatMessage.setLocation(10, 400);
        // 设置字体和颜色
        j123.setFont(new Font("楷体", Font.PLAIN, 15));
        chatMessage.setFont(new Font("楷体", Font.PLAIN, 15));
        // 设置边框
        j123.setBorder(BorderFactory.createLineBorder(Color.black));
        chatMessage.setBorder(BorderFactory.createLineBorder(Color.black));
        newJPanel.add(jsp1);
        newJPanel.add(chatMessage);
        newJPanel.add(b1);
        return newJPanel;
    }
}
