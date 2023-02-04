package CN.UI.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class HelloUI extends JFrame {
    static int num = 1;
    public HelloUI(){

        //获取屏幕大小
        Dimension getDesktopSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((getDesktopSize.width-300)/2,(getDesktopSize.height-200)/2,300,200);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //用户姓名
        JPanel panel1 = new JPanel();
        JLabel userNameLabel = new JLabel("在此处输入您的昵称,不用担心，您可以在后续进行修改");
        JTextField userNameField = new JTextField();
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(userNameLabel);
        panel1.add(userNameField);
        this.add(panel1);
        this.setResizable(false);
        this.setType(JFrame.Type.UTILITY);
        //介绍
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,1));
        JLabel introduceUser = new JLabel("欢迎用户",JLabel.CENTER);
        JLabel introduce = new JLabel("",JLabel.CENTER);
        JLabel introduceNext = new JLabel("下面的几页将为您介绍本程序的主要功能",JLabel.CENTER);
        panel2.add(introduceUser);
        panel2.add(introduce);
        panel2.add(introduceNext);
        //介绍1
        JPanel panel3 = new JPanel();
        JTextArea introduce1 = new JTextArea("         信了?这么小的窗口怎么可能装下那么多文字\n使用方法查看pdf文件，在安装目录下的Documents里面");
        panel3.setLayout(new BorderLayout());
        panel3.add(introduce1);
        introduce1.setEditable(false);
        introduce1.setLineWrap(true);
        //下一页按钮
        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(new GridLayout(1,2));
        JButton last = new JButton("上一页");
        JButton next = new JButton("下一页");
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(num == 2){
                    HelloUI.this.remove(panel2);
                    HelloUI.this.add(panel1);
                    next.setText("下一页");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num --;
                }
                else if(num ==3){
                    HelloUI.this.remove(panel3);
                    HelloUI.this.add(panel2);
                    next.setText("下一页");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num--;
                }
            }
        });

        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(num == 1){
                    introduce.setText(userNameField.getText());
                    HelloUI.this.remove(panel1);
                    HelloUI.this.add(panel2);
                    next.setText("下一页");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num ++;
                }
                else if(num == 2){
                    HelloUI.this.remove(panel2);
                    HelloUI.this.add(panel3);
                    next.setText("完成");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num ++;
                }
                else{
                    Map<String, String> map = System.getenv();
                    String userName = map.get("USERNAME");
                    File folderPath = new File("C:\\Users\\" + userName + "\\.DesktopAide\\user");
                    File filePath = new File(folderPath + "\\user.da");
                    folderPath.mkdirs();
                    try {
                        filePath.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                        fileOutputStream.write(userNameField.getText().getBytes(StandardCharsets.UTF_8));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        filePath = new File(folderPath + "\\success.da");
                        filePath.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    HelloUI.this.setVisible(false);
                }
            }
        });
        nextPanel.add(last);
        nextPanel.add(next);
        this.add(nextPanel,BorderLayout.SOUTH);
    }

}
