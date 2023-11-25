import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SelectLibrary {
    public List<String> bookId = new ArrayList<>();
    public List<String> author = new ArrayList<>();
    public List<String> publisher = new ArrayList<>();
    public List<String> genre = new ArrayList<>();
    public List<String> price = new ArrayList<>();
    public List<String> quantity = new ArrayList<>();
    public List<String> title = new ArrayList<>();
    public List<String> image = new ArrayList<>();

   public SelectLibrary(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // 加载属性文件
            prop.load(input);

            // 获取数据库配置
            String databaseUrl = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password = prop.getProperty("database.password");

            // 要查询的用户名和密码


            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 创建数据库连接
            try (Connection conn = DriverManager.getConnection(databaseUrl, user, password)) {
                String sql = "SELECT * FROM books";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {


                    // 执行查询
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while(rs.next()) {
                            bookId.add(rs.getString("BookID"));
                            author.add(rs.getString("Author"));
                            publisher.add(rs.getString("Publisher"));
                            genre.add(rs.getString("Genre"));
                            price.add(rs.getString("Price"));
                            quantity.add(rs.getString("Quantity"));
                            title.add(rs.getString("Title"));
                            image.add(rs.getString("image"));
//                            JOptionPane.showMessageDialog(null, "Login Successful");

                        }
//                        for (int i = 0; i < bookId.size(); i++) {
//                            System.out.println("BookID: " + bookId.get(i) + ", Author: " + author.get(i) +
//                                    ", Publisher: " + publisher.get(i) + ", Genre: " + genre.get(i) +
//                                    ", Price: " + price.get(i) + ", Quantity: " + quantity.get(i) +
//                                    ", Title: " + title.get(i) + ", Image: " + image.get(i));
//                        }


                        rs.close();
                        pstmt.close();
                        conn.close();
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
    public static void main(String[] args){

    }
}