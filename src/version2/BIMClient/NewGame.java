package version2.BIMClient;
import version2.BIMClient.clientutil.Request;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NewGame extends JFrame {
    JPanel centerpanel = null;
    splitShowMessage ssm = new splitShowMessage();
    Swing swing = new Swing();
    // 初始化面板
    private void initContorl() {
        centerpanel = new JPanel();
        JLabel j1 = new JLabel();
        // 清空centerpanel面板的默认布局
        centerpanel.setLayout(null);
        Image img1 = new ImageIcon("src/version2/BIMServer/info/mainPicture.jpg").getImage();
        j1.setIcon(new ImageIcon(img1));
        j1.setBounds(0, -120, 800,800);
        // 设置窗体大小不可改变
        this.setResizable(false);
        centerpanel.add(j1);
        this.add(centerpanel, BorderLayout.CENTER);
    }
    // 构造函数
    public NewGame() {
        // 初始化面板
        initContorl();
        // 添加菜单栏到面板中
        addMenu();
        this.setSize(800, 600);
        this.setTitle("EZ租");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initPanel(String str) {
        if (str.equals("check")) {
            centerpanel.setSize(800, 600);
            JPanel jPanel = swing.check();
            centerpanel.add(jPanel);
            centerpanel.setVisible(true);
        }
        if (str.equals("lodger")) {
            centerpanel.setSize(800, 600);
            JPanel jPanel = swing.sendMessage("求租");
            centerpanel.setVisible(true);
            centerpanel.add(jPanel);
        }
       if (str.equals("landLoad")) {
            centerpanel.setSize(800, 600);
            JPanel jPanel = swing.sendMessage("出租");
            centerpanel.setVisible(true);
            jPanel.setLayout(null);
            centerpanel.add(jPanel);
        }
        if (str.equals("seach")) {
            centerpanel.setSize(800, 600);
            JLabel j1 = new JLabel("请输入您要搜索的信息：");
            final JTextField seachMessage = new JTextField();
            seachMessage.setBounds(220, 50, 200, 25);
            JLabel j2 = new JLabel("搜索结果");
            final JTextArea JAra1 = new JTextArea("搜索结果为：\n");
            JButton b1 = new JButton("确认搜索");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("确认搜索".equals(cmd)) {
                        Request request = new Request();
                        request.setType(23);
                        request.setResearch(seachMessage.getText()) ;
                        ClientLogin clientLogin = new ClientLogin();
                        String seach = clientLogin.startSendMsg(request);
                        JAra1.setText(ssm.showMessage(seach));
                        JOptionPane.showMessageDialog(null, "搜索成功");
                    }
                }
            });
            //设置字体和颜色和手形指针
            b1.setFont(new Font("楷体", Font.PLAIN, 15));
            b1.setForeground(Color.BLACK);
            b1.setBounds(210, 120, 100, 20);
            j1.setSize(200, 25);
            j2.setSize(200, 25);
            JAra1.setSize(700, 300);
            j1.setFont(new Font("楷体", Font.PLAIN, 15));
            j2.setFont(new Font("楷体", Font.PLAIN, 15));
            j1.setLocation(50, 50);
            j2.setLocation(50, 200);
            JAra1.setLocation(50, 220);
            // 设置字体和颜色
            JAra1.setFont(new Font("楷体", Font.PLAIN, 15));
            // 设置边框
            JAra1.setBorder(BorderFactory.createLineBorder(Color.black));
            centerpanel.add(j1);
            centerpanel.add(j2);
            centerpanel.add(JAra1);
            centerpanel.add(b1);
            centerpanel.add(seachMessage);
        }
        if (str.equals("chat")) {
            centerpanel.setSize(800, 540);
            JPanel jPanel = swing.chat();
            centerpanel.add(jPanel);
            centerpanel.setVisible(true);
        }
        if (str.equals("placeAnOrder")) {
            centerpanel.setSize(800, 600);
            JLabel j1 = new JLabel("请输入订单的编号");
            final JTextField OrderNum = new JTextField();
            OrderNum.setBounds(220, 500, 200, 25);
            Request request = new Request();
            request.setType(21);
            ClientLogin clientLogin = new ClientLogin();
            String message = clientLogin.startLookAllMsg(request);
            // 新建一个框体用于显示信息
            final JTextArea JAra;
            JAra = new JTextArea(ssm.showMessage(message));
            JButton b1 = new JButton("确认");
            JButton b2 = new JButton("刷新");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("确认".equals(cmd)) {
                        Request request = new Request();
                        request.setType(251);
                        Integer integer = new Integer(OrderNum.getText());
                        request.setOrderChoice(integer);
                        ClientLogin clientLogin = new ClientLogin();
                        clientLogin.startSendMsg(request);//需整理
                        JOptionPane.showMessageDialog(null, "下单成功");
                    }
                }
            });
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd2 = e.getActionCommand();
                    if ("刷新".equals(cmd2)) {
                        Request request = new Request();
                        request.setType(21);
                        ClientLogin clientLogin = new ClientLogin();
                        String message1 = clientLogin.startLookAllMsg(request);
                        JAra.setText(ssm.showMessage(message1));
                    }
                }
            });
            //设置字体和颜色和手形指针
            b1.setFont(new Font("楷体", Font.PLAIN, 15));
            b2.setFont(new Font("楷体", Font.PLAIN, 15));
            b1.setForeground(Color.BLACK);
            b2.setForeground(Color.BLACK);
            b1.setBounds(340, 450, 100, 20);
            b2.setBounds(640, 360, 100, 20);
            j1.setSize(200, 25);
            JAra.setSize(700, 300);
            j1.setFont(new Font("楷体", Font.PLAIN, 15));
            OrderNum.setFont(new Font("楷体", Font.PLAIN, 15));
            j1.setLocation(200, 400);
            OrderNum.setLocation(380, 400);
            JAra.setLocation(50, 50);
            // 设置字体和颜色
            JAra.setFont(new Font("楷体", Font.PLAIN, 15));
            // 设置边框
            JAra.setBorder(BorderFactory.createLineBorder(Color.black));
            centerpanel.add(j1);
            centerpanel.add(OrderNum);
            centerpanel.add(JAra);
            centerpanel.add(b1);
            centerpanel.add(b2);
        }
        if (str.equals("cancellationOfOrder")) {
            centerpanel.setSize(800, 600);
            JLabel j1 = new JLabel("请输入您要取消订单的编号");
            final JTextField OrderNum = new JTextField();
            OrderNum.setBounds(220, 500, 200, 25);
            Request request = new Request();
            request.setType(21);
            ClientLogin clientLogin = new ClientLogin();
            String message = clientLogin.startLookAllMsg(request);
            // 新建一个框体用于显示信息
            final JTextArea JAra;
            JAra = new JTextArea(ssm.showMessage(message));
            JButton b1 = new JButton("确认取消");
            JButton b2 = new JButton("刷新");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("确认取消".equals(cmd)) {
                        Request request = new Request();
                        request.setType(252);
                        Integer integer = new Integer(OrderNum.getText());
                        request.setOrderChoice(integer);
                        ClientLogin clientLogin = new ClientLogin();
                        clientLogin.startSendMsg(request);//需整理
                        JOptionPane.showMessageDialog(null, "取消订单成功");
                    }
                }
            });
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd2 = e.getActionCommand();
                    if ("刷新".equals(cmd2)) {
                        Request request = new Request();
                        request.setType(21);
                        ClientLogin clientLogin = new ClientLogin();
                        String message1 = clientLogin.startLookAllMsg(request);
                        JAra.setText(ssm.showMessage(message1));
                    }
                }
            });
            //设置字体和颜色和手形指针
            b1.setFont(new Font("楷体", Font.PLAIN, 15));
            b2.setFont(new Font("楷体", Font.PLAIN, 15));
            b1.setForeground(Color.BLACK);
            b2.setForeground(Color.BLACK);
            b1.setBounds(340, 450, 100, 20);
            b2.setBounds(640, 360, 100, 20);
            j1.setSize(200, 25);
            JAra.setSize(700, 300);
            j1.setFont(new Font("楷体", Font.PLAIN, 15));
            OrderNum.setFont(new Font("楷体", Font.PLAIN, 15));
            j1.setLocation(200, 400);
            OrderNum.setLocation(380, 400);
            JAra.setLocation(50, 50);
            // 设置字体和颜色
            JAra.setFont(new Font("楷体", Font.PLAIN, 15));
            // 设置边框
            JAra.setBorder(BorderFactory.createLineBorder(Color.black));
            centerpanel.add(j1);
            centerpanel.add(OrderNum);
            centerpanel.add(JAra);
            centerpanel.add(b1);
            centerpanel.add(b2);
        }
        if (str.equals("completeOrder")) {
            centerpanel.setSize(800, 600);
            JLabel j1 = new JLabel("请输入您要完成的订单编号");
            final JTextField OrderNum = new JTextField();
            OrderNum.setBounds(220, 500, 200, 25);
            Request request = new Request();
            request.setType(21);
            ClientLogin clientLogin = new ClientLogin();
            final String message = clientLogin.startLookAllMsg(request);
            final JTextArea JAra;
            JAra = new JTextArea(ssm.showMessage(message));
            JButton b1 = new JButton("确认完成");
            JButton b2 = new JButton("刷新");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("确认完成".equals(cmd)) {
                        Request request = new Request();
                        request.setType(253);
                        Integer integer = new Integer(OrderNum.getText());
                        request.setOrderChoice(integer);
                        ClientLogin clientLogin = new ClientLogin();
                        clientLogin.startSendMsg(request);//需整理
                        JOptionPane.showMessageDialog(null, "成功");
                    }
                }
            });
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd2 = e.getActionCommand();
                    if ("刷新".equals(cmd2)) {
                        Request request = new Request();
                        request.setType(21);
                        ClientLogin clientLogin = new ClientLogin();
                        String message1 = clientLogin.startLookAllMsg(request);
                        JAra.setText(ssm.showMessage(message1));
                    }
                }
            });
            //设置字体和颜色和手形指针
            b1.setFont(new Font("楷体", Font.PLAIN, 15));
            b2.setFont(new Font("楷体", Font.PLAIN, 15));
            b1.setForeground(Color.BLACK);
            b2.setForeground(Color.BLACK);
            b1.setBounds(340, 450, 100, 20);
            b2.setBounds(640, 360, 100, 20);
            j1.setSize(200, 25);
            JAra.setSize(700, 300);
            j1.setFont(new Font("楷体", Font.PLAIN, 15));
            OrderNum.setFont(new Font("楷体", Font.PLAIN, 15));
            j1.setLocation(200, 400);
            OrderNum.setLocation(380, 400);
            JAra.setLocation(50, 50);
            // 设置字体和颜色
            JAra.setFont(new Font("楷体", Font.PLAIN, 15));
            // 设置边框
            JAra.setBorder(BorderFactory.createLineBorder(Color.black));
            centerpanel.add(j1);
            centerpanel.add(OrderNum);
            centerpanel.add(JAra);
            centerpanel.add(b1);
            centerpanel.add(b2);
        }
        if (str.equals("help")) {
        }
        if (str.equals("return")) {
            centerpanel.setSize(1000, 700);
        }
    }

    // 添加菜单
    // 完成菜单的添加
    private void addMenu() {
        // 创建一个菜单栏
        JMenuBar menubar = new JMenuBar();
        // 创建菜单按钮
        JMenu menu1 = new JMenu("信息");
        JMenu menu2 = new JMenu("发布信息");
        JMenu menu4 = new JMenu("聊天");
        JMenu menu5 = new JMenu("订单");
        /*JMenu menu6 = new JMenu("个人积分");*/
        JMenu menu7 = new JMenu("帮助");
        /*JMenu menu8 = new JMenu("退出");*/
        JMenu menu9 = new JMenu("返回主界面");
        // 将菜单按钮添加到菜单栏中
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu4);
        menubar.add(menu5);
    /*	menubar.add(menu6);*/
        menubar.add(menu7);
        /*menubar.add(menu8);*/
        menubar.add(menu9);
        // 添加菜单项
        JMenuItem item1 = new JMenuItem("查看所有信息");
        item1.setActionCommand("checkAllInfo");
        JMenuItem item9 = new JMenuItem("搜索信息");
        item9.setActionCommand("seach");
        JMenuItem item2 = new JMenuItem("求租");
        item2.setActionCommand("lodger");
        JMenuItem item3 = new JMenuItem("出租");
        item3.setActionCommand("landLoad");
        JMenuItem item5 = new JMenuItem("聊天");
        item5.setActionCommand("chat");
        JMenuItem item6 = new JMenuItem("下订单");
        item6.setActionCommand("placeAnOrder");
        JMenuItem item7 = new JMenuItem("取消订单");
        item7.setActionCommand("cancellationOfOrder");
        JMenuItem item8 = new JMenuItem("完成订单");
        item8.setActionCommand("completeOrder");
        JMenuItem item10 = new JMenuItem("帮助");
        item10.setActionCommand("help");
        JMenuItem item11 = new JMenuItem("退 出");
        item11.setActionCommand("exit");
        JMenuItem item12 = new JMenuItem("返回主界面");
        item12.setActionCommand("return");
        item1.addActionListener(new MenuItemClick());
        item2.addActionListener(new MenuItemClick());
        item3.addActionListener(new MenuItemClick());
        item5.addActionListener(new MenuItemClick());
        item6.addActionListener(new MenuItemClick());
        item7.addActionListener(new MenuItemClick());
        item8.addActionListener(new MenuItemClick());
        item9.addActionListener(new MenuItemClick());
        item10.addActionListener(new MenuItemClick());
        item11.addActionListener(new MenuItemClick());
        item12.addActionListener(new MenuItemClick());
        // 将菜单项添加到菜单按钮中
        menu1.add(item1);
        menu1.add(item9);
        menu2.add(item2);
        menu2.add(item3);
        menu4.add(item5);
        menu5.add(item6);
        menu5.add(item7);
        menu5.add(item8);
        /*menu6.add(item9);*/
        menu7.add(item10);
       /* menu8.add(item11);*/
        menu9.add(item12);
        // 将菜单栏添加到窗体中
        this.setJMenuBar(menubar);
    }

    // 单击菜单事件
    private class MenuItemClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String type = e.getActionCommand();
            //当点击的是查看信息按钮
            if (type.equals("checkAllInfo")) {
                centerpanel.removeAll();
                initPanel("check");
            }
            //当点击的是搜索按钮
            if (type.equals("seach")) {
                centerpanel.removeAll();
                initPanel("seach");
            }
            //当点击的是求租信息按钮
            if (type.equals("lodger")) {
                centerpanel.removeAll();
                initPanel("lodger");
            }
            //当点击的是出租信息按钮
            if (type.equals("landLoad")) {
                centerpanel.removeAll();
                initPanel("landLoad");
            }

            //当点击的是聊天按钮
            if (type.equals("chat")) {
                centerpanel.removeAll();
                initPanel("chat");
            }
            //当点击的下订单按钮
            if (type.equals("placeAnOrder")) {
                centerpanel.removeAll();
                initPanel("placeAnOrder");
            }
            //当点击的是取消订单按钮
            if (type.equals("cancellationOfOrder")) {
                centerpanel.removeAll();
                initPanel("cancellationOfOrder");
            }
            //当点击的是完成订单按钮
            if (type.equals("completeOrder")) {
                centerpanel.removeAll();
                initPanel("completeOrder");
            }
            /*//当点击的是个人积分按钮
            if (type.equals("intergration")) {
				centerpanel.removeAll();
				initPanel("intergration");
			}*/
            //当点击的是帮助按钮
            if (type.equals("help")) {
                centerpanel.removeAll();
                initPanel("help");
            }
            // 当点击的是退出按钮
          /*  if (type.equals("exit")) {
                System.exit(0);
            }*/
            if (type.equals("return")) {
                centerpanel.removeAll();
                initPanel("return");
            }
        }
    }
    public static void main(String[]args){
        new NewGame();
    }
}
