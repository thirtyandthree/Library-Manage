import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class navLibrary extends JFrame {

      public navLibrary(){
          setTitle("Main Page");
          setSize(600, 450);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);

          // 设置窗口图标
          // 确保 OIP-C.jpg 图片在项目路径中可以找到，或者提供完整路径
          ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
          setIconImage(imgIcon.getImage());
          // 创建带有背景图片的 BackgroundImage 实例
          Login.BackgroundImage backgroundImagePanel = new Login.BackgroundImage("image/742320.png"); // 替换为你的图片路径
          backgroundImagePanel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
          setContentPane(backgroundImagePanel);

          GridBagConstraints constraints = new GridBagConstraints();
          constraints.gridwidth = GridBagConstraints.REMAINDER;
          constraints.fill = GridBagConstraints.HORIZONTAL;
          constraints.anchor = GridBagConstraints.NORTH;
          constraints.insets = new Insets(10, 10, 10, 10);

          // 创建面板，并设置边框和布局
          JPanel panel = new JPanel();
          panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 设置边框
          panel.setLayout(new GridBagLayout()); // 使用 GridBagLayout 以获得更好的布局控制
          panel.setOpaque(false);
          panel.setBackground(new Color(255, 255, 255, 128)); // 半透明白色

          // 设置字体和颜色
          Font buttonFont = new Font("Microsoft YaHei", Font.BOLD, 14);
          Color buttonColor = new Color(0, 102, 204); // 深蓝色
          Color textColor = Color.WHITE;
          JTextField searchField = new JTextField(20);
          searchField.setText("                    欢迎来到图书系统");
          searchField.setBackground(new Color(0,51,102));
          searchField.setForeground(new Color(255,215,0));
          JButton searchButton = new JButton("搜索");

          // 创建和添加按钮
          JButton[] buttons = {new JButton("图书列表"), new JButton("添加图书"),
                  new JButton("借书管理"), new JButton("用户管理"),
                  new JButton("热门书榜"), searchButton};

          for (JButton button : buttons) {
              button.setFont(buttonFont);
              button.setForeground(textColor);
              button.setBackground(buttonColor);
              button.setBorder(BorderFactory.createRaisedBevelBorder());
              panel.add(button, constraints);
          }

          // 添加搜索框
          searchField.setFont(buttonFont);
          panel.add(searchField, constraints);
          add(panel);
          // 设置窗口可见
          setVisible(true);
          buttons[0].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new booksCollect();
              }
          });
          buttons[1].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new addBook();
              }
          });
          buttons[3].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new SQlUser();
              }
          });
          buttons[4].addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new BookRank().setVisible(true);;
              }
          });
       searchButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new search();
              }
          });
      }

    public static void main(String[] args) {
        new navLibrary();
    }
}