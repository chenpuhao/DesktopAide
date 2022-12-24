package cn.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class YHXY extends JDialog {
    public String readFileToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in=null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
            return new String(filecontent, encoding);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }
    public  YHXY(MainUI UI){
        super(UI,"用户协议",true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screen.width-600)/2,(screen.height-500)/2,600,500);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        JTextArea jt = new JTextArea();
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(jt);
        jt.setEditable(false);
        String s =readFileToString("data/MainUI/用户协议.txt");
        jt.setText(s);
        this.add(jsp,BorderLayout.CENTER);
    }
}
