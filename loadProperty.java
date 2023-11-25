import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class  loadProperty {
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // 加载属性文件
            prop.load(input);

            // 获取属性值
            String databaseUrl = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password = prop.getProperty("database.password");

            System.out.println("Database URL: " + databaseUrl);
            System.out.println("User: " + user);
            System.out.println("Password: " + password);

        } catch (IOException ex) {
            ex.printStackTrace();
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
