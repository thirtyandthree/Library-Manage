import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;



public class SQlUser extends JFrame {
    private DefaultTableModel model;

    public SQlUser() {
        setTitle("用户信息");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置窗口图标（确保路径正确，否则图标不会显示）
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());

        // 创建带有背景图片的面板
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 绘制背景图片（确保路径正确，否则背景不会显示）
                ImageIcon background = new ImageIcon("image/742320.png");
                g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        backgroundPanel.setOpaque(false);
        setContentPane(backgroundPanel);

        // 初始化表格模型并将其设置为表格的模型
        model = new DefaultTableModel(new String[]{"ID", "Name", "Address", "Phone", "Email"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        table.setOpaque(false); // 设置表格透明
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        scrollPane.setOpaque(false); // 设置滚动面板透明
        scrollPane.getViewport().setOpaque(false); // 设置滚动面板视口透明
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // 设置单元格渲染器
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setOpaque(isSelected); // 使选中的单元格不透明，以提高可读性
                return this;
            }
        };
        renderer.setForeground(Color.YELLOW); // 设置文本颜色为金色

        // 应用自定义渲染器到所有列
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // 显示窗口
        setVisible(true);

        // 加载用户数据
        loadUserData();
    }

    private void loadUserData() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);

            String databaseUrl = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password1 = prop.getProperty("database.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(databaseUrl, user, password1)) {
                String sql = "SELECT * FROM members";
                try (PreparedStatement pstmt = conn.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("memberid"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("phone"),
                                rs.getString("email")
                        });
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 确保GUI更新在事件分派线程中执行


        new SQlUser();


    }
}
