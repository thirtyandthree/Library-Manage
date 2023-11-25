import java.sql.*;

public class Mysql {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/abb?serverTimezone=UTC"; // 或者使用您的时区

        String username = "root";
        String password = "123mysql";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");


            // 建立连接
            conn = DriverManager.getConnection(url, username, password);

            // 创建 Statement
            stmt = conn.createStatement();

            // 执行查询
            String sql = "SELECT name,age FROM beautygirls";
            rs = stmt.executeQuery(sql);

            // 处理结果集
            while (rs.next()) {
                String column1 = rs.getString("name");
                String column2 = rs.getString("age");
                System.out.println("Column1: " + column1 + ", Column2: " + column2);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // JDBC 驱动类未找到异常处理
        } catch (SQLException e) {
            e.printStackTrace();
            // SQL 异常处理
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
