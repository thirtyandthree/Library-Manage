import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Insert {

    public boolean ArrayNotNull(String[] array) {
        for (String element : array) {
            if (element == null) {
                return false; // 发现一个空元素，返回false
            }
        }
        return true; // 所有元素都不为空
    }
        private String name,address,phone,email,password;
        public void Insert1(String name,String address,String phone,String email,String password){

            Properties prop = new Properties();
            InputStream input = null;
            name=name;
            address=address;
            phone=phone;
            email=email;
            password=password;
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
                    String sql = "insert members(name,address,phone,email,password) values (?,?,?,?,?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, name);
                        pstmt.setString(2, address);
                        pstmt.setString(3, phone);
                        pstmt.setString(4, email);
                        pstmt.setString(5, password);
                        int affectedRows = pstmt.executeUpdate();
                        System.out.println("插入了 " + affectedRows + " 行。");

                        // 执行查询

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

    }
