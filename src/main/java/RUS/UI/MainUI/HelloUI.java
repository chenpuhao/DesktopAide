package RUS.UI.MainUI;

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
        JLabel userNameLabel = new JLabel("Введите свое прозвище здесь");
        JTextField userNameField = new JTextField();
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(userNameLabel);
        panel1.add(userNameField);
        this.add(panel1);
        this.setResizable(false);
        this.setType(Type.UTILITY);
        //介绍
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,1));
        JLabel introduceUser = new JLabel("Добр пожалова",JLabel.CENTER);
        JLabel introduce = new JLabel("",JLabel.CENTER);
        JLabel introduceNext = new JLabel("Следующие страницы покажут вам основные функции программы",JLabel.CENTER);
        panel2.add(introduceUser);
        panel2.add(introduce);
        panel2.add(introduceNext);
        //介绍1
        JPanel panel3 = new JPanel();
        JTextArea introduce1 = new JTextArea("         Да? Как может такое маленькое окно содержать так много текста/n методов для просмотра PDF файлов внутри документов в каталоге установки");
        panel3.setLayout(new BorderLayout());
        panel3.add(introduce1);
        introduce1.setEditable(false);
        introduce1.setLineWrap(true);
        //На следующей странице.按钮
        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(new GridLayout(1,2));
        JButton last = new JButton("На последней странице.");
        JButton next = new JButton("На следующей странице.");
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(num == 2){
                    HelloUI.this.remove(panel2);
                    HelloUI.this.add(panel1);
                    next.setText("На следующей странице.");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num --;
                }
                else if(num ==3){
                    HelloUI.this.remove(panel3);
                    HelloUI.this.add(panel2);
                    next.setText("На следующей странице.");
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
                    next.setText("На следующей странице.");
                    HelloUI.this.revalidate();
                    HelloUI.this.repaint();
                    num ++;
                }
                else if(num == 2){
                    HelloUI.this.remove(panel2);
                    HelloUI.this.add(panel3);
                    next.setText("законч");
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
