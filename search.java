import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class search extends JFrame {
    public JTextField userText;
    public JTextArea resultArea;
    public String retext="";
    public JButton searchButton;
    public search() {
        setTitle("Search Page");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 确保图片路径正确
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());

        // 创建背景面板并添加背景图片
        BackgroundImage backgroundImagePanel = new BackgroundImage("image/742320.png");
        setContentPane(backgroundImagePanel);
        backgroundImagePanel.setLayout(null);

        // 创建文本框
         userText = new JTextField(20);
        userText.setBounds(180, 100, 230, 40);
        userText.setForeground(new Color(255,215,0));
        userText.setBackground(new Color(0,51,102));
        userText.setFont(new Font("宋体", Font.PLAIN, 18)); // 设置字体样式和大小
        Border userTextBorder = BorderFactory.createLineBorder(Color.BLUE, 3); // 设置边框颜色和粗细
        userText.setBorder(userTextBorder);
        backgroundImagePanel.add(userText);

        // 创建按钮
        searchButton = new JButton("Search");
        searchButton.setBounds(210, 150, 150, 30);
        searchButton.setFont(new Font("宋体", Font.BOLD, 16)); // 设置字体样式和大小
        searchButton.setBackground(Color.LIGHT_GRAY); // 设置背景色
        searchButton.setForeground(new Color(255,215,0)); // 设置文字颜色
        Border buttonBorder = BorderFactory.createLineBorder(Color.YELLOW, 5); // 设置边框颜色和粗细
        searchButton.setBorder(buttonBorder);
        backgroundImagePanel.add(searchButton);
        resultArea = new JTextArea();
        resultArea.setBounds(100, 200, 400, 200);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 16));
        resultArea.setForeground(new Color(255,215,0)); // 设置文字颜色
        resultArea.setBackground(new Color(255, 182, 193));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false); // 设置为不可编辑
        Border resultAreaBorder = BorderFactory.createLineBorder(Color.YELLOW, 5);
        resultArea.setBorder(resultAreaBorder);

        // 如果需要滚动条
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(100, 200, 400, 200);
        backgroundImagePanel.add(scrollPane);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sousuo();
            }
        });
        setVisible(true);}
    public void sousuo() {
        Properties prop = new Properties();
        String retext = "";
        try {
            // 加载配置文件
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            input.close(); // 关闭 InputStream

            String databaseUrl = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password1 = prop.getProperty("database.password");

            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            try (Connection conn = DriverManager.getConnection(databaseUrl, user, password1)) {
                String text = userText.getText();

                // 第一个 SQL 命令
                String sql1 = "SELECT * FROM members WHERE Name LIKE ?";
                try (PreparedStatement pstmt1 = conn.prepareStatement(sql1)
                     ) {
                    pstmt1.setString(1, "%" + text + "%");
                    ResultSet rs1 = pstmt1.executeQuery();
                    while (rs1.next()) {
                        retext += "Name" + rs1.getString("name") + " Address" + rs1.getString("address") + " Phone" + rs1.getString("phone") +
                                " Email" + rs1.getString("email") + '\n';
                    }
                }

                // 第二个 SQL 命令
                String sql2 = "SELECT * FROM books WHERE title LIKE ?";
                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)
                     ) {
                    pstmt2.setString(1, "%" + text + "%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        retext += "Title" + rs2.getString("title") + " Author" + rs2.getString("author") +
                                " Genre" + rs2.getString("genre") + " Price" + rs2.getString("price") +
                                " Quantity" + rs2.getString("quantity") + '\n';
                    }
                }

                // 第三个 SQL 命令
                String sql3 = "SELECT * FROM users WHERE name LIKE ?";
                try (PreparedStatement pstmt3 = conn.prepareStatement(sql3)
                     ) {
                    pstmt3.setString(1, "%" + text + "%");
                    ResultSet rs3 = pstmt3.executeQuery();
                    while (rs3.next()) {
                        retext += "Name" + rs3.getString("name") + " Birthday" + rs3.getString("birthdate") +
                                " Motto" + rs3.getString("motto") + " Hometown" + rs3.getString("hometown") +
                                " Education" + rs3.getString("education") + " Workplace" + rs3.getString("workplace") + '\n';
                    }
                }

                resultArea.setText(retext);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }








    public static void main(String[] args) {
        new search();
    }

    // 自定义的背景面板类
    private class BackgroundImage extends JPanel {
        private Image backgroundImage;

        public BackgroundImage(String fileName) {
            backgroundImage = new ImageIcon(fileName).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
