import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.net.URL;

public class booksCollect extends JFrame {
    private DefaultTableModel model;
    private String bookId,author,title,image,publisher,genre,price,quantity;
    public booksCollect() {
        setTitle("书库");
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
        setVisible(true);

        // 创建表格模型
        model = new DefaultTableModel(new Object[]{"Image", "BookID", "Author", "Publisher", "Genre", "Price", "Quantity"}, 0);
        JTable table = new JTable(model) {
            // 重写方法来设置行高以适应图片
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                // 如果是图片列
                if (column == 0) {
                    setRowHeight(row, 100); // 设置行高为100像素
                }
                return component;
            }
        };
        // 设置表格字体和颜色
        table.setFont(new Font("SimSun", Font.PLAIN, 14)); // 设置表格字体和大小

        table.setForeground(Color.BLACK);
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


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 设置滚动面板边框
        scrollPane.getViewport().setBackground(Color.GRAY); // 设置滚动面板的背景颜色

        // 用于渲染图片的单元格渲染器
        table.getColumn("Image").setCellRenderer(new DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    // 获取单元格尺寸
                    int cellWidth = table.getColumnModel().getColumn(column).getWidth();
                    int cellHeight = table.getRowHeight(row);

                    // 创建新的缩放图片，保持纵横比
                    ImageIcon icon = (ImageIcon) value;
                    Image scaledImage = icon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    // 设置标签并返回
                    setIcon(scaledIcon);
                    setText(""); // 不显示文本
                    setHorizontalAlignment(CENTER);
                    setVerticalAlignment(CENTER);
                } else {
                    // 如果值不是ImageIcon类型，使用默认渲染器
                    super.setValue(value);
                }
                return this;
            }
        });
        SelectLibrary a=new SelectLibrary();

        // 添加示例数据
        addBook("image/1.png", "0", "F. Scott Fitzgerald", "Scribner", "Classic", "$10", "5");
        for (int i = 0; i < a.bookId.size(); i++) {
            addBook(a.image.get(i), a.bookId.get(i), a.author.get(i), a.publisher.get(i), a.genre.get(i), a.price.get(i), a.quantity.get(i));
        }
        // 显示窗口
        setVisible(true);
    }

    private void addBook(String imagePath, String id, String author, String publisher, String genre, String price, String quantity) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        model.addRow(new Object[]{imageIcon, id, author, publisher, genre, price, quantity});
    }

    public static void main(String[] args) {
        new booksCollect();
    }
}
