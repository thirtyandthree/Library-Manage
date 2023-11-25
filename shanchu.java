import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class shanchu {
    public void del(String title) {
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
                String sql ="delete from books where title = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, title);
                    int affectedRows = pstmt.executeUpdate();
                    System.out.println(" " + affectedRows + " 行。");
                    if (affectedRows == 1) {
                        JOptionPane.showMessageDialog(null, "Delete Successful");
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


    }}
