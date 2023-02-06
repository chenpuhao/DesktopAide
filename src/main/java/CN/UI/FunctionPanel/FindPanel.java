package CN.UI.FunctionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class FindPanel extends JPanel {
    static JTextField pathField = new JTextField();
    static JTextField nameField = new JTextField();
    static File file = new File(pathField.getText());
    static String keyWord = nameField.getText();
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<>();
        if (folder.isFile())
            result.add(folder);
        File[] subFolders = folder.listFiles(file -> {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().toLowerCase().contains(keyword);
        });
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }
    public FindPanel(){
        this.setLayout(new BorderLayout());
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        JLabel pathLabel = new JLabel("需要查找的目录，如C:\\Users\\" + userName + "\\Desktop");
        JLabel fileLabel = new JLabel("需要查找的文件名的关键字，如DesktopAide");
        JButton check = new JButton("确定");
        check.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                file = new File(pathField.getText());
                File file3 = new File(file.toURI());
                keyWord = nameField.getText();
                List<File> files = searchFiles(file3, keyWord);
                StringBuilder filePath = new StringBuilder();
                for (File file : files) {
                    File file1 = new File(file.getAbsolutePath());
                    filePath.append("\n").append(file1);
                    try {
                        Desktop.getDesktop().open(file1);
                    } catch (IOException e1) {

                        e1.printStackTrace();
                    }
                }
                if (filePath.toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "在此目录下没有找到带此关键字的文件", "查找完成！", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "一共查找到如下文件:" + filePath, "查找完成！", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        JPanel findPanel = new JPanel();
        findPanel.setLayout(new GridLayout (5 , 1 ));
        findPanel.add(pathLabel);
        findPanel.add(pathField);
        findPanel.add(fileLabel);
        findPanel.add(nameField);
        findPanel.add(check);
        this.add(findPanel);
    }
}
