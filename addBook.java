import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class addBook extends JFrame{
    private JFrame frame;
    public String bookId,author,publisher,genre,price,quantity,title,image_real;
    public addBook(){
        frame=new JFrame();
        frame.setTitle("管理书籍");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 设置窗口图标
        // 确保 OIP-C.jpg 图片在项目路径中可以找到，或者提供完整路径
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        frame.setIconImage(imgIcon.getImage());
        // 创建带有背景图片的 BackgroundImage 实例
        Login.BackgroundImage backgroundImagePanel = new Login.BackgroundImage("image/742320.png"); // 替换为你的图片路径
        backgroundImagePanel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
        frame.setContentPane(backgroundImagePanel);

        String[] labels = {"BookID", "Author", "Publisher",  "Price", "Quantity", "Title", "Image"};
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        for (String label : labels) {
            add(new JLabel(label), gbc);
            gbc.gridx++;
            add(new JTextField(10), gbc);
            gbc.gridx = 0;
            gbc.gridy++;
        }

        String[] bookTypes = {"古典文学", "文学", "武侠", "现代文学", "散文", "历史", "科幻", "哲学", "健康/健身"};
        JComboBox<String> bookTypeComboBox = new JComboBox<>(bookTypes);
        bookTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 14)); // 设置字体样式
        bookTypeComboBox.setPreferredSize(new Dimension(200, 30)); // 设置大小
        bookTypeComboBox.setBackground(new Color(0, 51, 102)); // 浅紫色背景
        bookTypeComboBox.setForeground(new Color(255,215,0)); // 深灰色字体
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("宋体", Font.BOLD, 14));
        timeLabel.setForeground(new Color(255, 215, 0));


        // 使用 Timer 更新时间标签
        Timer timer = new Timer(1000, new ActionListener() {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(df.format(new Date()));
            }
        });
        timer.start();
        frame.add(timeLabel, gbc);
        Color[] textFieldColors = {
                new Color(137, 207, 240), // 浅蓝色
                new Color(112, 146, 190), // 间蓝色
                new Color(95, 158, 160),  // 青色
                new Color(70, 130, 180),  // 钢青色
        };

        // 添加标签和文本框
        gbc.gridy=0;

        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setForeground(new Color(255, 255, 255)); // 设置标签的文字为白色
            frame.add(label, gbc);
            gbc.gridx++;
            textFields[i] = new JTextField(15);
            textFields[i].setBorder(BorderFactory.createLineBorder(textFieldColors[i % textFieldColors.length], 2)); // 使用颜色数组中的颜色为边框
            frame.add(textFields[i], gbc);
            gbc.gridx = 0;

            gbc.gridy++;
        }

        gbc.gridy++;
        // 添加提交按钮
        frame.add(bookTypeComboBox,gbc);
        gbc.gridy++;
        JButton submitButton = new JButton("Add Book");
        submitButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        submitButton.setBackground(new Color(0, 105, 148)); // 设置按钮背景为深海蓝
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JButton deleteButton = new JButton("Delete Book");
        JTextField delText=new JTextField("填入你想要删除的书的名字");


        deleteButton.setForeground(Color.WHITE); // 设置按钮文字颜色为白色
        deleteButton.setOpaque(true);
        deleteButton.setBackground(new Color(0, 105, 148));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        frame.add(submitButton, gbc);
        gbc.gridy++;
        frame.add(deleteButton,gbc);
        gbc.gridy++;
        frame.add(delText,gbc);


        // 显示窗口
        frame.setVisible(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String delTitle=delText.getText();
                shanchu a=new shanchu();
                a.del(delTitle);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 bookId = textFields[0].getText();
                 author = textFields[1].getText();
                 publisher = textFields[2].getText();

                 price = textFields[3].getText();
                 quantity = textFields[4].getText();
                 title = textFields[5].getText();
                image_real = textFields[6].getText();
                String genre = (String) bookTypeComboBox.getSelectedItem();
                Properties prop = new Properties();
                InputStream input = null;
                try {

                    input = new FileInputStream("config.properties");

                    // 加载属性文件
                    prop.load(input);

                    // 获取数据库配置
                    String databaseUrl = prop.getProperty("database.url");
                    String user = prop.getProperty("database.user");
                    String password1 = prop.getProperty("database.password");



                    // 加载 JDBC 驱动
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 创建数据库连接
                    try (Connection conn = DriverManager.getConnection(databaseUrl, user, password1)) {
                        String sql = "insert into books (bookid,author,publisher,genre,price,quantity,title,image) values (?,?,?,?,?,?,?,?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, bookId);
                            pstmt.setString(2, author);
                            pstmt.setString(3, publisher);
                            pstmt.setString(4, genre);
                            pstmt.setString(5, price);
                            pstmt.setString(6, quantity);
                            pstmt.setString(7, title);
                            pstmt.setString(8, image_real);
                            int affectedRows = pstmt.executeUpdate();
                            System.out.println("插入了 " + affectedRows + " 行。");
                            if(affectedRows==1){
                                JOptionPane.showMessageDialog(null, "Login Successful");
                            }

                            // 执行查询

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    System.out.println("MySQL JDBC Driver not found.");
                    e2.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    public static void main(String[] args){
        addBook a=new addBook();

    }
}
