import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class BookRank extends JFrame {

    private JTextArea bookDescriptionArea;
    private JTable table;

    public BookRank() {
        setTitle("热门书籍排行榜");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        ImageIcon imgIcon = new ImageIcon("image/OIP-C.jpg");
        setIconImage(imgIcon.getImage());


        // 表格模型，定义列名
        String[] columnNames = {"排名", "书名", "作者"};
        Object[][] data = {
                {"1", "百年孤独", "加西亚·马尔克斯"},
                {"2", "追风筝的人", "卡勒德·胡赛尼"},
                {"3", "解忧杂货店", "东野圭吾"},
                {"4", "挪威的森林", "村上春树"},
                {"5", "活着", "余华"},
                {"6", "红楼梦", "曹雪芹"},
                {"7", "三体", "刘慈欣"}
                // 更多书籍...
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {
            // 重写方法来设置行高以适应图片
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                // 如果是图片列
                if (column == 0) {
                    setRowHeight(row, 40); // 设置行高为100像素
                }
                return component;
            }
        };

        // 添加表格选中行的监听器
        table.getSelectionModel().addListSelectionListener(new RowSelectionListener());

        // 设置滚动面板包含表格
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.setFont(new Font("SimSun", Font.PLAIN, 14)); // 设置表格字体和大小


        table.setGridColor(Color.GRAY);
        table.setShowGrid(true);
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 设置表格边框颜色
        table.setIntercellSpacing(new Dimension(10, 10)); // 设置单元格之间的间距
        table.setBackground(new Color(0, 51, 102)); // 浅紫色背景
        table.setForeground(new Color(255,215,0)); // 深灰色字体

// 设置表头样式
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255,215,0)); // 设置背景颜色
        header.setForeground(Color.WHITE); // 设置字体颜色
        header.setFont(new Font("Dialog", Font.BOLD, 12));

// 设置表格的透明度



        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 设置滚动面板边框
        scrollPane.getViewport().setBackground(Color.GRAY); // 设置滚动面板的背景颜色

        // 书籍简介区域
        bookDescriptionArea = new JTextArea();
        bookDescriptionArea.setEditable(false);
        bookDescriptionArea.setLineWrap(true);
        bookDescriptionArea.setWrapStyleWord(true);
        bookDescriptionArea.setFont(new Font("宋体", Font.PLAIN, 14));

// 设置前景色（文本颜色）和背景色
        bookDescriptionArea.setForeground(new Color(255, 215, 0)); // 金色文字
        bookDescriptionArea.setBackground(new Color(0, 51, 102)); // 深蓝背景

// 设置边框
        bookDescriptionArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0), 1), // 外边框
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // 内边距
        ));
        JScrollPane descriptionScrollPane = new JScrollPane(bookDescriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(200, 150));

        add(descriptionScrollPane, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 窗口居中显示
    }

    private class RowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                // 根据选中的行显示不同的书籍简介
                if (selectedRow != -1) {
                    String bookName = (String) table.getValueAt(selectedRow, 1);
                    String description = getBookDescription(bookName);
                    bookDescriptionArea.setText(description);
                }
            }
        }

        private String getBookDescription(String bookName) {
            switch (bookName) {
                case "百年孤独":
                    return "《百年孤独》这本书是加西亚·马尔克斯的杰作，被广泛认为是魔幻现实主义文学的经典之作。它讲述了布恩迪亚家族在哥伦比亚小镇马孔多历代的奇异经历，" +
                            "展现了拉丁美洲的历史和文化。家族的创始人何塞·阿尔卡蒂奥·布恩迪亚和乌尔苏拉，通过他们的孩子和孙子的奋斗、爱情、梦想和诅咒，绘制了一幅世界的微缩图。";
                case "追风筝的人":
                    return "《追风筝的人》这是一部动人心弦的小说，讲述了在阿富汗长大的两个男孩阿米尔和哈桑之间的深厚友谊。" +
                            "故事穿越了几十年的历史，从阿富汗君主制的最后日子到塔利班统治的暴政。小说深刻地探讨了背叛、救赎和忠诚的主题，描绘了人性的复杂性和寻求宽恕的必要性";
                case "解忧杂货店":
                    return "《解忧杂货店》这部小说设定在东京的一个叫作“解忧杂货店”的神秘商店，" +
                            "商店的主人为那些夜晚投递咨询信的人提供着智慧和解决问题的方法。故事围绕着几个不同的角色展开，揭示了他们的痛苦、困惑和欲望，同时也展示了如何通过关爱和理解来治愈心灵。";
                case "挪威的森林":
                    return "《挪威的森林》这本小说是村上春树的突破之作，它以1960年代的东京为背景，" +
                            "讲述了青年学生渡边和两个女孩直子与绿子之间的复杂感情。小说透过这些角色的爱情和挣扎，探讨了生命、死亡和成长的主题，以及如何在失去中找到自我";
                case "活着":
                    return "《活着》这部作品是余华的代表作，它通过农民福贵的一生，" +
                            "展示了中国社会从封建时代到文化大革命的剧变。小说描绘了福贵面对家庭悲剧和社会变迁时的坚韧和生存斗争，反映了人类在逆境中追求尊严和生命意义的不朽主题";
                case "红楼梦":
                    return "《红楼梦》作为中国文学史上最伟大的小说之一，" +
                            "这本书详细描绘了贾、王、史、薛四大家族的兴衰。小说通过贾宝玉和林黛玉的爱情悲剧以及宝玉对封建礼教束缚的反叛，展示了18世纪中国社会的面貌，充满了丰富的人物和细节描写。。";
                case "三体":
                    return "《三体》是中国科幻作家刘慈欣的杰作，首部作为“三体”三部曲的开篇。小说开始于文化大革命期间，讲述了天文学家叶文洁在政治动荡中遭受个人悲剧后，" +
                            "她通过秘密军事项目向深空发送信息并意外接触到了遥远的三体文明。三体世界因其星系内三颗恒星的不规则运动而无法预测其气候和环境，这使得该文明面临灭绝的危机，他们决定寻找新的可居住星球。";
                default:
                    return "";
            }
        }
    }

    public static void main(String[] args) {
         new BookRank().setVisible(true);
    }
}
