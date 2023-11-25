import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
  static class BackgroundImage extends JPanel{
      private Image backgroundImage;
      public BackgroundImage(String imagePath) {
          backgroundImage = new ImageIcon(imagePath).getImage();
      }
      protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
      }
  }
    public void Login1() {
        setTitle("Login Page");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置窗口图标
        // 确保 OIP-C.jpg 图片在项目路径中可以找到，或者提供完整路径
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());
        // 创建带有背景图片的 BackgroundImage 实例
        BackgroundImage backgroundImagePanel = new BackgroundImage("image/742320.png"); // 替换为你的图片路径
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
        JLabel titleLabel = new JLabel("Welcome to Login Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // 标题颜色
        panel.add(titleLabel, constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // 添加组件到面板
        placeComponents(panel, constraints);

        // 添加面板到窗口
        BackgroundImage panelBackground=new BackgroundImage("image/1.png");
        add(panelBackground);
        add(panel);

        // 显示窗口
        setVisible(true);
    }

    public static void placeComponents(JPanel panel, GridBagConstraints constraints) {
        // 设置字体
        Font font = (new Font("Microsoft YaHei", Font.PLAIN, 16));
        // 设置字体颜色
        Color colorWord = new Color(255, 255, 255);
        Color fieldColor = new Color(255, 255, 255);
        Color buttonColor = new Color(16, 124, 204);
        Color buttonHoverColor = new Color(3, 169, 244);

        // 创建用户名标签和文本框
        JLabel userLabel = new JLabel("User:");
        userLabel.setFont(font);
        userLabel.setForeground(colorWord);
        panel.add(userLabel, constraints);

        JTextField userText = new JTextField(20);
        userText.setFont(font);
        userText.setForeground(Color.YELLOW);
        userText.setBackground(fieldColor);
        userText.setBorder(BorderFactory.createCompoundBorder(
                userText.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(userText, constraints);

        // 创建密码标签和密码文本框
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        passwordLabel.setForeground(colorWord);
        panel.add(passwordLabel, constraints);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(font);
        passwordText.setForeground(Color.BLACK);
        passwordText.setBackground(fieldColor);
        passwordText.setBorder(BorderFactory.createCompoundBorder(
                passwordText.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(passwordText, constraints);

        // 创建登录按钮
        JButton loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.setForeground(colorWord);
        loginButton.setBackground(buttonColor);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createRaisedBevelBorder());
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(buttonColor);
            }
        });
        constraints.gridy = GridBagConstraints.RELATIVE;
        panel.add(loginButton, constraints);

        // 创建注册按钮
        JButton registerButton = new JButton("Register");
        registerButton.setFont(font);
        registerButton.setForeground(colorWord);
        registerButton.setBackground(buttonColor);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createRaisedBevelBorder());
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(buttonColor);
            }
        });
        panel.add(registerButton, constraints);
        panel.setOpaque(false);
        panel.setBackground(new Color(255, 255, 255, 128)); // 半透明白色




    // 添加按钮的事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此处处理登录逻辑
                String userName = userText.getText();
                String password = new String(passwordText.getPassword());
                if (!userName.isEmpty() && !password.isEmpty()) {
                    SqlLogin sqllogin = new SqlLogin();
                    sqllogin.denglu(userName,password);

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register z=new register();
                z.register1();
            }
        });
    }

    public static void main(String[] args) {
        // 在事件调度线程中运行应用程序
       Login d= new Login();
       d.Login1();
    }
}
