import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class SqlLogin {

    private String account1,Password1;
    public void denglu(String account,String Password){

            Properties prop = new Properties();
            InputStream input = null;
            account1=account;
            Password1=Password;
            try {
                input = new FileInputStream("config.properties");

                // 加载属性文件
                prop.load(input);

                // 获取数据库配置
                String databaseUrl = prop.getProperty("database.url");
                String user = prop.getProperty("database.user");
                String password = prop.getProperty("database.password");

                // 要查询的用户名和密码
                String memberName = account1; // 替换为实际用户名
                String memberPassword = Password1; // 替换为实际密码

                // 加载 JDBC 驱动
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 创建数据库连接
                try (Connection conn = DriverManager.getConnection(databaseUrl, user, password)) {
                    String sql = "SELECT * FROM members WHERE Name = ? AND Password = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, memberName);
                        pstmt.setString(2, memberPassword);

                        // 执行查询
                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(null, "Login Successful");
                                 navLibrary a=new navLibrary();
                                 new navLibrary();
                            } else {

                                JOptionPane.showMessageDialog(null, "Invalid username or password");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public String getAccount1() {
        return account1;
    }

    public void setAccount1(String account1) {
        this.account1 = account1;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String password1) {
        Password1 = password1;
    }
}


