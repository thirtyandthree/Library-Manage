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

public class gptWindow extends JFrame {
    public JTextField userText;
    public JTextArea resultArea;
    public String answer="";
    public JButton searchButton;
    public gptWindow() {
        setTitle("ChatGPT");
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
        searchButton = new JButton("发送");
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
        resultArea.setForeground(new Color(0,0,128)); // 设置文字颜色
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


        setVisible(true);
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            bot();
        }
    });}

public void bot(){
        String text= userText.getText();
        gpt a=new gpt();
        String contain=a.chatgpt(text);
    if (contain != null && !contain.isEmpty()) {
        resultArea.setText(contain);
    }


}








    public static void main(String[] args) {
        new gptWindow();
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
