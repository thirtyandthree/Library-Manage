import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register extends JFrame {

    public void register2(){
        setTitle("Login Page");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置窗口图标
        // 确保 OIP-C.jpg 图片在项目路径中可以找到，或者提供完整路径
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());
        // 创建带有背景图片的 BackgroundImage 实例
        Login.BackgroundImage backgroundImagePanel = new Login.BackgroundImage("image/742320.png"); // 替换为你的图片路径
        backgroundImagePanel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
        setContentPane(backgroundImagePanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 5, 0);

        // 创建面板，并设置边框和布局
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 设置边框
        panel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
        add(panel);
        setVisible(true);
    }
    public void register1() {
        setTitle("注册");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置窗口图标
        // 确保 OIP-C.jpg 图片在项目路径中可以找到，或者提供完整路径
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());
        // 创建带有背景图片的 BackgroundImage 实例
        Login.BackgroundImage backgroundImagePanel = new Login.BackgroundImage("image/742320.png"); // 替换为你的图片路径
        backgroundImagePanel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
        setContentPane(backgroundImagePanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 5, 0);

        // 创建面板，并设置边框和布局
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 设置边框
        panel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制



        // 创建标题标签，并应用字体
        JButton openwindow = new JButton("注册");

// 设置按钮字体大小和样式
        openwindow.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        openwindow.setPreferredSize(new Dimension(150, 50));

// 设置前景色（文本颜色）和背景色
        openwindow.setForeground(Color.WHITE); // 文字颜色
        openwindow.setBackground(new Color(0, 102, 204)); // 背景色

// 设置按钮边框
        openwindow.setBorder(BorderFactory.createRaisedBevelBorder());

// 改变光标样式
        openwindow.setCursor(new Cursor(Cursor.HAND_CURSOR));

// 添加鼠标悬停效果
        openwindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openwindow.setBackground(new Color(3, 169, 244)); // 鼠标悬停时的背景色
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                openwindow.setBackground(new Color(0, 102, 204)); // 鼠标离开时的背景色
            }
        });

        panel.add(openwindow, constraints);
        panel.setOpaque(false);
        panel.setBackground(new Color(255, 255, 255, 128)); // 半透明白色
        //插入五个关于注册的标签和文本框
        Font labelFont = new Font("Microsoft YaHei", Font.BOLD, 14);
        Font textFieldFont = new Font("Microsoft YaHei", Font.PLAIN, 14);
        Color textColor = Color.BLACK;

// 插入五个关于注册的标签和文本框
// 通过循环来添加组件可以使代码更简洁
        JLabel[] labels = new JLabel[] {
                new JLabel("Name"), new JLabel("Address"),
                new JLabel("Phone"), new JLabel("Email"),
                new JLabel("Password")
        };
        JTextField[] textFields = new JTextField[] {
                new JTextField(30), new JTextField(30),
                new JTextField(30), new JTextField(30),
                new JTextField(30)
        };
        String[] registerInfo=new String[5];
        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(labelFont);
            labels[i].setForeground(textColor);
            textFields[i].setFont(textFieldFont);
            textFields[i].setForeground(textColor);
            textFields[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // 调整布局
            constraints.gridx = 0;
            constraints.gridy = i * 2;
            panel.add(labels[i], constraints);

            constraints.gridy = i * 2 + 1;
            panel.add(textFields[i], constraints);

        }

// 添加注册按钮
        constraints.gridy = labels.length * 2;
        panel.add(openwindow, constraints);

// 添加面板到窗口
        add(panel);

// 显示窗口
        setVisible(true);

        // 添加面板到窗口
        Login.BackgroundImage panelBackground=new Login.BackgroundImage("image/1.png");
        add(panelBackground);
        add(panel);

        // 显示窗口
        setVisible(true);

        openwindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerInfo[0] = textFields[0].getText();
                registerInfo[1] = textFields[1].getText();
                registerInfo[2] = textFields[2].getText();
                registerInfo[3] = textFields[3].getText();
                registerInfo[4] = textFields[4].getText();
                Insert Finsert = new Insert();
                if (Finsert.ArrayNotNull(registerInfo)) {
                    Finsert.Insert1(registerInfo[0], registerInfo[1], registerInfo[2], registerInfo[3], registerInfo[4]);
                    JOptionPane.showMessageDialog(null, "Login Successful");
                     Login a=new Login();
                    a.Login1();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        });


    }


}
