package cn.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PiFu extends JDialog {
    public PiFu(MainUI UI) {
        super(UI, "修改皮肤", true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setType(JFrame.Type.UTILITY);
        this.setLayout(new BorderLayout());
        this.setBounds((ds.width - 500) / 2, (ds.height - 300) / 2, 500, 300);
        File file = new File("icon/pictures");
        if (!file.exists()) {
            file.mkdir();
        }
        JLabel info = new JLabel("请选择图片:");
        this.add(info,BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane();
        JPanel jp = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        String[] list = file.list();
        for (int i = 0; i < list.length; i++) {
            ImageIcon img = new ImageIcon(file + "\\" + list[i]);
            File file2 = new File(file + "\\" + list[i]);
            if (file2.isDirectory()) {
            } else {
                JButton get = new JButton(list[i]);
                get.setIcon(img);
                get.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String name = get.getText();
                        MainUI.MainUI.remove(MainUI.BodyLabel);
                        ImageIcon i = new ImageIcon("icon/pictures/"+name);
                        MainUI.BodyLabel =new JLabel(i);
                        MainUI.MainUI.add(MainUI.BodyLabel,BorderLayout.CENTER);
                        MainUI.MainUI.revalidate();
                        MainUI.MainUI.repaint();
                        PiFu.this.setVisible(false);
                    }
                });
                bg.add(get);
                jp.setLayout(new GridLayout(list.length, 1, 1, 1));
                jsp.setViewportView(jp);
                jp.add(get);
            }
            this.add(jsp,BorderLayout.CENTER);
        }
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1,3,1,1));
        JButton mr =new JButton("修改默认图片");
        mr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "将你的图片压缩为173x220并重命名为Body.png后替换目录下的原图片", "注意", JOptionPane.INFORMATION_MESSAGE);
                File file =new File("icon");
                try {
                    Desktop.getDesktop().open(new File(file.toURI()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton ml = new JButton("打开目录");
        ml.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file =new File("icon/pictures");
                try {
                    Desktop.getDesktop().open(new File(file.toURI()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton no = new JButton("取消");
        no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PiFu.this.setVisible(false);
            }
        });
        button.add(mr);
        button.add(ml);
        button.add(no);
        this.add(button,BorderLayout.SOUTH);
    }
}

