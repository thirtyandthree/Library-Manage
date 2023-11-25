import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class borrowBook extends JFrame {
    JTextField memberIDField, bookIDField, borrowDateField, dueDateField, returnDateField;
    JButton submitButton;

    public borrowBook() {
        setTitle("借阅书籍");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());

        BackgroundImage backgroundImagePanel = new BackgroundImage("image/742320.png");
        setContentPane(backgroundImagePanel);
        backgroundImagePanel.setLayout(null);

        // 初始化组件并设置边界
        memberIDField = new JTextField();
        memberIDField.setBounds(150, 20, 200, 30); // x, y, width, height

        bookIDField = new JTextField();
        bookIDField.setBounds(150, 60, 200, 30);

        borrowDateField = new JTextField();
        borrowDateField.setBounds(150, 100, 200, 30);

        dueDateField = new JTextField();
        dueDateField.setBounds(150, 140, 200, 30);

        returnDateField = new JTextField();
        returnDateField.setBounds(150, 180, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 220, 100, 30);
        JLabel memberIDLabel = new JLabel("Member ID:");
        memberIDLabel.setForeground(new Color(255, 215, 0)); // 设置为金色
        memberIDLabel.setBounds(20, 20, 100, 30);
        backgroundImagePanel.add(memberIDLabel);
        backgroundImagePanel.add(memberIDField);

        JLabel bookIDLabel = new JLabel("Book ID:");
        bookIDLabel.setForeground(new Color(255, 215, 0)); // 设置为金色
        bookIDLabel.setBounds(20, 60, 100, 30);
        backgroundImagePanel.add(bookIDLabel);
        backgroundImagePanel.add(bookIDField);

        JLabel borrowDateLabel = new JLabel("Borrow Date:");
        borrowDateLabel.setForeground(new Color(255, 215, 0)); // 设置为金色
        borrowDateLabel.setBounds(20, 100, 120, 30);
        backgroundImagePanel.add(borrowDateLabel);
        backgroundImagePanel.add(borrowDateField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setForeground(new Color(255, 215, 0)); // 设置为金色
        dueDateLabel.setBounds(20, 140, 100, 30);
        backgroundImagePanel.add(dueDateLabel);
        backgroundImagePanel.add(dueDateField);

        JLabel returnDateLabel = new JLabel("Return Date:");
        returnDateLabel.setForeground(new Color(255, 215, 0)); // 设置为金色
        returnDateLabel.setBounds(20, 180, 100, 30);
        backgroundImagePanel.add(returnDateLabel);
        backgroundImagePanel.add(returnDateField);
        backgroundImagePanel.add(submitButton);

        setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrow();
            }
        });
    }
    public void borrow(){
        Properties prop = new Properties();

        try {
            // 加载配置文件
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            input.close(); // 关闭 InputStream

            String databaseUrl = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password1 = prop.getProperty("database.password");
            String memberId=memberIDField.getText();
            String bookId=bookIDField.getText();
            String date1=borrowDateField.getText();
            String date2=dueDateField.getText();
            String date3=returnDateField.getText();

            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            try (Connection conn = DriverManager.getConnection(databaseUrl, user, password1)) {


                // 第一个 SQL 命令
                String sql = "INSERT INTO borrowings (MemberID, BookID, BorrowDate, DueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)
                ) {
                    java.sql.Date sqlDate1 = convertStringToDate(date1);
                    java.sql.Date sqlDate2 = convertStringToDate(date2);
                    java.sql.Date sqlDate3 = convertStringToDate(date3);
                    pstmt.setString(1,memberId );
                    pstmt.setString(2,bookId );
                    pstmt.setDate(3,sqlDate1 );
                    pstmt.setDate(4,sqlDate2 );
                    pstmt.setDate(5,sqlDate3 );

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "插入成功");
                    }

                }

                // 第二个 SQL 命令


                // 第三个 SQL 命令

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static java.sql.Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateString);
        return new java.sql.Date(date.getTime());
    }

    public static void main(String[] args) {
        new borrowBook();
    }

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
