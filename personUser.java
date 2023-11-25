import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class personUser extends JFrame {

    private BufferedImage backgroundImage;




    public personUser() {
        setTitle("用户信息");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLayout(new BorderLayout());

        // 左侧头像
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, 150));
        leftPanel.setBackground(Color.WHITE); // 设置背景色



        // 使用自定义标签显示头像
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());
        // 创建带有背景图片的 BackgroundImage 实例
        Login.BackgroundImage backgroundImagePanel = new Login.BackgroundImage("image/742320.png"); // 替换为你的图片路径

       setContentPane(backgroundImagePanel);



        // 右侧信息
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(9, 2, 10, 10)); // 9行，2列，间距
        rightPanel.setBackground(new Color(70,130,180)); // 设置背景色

        // 添加标签和信息
        addToPanel(rightPanel, "ID: ", "1");
        addToPanel(rightPanel, "Name: ", "张三");
        addToPanel(rightPanel, "Phone Number: ", "001");
        addToPanel(rightPanel, "Email: ", "zhangsan@qq.com");
        addToPanel(rightPanel, "QQ: ", "123456789");
        addToPanel(rightPanel, "Birthdate: ", "1992-02-20");
        addToPanel(rightPanel, "Motto: ", "Keep Learning");
        addToPanel(rightPanel, "Hometown: ", "北京市朝阳区");
        addToPanel(rightPanel, "Education: ", "Bachelor's Degree");
        addToPanel(rightPanel, "Workplace: ", "Some Company Ltd.");
//        rightPanel.setVisible(false);
        add(rightPanel, BorderLayout.CENTER);
        JPanel panel1=new JPanel();
       JCalendar a=new JCalendar();
       panel1.add(a);
       add(panel1);
        // 显示窗口
//        Date date = a.getDate();
//        // 在这里处理日期。例如，打印到控制台
//        System.out.println("选中的日期是: " + date);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addToPanel(JPanel panel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        JLabel value = new JLabel(valueText);

        // 设置字体样式
        label.setFont(new Font("宋体", Font.BOLD, 14));
        value.setFont(new Font("宋体", Font.PLAIN, 14));

        // 设置前景色
        // 设置前景色
        label.setForeground(new Color(50, 50, 50)); // 深灰色
        value.setForeground(new Color(80, 80, 80)); // 浅灰色

// 设置背景色和透明度
        label.setOpaque(true);
        value.setOpaque(true);
        label.setBackground(new Color(70, 130, 180)); // 偏蓝紫色，海洋风格的背景
        value.setBackground(new Color(100, 149, 237)); // 更浅的偏蓝紫色，海洋风格的背景


        // 设置内边距
//        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        value.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(label);
        panel.add(value);
    }


    public static void main(String[] args) {
        new personUser();
    }

    // 自定义的JLabel，用于显示裁剪和缩放后的图像

}
