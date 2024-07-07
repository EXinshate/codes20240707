import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Main extends JFrame {
    private JPanel contentPane;
    private final JPanel panel = new JPanel();

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (Throwable e){
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public Main(){
        setTitle("用动态数组保存学生姓名");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 269);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout( new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        list = new JList<>();
        scrollPane.setViewportView(list);

        JPanel panel_1 = new JPanel();
        scrollPane.setColumnHeaderView(panel_1);

        JLabel label = new JLabel("学生姓名: ");
        panel_1.add(label);

        textField = new JTextField();
        panel_1.add(textField);
        textField.setColumns(10);

        panel.setPreferredSize(new Dimension(100, 10));
        contentPane.add(panel,BorderLayout.EAST);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton button =new JButton("添加学生");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button);

        JButton button_1 = new JButton("删除学生");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        panel.add(button_1);
    }

    private ArrayList<String> arrayList = new ArrayList<String>();
    private JTextField textField;
    private JList list;

    protected void do_button_actionPerformed(ActionEvent e){
        textField.requestFocusInWindow();
        textField.selectAll();;
        String text = textField.getText();
        if (text.isEmpty())
            return;
        arrayList.add(text);
        replaceModer();
    }
    protected void do_button_1_actionPerformed(ActionEvent e){
        Object value = list.getSelectedValue();
        arrayList.remove(value);
        replaceModer();
    }
    private void replaceModer(){
        list.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return arrayList.size();
            }
            public Object getElementAt(int index) {
                return arrayList.get(index);
            }
        });
    }
}